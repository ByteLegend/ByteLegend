package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.CoordinateAware
import com.bytelegend.app.client.api.GameObjectContainer
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.mapToArrayWithIndex
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.engine.util.JSArrayBackedList
import com.bytelegend.client.app.engine.util.JSObjectBackedMap
import com.bytelegend.client.app.obj.BackgroundSpriteLayer
import com.bytelegend.client.app.obj.toSprite

class DefaultGameObjectContainer(
    private val gameScene: GameScene
) : GameObjectContainer {
    private val objectsById: IdGameObjectContainer = IdGameObjectContainer()
    private val objectsByRole: MutableMap<String, IdGameObjectContainer> = JSObjectBackedMap()
    val background: Array<Array<List<BackgroundSpriteLayer>>> = gameScene.map.rawTiles.mapToArrayWithIndex { it, coordinate ->
        val list = JSArrayBackedList<BackgroundSpriteLayer>()
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
