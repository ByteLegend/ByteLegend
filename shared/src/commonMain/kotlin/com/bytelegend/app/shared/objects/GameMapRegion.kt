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

import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.annotations.JsonIgnore
import kotlinx.serialization.Serializable

@Serializable
data class CompressedGameMapRegion(
    override val id: String,
    val center: List<Int>,
    val vertices: List<List<Int>>,
    val next: List<String>
) : CompressedGameMapObject {
    override val type: Int = GameMapObjectType.GameMapRegion.index
    @get:JsonIgnore
    override val layer: Int = 0

    override fun decompress() = GameMapRegion(
        id,
        PixelCoordinate(center),
        vertices.map { PixelCoordinate(it) },
        next
    )
}

class GameMapRegion(
    override val id: String,
    val center: PixelCoordinate,
    val vertices: List<PixelCoordinate>,
    val next: List<String>
) : GameMapObject, GameObject {
    override val layer: Int = 0
    override val type: GameMapObjectType = GameMapObjectType.GameMapRegion
    override val roles: Set<String> = setOf(GameObjectRole.MapRegion.toString())

    override fun compress() = CompressedGameMapRegion(
        id,
        center.compress(),
        vertices.map { it.compress() },
        next
    )
}
