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

import kotlinx.serialization.Polymorphic

/**
 * GameMapObject are objects that are easier to draw in Tiled than in code,
 * for example, a point, a region, etc.
 */
interface GameMapObject {
    val id: String
    val type: GameMapObjectType
    val layer: Int

    fun compress(): CompressedGameMapObject {
        throw UnsupportedOperationException("${this::class} doesn't support compress operation")
    }
}

@Polymorphic
interface CompressedGameMapObject {
    val id: String
    val type: Int
    val layer: Int

    fun decompress(): GameMapObject
}

enum class GameMapObjectType(
    val index: Int
) {
    /**
     * A large text displayed on the map. For example, the map region name.
     */
    GameMapText(1),

    /**
     * A region on map (polygon in Tiled).
     */
    GameMapRegion(2),

    /**
     * A point on a map.
     */
    GameMapPoint(3),

    /**
     * Ordered series of points, to form a curve.
     */
    GameMapCurve(4),

    /**
     * A special layer in "DynamicSprites" group layer.
     */
    GameMapDynamicSprite(5),

    GameMapMission(6),

    /**
     * A point that will be placed an entrance so player can travel
     * across different maps. The name must be "{mapX}-{mapY}-entrance-point"
     */
    GameMapEntrancePoint(7),
    GameMapEntranceDestinationPoint(8),

    /**
     * A special layer in "Animations" group layer.
     */
    GameMapAnimation(9);

    companion object {
        fun fromIndex(index: Int): GameMapObjectType = values()[index - 1]
    }
}
