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
import kotlinx.serialization.Serializable

class GameMapCurve(
    override val id: String,
    override val layer: Int,
    val points: List<PixelCoordinate>
) : GameMapObject, GameObject {
    override val type: GameMapObjectType = GameMapObjectType.GameMapCurve
    override val roles: Set<String> = setOf(GameObjectRole.MapCurve.toString())
    override fun compress() = CompressedGameMapCurve(
        id,
        layer,
        points.map { it.compress() }
    )
}

@Serializable
data class CompressedGameMapCurve(
    override val id: String,
    override val layer: Int,
    val points: List<List<Int>>
) : CompressedGameMapObject {
    override val type: Int = GameMapObjectType.GameMapCurve.index

    override fun decompress() = GameMapCurve(
        id,
        layer,
        points.map { PixelCoordinate(it) }
    )
}
