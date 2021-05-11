package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.GameMapResource
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.GameSceneContainer
import com.bytelegend.app.client.api.I18nTextResource
import com.bytelegend.app.client.api.ImageResource
import com.bytelegend.app.client.api.JSObjectBackedMap
import com.bytelegend.app.client.api.ResourceLoader
import com.bytelegend.app.client.api.TextAjaxResource
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.client.app.page.game
import com.bytelegend.client.app.script.effect.fadeInEffect
import com.bytelegend.client.app.web.GameSceneInitResource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

const val SCENE_LOADING_START_EVENT = "scene.switch.start"
const val SCENE_LOADING_END_EVENT = "scene.switch.end"

fun mapJsonResourceId(mapId: String) = "$mapId-map"
fun mapTilesetResourceId(mapId: String) = "$mapId-tileset"
fun mapRoadmapResourceId(mapId: String) = "$mapId-roadmap"
fun mapScriptResourceId(mapId: String) = "$mapId-script"
fun mapTextResourceId(mapId: String, locale: Locale) = "$mapId-${locale.toLowerCase()}"

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
                eventBus.emit(SCENE_LOADING_START_EVENT, null)
                createThenSwitchScene(oldScene, mapId, switchAfterLoad, onFinish)
                eventBus.emit(SCENE_LOADING_END_EVENT, null)
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
        val map = resourceLoader.loadAsync(GameMapResource(mapJsonResourceId(mapId), "$RRBD/map/$mapId/map.json", 1))
        val tileset = resourceLoader.loadAsync(ImageResource(mapTilesetResourceId(mapId), "$RRBD/map/$mapId/tileset.png", 1))
        val mapScript = resourceLoader.loadAsync(TextAjaxResource(mapScriptResourceId(mapId), "$RRBD/js/game-$mapId.js", 1))
        val i18nText = resourceLoader.loadAsync(I18nTextResource(mapTextResourceId(mapId, locale), "$RRBD/i18n/$mapId/${locale.toLowerCase()}.json", 1, game.i18nTextContainer))
        val sceneInitData = resourceLoader.loadAsync(GameSceneInitResource(mapId, game.webSocketClient))

        if (game.idToMapDefinition.getValue(mapId).roadmap) {
            resourceLoader.loadAsync(ImageResource(mapRoadmapResourceId(mapId), "$RRBD/map/$mapId/roadmap.svg", 1))
        }

        i18nContainer.putAll(i18nText.await())

        val scene = DefaultGameScene(di, map.await(), tileset.await(), gameContainerSize)
        scene.players = PlayerContainer(mapId, eventBus, game.webSocketClient, resourceLoader, sceneInitData.await().players).apply { init(scene) }
        scene.playerMissions = DefaultPlayerMissionContainer(di, sceneInitData.await().missions.asDynamic()).apply { init(scene) }
        scenes[mapId] = scene
        switchScene(oldScene, scene, switchAfterLoading, action)

        eval(mapScript.await())
        resourceLoader.resetSession()
    }
}
