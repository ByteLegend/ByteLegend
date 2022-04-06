/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.Character
import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.GameContainerSizeAware
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.GameSceneContainer
import com.bytelegend.app.client.api.Logger
import com.bytelegend.app.client.api.ResourceLoader
import com.bytelegend.app.client.api.Timestamp
import com.bytelegend.app.client.api.WindowBasedEventBus
import com.bytelegend.app.client.utils.JSObjectBackedMap
import com.bytelegend.app.shared.GameInitData
import com.bytelegend.app.shared.GameMapDefinition
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.entities.Player
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.app.shared.i18n.render
import com.bytelegend.client.app.obj.character.CharacterSprite
import com.bytelegend.client.app.ui.DefaultBannerController
import com.bytelegend.client.app.ui.DefaultModalController
import com.bytelegend.client.app.ui.DefaultToastController
import com.bytelegend.client.app.ui.ModalControllerInternal
import com.bytelegend.client.app.web.WebSocketClient
import kotlinx.js.jso
import kotlinx.browser.localStorage
import kotlinx.browser.window
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bind
import org.kodein.di.eagerSingleton
import org.kodein.di.instance
import org.kodein.di.singleton
import org.w3c.fetch.RequestInit

private const val GAME_CLOCK_60S = 60000
private const val GAME_CLOCK_1S = 1000
private const val GAME_CLOCK_100MS = 100
private const val GAME_CLOCK_20MS = 20

fun init(gameInitData: GameInitData): Game {
    val di = DI {
        bind<ResourceLoader>() with singleton { DefaultResourceLoader(di) }
        bind<EventBus>() with instance(WindowBasedEventBus)
        bind<GameSceneContainer>() with singleton { DefaultGameSceneContainer(di, PixelSize(window.innerWidth, window.innerHeight)) }
        bind<String>(tag = "RRBD") with instance(gameInitData.rrbd)
        bind<Locale>() with instance(determineLocale(gameInitData))
        bind<MutableMap<String, String>>(tag = "i18nTextContainer") with instance(JSObjectBackedMap())
        bind<Player>() with instance(gameInitData.player)
        bind<GameRuntime>() with eagerSingleton { Game(di, gameInitData) }
        bind<GameControl>() with singleton { GameControl(di) }
        bind<WebSocketClient>() with singleton { WebSocketClient(di) }
        bind<ItemAchievementManager>() with singleton { DefaultItemAchievementManager(di) }
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
    val gameInitData: GameInitData
) : DIAware, GameRuntime, GameContainerSizeAware {
    override val RRBD: String by di.instance(tag = "RRBD")
    override val locale: Locale by di.instance()
    override val eventBus: EventBus by di.instance()
    override val sceneContainer: GameSceneContainer by di.instance()
    val itemAchievementManager: ItemAchievementManager by di.instance()
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
    var _hero: CharacterSprite? = null
    override val hero: Character?
        get() = _hero
    override val heroPlayer: Player by di.instance()
    val joinQQGroupSecret = gameInitData.joinQQGroupSecret
    var onlineNumber: Int = gameInitData.onlineCount

    val mapHierarchy: List<GameMapDefinition> = gameInitData.maps
    val idToMapDefinition: Map<String, GameMapDefinition> by lazy {
        mapHierarchy.toMap()
    }
    val i18nTextsForWebEditor: dynamic by lazy {
        val ret: dynamic = jso()
        i18nTextContainer.forEach {
            ret[it.key] = it.value
        }
        ret
    }

    override val modalController: ModalControllerInternal by lazy {
        DefaultModalController(di)
    }

    private val startTime: Timestamp = Timestamp.now()
    var lastAnimationFrameTime: Timestamp = startTime

    val i18nTextContainer: MutableMap<String, String> by di.instance(tag = "i18nTextContainer")
    val resourceLoader: ResourceLoader by di.instance()

    val gameControl: GameControl by di.instance()
    val mainMapCanvasRenderer: MainMapCanvasRenderer = MainMapCanvasRenderer(this)
    override val toastController = DefaultToastController(eventBus)
    override val bannerController = DefaultBannerController(eventBus)
    private val globalEventHandler = GlobalEventHandler(di, this)

    var gfw: Boolean = true

    fun start() {
        gameControl.start()
        globalEventHandler.start()
        animate()
        setClock(GAME_CLOCK_60S, GAME_CLOCK_60S_EVENT)
        setClock(GAME_CLOCK_1S, GAME_CLOCK_1S_EVENT)
        setClock(GAME_CLOCK_100MS, GAME_CLOCK_100MS_EVENT)
        setClock(GAME_CLOCK_20MS, GAME_CLOCK_20MS_EVENT)

        checkGfw()

        showWarningOnMobileDevice()
    }

    private fun showWarningOnMobileDevice() {
        if (window.navigator.userAgent.matches(".*(Mobi|Android).*".toRegex())) {
            modalController.showModal(i("YouMayExperienceIssuesOnMobileDevices", window.navigator.userAgent), i("NotOptimizedForMobileDevices"))
        }
    }

    fun transformGitHubUrl(url: String): String {
        return if (!gfw) {
            url
        } else {
            return url.replace("https://raw.githubusercontent.com/", "/ghraw/")
                .replace("https://avatars.githubusercontent.com/", "/ghavatars/")
        }
    }

    private fun checkGfw() {
        window.fetch("https://raw.githubusercontent.com/ByteLegend/ByteLegend/master/README.md", RequestInit(method = "HEAD")).then({
            gfw = false
            logger.debug("We're free! :-)")
        }, {
            gfw = true
            logger.debug("We're gfwed! :-(")
        })
    }

    private fun setClock(ms: Int, eventName: String) {
        window.setInterval(
            {
                eventBus.emit(eventName, null)
            },
            ms
        )
    }

    private fun animate() {
        sceneContainer.activeScene?.canvasState?.unsafeCast<DefaultGameCanvasState>()?.onAnimate()
        eventBus.emit(GAME_ANIMATION_EVENT, lastAnimationFrameTime)
        lastAnimationFrameTime = Timestamp.now()
        window.requestAnimationFrame { animate() }
    }

    override fun i(textId: String, vararg args: String): String = i18nTextContainer.getValue(textId).render(*args)
    override fun putText(textId: String, text: String) {
        i18nTextContainer[textId] = text
    }

    fun resolve(path: String) = "${RRBD}$path"
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

val Game.gameContainerHeight: Int
    get() = gameContainerSize.height
val Game.uiContainerCoordinateInGameContainer: PixelCoordinate
    get() = activeScene.canvasState.getUICoordinateInGameContainer()
val Game.uiContainerSize: PixelSize
    get() = activeScene.canvasState.getUIContainerSize()
