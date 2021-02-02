package com.bytelegend.app.shared.objects

import com.bytelegend.app.shared.PixelCoordinate

class GameMapCurve(
    override val id: String,
    override val layer: Int,
    val points: List<PixelCoordinate>
) : GameMapObject, GameObject {
    override val type: GameMapObjectType = GameMapObjectType.GameMapCurve
    override val roles: Set<GameObjectRole> = setOf(GameObjectRole.MapCurve)
    override fun compress() = CompressedGameMapCurve(
        id,
        layer,
        points.map { it.compress() }
    )
}

data class CompressedGameMapCurve(
    override val id: String,
    override val layer: Int,
    val points: List<List<Int>>
) : CompressedGameMapObject {
    override val type: Int = GameMapObjectType.GameMapCurve.index

    override fun decompress() = GameMapCurve(
        id,
        layer,
        points.map { PixelCoordinate(it) }
    )
}
