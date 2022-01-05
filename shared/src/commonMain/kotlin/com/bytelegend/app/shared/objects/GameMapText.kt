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

class GameMapText(
    override val id: String,
    override val layer: Int,
    val coordinate: PixelCoordinate,
    val fontSize: Int,
    val rotation: Int
) : GameMapObject, GameObject {
    override val type: GameMapObjectType = GameMapObjectType.GameMapText
    override val roles: Set<String> = setOf(GameObjectRole.MapText.toString())

    override fun compress() = CompressedGameMapText(
        id,
        layer,
        type.index,
        coordinate.compress(),
        fontSize,
        rotation
    )
}

@Serializable
data class CompressedGameMapText(
    override val id: String,
    override val layer: Int,
    override val type: Int,
    // [1,2] -> {x:1,y:2}
    val coordinate: List<Int>,
    val fontSize: Int,
    val rotation: Int
) : CompressedGameMapObject {
    override fun decompress() = GameMapText(
        id,
        layer,
        PixelCoordinate(coordinate),
        fontSize,
        rotation
    )
}
