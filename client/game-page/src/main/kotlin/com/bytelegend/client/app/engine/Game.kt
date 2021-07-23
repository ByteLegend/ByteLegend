package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.Character
import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.GameContainerSizeAware
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.GameSceneContainer
import com.bytelegend.app.client.api.Logger
import com.bytelegend.app.client.api.ResourceLoader
import com.bytelegend.app.client.api.Timestamp
import com.bytelegend.app.client.api.WindowBasedEventBus
import com.bytelegend.app.shared.GameInitData
import com.bytelegend.app.shared.GameMapDefinition
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.entities.Player
import com.bytelegend.app.shared.enums.ServerLocation
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.app.shared.i18n.render
import com.bytelegend.app.shared.protocol.ITEMS_STATES_UPDATE_EVENT
import com.bytelegend.app.shared.protocol.ItemsStatesUpdateEventData
import com.bytelegend.client.utils.JSObjectBackedMap
import com.bytelegend.client.utils.jsObjectBackedSetOf
import com.bytelegend.client.app.obj.PlayerSprite
import com.bytelegend.client.app.ui.DefaultBannerController
import com.bytelegend.client.app.ui.DefaultModalController
import com.bytelegend.client.app.ui.DefaultToastController
import com.bytelegend.client.app.ui.ModalControllerInternal
import com.bytelegend.client.app.web.WebSocketClient
import kotlinx.browser.localStorage
import kotlinx.browser.window
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bind
import org.kodein.di.eagerSingleton
import org.kodein.di.instance
import org.kodein.di.singleton

const val GAME_CLOCK_1HZ = 1 // Hertz
const val GAME_CLOCK_10HZ = 10 // Hertz
const val GAME_CLOCK_50HZ = 50 // Hertz

fun init(gameInitData: GameInitData): Game {
    val di = DI {
        bind<ResourceLoader>() with singleton { DefaultResourceLoader(di) }
        bind<EventBus>() with instance(WindowBasedEventBus)
        bind<GameSceneContainer>() with singleton { DefaultGameSceneContainer(di, PixelSize(window.innerWidth, window.innerHeight)) }
        bind<String>(tag = "RRBD") with instance(gameInitData.rrbd)
        bind<ServerLocation>() with instance(gameInitData.serverLocation)
        bind<Locale>() with instance(determineLocale(gameInitData))
        bind<MutableMap<String, String>>(tag = "i18nTextContainer") with instance(JSObjectBackedMap())
        bind<Player>() with instance(gameInitData.player)
        bind<GameRuntime>() with eagerSingleton { Game(di, gameInitData) }
        bind<GameControl>() with singleton { GameControl(di) }
        bind<WebSocketClient>() with singleton { WebSocketClient(di) }
    }
    val runtime by di.instance<GameRuntime>()
    return runtime as Game
}

private fun determineLocale(gameInitData: GameInitData): Locale {
    return try {
        Locale.of(localStorage.getItem("locale")!!)
    } catch (e: Exception) {
        Locale.of(gameInitData.player.locale!!)
    }
}

val logger: Logger = BrowserConsoleLogger

