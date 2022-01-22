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
import com.bytelegend.app.shared.GridSize
import com.bytelegend.app.shared.PLAYER_LAYER
import com.bytelegend.app.shared.annotations.JsonIgnore
import com.bytelegend.app.shared.map
import kotlinx.serialization.Serializable

/**
 * Dynamic objects aren't displayed on the map directly, but added by game script
 * dynamically. A dynamic sprite's frames are stored in map global tileset,
 * so it must have same tile size as the map (32x32)
 */
class GameMapDynamicSprite(
    override val id: String,
    val frames: List<List<List<GridCoordinate>>>
) : GameMapObject, GameObject {
    override val layer: Int = PLAYER_LAYER + 1
    override val roles: Set<String> = setOf()
    override val type: GameMapObjectType = GameMapObjectType.GameMapDynamicSprite

    // 3 dimension:
    // first/second dimension: the y/x of tiles because a sprite can cross multiple tiles.
    // third dimension: the frame coordinate of tile in tileset
    val size: GridSize = GridSize(frames[0].size, frames.size)

    override fun compress() = CompressedGameMapDynamicObject(
        id,
        frames.map {
            val tmp = mutableListOf<List<Int>>()
            it.forEach {
                tmp.add(it.toCompressedList())
            }
            tmp
        }
    )
}

@Serializable
data class CompressedGameMapDynamicObject(
    override val id: String,
    val frames: List<List<List<List<Int>>>>
) : CompressedGameMapObject {
    @get:JsonIgnore
    override val layer: Int = PLAYER_LAYER + 1

    override val type: Int = GameMapObjectType.GameMapDynamicSprite.index

    override fun decompress() = GameMapDynamicSprite(
        id,
        frames.map {
            val tmp = mutableListOf<GridCoordinate>()
            it.forEach {
                tmp.add(GridCoordinate(it))
            }
            tmp
        }
    )
}
