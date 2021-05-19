package com.bytelegend.app.shared.objects

import com.bytelegend.app.shared.GridCoordinate
import kotlinx.serialization.Serializable

fun defaultMapEntranceId(srcMapId: String, destMapId: String) = "$srcMapId-$destMapId-entrance"

fun defaultMapEntrancePointId(mapEntranceId: String) = "$mapEntranceId-point"

fun defaultMapEntranceDestinationId(mapEntranceId: String) = "$mapEntranceId-destination"

fun defaultMapEntrancePointId(srcMapId: String, destMapId: String) = defaultMapEntrancePointId(defaultMapEntranceId(srcMapId, destMapId))


class GameMapPoint(
    override val id: String,
    override val layer: Int,
    val point: GridCoordinate
) : GameMapObject, GameObject {
    override val type: GameMapObjectType = GameMapObjectType.GameMapPoint
    override val roles: Set<String> = setOf(GameObjectRole.MapPoint.toString())

    override fun compress() = CompressedGameMapPoint(id, layer, point.compress())
}

@Serializable
data class CompressedGameMapPoint(
    override val id: String,
    override val layer: Int,
    val point: List<Int>
) : CompressedGameMapObject {
    override val type: Int = GameMapObjectType.GameMapPoint.index

    override fun decompress() = GameMapPoint(id, layer, GridCoordinate(point))
}
