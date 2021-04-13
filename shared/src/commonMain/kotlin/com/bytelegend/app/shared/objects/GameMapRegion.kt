package com.bytelegend.app.shared.objects

import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate

data class CompressedGameMapRegion(
    override val id: String,
    override val layer: Int,
    val vertices: List<List<Int>>
) : CompressedGameMapObject {
    override val type: Int = GameMapObjectType.GameMapRegion.index

    override fun decompress() = GameMapRegion(
        id,
        layer,
        vertices.map { PixelCoordinate(it) },
    )
}

class GameMapRegion(
    override val id: String,
    override val layer: Int,
    val vertices: List<PixelCoordinate>
) : GameMapObject, GameObject {
    override val type: GameMapObjectType = GameMapObjectType.GameMapRegion
    override val roles: Set<GameObjectRole> = setOf(GameObjectRole.MapRegion)

    override fun compress() = CompressedGameMapRegion(
        id,
        layer,
        vertices.map { it.compress() }
    )
}
