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

import com.bytelegend.app.client.api.GameMission
import com.bytelegend.app.client.api.GameObjectContainer
import com.bytelegend.app.client.api.GameObjectsOnTile
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.utils.JSArrayBackedList
import com.bytelegend.app.client.utils.JSObjectBackedMap
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.mapToArrayWithIndex
import com.bytelegend.app.shared.objects.CoordinateAware
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.app.shared.objects.GridCoordinateAware
import com.bytelegend.client.app.obj.BackgroundLayer
import com.bytelegend.client.app.obj.toSprite
import kotlin.math.max
import kotlin.math.min

class DefaultGameObjectsOnTile : GameObjectsOnTile {
    override val objects: MutableMap<String, GameObject> = IdGameObjectContainer()
    override var mission: GameMission? = null
    override val missionsAround: MutableList<GameMission> = JSArrayBackedList()
}

class DefaultGameObjectContainer(
    private val gameScene: GameScene
) : GameObjectContainer {
    private val objectsById: IdGameObjectContainer = IdGameObjectContainer()
    private val objectsByRole: MutableMap<String, IdGameObjectContainer> = JSObjectBackedMap()

    // "(x,y)" -> {objects, missions, missionsAround}
    private val objectsByCoordinate: MutableMap<String, DefaultGameObjectsOnTile> = JSObjectBackedMap()
    val background: Array<Array<List<BackgroundLayer>>> = gameScene.map.rawTiles.mapToArrayWithIndex { it, coordinate ->
        val list = JSArrayBackedList<BackgroundLayer>()
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
        val objectsOnTile = getByCoordinate(newCoordinate).unsafeCast<DefaultGameObjectsOnTile>()
        objectsOnTile.objects[gameObject.id] = gameObject
        if (gameObject.roles.contains(GameObjectRole.Mission.toString())) {
            val mission = gameObject.unsafeCast<GameMission>()
            val mapWidth = mission.gameScene.map.size.width
            val mapHeight = mission.gameScene.map.size.height
            objectsOnTile.mission = mission

            val range = 3

            val left = max(newCoordinate.x - range, 0)
            val top = max(newCoordinate.y - range, 0)
            val right = min(newCoordinate.x + range, mapWidth - 1)
            val bottom = min(newCoordinate.y + range, mapHeight - 1)
            for (x in left..right) {
                for (y in top..bottom) {
                    val around = getByCoordinate(GridCoordinate(x, y)).unsafeCast<DefaultGameObjectsOnTile>()
                    around.missionsAround.add(mission)
                }
            }
        }
    }

    override fun removeFromCoordinate(gameObject: GameObject, oldCoordinate: GridCoordinate) {
        val objectsOnTile = objectsByCoordinate.get(oldCoordinate.toString()) ?: return
        // TODO not remove mission/missionsAround, but it's fine now.
        objectsOnTile.objects.remove(gameObject.id)
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

    override fun getByCoordinate(coordinate: GridCoordinate): GameObjectsOnTile {
        return objectsByCoordinate.getOrPut(coordinate.toString()) { DefaultGameObjectsOnTile() }
    }

    @Suppress("UnsafeCastFromDynamic")
    override fun <T : GameObject> getByRole(role: GameObjectRole): List<T> {
        return objectsByRole.getGameObject(role)
    }
}

fun MutableMap<String, IdGameObjectContainer>.putGameObject(key: Any, gameObject: GameObject) {
    getOrPut(key.toString()) { IdGameObjectContainer() }[gameObject.id] = gameObject
}

@Suppress("UnsafeCastFromDynamic", "UNUSED_VARIABLE")
fun <T : GameObject> MutableMap<String, IdGameObjectContainer>.getGameObject(key: Any): List<T> {
    val containerDelegate: dynamic = getOrPut(key.toString()) { IdGameObjectContainer() }.delegate
    val valueArray: dynamic = js("Object.values(containerDelegate)")
    return JSArrayBackedList(delegate = valueArray)
}

fun MutableMap<String, IdGameObjectContainer>.removeGameObject(key: Any, id: String) {
    getOrPut(key.toString()) { IdGameObjectContainer() }.remove(id)
}

typealias IdGameObjectContainer = JSObjectBackedMap<GameObject>
