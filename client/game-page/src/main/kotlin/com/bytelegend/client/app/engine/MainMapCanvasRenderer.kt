package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.Sprite
import com.bytelegend.app.client.api.Timestamp
import com.bytelegend.app.shared.PLAYER_LAYER
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.utils.JSArrayBackedList
import com.bytelegend.client.utils.JSObjectBackedMap
import com.bytelegend.client.app.obj.BackgroundSpriteLayer
import kotlinx.browser.document
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement

/**
 * This class is carefully tuned, don't surprise on the weird usage and don't change it without profiling.
 */
class MainMapCanvasRenderer(
    private val game: Game
) {
    private val canvasCaches: MutableMap<String, List<HTMLCanvasElement>> = JSObjectBackedMap()
    private val objectContainer: DefaultGameObjectContainer
        get() = game.activeScene.objects.unsafeCast<DefaultGameObjectContainer>()

    /**
     * The pre-render-able tiles, mostly background.
     */
    lateinit var mapBackgroundLayer: CanvasRenderingContext2D

    /**
     * The non-pre-render-able tiles, including dynamic sprites (e.g. NPC) and
     * the tile layer above pal
     */
    lateinit var mapObjectsLayer: CanvasRenderingContext2D

    /**
     * We don't need to re-render the whole background if it's not moving.
     */
    lateinit var lastBackgroundRenderTime: Timestamp
    lateinit var lastBackgroundRenderCanvasPixelSize: PixelSize
    lateinit var lastBackgroundRenderCanvasCoordinateInMap: PixelCoordinate

    @Suppress("UnsafeCastFromDynamic")
    fun putSceneBackgroundIntoCanvasCacheIfAbsent(gameScene: GameScene) {
        val mapId = gameScene.map.id
        if (canvasCaches.containsKey(mapId)) {
            return
        }
        val mapBackgroundFrames = game.idToMapDefinition.getValue(mapId).frames

        val canvasElements: List<HTMLCanvasElement> = List(mapBackgroundFrames) {
            document.createElement("canvas").apply {
                this.id = "canvas-cache-$mapId-$it"
                document.body?.appendChild(this)
            }.asDynamic()
        }
        canvasCaches[mapId] = canvasElements

        lastBackgroundRenderTime = Timestamp(0)
        lastBackgroundRenderCanvasCoordinateInMap = PixelCoordinate(0, 0)
        lastBackgroundRenderCanvasPixelSize = PixelSize(0, 0)

        val background = objectContainer.background
        // draw static tiles to all caches, and animation tiles to corresponding frames
        canvasElements.forEachIndexed { cacheCanvasIndex, canvasElement ->
            canvasElement.width = gameScene.map.pixelSize.width
            canvasElement.height = gameScene.map.pixelSize.height
            canvasElement.style.display = "none"
            val context: CanvasRenderingContext2D = canvasElement.getContext("2d").asDynamic()

            for (y in 0 until gameScene.map.size.height) {
                for (x in 0 until gameScene.map.size.width) {
                    val layers = background[y][x]
                    // Draw all tiles, no matter whether they support pre-rendering or not
                    // to avoid black crack on tile border
                    layers.forEach {
                        it.prerenderFrame(cacheCanvasIndex, context)
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

    private fun drawPrerenderedBackgroundLayer() {
        val gameScene = game.activeScene
        val mapId = gameScene.map.id
        val currentFrameIndex = ((game.elapsedTimeSinceStart / 500) % (game.idToMapDefinition.getValue(mapId).frames)).toInt()
        val canvasElement = canvasCaches.getValue(mapId)[currentFrameIndex]

        val canvasPixelSize = gameScene.canvasState.getCanvasPixelSize()
        val canvasCoordinateInMap = gameScene.canvasState.getCanvasCoordinateInMap()

        if (backgroundRefreshRequired(canvasCoordinateInMap, canvasPixelSize)) {
            lastBackgroundRenderTime = Timestamp.now()
            lastBackgroundRenderCanvasCoordinateInMap = canvasCoordinateInMap
            lastBackgroundRenderCanvasPixelSize = canvasPixelSize
        } else {
            return
        }

        mapBackgroundLayer.clearRect(0.0, 0.0, canvasPixelSize.width.toDouble(), canvasPixelSize.height.toDouble())
        mapBackgroundLayer.drawImage(
            canvasElement,
            canvasCoordinateInMap.x.toDouble(),
            canvasCoordinateInMap.y.toDouble(),
            canvasPixelSize.width.toDouble(),
            canvasPixelSize.height.toDouble(),
            0.0,
            0.0,
            canvasPixelSize.width.toDouble(),
            canvasPixelSize.height.toDouble()
        )
    }

    private fun backgroundRefreshRequired(
        currentCanvasCoordinateInMap: PixelCoordinate,
        currentCanvasPixelSize: PixelSize
    ): Boolean {
        val frameNum = game.idToMapDefinition.getValue(game.activeScene.map.id).frames
        return Timestamp.now() - lastBackgroundRenderTime > 1000 / frameNum ||
            lastBackgroundRenderCanvasCoordinateInMap != currentCanvasCoordinateInMap ||
            lastBackgroundRenderCanvasPixelSize != currentCanvasPixelSize
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
                    layersAbovePlayerLayer(background[realY][realX]).forEach { spriteLayer ->
                        indexes.add(spriteLayer.layer)
                        layerToSprites.getOrPut(spriteLayer.layer.toString()) { JSArrayBackedList() }.add(spriteLayer)
                    }
                }
            }
        }
        objectContainer.getByRole<Sprite>(GameObjectRole.Sprite)
            .forEach {
                if (!it.outOfCanvas()) {
                    indexes.add(it.layer)
                    layerToSprites.getOrPut(it.layer.toString()) { JSArrayBackedList() }.add(it)
                }
            }

        gameScene.players.getDrawableCharacters().forEach {
            indexes.add(it.layer)
            layerToSprites.getOrPut(it.layer.toString()) { JSArrayBackedList() }.add(it)
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
    private fun layersAbovePlayerLayer(layers: List<BackgroundSpriteLayer>): List<BackgroundSpriteLayer> {
        val ret = JSArrayBackedList<BackgroundSpriteLayer>()

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