class Game(
    override val di: DI,
    gameInitData: GameInitData
) : DIAware, GameRuntime, GameContainerSizeAware {
    override val RRBD: String by di.instance(tag = "RRBD")
    override val locale: Locale by di.instance()
    override val eventBus: EventBus by di.instance()
    override val sceneContainer: GameSceneContainer by di.instance()
    override val elapsedTimeSinceStart: Long
        get() = startTime.elapsedTimeMs()
    override var gameContainerSize: PixelSize
        get() = sceneContainer.gameContainerSize
        set(value) {
            sceneContainer.gameContainerSize = value
        }
    override val activeScene: GameScene
        get() = sceneContainer.activeScene!!
    val webSocketClient: WebSocketClient by di.instance()
    var _hero: PlayerSprite? = null
    override val hero: Character?
        get() = _hero
    override val heroPlayer: Player by di.instance()

    val mapHierarchy: List<GameMapDefinition> = gameInitData.maps
    val idToMapDefinition: Map<String, GameMapDefinition> by lazy {
        mapHierarchy.toMap()
    }

    override val modalController: ModalControllerInternal by lazy {
        DefaultModalController(di)
    }

    val serverLocation: ServerLocation by di.instance()
    val startTime: Timestamp = Timestamp.now()
    var lastAnimationFrameTime: Timestamp = startTime

    val i18nTextContainer: MutableMap<String, String> by di.instance(tag = "i18nTextContainer")
    val resourceLoader: ResourceLoader by di.instance()

    val gameControl: GameControl by di.instance()
    val mainMapCanvasRenderer: MainMapCanvasRenderer = MainMapCanvasRenderer(this)
    override val toastController = DefaultToastController(eventBus)
    override val bannerController = DefaultBannerController(eventBus)

    private val onItemsStatesUpdateEventListener: EventListener<ItemsStatesUpdateEventData> = this::onItemsStatesUpdateEvent

    fun start() {
        gameControl.start()
        animate()
        setClock(GAME_CLOCK_1HZ, GAME_CLOCK_1HZ_EVENT)
        setClock(GAME_CLOCK_10HZ, GAME_CLOCK_10HZ_EVENT)
        setClock(GAME_CLOCK_50HZ, GAME_CLOCK_50HZ_EVENT)
        eventBus.on(ITEMS_STATES_UPDATE_EVENT, onItemsStatesUpdateEventListener)
    }

    private fun setClock(hz: Int, eventName: String) {
        window.setInterval(
            {
                eventBus.emit(eventName, null)
            },
            1000 / hz
        )
    }

    private fun animate() {
        sceneContainer.activeScene?.canvasState?.unsafeCast<DefaultGameCanvasState>()?.onAnimate()
        eventBus.emit(GAME_ANIMATION_EVENT, lastAnimationFrameTime)
        lastAnimationFrameTime = Timestamp.now()
        window.requestAnimationFrame { animate() }
    }

    override fun i(textId: String, vararg args: String): String = i18nTextContainer.getValue(textId).render(*args)

    fun resolve(path: String) = "${RRBD}$path"

    private fun onItemsStatesUpdateEvent(itemsStatesUpdateEvent: ItemsStatesUpdateEventData) {
        if (!itemsStatesUpdateEvent.onFinishSpec.items.isEmpty()) {
            val set = jsObjectBackedSetOf().apply { addAll(heroPlayer.items) }
            itemsStatesUpdateEvent.onFinishSpec.items.add.forEach {
                set.add(it)
            }
            itemsStatesUpdateEvent.onFinishSpec.items.remove.forEach {
                set.remove(it)
            }
            heroPlayer.items.clear()
            heroPlayer.items.addAll(set)
            activeScene.unsafeCast<DefaultGameScene>().playerChallenges.onItemsUpdate(itemsStatesUpdateEvent)
        }
        if (!itemsStatesUpdateEvent.onFinishSpec.states.isEmpty()) {
            itemsStatesUpdateEvent.onFinishSpec.states.put.forEach {
                heroPlayer.states[it.key] = it.value
            }
            itemsStatesUpdateEvent.onFinishSpec.states.remove.forEach {
                heroPlayer.states.remove(it)
            }
        }
        eventBus.emit(GAME_UI_UPDATE_EVENT, null)
    }
}

private fun List<GameMapDefinition>.toMap(): Map<String, GameMapDefinition> {
    val ret = JSObjectBackedMap<GameMapDefinition>()
    forEach { it.putIntMap(ret) }
    return ret
}

private fun GameMapDefinition.putIntMap(map: MutableMap<String, GameMapDefinition>) {
    map[id] = this
    children.forEach {
        it.putIntMap(map)
    }
}
