package com.bytelegend.app.shared.objects

import com.bytelegend.app.shared.GridCoordinate

// Dummy value for the checkpoints/missions which are invisible on map
// Far from canvas so it will not be displayed anyway
val INVISIBLE_COORDINATE = GridCoordinate(-9, -9)

/**
 * GameMapMission doesn't contain all information of missions, just partial of them
 * (id, title) to display them. The whole information is loaded via AJAX.
 */
class GameMapCheckpoint(
    override val id: String,
    val title: String,
    val point: GridCoordinate
) : GameMapObject, GameObject {
    override val layer: Int = 0
    override val roles: Set<GameObjectRole> = setOf(GameObjectRole.Checkpoint)
    override val type: GameMapObjectType = GameMapObjectType.GameMapCheckpoint
    override fun compress() = CompressedGameMapCheckpoint(
        id,
        title,
        point.toCompressedList()
    )
}

data class CompressedGameMapCheckpoint(
    override val id: String,
    val title: String,
    val point: List<Int>
) : CompressedGameMapObject {
    override val layer: Int = 0
    override val type: Int = GameMapObjectType.GameMapCheckpoint.index

    override fun decompress() = GameMapCheckpoint(
        id,
        title,
        GridCoordinate(point)
    )
}
