package com.bytelegend.app.shared.objects

/**
 * GameMapObject are objects that are easier to draw in Tiled than in code,
 * for example, a point, a region, etc.
 */
interface GameMapObject {
    val id: String
    val layer: Int
    val type: GameMapObjectType

    fun compress(): CompressedGameMapObject {
        throw UnsupportedOperationException("${this::class} doesn't support compress operation")
    }
}

interface CompressedGameMapObject {
    val id: String
    val layer: Int
    val type: Int

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

    GameMapDynamicSprite(5);

    companion object {
        fun fromIndex(index: Int): GameMapObjectType = values()[index - 1]
    }
}

fun List<CompressedGameMapObject>.decompress(): List<GameMapObject> {
    val ret = map { it.decompress() }
    val idToCompressedRegion = this.filter { it.type == GameMapObjectType.GameMapRegion.index }
        .map { it.id to (it as CompressedGameMapRegion) }
        .toMap()
    val idToRegion = ret.filter { it.type == GameMapObjectType.GameMapRegion }
        .map { it.id to (it as GameMapRegion) }
        .toMap()
    idToRegion.values.forEach { region ->
        idToCompressedRegion.getValue(region.id).next
            .forEach { nextRegionId ->
                ((region.nextRegions) as MutableList).add(idToRegion.getValue(nextRegionId))
            }
    }
    return ret
}

fun List<GameMapObject>.compress(): List<CompressedGameMapObject> {
    return map { it.compress() }
}
