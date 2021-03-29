package com.bytelegend.app.shared.objects

import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.entities.MissionDefinition
import com.bytelegend.app.shared.entities.MissionType

class GameMapMissionDefinition(
    val missionDefinition: MissionDefinition,
    val point: GridCoordinate
) : GameMapObject, GameObject {
    override val id: String = missionDefinition.id
    override val layer: Int = 0
    override val roles: Set<GameObjectRole> = setOf(GameObjectRole.MissionDefinition)
    override val type: GameMapObjectType = GameMapObjectType.GameMapMissionDefinition
    override fun compress() = CompressedGameMapMissionDefinition(
        id,
        missionDefinition.type.toString(),
        missionDefinition.star,
        missionDefinition.data,
        point.toCompressedList()
    )
}

data class CompressedGameMapMissionDefinition(
    override val id: String,
    val missionType: String,
    val star: Int,
    val data: String,
    val point: List<Int>
) : CompressedGameMapObject {
    override val layer: Int = 0
    override val type: Int = GameMapObjectType.GameMapMissionDefinition.index

    override fun decompress() = GameMapMissionDefinition(
        MissionDefinition(
            id,
            MissionType.valueOf(missionType),
            star,
            data
        ),
        GridCoordinate(point)
    )
}
