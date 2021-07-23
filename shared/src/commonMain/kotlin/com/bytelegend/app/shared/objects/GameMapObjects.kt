package com.bytelegend.app.shared.objects

import kotlinx.serialization.Polymorphic

/**
 * GameMapObject are objects that are easier to draw in Tiled than in code,
 * for example, a point, a region, etc.
 */
interface GameMapObject {
    val id: String
    val type: GameMapObjectType
    val layer: Int

    fun compress(): CompressedGameMapObject {
        throw UnsupportedOperationException("${this::class} doesn't support compress operation")
    }
}

@Polymorphic
interface CompressedGameMapObject {
    val id: String
    val type: Int
    val layer: Int

    fun decompress(): GameMapObject
}

enum class GameMapObjectType(
    val index: Int
) {
    /**
     * A large text displayed on the map. For example, the map region name.
     */
    GameMapText(1),

    /**
     * A region on map (polygon in Tiled).
     */
    GameMapRegion(2),

    /**
     * A point on a map.
     */
    GameMapPoint(3),

    /**
     * Ordered series of points, to form a curve.
     */
    GameMapCurve(4),

    GameMapDynamicSprite(5),
    GameMapMission(6);

    companion object {
        fun fromIndex(index: Int): GameMapObjectType = values()[index - 1]
    }
}
