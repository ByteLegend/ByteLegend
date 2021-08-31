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

fun defaultMapEntranceId(srcMapId: String, destMapId: String) = "$srcMapId-$destMapId-entrance"

fun defaultMapEntrancePointId(mapEntranceId: String) = "$mapEntranceId-point"

fun defaultMapEntranceDestinationId(mapEntranceId: String) = "$mapEntranceId-destination"

fun defaultMapEntrancePointId(srcMapId: String, destMapId: String) = defaultMapEntrancePointId(defaultMapEntranceId(srcMapId, destMapId))

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
