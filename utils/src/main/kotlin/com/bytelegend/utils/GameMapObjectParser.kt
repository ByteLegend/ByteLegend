package com.bytelegend.utils

import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.objects.GameMapCurve
import com.bytelegend.app.shared.objects.GameMapObject
import com.bytelegend.app.shared.objects.GameMapPoint
import com.bytelegend.app.shared.objects.GameMapRegion
import com.bytelegend.app.shared.objects.GameMapText
import com.bytelegend.github.utils.generated.TiledMap
import com.bytelegend.utils.TiledObjectType.GamePoint
import com.bytelegend.github.utils.generated.TiledMap.Object as TiledObject

/**
 * The "Type" property of an object on Tiled map.
 */
enum class TiledObjectType {
    /**
     * An i18n text to be displayed on the map.
     * The object's name is the id of i18n text
     */
    GameLocationText,

    /**
     * A polygon on Tiled map.
     * The object's name is the region id.
     */
    GameMapRegion,

    /**
     * A point on the curve. It's name is the curve id.
     */
    CurvePoint,

    /**
     * A point on the map. Game script can query the point
     * and add objects on the point.
     */
    GamePoint,
}

class TiledObjectReader(
    private val tiledMap: TiledMap,
    private val rawLayerIdToIndexMap: Map<Int, Int>
) {
    fun readRawObjects(): List<GameMapObject> {
        tiledMap.verify()
        return tiledMap.readRawObjects()
    }

    private fun TiledMap.verify(): TiledMap {
        layers.flatMap { it.objects }.forEach {
            TiledObjectType.valueOf(it.type)
        }
        return this
    }

    private fun TiledObject.toPixelPoint() = PixelCoordinate(x.toInt(), y.toInt())

    private fun TiledObject.getPolygonCoordinates(tileSize: PixelSize): List<GridCoordinate> {
        return polygon.map { relativePoint ->
            // Relative point to absolute pixel point
            val absolutePoint = toPixelPoint() + PixelCoordinate(relativePoint.x.toInt(), relativePoint.y.toInt())
            absolutePoint / tileSize
        }
    }

    /**
     * Convention: if a region name is "XRegion",
     * then "XRegionName" is its name
     * "XRegionCenterPoint" is its center point
     */
    private fun TiledMap.readRegions(): List<GameMapRegion> {
        val layerObjectPairs = layers.flatMap { layer ->
            layer.objects.map { rawLayerIdToIndexMap.getValue(layer.id.toInt()) to it }
        }
        val objects = layers.flatMap { it.objects }

        val layerAndRegions = layerObjectPairs.filter { it.second.type == TiledObjectType.GameMapRegion.toString() }
        val nameToPoint: Map<String, TiledObject> = objects
            .filter { it.type == GamePoint.toString() }
            .map { it.name to it }
            .toMap()
        val idToPoint: Map<Long, TiledObject> = objects
            .filter { it.type == GamePoint.toString() }
            .map { it.id to it }
            .toMap()


        // First pass, instantiate all regions
        val ret = layerAndRegions.map {
            val regionId = it.second.name
            val centerPoint = nameToPoint.getValue("${regionId}CenterPoint")

            GameMapRegion(
                regionId,
                it.first,
                centerPoint.toPixelPoint() / getTileSize(),
                it.second.getPolygonCoordinates(getTileSize()),
                mutableListOf()
            )
        }
        val idToRegionObject = ret.map { it.id to it }.toMap()
        // Second pass, map the correct region into
        ret.forEach { gameMapRegionObject ->
            nameToPoint.getValue("${gameMapRegionObject.id}CenterPoint")
                .properties
                .filter { it.name == "next" }
                .map { idToPoint.getValue(it.value.toLong()).name.substringBefore("CenterPoint") }
                .forEach {
                    (gameMapRegionObject.nextRegions as MutableList).add(idToRegionObject.getValue(it))
                }
        }
        return ret
    }

    private fun TiledMap.readRawObjects(): List<GameMapObject> {
        return readCurves() + readTexts() + readRegions() + readPoints()
    }

    private fun TiledMap.readPoints(): List<GameMapObject> {
        return layers.flatMap { layer ->
            layer.objects.filter { it.type == GamePoint.toString() }
                .map {
                    GameMapPoint(
                        it.name,
                        rawLayerIdToIndexMap.getValue(layer.id.toInt()),
                        PixelCoordinate(it.x.toInt(), it.y.toInt()) / getTileSize()
                    )
                }
        }
    }

    private fun TiledMap.readCurves(): List<GameMapCurve> {
        return layers.flatMap { layer ->
            layer.objects.filter { it.type == TiledObjectType.CurvePoint.toString() }
                .groupBy { it.name }
                .map { it.value.toCurveObject(it.key, rawLayerIdToIndexMap.getValue(layer.id.toInt())) }
        }
    }

    private fun TiledMap.readTexts(): List<GameMapText> {
        return layers.flatMap { layer ->
            layer.objects
                .filter { it.type == TiledObjectType.GameLocationText.toString() }
                .map { it.toTextObject(rawLayerIdToIndexMap.getValue(layer.id.toInt())) }
        }
    }

    private fun TiledMap.Object.toTextObject(layerIndex: Int): GameMapText = GameMapText(
        name,
        layerIndex,
        PixelCoordinate(x.toInt(), y.toInt()),
        text.pixelsize.toInt(),
        rotation.toInt()
    )

    private fun List<TiledMap.Object>.toCurveObject(curveId: String, layerIndex: Int): GameMapCurve {
        require(size >= 3) { "A curve must have at least 3 points!" }

        return GameMapCurve(
            curveId,
            layerIndex,
            this.sortedBy { it.properties.findProperty("order").toDouble() }
                .map { PixelCoordinate(it.x.toInt(), it.y.toInt()) }
        )
    }

    private fun List<TiledMap.Property>.findProperty(name: String): String {
        return first { it.name == name }.value
    }
}
