package com.bytelegend.app.shared.objects

import com.bytelegend.app.shared.GridCoordinate

data class CompressedGameMapRegion(
    override val id: String,
    override val layer: Int,
    val center: List<Int>,
    val vertices: List<List<Int>>,
    val next: List<String>
) : CompressedGameMapObject {
    override val type: Int = GameMapObjectType.GameMapRegion.index

    override fun decompress() = GameMapRegion(
        id,
        layer,
        GridCoordinate(center),
        vertices.map { GridCoordinate(it) },
        mutableListOf()
    )
}

class GameMapRegion(
    override val id: String,
    override val layer: Int,
    val center: GridCoordinate,
    val vertices: List<GridCoordinate>,
    val nextRegions: List<GameMapRegion>
) : GameMapObject, GameObject {
    override val type: GameMapObjectType = GameMapObjectType.GameMapRegion
    override val roles: Set<GameObjectRole> = setOf(GameObjectRole.MapRegion)

    override fun compress() = CompressedGameMapRegion(
        id,
        layer,
        center.compress(),
        vertices.map { it.compress() },
        nextRegions.map { it.id }
    )
}
