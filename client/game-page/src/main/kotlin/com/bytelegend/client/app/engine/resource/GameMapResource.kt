@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.client.app.engine.resource

import com.bytelegend.app.shared.CompressedGameMap
import com.bytelegend.app.shared.ConstantPoolEntry
import com.bytelegend.app.shared.ConstantPoolType
import com.bytelegend.app.shared.GridSize
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.RawGameMap
import com.bytelegend.app.shared.mapToList
import com.bytelegend.app.shared.mapToList4
import com.bytelegend.app.shared.objects.CompressedGameMapCurve
import com.bytelegend.app.shared.objects.CompressedGameMapDynamicObject
import com.bytelegend.app.shared.objects.CompressedGameMapMission
import com.bytelegend.app.shared.objects.CompressedGameMapObject
import com.bytelegend.app.shared.objects.CompressedGameMapPoint
import com.bytelegend.app.shared.objects.CompressedGameMapRegion
import com.bytelegend.app.shared.objects.CompressedGameMapText
import com.bytelegend.app.shared.objects.GameMapObjectType
import kotlinx.coroutines.await
import kotlinx.serialization.json.Json
import org.w3c.fetch.Response

class GameMapResource(
    override val id: String,
    url: String
) : AjaxResource<RawGameMap>(id, url) {
    override suspend fun decode(response: Response): RawGameMap {
        val jsCompressedGameMap: dynamic = JSON.parse(response.text().await())
        val constantPool: List<ConstantPoolEntry> = (jsCompressedGameMap.constantPool as Array<dynamic>).map {
            val type = ConstantPoolType.ofIndex(it.t as Int)
            val valueJson = JSON.stringify(it.v)
            val value = Json.decodeFromString(type.serializer, valueJson)

            type.of(value)
        }
        return CompressedGameMap(
            jsCompressedGameMap.id as String,
            GridSize(jsCompressedGameMap.size.width as Int, jsCompressedGameMap.size.height as Int),
            PixelSize(jsCompressedGameMap.tileSize.width as Int, jsCompressedGameMap.tileSize.height as Int),
            constantPool,
            (jsCompressedGameMap.tiles as Array<Int>).toList(),
            readObjects(jsCompressedGameMap.compressedObjects as Array<dynamic>)
        ).decompress()
    }
}

private fun readObjects(objects: Array<dynamic>): List<CompressedGameMapObject> = objects.map {
    when (GameMapObjectType.fromIndex(it.type)) {
        GameMapObjectType.GameMapText -> CompressedGameMapText(
            it.id,
            it.type,
            it.layer,
            (it.coordinate as Array<Int>).toList(),
            it.fontSize,
            it.rotation
        )
        GameMapObjectType.GameMapRegion -> CompressedGameMapRegion(
            it.id,
            (it.center as Array<Int>).toList(),
            (it.vertices as Array<Array<Int>>).mapToList { it },
            it.next
        )
        GameMapObjectType.GameMapCurve -> CompressedGameMapCurve(
            it.id,
            it.layer,
            (it.points as Array<Array<Int>>).mapToList { it }
        )
        GameMapObjectType.GameMapPoint -> CompressedGameMapPoint(
            it.id,
            it.layer,
            (it.gridCoordinate as Array<Int>).toList()
        )
        GameMapObjectType.GameMapDynamicSprite -> CompressedGameMapDynamicObject(
            it.id,
            (it.frames as Array<Array<Array<Array<Int>>>>).mapToList4 { it }
        )
        GameMapObjectType.GameMapMission -> CompressedGameMapMission(
            it.id,
            it.title,
            it.totalStar,
            (it.challenges as Array<String>).toList(),
            it.map,
            it.sprite,
            (it.point as Array<Int>).toList(),
            (it.next as Array<String>).toList(),
            it.region
        )
        else -> throw IllegalStateException("Unrecognized type: ${it.type}")
    }
}
