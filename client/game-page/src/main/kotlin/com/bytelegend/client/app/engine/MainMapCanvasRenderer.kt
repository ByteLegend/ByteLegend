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

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.Sprite
import com.bytelegend.app.shared.PLAYER_LAYER
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.obj.BackgroundLayer
import com.bytelegend.client.app.ui.Layer
import com.bytelegend.app.client.utils.JSArrayBackedList
import com.bytelegend.app.client.utils.JSObjectBackedMap
import kotlinx.browser.document
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import react.RefObject

/**
 * This class is carefully tuned, don't surprise on the weird usage and don't change it without profiling.
 */
class MainMapCanvasRenderer(
    private val game: Game
) {
    private val canvasCache: MutableMap<String, List<HTMLCanvasElement>> = JSObjectBackedMap()
    private val objectContainer: DefaultGameObjectContainer
        get() = game.activeScene.objects.unsafeCast<DefaultGameObjectContainer>()

    /**
     * The non-pre-render-able tiles, including dynamic sprites (e.g. NPC) and
     * the tile layer above pal
     */
    lateinit var mapObjectsLayerRef: RefObject<HTMLCanvasElement>
    private val mapObjectsLayer: CanvasRenderingContext2D
        get() = mapObjectsLayerRef.current!!.getContext("2d").unsafeCast<CanvasRenderingContext2D>()

    @Suppress("UnsafeCastFromDynamic")
    fun putSceneBackgroundIntoCanvasCacheIfAbsent(gameScene: GameScene) {
        val mapId = gameScene.map.id
        if (canvasCache.containsKey(mapId)) {
            return
        }
        val mapBackgroundFrames = game.idToMapDefinition.getValue(mapId).frames

        val canvasElements: List<HTMLCanvasElement> = List(mapBackgroundFrames) {
            document.createElement("canvas").unsafeCast<HTMLCanvasElement>().apply {
                id = "canvas-cache-$mapId-$it"
                style.visibility = "hidden"
                document.body?.appendChild(this)
            }
        }
        canvasCache[mapId] = canvasElements

        val background = objectContainer.background
        // draw static tiles to all caches, and animation tiles to corresponding frames
        canvasElements.forEachIndexed { cacheCanvasIndex, canvasElement ->
            canvasElement.width = gameScene.map.pixelSize.width
            canvasElement.height = gameScene.map.pixelSize.height
            val context: CanvasRenderingContext2D = canvasElement.getContext("2d").asDynamic()

            for (y in 0 until gameScene.map.size.height) {
                for (x in 0 until gameScene.map.size.width) {
                    val layers = background[y][x]
                    // Draw all tiles, no matter whether they support pre-rendering or not
                    // to avoid black crack on tile border
                    for (i in 0 until layers.size) {
                        val layer = layers[i]
                        if (layer.layer < 0) {
                            layer.prerenderFrame(cacheCanvasIndex, context)
                        }
                    }
                }
            }
        }
    }

    fun onAnimation() {
        // All layers below players are pre-rendered
        drawPrerenderedBackgroundLayer()
        // All layers above players are rendered in every drawing
        drawSpritesAndTilesAbovePlayer()
    }

    private fun HTMLCanvasElement.show(coordinate: PixelCoordinate) {
        style.visibility = "visible"
        style.position = "absolute"
        style.left = "${-coordinate.x}px"
        style.top = "${-coordinate.y}px"
        style.zIndex = Layer.MapCanvas.zIndex().toString()
    }

    private fun HTMLCanvasElement.hide() {
        style.visibility = "hidden"
    }

    fun hideMap(mapId: String) {
        canvasCache[mapId]?.forEach {
            it.hide()
        }
    }

    private fun drawPrerenderedBackgroundLayer() {
        val gameScene = game.activeScene
        val activeMapId = gameScene.map.id
        val currentFrameIndex = ((game.elapsedTimeSinceStart / 500) % (game.idToMapDefinition.getValue(activeMapId).frames)).toInt()

        val canvasCoordinate = gameScene.canvasState.getCanvasCoordinateInMap() - gameScene.canvasState.getCanvasCoordinateInGameContainer() - /* canvas border */ PixelCoordinate(2, 2)

        canvasCache[activeMapId]?.forEachIndexed { index: Int, canvas: HTMLCanvasElement ->
            if (index == currentFrameIndex) {
                canvas.show(canvasCoordinate)
            } else {
                canvas.hide()
            }
        }
    }

    // TODO only rerendering dirty rectangles
    private fun drawSpritesAndTilesAbovePlayer() {
        val gameScene = game.activeScene.unsafeCast<DefaultGameScene>()
        val canvasPixelSize = gameScene.canvasState.getCanvasPixelSize()
        val canvasGridWidth = gameScene.canvasState.getCanvasGridSize().width
        val canvasGridHeight = gameScene.canvasState.getCanvasGridSize().height
        val canvasGridInMapX = gameScene.canvasState.getCanvasGridCoordinateInMap().x
        val canvasGridInMapY = gameScene.canvasState.getCanvasGridCoordinateInMap().y
        val mapGridWidth = gameScene.map.size.width
        val mapGridHeight = gameScene.map.size.height

        mapObjectsLayer.clearRect(0.0, 0.0, canvasPixelSize.width.toDouble(), canvasPixelSize.height.toDouble())

        val background = objectContainer.unsafeCast<DefaultGameObjectContainer>().background
        // filter non-pre-render-able tiles, draw them.
        val indexes = js("new Set()")
        // Key: layer index; Value: list of sprites
        val layerToSprites = JSObjectBackedMap<MutableList<Sprite>>()
        for (x in 0 until canvasGridWidth + 1) {
            for (y in 0 until canvasGridHeight + 1) {
                val realX = canvasGridInMapX + x
                val realY = canvasGridInMapY + y

                if (realX < mapGridWidth && realY < mapGridHeight) {
                    val layers = background[realY][realX]

                    for (i in 0 until layers.size) {
                        if (layers[i].layer > PLAYER_LAYER) {
                            val spriteLayer = layers[i]
                            indexes.add(spriteLayer.layer)
                            layerToSprites.getOrPut(spriteLayer.layer.toString()) { JSArrayBackedList() }.add(spriteLayer)
                        }
                    }
                }
            }
        }
        val sprites = objectContainer.getByRole<Sprite>(GameObjectRole.Sprite)
        for (i in 0 until sprites.size) {
            val sprite = sprites[i]
            if (!sprite.outOfCanvas()) {
                indexes.add(sprite.layer)
                layerToSprites.getOrPut(sprite.layer.toString()) { JSArrayBackedList() }.add(sprite)
            }
        }

        val players = gameScene.players.getDrawableCharacters()
        for (i in 0 until players.size) {
            val player = players[i]
            indexes.add(player.layer)
            layerToSprites.getOrPut(player.layer.toString()) { JSArrayBackedList() }.add(player)
        }

        drawByLayerOrder(indexes, layerToSprites, mapObjectsLayer)
    }

    /*
     NOTE: don't use Kotlin collection.any/all, the compiled js uses very slow Kotlin.isType:
        any$break: do {
            var tmp$_1;
            if (Kotlin.isType(layers, Collection) && layers.isEmpty()) {
              any$result = false;
              break any$break;
         }
     */
    @Suppress("ReplaceManualRangeWithIndicesCalls")
    private fun layersAbovePlayerLayer(layers: List<BackgroundLayer>): List<BackgroundLayer> {
        val ret = JSArrayBackedList<BackgroundLayer>()

        for (i in 0 until layers.size) {
            if (layers[i].layer > PLAYER_LAYER) {
                ret.add(layers[i])
            }
        }
        return ret
    }

    @Suppress("UNUSED_PARAMETER")
    private fun drawByLayerOrder(
        indexes: dynamic,
        layerToSprites: JSObjectBackedMap<MutableList<Sprite>>,
        context: CanvasRenderingContext2D
    ) {
        val sorted = JSArrayBackedList<Int>(delegate = js("Array.from(indexes).sort(function(a, b){return a - b;})"))
        sorted.forEach {
            layerToSprites.getValue(it.toString()).forEach {
                it.draw(context)
            }
        }
    }
}
