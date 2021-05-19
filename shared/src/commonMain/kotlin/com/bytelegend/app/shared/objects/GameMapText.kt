package com.bytelegend.app.shared.objects

import com.bytelegend.app.shared.PixelCoordinate
import kotlinx.serialization.Serializable

class GameMapText(
    override val id: String,
    override val layer: Int,
    val coordinate: PixelCoordinate,
    val fontSize: Int,
    val rotation: Int
) : GameMapObject, GameObject {
    override val type: GameMapObjectType = GameMapObjectType.GameMapText
    override val roles: Set<String> = setOf(GameObjectRole.MapText.toString())

    override fun compress() = CompressedGameMapText(
        id,
        layer,
        type.index,
        coordinate.compress(),
        fontSize,
        rotation
    )
}

@Serializable
data class CompressedGameMapText(
    override val id: String,
    override val layer: Int,
    override val type: Int,
    // [1,2] -> {x:1,y:2}
    val coordinate: List<Int>,
    val fontSize: Int,
    val rotation: Int
) : CompressedGameMapObject {
    override fun decompress() = GameMapText(
        id,
        layer,
        PixelCoordinate(coordinate),
        fontSize,
        rotation
    )
}
