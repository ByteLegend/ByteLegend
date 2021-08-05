package com.bytelegend.app.shared.objects

import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.annotations.JsonIgnore
import kotlinx.serialization.Serializable

@Serializable
data class CompressedGameMapRegion(
    override val id: String,
    val center: List<Int>,
    val vertices: List<List<Int>>,
    val next: List<String>
) : CompressedGameMapObject {
    override val type: Int = GameMapObjectType.GameMapRegion.index
    @get:JsonIgnore
    override val layer: Int = 0

    override fun decompress() = GameMapRegion(
        id,
        PixelCoordinate(center),
        vertices.map { PixelCoordinate(it) },
        next
    )
}

class GameMapRegion(
    override val id: String,
    val center: PixelCoordinate,
    val vertices: List<PixelCoordinate>,
    val next: List<String>
) : GameMapObject, GameObject {
    override val layer: Int = 0
    override val type: GameMapObjectType = GameMapObjectType.GameMapRegion
    override val roles: Set<String> = setOf(GameObjectRole.MapRegion.toString())

    override fun compress() = CompressedGameMapRegion(
        id,
        center.compress(),
        vertices.map { it.compress() },
        next
    )
}
