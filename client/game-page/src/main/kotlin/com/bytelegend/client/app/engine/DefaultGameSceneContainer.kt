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
@file:Suppress("EXPERIMENTAL_API_USAGE", "DeferredResultUnused")

package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.GameSceneContainer
import com.bytelegend.app.client.api.HERO_ID
import com.bytelegend.app.client.api.ResourceLoader
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.app.shared.objects.mapEntranceDestinationId
import com.bytelegend.app.shared.protocol.ONLINE_COUNTER_UPDATE_EVENT
import com.bytelegend.client.app.engine.resource.GameMapResource
import com.bytelegend.client.app.engine.resource.I18nTextResource
import com.bytelegend.client.app.engine.resource.ImageResource
import com.bytelegend.client.app.engine.resource.TextAjaxResource
import com.bytelegend.client.app.obj.character.CharacterSprite
import com.bytelegend.client.app.obj.character.HeroCharacter
import com.bytelegend.client.app.script.effect.fadeInEffect
import com.bytelegend.client.app.web.GameSceneInitResource
import com.bytelegend.client.utils.JSObjectBackedMap
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

// Emitted when first resource starts loading.
const val SCENE_LOADING_START_EVENT = "scene.loading.start"

// Emitted when last resource loading finishes.
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
    private val gameRuntime: GameRuntime by di.instance()
    private val game: Game = gameRuntime.unsafeCast<Game>()

    private var _activeScene: GameScene? = null

    override fun getSceneByIdOrNull(mapId: String): GameScene? = scenes.get(mapId)

    override fun getSceneById(mapId: String): GameScene = scenes.getValue(mapId)

    override val activeScene: GameScene?
        get() = _activeScene

    override var gameContainerSize: PixelSize = containerSize
        set(value) {
            field = value
            scenes.values.forEach { it.gameContainerSize = value }
        }

    override fun loadScene(mapId: String, switchAfterLoad: Boolean, onFinish: suspend (GameScene?, GameScene) -> Unit) {
        if (_activeScene?.unsafeCast<DefaultGameScene>()?.mainChannelDirector?.isRunning == true) {
            return
        }
        GlobalScope.launch {
            // during loading, activeScene may already changed, so we save the reference
            val oldScene = _activeScene
            if (oldScene != null) {
                game.mainMapCanvasRenderer.hideMap(oldScene.map.id)
            }
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
        if (logger.debugEnabled) {
            logger.debug("Switch scene from ${oldScene?.map?.id} to ${newScene.map.id} finished.")
            // Don't remove this log because it's used in browser test to locate the canvas
            logger.debug("canvasCoordinateInMap: ${newScene.canvasState.getCanvasCoordinateInMap().x},${newScene.canvasState.getCanvasCoordinateInMap().y}")
        }
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
        scene.challengeAnswers = DefaultChallengeAnswersContainer(di, sceneInitData.await().challengeAnswers.asDynamic()).apply { init(scene) }
        scenes[mapId] = scene
        switchScene(oldScene, scene, switchAfterLoading, action)

        eventBus.emit(ONLINE_COUNTER_UPDATE_EVENT, initData.online)

        eval(mapScript.await())
        resourceLoader.unsafeCast<DefaultResourceLoader>().sceneSwitchReady()
    }

    /**
     * 1. Switch scene.
     * 2. Close hero object in the old scene.
     * 3. Create a new one in the new scene.
     */
    fun heroEnterScene(destMapId: String) {
        loadScene(destMapId) { oldScene, newScene ->
            val srcMap = game.heroPlayer.map

            val destinationOnNewMap = newScene.objects.getPointById(mapEntranceDestinationId(srcMap, destMapId))

            val oldHero = oldScene!!.objects.getById<CharacterSprite>(HERO_ID)
            val newHero = HeroCharacter(newScene, game.heroPlayer).apply { init() }
            newHero.direction = oldHero.direction
            newHero.pixelCoordinate = destinationOnNewMap * newScene.map.tileSize

            game.heroPlayer.map = newScene.map.id
            game.heroPlayer.x = destinationOnNewMap.x
            game.heroPlayer.y = destinationOnNewMap.y

            oldHero.close()
            game._hero = newHero
        }
    }
}
