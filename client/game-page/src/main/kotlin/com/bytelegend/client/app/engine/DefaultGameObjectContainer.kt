package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.CoordinateAware
import com.bytelegend.app.client.api.GameObjectContainer
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.JSArrayBackedList
import com.bytelegend.app.client.api.JSObjectBackedMap
import com.bytelegend.app.client.api.Sprite
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.mapToArrayWithIndex
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.obj.toSprite

class DefaultGameObjectContainer(
    private val gameScene: GameScene
) : GameObjectContainer {
    private val objectsById: IdGameObjectContainer = IdGameObjectContainer()
    private val objectsByRole: MutableMap<String, IdGameObjectContainer> = JSObjectBackedMap()
    private val background: Array<Array<List<Sprite>>> = gameScene.map.rawTiles.mapToArrayWithIndex { it, coordinate ->
        val list = JSArrayBackedList<Sprite>()
        it.layers.forEach {
            list.add(it.toSprite(gameScene, coordinate, gameScene.tileset.htmlElement))
        }
        list
    }

    override fun <T : GameObject> getByIdOrNull(id: String): T? {
        return objectsById[id].unsafeCast<T?>()
    }

    override fun <T : GameObject> getById(id: String): T {
        return objectsById.getValue(id).unsafeCast<T>()
    }

    override fun add(gameObject: GameObject) {
        objectsById[gameObject.id] = gameObject
        gameObject.roles.forEach {
            objectsByRole.putGameObject(it, gameObject)
        }
    }

    override fun <T : GameObject> remove(id: String): T? {
        val obj = objectsById.remove(id)?.unsafeCast<T>()
        obj?.roles?.forEach {
            objectsByRole.removeGameObject(it, id)
        }
        return obj?.unsafeCast<T>()
    }

    override fun getByCoordinate(coordinate: GridCoordinate): List<GameObject> {
        return getByRole<GameObject>(GameObjectRole.CoordinateAware).filter {
            it.unsafeCast<CoordinateAware>().gridCoordinate == coordinate
        }
    }

    @Suppress("UnsafeCastFromDynamic")
    override fun <T : GameObject> getByRole(role: GameObjectRole): List<T> {
        return objectsByRole.getGameObject(role)
    }

    /**
     * This method is carefully tuned as it's the most frequently invoked method.
     */
    override fun getDrawableSprites(): List<Sprite> {
        // 1. Scan background for tiles in canvas viewport.
        // 2. Scan sprites in canvas viewport.
        // 3. Sort by layer.
        // 4. Draw.

        val indexes = js("new Set()")
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
                        indexes.add(spriteLayer.layer)
                        layerToSprites.getOrPut(spriteLayer.layer.toString()) { JSArrayBackedList() }.add(spriteLayer)
                    }
                }
            }
        }

        val sprites = getByRole<Sprite>(GameObjectRole.Sprite)

        sprites.forEach {
            if (!it.outOfCanvas()) {
                indexes.add(it.layer)
                layerToSprites.getOrPut(it.layer.toString()) { JSArrayBackedList() }.add(it)
            }
        }

        val sorted = JSArrayBackedList<Int>(delegate = js("Array.from(indexes).sort(function(a, b){return a - b;})"))
        val ret = JSArrayBackedList<Sprite>()
        sorted.forEach {
            ret.addAll(layerToSprites.getValue(it.toString()))
        }

        return ret
    }
}

fun MutableMap<String, IdGameObjectContainer>.putGameObject(key: Any, gameObject: GameObject) {
    getOrPut(key.toString()) { IdGameObjectContainer() }.put(gameObject.id, gameObject)
}

@Suppress("UnsafeCastFromDynamic")
fun <T : GameObject> MutableMap<String, IdGameObjectContainer>.getGameObject(key: Any): List<T> {
    return JSArrayBackedList(getOrPut(key.toString()) { IdGameObjectContainer() }.values).asDynamic()
}

fun MutableMap<String, IdGameObjectContainer>.removeGameObject(key: Any, id: String) {
    getOrPut(key.toString()) { IdGameObjectContainer() }.remove(id)
}

class IdGameObjectContainer : MutableMap<String, GameObject> by JSObjectBackedMap()
