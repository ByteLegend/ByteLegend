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
    val title: String,
    val totalStar: Int,
    val challenges: List<String>,
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
        map,
        sprite,
        GridCoordinate(point),
        next,
        region,
    )
}
