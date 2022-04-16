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
import com.bytelegend.app.shared.annotations.JsonIgnore
import com.bytelegend.app.shared.entities.mission.MapMissionSpec
import kotlinx.serialization.Serializable

/**
 * GameMapMission doesn't contain all information of missions, just partial of them
 * (id, title) to display them. The whole information is loaded via AJAX.
 */
class GameMapMission(
    override val id: String,
    /**
     * The i18n id of the mission title
     */
    val title: String,
    val totalStar: Int,
    val challenges: List<String>,
    val tutorialsPrice: Int,
    override val map: String,
    override val sprite: String,
    override val gridCoordinate: GridCoordinate,
    // Next mission id
    override val next: List<String>,
    val region: String?,
) : GameMapObject, MapMissionSpec, GridCoordinateAware {
    override val layer: Int = 0
    override val type: GameMapObjectType = GameMapObjectType.GameMapMission
    override fun compress() = CompressedGameMapMission(
        id,
        title,
        totalStar,
        challenges,
        tutorialsPrice,
        map,
        sprite,
        gridCoordinate.toCompressedList(),
        next,
        region
    )

    override val roadmap: Boolean = true
}

@Serializable
data class CompressedGameMapMission(
    override val id: String,
    val title: String,
    val totalStar: Int,
    val challenges: List<String>,
    val tutorialsPrice: Int,
    val map: String,
    val sprite: String,
    val point: List<Int>,
    val next: List<String>,
    val region: String? = null
) : CompressedGameMapObject {
    @get:JsonIgnore
    override val layer: Int = 0
    override val type: Int = GameMapObjectType.GameMapMission.index

    override fun decompress() = GameMapMission(
        id,
        title,
        totalStar,
        challenges,
        tutorialsPrice,
        map,
        sprite,
        GridCoordinate(point),
        next,
        region,
    )
}
