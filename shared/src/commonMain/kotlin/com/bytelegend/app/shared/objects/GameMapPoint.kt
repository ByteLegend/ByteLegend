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
package com.bytelegend.app.shared.objects

import com.bytelegend.app.shared.GridCoordinate
import kotlinx.serialization.Serializable

/**
 * The id of entrance object on src map.
 */
fun mapEntranceId(srcMapId: String, destMapId: String) = "$srcMapId-$destMapId-entrance"

/**
 * The id of entrance point object on src map.
 */
fun mapEntrancePointId(mapEntranceId: String) = "$mapEntranceId-point"

/**
 * When player moves from src map to dest map, it will be placed at this point in dest map.
 */
fun mapEntranceDestinationId(mapEntranceId: String) = "$mapEntranceId-destination"

fun mapEntranceDestinationId(srcMapId: String, destMapId: String) = mapEntranceDestinationId(mapEntranceId(srcMapId, destMapId))

class GameMapPoint(
    override val id: String,
    override val layer: Int,
    override val gridCoordinate: GridCoordinate
) : GameMapObject, GameObject, GridCoordinateAware {
    override val type: GameMapObjectType = GameMapObjectType.GameMapPoint
    override val roles: Set<String> = setOf(GameObjectRole.MapPoint.toString())

    override fun compress() = CompressedGameMapPoint(id, layer, gridCoordinate.compress())
}

@Serializable
data class CompressedGameMapPoint(
    override val id: String,
    override val layer: Int,
    val gridCoordinate: List<Int>
) : CompressedGameMapObject {
    override val type: Int = GameMapObjectType.GameMapPoint.index

    override fun decompress() = GameMapPoint(id, layer, GridCoordinate(gridCoordinate))
}

class GameMapEntrancePoint(
    override val id: String,
    override val layer: Int,
    override val gridCoordinate: GridCoordinate,
    val srcMap: String,
    val destMap: String,
    override val type: GameMapObjectType
) : GameMapObject, GameObject, GridCoordinateAware {
    override val roles: Set<String> = setOf(GameObjectRole.MapPoint.toString())
    override fun compress() = CompressedGameMapEntrancePoint(id, layer, gridCoordinate.compress(), srcMap, destMap, type.index)
}

@Serializable
data class CompressedGameMapEntrancePoint(
    override val id: String,
    override val layer: Int,
    val gridCoordinate: List<Int>,
    val srcMap: String,
    val destMap: String,
    override val type: Int
) : CompressedGameMapObject {
    override fun decompress() = when (type) {
        GameMapObjectType.GameMapEntrancePoint.index ->
            GameMapEntrancePoint(id, layer, GridCoordinate(gridCoordinate), srcMap, destMap, GameMapObjectType.GameMapEntrancePoint)
        GameMapObjectType.GameMapEntranceDestinationPoint.index ->
            GameMapEntrancePoint(id, layer, GridCoordinate(gridCoordinate), srcMap, destMap, GameMapObjectType.GameMapEntranceDestinationPoint)
        else -> throw IllegalArgumentException("Type must be either GameMapEntrancePoint or GameMapEntranceDestinationPoint")
    }
}
