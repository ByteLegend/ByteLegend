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

import com.bytelegend.app.shared.PLAYER_LAYER
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.annotations.JsonIgnore
import kotlinx.serialization.Serializable

class GameMapAnimation(
    override val id: String,
    val frameSize: PixelSize,
    val frameCount: Int
) : GameMapObject, GameObject {
    override val layer: Int = PLAYER_LAYER + 1
    override val roles: Set<String> = setOf()
    override val type: GameMapObjectType = GameMapObjectType.GameMapAnimation

    override fun compress(): CompressedGameMapObject = CompressedGameMapAnimation(id, listOf(frameSize.width, frameSize.height), frameCount)
}

@Serializable
data class CompressedGameMapAnimation(
    override val id: String,
    val size: List<Int>,
    val count: Int
) : CompressedGameMapObject {
    @get: JsonIgnore
    override val layer: Int = PLAYER_LAYER + 1

    override val type: Int = GameMapObjectType.GameMapAnimation.index

    override fun decompress() = GameMapAnimation(id, PixelSize(size[0], size[1]), count)
}
