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

import com.bytelegend.app.client.api.GameObjectContainer
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.mapToArrayWithIndex
import com.bytelegend.app.shared.objects.CoordinateAware
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.app.shared.objects.GridCoordinateAware
import com.bytelegend.client.app.obj.BackgroundSpriteLayer
import com.bytelegend.client.app.obj.toSprite
import com.bytelegend.client.utils.JSArrayBackedList
import com.bytelegend.client.utils.JSObjectBackedMap

class DefaultGameObjectContainer(
    private val gameScene: GameScene
) : GameObjectContainer {
    private val objectsById: IdGameObjectContainer = IdGameObjectContainer()
    private val objectsByRole: MutableMap<String, IdGameObjectContainer> = JSObjectBackedMap()

    // {"y" -> { "x" ->  {id1:obj1, id2:obj2} }}
    private val objectsByCoordinate: MutableMap<String, MutableMap<String, IdGameObjectContainer>> = JSObjectBackedMap()
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

    override fun getPointById(id: String): GridCoordinate {
        return getById<GameObject>(id).unsafeCast<GridCoordinateAware>().gridCoordinate
    }

    override fun putIntoCoordinate(gameObject: GameObject, newCoordinate: GridCoordinate) {
        objectsByCoordinate
            .getNextLevelMap(newCoordinate.y)
            .putGameObject(newCoordinate.x.toString(), gameObject)
    }

    override fun removeFromCoordinate(gameObject: GameObject, oldCoordinate: GridCoordinate) {
        objectsByCoordinate
            .getNextLevelMap(oldCoordinate.y)
            .removeGameObject(oldCoordinate.x.toString(), gameObject.id)
    }

    override fun add(gameObject: GameObject) {
        val oldValue = objectsById.put(gameObject.id, gameObject)
        if (oldValue != null) {
            console.warn("Overwriting object: ${gameObject.id}")
        }
        gameObject.roles.forEach {
            if (it == GameObjectRole.CoordinateAware.toString()) {
                putIntoCoordinate(gameObject, gameObject.unsafeCast<CoordinateAware>().gridCoordinate)
            }
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
        return objectsByCoordinate
            .getNextLevelMap(coordinate.y)
            .getGameObject(coordinate.x)
    }

    @Suppress("UnsafeCastFromDynamic")
    override fun <T : GameObject> getByRole(role: GameObjectRole): List<T> {
        return objectsByRole.getGameObject(role)
    }
}

fun MutableMap<String, MutableMap<String, IdGameObjectContainer>>.getNextLevelMap(key: Any) = getOrPut(key.toString()) { JSObjectBackedMap() }

fun MutableMap<String, IdGameObjectContainer>.putGameObject(key: Any, gameObject: GameObject) {
    getOrPut(key.toString()) { IdGameObjectContainer() }[gameObject.id] = gameObject
}

@Suppress("UnsafeCastFromDynamic")
fun <T : GameObject> MutableMap<String, IdGameObjectContainer>.getGameObject(key: Any): List<T> {
    val containerDelegate: dynamic = getOrPut(key.toString()) { IdGameObjectContainer() }.delegate
    val valueArray: dynamic = js("Object.values(containerDelegate)")
    return JSArrayBackedList(delegate = valueArray)
}

fun MutableMap<String, IdGameObjectContainer>.removeGameObject(key: Any, id: String) {
    getOrPut(key.toString()) { IdGameObjectContainer() }.remove(id)
}

typealias IdGameObjectContainer = JSObjectBackedMap<GameObject>
