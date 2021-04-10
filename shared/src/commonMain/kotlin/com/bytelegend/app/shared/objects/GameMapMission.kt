package com.bytelegend.app.shared.objects

import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.entities.MissionType

/**
 * GameMapMission doesn't contain all information of missions, just partial of them
 * (id, title) to display them. The whole information is loaded via AJAX.
 */
class GameMapMission(
    override val id: String,
    val title: String,
    val sprite: String,
    val missionType: MissionType,
    val point: GridCoordinate,
    // Next mission id
    val next: String?
) : GameMapObject, GameObject {
    override val layer: Int = 0
    override val roles: Set<GameObjectRole> = setOf(GameObjectRole.Mission)
    override val type: GameMapObjectType = GameMapObjectType.GameMapMission
    override fun compress() = CompressedGameMapMission(
        id,
        title,
        sprite,
        missionType.toString(),
        point.toCompressedList(),
        next
    )
}

data class CompressedGameMapMission(
    override val id: String,
    val title: String,
    val sprite: String,
    val missionType: String,
    val point: List<Int>,
    val next: String?
) : CompressedGameMapObject {
    override val layer: Int = 0
    override val type: Int = GameMapObjectType.GameMapMission.index

    override fun decompress() = GameMapMission(
        id,
        title,
        sprite,
        MissionType.valueOf(missionType),
        GridCoordinate(point),
        next
    )
}
