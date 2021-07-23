@file:Suppress("EXPERIMENTAL_API_USAGE", "DeferredResultUnused")

package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.GameSceneContainer
import com.bytelegend.app.client.api.ResourceLoader
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.app.shared.protocol.ONLINE_COUNTER_UPDATE_EVENT
import com.bytelegend.client.app.engine.resource.GameMapResource
import com.bytelegend.client.app.engine.resource.I18nTextResource
import com.bytelegend.client.app.engine.resource.ImageResource
import com.bytelegend.client.app.engine.resource.TextAjaxResource
import com.bytelegend.client.utils.JSObjectBackedMap
import com.bytelegend.client.app.page.game
import com.bytelegend.client.app.script.effect.fadeInEffect
import com.bytelegend.client.app.web.GameSceneInitResource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

const val SCENE_LOADING_START_EVENT = "scene.loading.start"
const val SCENE_LOADING_END_EVENT = "scene.loading.end"

fun mapJsonResourceId(mapId: String) = "$mapId-map"
fun mapTilesetResourceId(mapId: String) = "$mapId-tileset"
fun mapScriptResourceId(mapId: String) = "$mapId-script"
fun mapTextResourceId(mapId: String, locale: Locale) = "$mapId-${locale.lowercase()}"

class DefaultGameSceneContainer(
    override val di: DI,
    containerSize: PixelSize
) : GameSceneContainer, DIAware {
    private val eventBus: EventBus by di.instance()
    private val i18nContainer: MutableMap<String, String> by di.instance(tag = "i18nTextContainer")
    private val scenes: MutableMap<String, GameScene> = JSObjectBackedMap()
    private val RRBD: String by di.instance(tag = "RRBD")
    private val locale: Locale by di.instance()
    private val resourceLoader: ResourceLoader by instance()

    private var _activeScene: GameScene? = null

    override fun getSceneByIdOrNull(mapId: String): GameScene? = scenes.get(mapId)

    override fun getSceneById(mapId: String): GameScene = scenes.getValue(mapId)

    override val activeScene: GameScene?
        get() = _activeScene

    override var gameContainerSize: PixelSize = containerSize
        get() = field
        set(value) {
            field = value
            scenes.values.forEach { it.gameContainerSize = value }
        }

    override fun loadScene(mapId: String, switchAfterLoad: Boolean, onFinish: suspend (GameScene?, GameScene) -> Unit) {
        GlobalScope.launch {
            // during loading, activeScene may already changed, so we save the reference
            val oldScene = _activeScene
            val scene = scenes[mapId]
            if (scene == null) {
                createThenSwitchScene(oldScene, mapId, switchAfterLoad, onFinish)
            } else {
                switchScene(oldScene, scene, switchAfterLoad, onFinish)
            }

            launch { fadeInEffect(gameContainerSize) }
            eventBus.emit(GAME_UI_UPDATE_EVENT, null)
        }
    }

    private suspend fun switchScene(oldScene: GameScene?, newScene: GameScene, switch: Boolean, action: suspend (GameScene?, GameScene) -> Unit) {
        action(oldScene, newScene)
        if (switch && _activeScene == oldScene) {
            // the current active scene may be changed during loading
            // don't switch in this case
            _activeScene = newScene
            game.mainMapCanvasRenderer.putSceneBackgroundIntoCanvasCacheIfAbsent(newScene)
        }
    }

    @Suppress("UnsafeCastFromDynamic")
    private suspend fun createThenSwitchScene(oldScene: GameScene?, mapId: String, switchAfterLoading: Boolean, action: suspend (GameScene?, GameScene) -> Unit) {
        val map = resourceLoader.loadAsync(GameMapResource(mapJsonResourceId(mapId), "$RRBD/map/$mapId/map.json"))
        val tileset = resourceLoader.loadAsync(ImageResource(mapTilesetResourceId(mapId), "$RRBD/map/$mapId/tileset.png"))
        val mapScript = resourceLoader.loadAsync(TextAjaxResource(mapScriptResourceId(mapId), "$RRBD/js/game-$mapId.js"))
        val i18nText = resourceLoader.loadAsync(I18nTextResource(mapTextResourceId(mapId, locale), "$RRBD/i18n/$mapId/${locale.lowercase()}.json", game.i18nTextContainer))
        val sceneInitData = resourceLoader.loadAsync(GameSceneInitResource(mapId, game.webSocketClient))

        i18nContainer.putAll(i18nText.await())

        val scene = DefaultGameScene(di, map.await(), tileset.await(), gameContainerSize)
        val initData = sceneInitData.await()

        scene.players = PlayerContainer(mapId, eventBus, game.webSocketClient, resourceLoader, initData.players).apply { init(scene) }
        scene.playerChallenges = DefaultPlayerChallengeContainer(di, sceneInitData.await().playerChallenges.asDynamic()).apply { init(scene) }
        scenes[mapId] = scene
        switchScene(oldScene, scene, switchAfterLoading, action)

        eventBus.emit(ONLINE_COUNTER_UPDATE_EVENT, initData.online)

        eval(mapScript.await())
        resourceLoader.unsafeCast<DefaultResourceLoader>().sceneSwitchReady()
    }
}
