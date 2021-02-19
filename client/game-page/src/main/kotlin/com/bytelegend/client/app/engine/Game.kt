package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.Character
import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.GAME_MAP_HIERARCHY_ID
import com.bytelegend.app.client.api.GameContainerSizeAware
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.GameSceneContainer
import com.bytelegend.app.client.api.JSObjectBackedMap
import com.bytelegend.app.client.api.ResourceLoader
import com.bytelegend.app.client.api.Timestamp
import com.bytelegend.app.client.api.WindowBasedEventBus
import com.bytelegend.app.shared.GameMapDefinition
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.Player
import com.bytelegend.app.shared.ServerLocation
import com.bytelegend.app.shared.ServerSideData
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.client.app.obj.PlayerCharacter
import com.bytelegend.client.app.ui.DefaultModalController
import com.bytelegend.client.app.ui.ModalControllerInternal
import kotlinx.browser.localStorage
import kotlinx.browser.window
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bind
import org.kodein.di.eagerSingleton
import org.kodein.di.instance
import org.kodein.di.singleton

const val GAME_CLOCK_10HZ = 10 // Hertz
const val GAME_CLOCK_50HZ = 50 // Hertz

fun init(serverSideData: ServerSideData): Game {
    val di = DI {
        bind<ResourceLoader>() with singleton { DefaultResourceLoader(di) }
        bind<EventBus>() with instance(WindowBasedEventBus)
        bind<GameSceneContainer>() with singleton { DefaultGameSceneContainer(di, PixelSize(window.innerWidth, window.innerHeight)) }
        bind<String>(tag = "RRBD") with instance(serverSideData.RRBD)
        bind<ServerLocation>() with instance(serverSideData.serverLocation)
        bind<Locale>() with instance(determineLocale(serverSideData))
        bind<MutableMap<String, String>>(tag = "i18nTextContainer") with instance(JSObjectBackedMap())
        bind<Player>() with instance(serverSideData.player)
        bind<GameRuntime>() with eagerSingleton { Game(di) }
        bind<GameControl>() with singleton { GameControl(di) }
    }
    val runtime by di.instance<GameRuntime>()
    return runtime as Game
}

private fun determineLocale(serverSideData: ServerSideData): Locale {
    return try {
        Locale.valueOf(localStorage.getItem("locale")!!)
    } catch (e: Exception) {
        serverSideData.locale
    }
}

class Game(
    override val di: DI
) : DIAware, GameRuntime, GameContainerSizeAware {
    override val RRBD: String by di.instance(tag = "RRBD")
    override val locale: Locale by di.instance()
    override val eventBus: EventBus by di.instance()
    override val sceneContainer: GameSceneContainer by di.instance()
    override val currentTimeMillis: Long
        get() = Timestamp.now() - startTime
    override var gameContainerSize: PixelSize
        get() = sceneContainer.gameContainerSize
        set(value) {
            sceneContainer.gameContainerSize = value
        }
    override val activeScene: GameScene
        get() = sceneContainer.activeScene!!
    var _hero: PlayerCharacter? = null
    override val hero: Character?
        get() = _hero

    val mapHierarchy: List<GameMapDefinition> by lazy {
        resourceLoader.getLoadedResource(GAME_MAP_HIERARCHY_ID)
    }

    override val modalController: ModalControllerInternal by lazy {
        DefaultModalController(di)
    }

    val player: Player by di.instance()
    val serverLocation: ServerLocation by di.instance()
    val startTime: Timestamp = Timestamp.now()
    var lastAnimationFrameTime: Timestamp = startTime

    val i18nTextContainer: MutableMap<String, String> by di.instance(tag = "i18nTextContainer")
    val resourceLoader: ResourceLoader by di.instance()

    val gameControl: GameControl by di.instance()

    fun start() {
        gameControl.start()
        animate()
        window.setInterval(
            {
                eventBus.emit(GAME_CLOCK_10HZ_EVENT, null)
            },
            1000 / GAME_CLOCK_10HZ
        )
        window.setInterval(
            {
                eventBus.emit(GAME_CLOCK_50HZ_EVENT, null)
            },
            1000 / GAME_CLOCK_50HZ
        )
    }

    private fun animate() {
        sceneContainer.activeScene?.canvasState?.onAnimate(lastAnimationFrameTime)
        eventBus.emit(GAME_ANIMATION_EVENT, lastAnimationFrameTime)
        lastAnimationFrameTime = Timestamp.now()
        window.requestAnimationFrame { animate() }
    }

    override fun i(textId: String, vararg args: String): String = i18nTextContainer.getValue(textId).let { template ->
        var ret = template
        args.withIndex().forEach {
            ret = ret.replace("{${it.index}}", it.value)
        }
        return ret
    }

    fun resolve(path: String) = "${RRBD}$path"
}
