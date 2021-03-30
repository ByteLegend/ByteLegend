package com.bytelegend.app.shared.objects

import com.bytelegend.app.shared.annotations.JsonIgnore
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PLAYER_LAYER
import com.bytelegend.app.shared.map

/**
 * Dynamic objects aren't displayed on the map directly, but added by game script
 * dynamically. It might have outer glow effect, switch animation frames on demand, etc.
 */
class GameMapDynamicSprite(
    override val id: String,
    val topLeftCorner: GridCoordinate,
    val frames: List<List<List<GridCoordinate>>>
) : GameMapObject, GameObject {
    override val layer: Int = PLAYER_LAYER + 1
    override val roles: Set<GameObjectRole> = setOf()
    override val type: GameMapObjectType = GameMapObjectType.GameMapDynamicSprite
    // 3 dimension:
    // first/second dimension: the y/x of tiles because a sprite can cross multiple tiles.
    // third dimension: the frame coordinate of tile in tileset
    val width = frames[0].size
    val height = frames.size

    override fun compress() = CompressedGameMapDynamicObject(
        id,
        topLeftCorner.toCompressedList(),
        frames.map {
            val tmp = mutableListOf<List<Int>>()
            it.forEach {
                tmp.add(it.toCompressedList())
            }
            tmp
        }
    )
}

data class CompressedGameMapDynamicObject(
    override val id: String,
    val topLeftCorner: List<Int>,
    val frames: List<List<List<List<Int>>>>
) : CompressedGameMapObject {
    override val layer: Int = PLAYER_LAYER + 1
        @JsonIgnore get
    override val type: Int = GameMapObjectType.GameMapDynamicSprite.index

    override fun decompress() = GameMapDynamicSprite(
        id,
        GridCoordinate(topLeftCorner),
        frames.map {
            val tmp = mutableListOf<GridCoordinate>()
            it.forEach {
                tmp.add(GridCoordinate(it))
            }
            tmp
        }
    )
}
