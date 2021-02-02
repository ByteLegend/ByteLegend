package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.GameObjectContainer
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.GridCoordinateAware
import com.bytelegend.app.client.api.JSObjectBackedMap
import com.bytelegend.app.client.api.Sprite
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.mapToArrayWithIndex
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.sprite.toSprite

class DefaultGameObjectContainer(
    private val gameScene: GameScene
) : GameObjectContainer {
    private val objectsById: MutableMap<String, GameObject> = JSObjectBackedMap()
    private val objectsByRole: MutableMap<String, MutableMap<String, GameObject>> = JSObjectBackedMap()
    private val background: Array<Array<List<Sprite>>> = gameScene.map.rawTiles.mapToArrayWithIndex { it, coordinate ->
        it.layers.map { it.toSprite(gameScene, gameScene.map.tileSize * coordinate, gameScene.tileset.htmlElement) }
    }

    override fun <T : GameObject> getByIdOrNull(id: String): T? {
        return objectsById.get(id).unsafeCast<T?>()
    }

    override fun <T : GameObject> getById(id: String): T {
        return objectsById.getValue(id).unsafeCast<T>()
    }

    override fun add(gameObject: GameObject) {
        objectsById[gameObject.id] = gameObject
        gameObject.roles.forEach {
            objectsByRole.getOrPut(it.toString()) { JSObjectBackedMap() }.put(gameObject.id, gameObject)
        }
    }

    override fun <T : GameObject> remove(id: String): T? {
        val obj = objectsById.remove(id)
        obj?.roles?.forEach {
            objectsByRole.get(it.toString())?.remove(id)
        }
        return obj?.unsafeCast<T>()
    }

    override fun getByCoordinate(coordinate: GridCoordinate): List<GameObject> {
        return objectsById.values.filter {
            it is GridCoordinateAware && it.gridCoordinate == coordinate
        }
    }

    @Suppress("UnsafeCastFromDynamic")
    override fun <T : GameObject> getByRole(role: GameObjectRole): List<T> {
        return objectsByRole.getOrPut(role.toString()) { JSObjectBackedMap() }.values.toList().asDynamic()
    }

    override fun getDrawableSprites(): List<Sprite> {
        // 1. Scan background for tiles in canvas viewport.
        // 2. Scan sprites in canvas viewport.
        // 3. Sort by layer.
        // 4. Draw.

        // All referenced layer indices
        val layerIndices = mutableSetOf<Int>()
        // Key: layer index; Value: list of sprites
        val layerToSprites = JSObjectBackedMap<MutableList<Sprite>>()

        val canvasGridWidth = gameScene.canvasState.getCanvasGridSize().width
        val canvasGridHeight = gameScene.canvasState.getCanvasGridSize().height
        val canvasGridInMapX = gameScene.canvasState.getCanvasGridCoordinateInMap().x
        val canvasGridInMapY = gameScene.canvasState.getCanvasGridCoordinateInMap().y
        val mapGridWidth = gameScene.map.size.width
        val mapGridHeight = gameScene.map.size.height

        // One extra line/row to avoid flicking
        for (x in 0 until canvasGridWidth + 1) {
            for (y in 0 until canvasGridHeight + 1) {
                val realX = canvasGridInMapX + x
                val realY = canvasGridInMapY + y

                if (realX < mapGridWidth && realY < mapGridHeight) {
                    background[realY][realX].forEach { spriteLayer ->
                        layerIndices.add(spriteLayer.layer)
                        layerToSprites.getOrPut(spriteLayer.layer.toString()) { mutableListOf() }.add(spriteLayer)
                    }
                }
            }
        }

        getByRole<Sprite>(GameObjectRole.Sprite)
            .filter { !it.outOfCanvas() }
            .forEach {
                layerIndices.add(it.layer)
                layerToSprites.getOrPut(it.layer.toString()) { mutableListOf() }.add(it)
            }

        return layerIndices.sorted().flatMap { layerIndex ->
            layerToSprites.getValue(layerIndex.toString())
        }
    }
}
