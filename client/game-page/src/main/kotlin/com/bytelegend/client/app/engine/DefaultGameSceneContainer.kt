package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.GameMapResource
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.GameSceneContainer
import com.bytelegend.app.client.api.I18nTextResource
import com.bytelegend.app.client.api.ImageResource
import com.bytelegend.app.client.api.ImageResourceData
import com.bytelegend.app.client.api.JSObjectBackedMap
import com.bytelegend.app.client.api.ResourceLoader
import com.bytelegend.app.client.api.TextAjaxResource
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.RawGameMap
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.client.app.page.game
import com.bytelegend.client.app.ui.SCENE_SWITCH_START_EVENT
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

fun mapJsonResourceId(mapId: String) = "$mapId-map"
fun mapTilesetResourceId(mapId: String) = "$mapId-tileset"
fun mapNpcAnimationSetResourceId(mapId: String) = "$mapId-npc"
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

    override fun getSceneById(mapId: String): GameScene = scenes.getValue(mapId)

    override val activeScene: GameScene?
        get() = _activeScene

    override var gameContainerSize: PixelSize = containerSize
        get() = field
        set(value) {
            field = value
            scenes.values.forEach { it.gameContainerSize = value }
        }

    override fun loadScene(mapId: String, switchAfterLoad: Boolean, onFinish: (GameScene?, GameScene) -> Unit) {
        // during loading, activeScene may already changed, so we save the reference
        val oldScene = _activeScene
        val scene = scenes[mapId]
        if (scene == null) {
            createThenSwitchScene(oldScene, mapId, switchAfterLoad, onFinish)
        } else {
            loadScene(oldScene, scene, switchAfterLoad, onFinish)
        }
    }

    private fun loadScene(oldScene: GameScene?, newScene: GameScene, switch: Boolean, action: (GameScene?, GameScene) -> Unit) {
        action(oldScene, newScene)
        if (switch && _activeScene == oldScene) {
            // the current active scene may be changed during loading
            // don't switch in this case
            _activeScene = newScene
            game.mainMapCanvasRenderer.putSceneBackgroundIntoCanvasCacheIfAbsent(newScene)
            eventBus.emit(GAME_UI_UPDATE_EVENT, null)
            eventBus.emit(SCENE_SWITCH_START_EVENT, null)
        } else {
            eventBus.emit(GAME_UI_UPDATE_EVENT, null)
        }
    }

    private fun createThenSwitchScene(oldScene: GameScene?, mapId: String, switchAfterLoading: Boolean, action: (GameScene?, GameScene) -> Unit) {
        resourceLoader.add(GameMapResource(mapJsonResourceId(mapId), "$RRBD/map/$mapId/map.json", 1)) {
            createSceneOnReady(oldScene, mapId, switchAfterLoading, action)
        }
        resourceLoader.add(ImageResource(mapTilesetResourceId(mapId), "$RRBD/map/$mapId/tileset.png", 1)) {
            createSceneOnReady(oldScene, mapId, switchAfterLoading, action)
        }
        resourceLoader.add(ImageResource(mapNpcAnimationSetResourceId(mapId), "$RRBD/map/$mapId/npc.png", 1)) {
            createSceneOnReady(oldScene, mapId, switchAfterLoading, action)
        }
        resourceLoader.add(TextAjaxResource(mapScriptResourceId(mapId), "$RRBD/js/game-$mapId.js", 1)) {
            createSceneOnReady(oldScene, mapId, switchAfterLoading, action)
        }
        resourceLoader.add(I18nTextResource(mapTextResourceId(mapId, locale), "$RRBD/i18n/$mapId/${locale.toLowerCase()}.json", 1)) {
            createSceneOnReady(oldScene, mapId, switchAfterLoading, action)
        }
    }

    private fun createSceneOnReady(oldScene: GameScene?, mapId: String, switchAfterLoading: Boolean, action: (GameScene?, GameScene) -> Unit) {
        val map = resourceLoader.getLoadedResourceOrNull<RawGameMap>("$mapId-map") ?: return
        val tileset = resourceLoader.getLoadedResourceOrNull<ImageResourceData>("$mapId-tileset") ?: return
        val script = resourceLoader.getLoadedResourceOrNull<String>("$mapId-script") ?: return
        val i18nText = resourceLoader.getLoadedResourceOrNull<Map<String, String>>("$mapId-${locale.toLowerCase()}") ?: return

        i18nContainer.putAll(i18nText)

        val scene = DefaultGameScene(di, map, tileset, gameContainerSize)
        scenes[mapId] = scene
        loadScene(oldScene, scene, switchAfterLoading, action)

        eval(script)

        resourceLoader.resetSession()
    }
}
