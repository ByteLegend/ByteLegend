package com.bytelegend.app.shared.objects

import com.bytelegend.app.shared.GridCoordinate

class GameMapPoint(
    override val id: String,
    override val layer: Int,
    val point: GridCoordinate
) : GameMapObject, GameObject {
    override val type: GameMapObjectType = GameMapObjectType.GameMapPoint
    override val roles: Set<GameObjectRole> = setOf(GameObjectRole.MapPoint)

    override fun compress() = CompressedGameMapPoint(id, layer, point.compress())
}

data class CompressedGameMapPoint(
    override val id: String,
    override val layer: Int,
    val point: List<Int>
) : CompressedGameMapObject {
    override val type: Int = GameMapObjectType.GameMapPoint.index

    override fun decompress() = GameMapPoint(id, layer, GridCoordinate(point))
}
