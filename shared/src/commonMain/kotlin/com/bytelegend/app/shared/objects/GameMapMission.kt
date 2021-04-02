package com.bytelegend.app.shared.objects

import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.entities.MissionType

/**
 * GameMapMission doesn't contain all information of missions, just partial of them
 * (id, title) to display them. The whole information is loaded via AJAX.
 */
class GameMapMission(
    override val id: String,
    val missionType: MissionType,
    val title: String,
    val point: GridCoordinate
) : GameMapObject, GameObject {
    override val layer: Int = 0
    override val roles: Set<GameObjectRole> = setOf(GameObjectRole.Mission)
    override val type: GameMapObjectType = GameMapObjectType.GameMapMission
    override fun compress() = CompressedGameMapMission(
        id,
        missionType.toString(),
        title,
        point.toCompressedList()
    )
}

data class CompressedGameMapMission(
    override val id: String,
    val missionType: String,
    val title: String,
    val point: List<Int>
) : CompressedGameMapObject {
    override val layer: Int = 0
    override val type: Int = GameMapObjectType.GameMapMission.index

    override fun decompress() = GameMapMission(
        id,
        MissionType.valueOf(missionType),
        title,
        GridCoordinate(point)
    )
}
