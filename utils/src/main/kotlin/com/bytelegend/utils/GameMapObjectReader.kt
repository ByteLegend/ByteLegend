package com.bytelegend.utils

import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.objects.GameMapCheckpoint
import com.bytelegend.app.shared.objects.GameMapCurve
import com.bytelegend.app.shared.objects.GameMapMission
import com.bytelegend.app.shared.objects.GameMapObject
import com.bytelegend.app.shared.objects.GameMapRegion
import com.bytelegend.app.shared.objects.GameMapText
import com.bytelegend.github.utils.generated.TiledMap
import com.bytelegend.github.utils.generated.TiledMap.Layer as TiledMapLayer
import com.bytelegend.github.utils.generated.TiledMap.Object as TiledMapObject

/**
 * The "Type" property of an object on Tiled map.
 */
enum class TiledObjectType {
    /**
     * An i18n text to be displayed on the map.
     * The object's name is the id of i18n text
     */
    GameMapText,

    /**
     * A polygon on Tiled map.
     * The object's name is the region id.
     */
    GameMapRegion,

    /**
     * A point on the curve. It's name is the curve id.
     */
    GameMapCurvePoint,

    /**
     * A point on the map. Game script can query the point
     * and add objects on the point.
     */
    GameMapPoint,

    /**
     * A reference to Checkpoint defined in `game-data/` directory.
     */
    GameMapCheckpoint,

    /**
     * A reference to Mission defined in `game-data/` directory.
     */
    GameMapMission
}

class TiledObjectReader(
    private val mapId: String,
    private val tiledMap: TiledMap,
    private val checkpointDataReader: CheckpointDataReader,
    private val rawLayerIdToIndexMap: Map<Int, Int>
) {
    private val tileSize = tiledMap.getTileSize()
    fun readRawObjects(): List<GameMapObject> {
        tiledMap.verify()
        return tiledMap.readRawObjects()
    }

    private fun TiledMap.readRawObjects(): List<GameMapObject> {
        return readCurves() +
            readTexts() +
            readRegions() +
            readPoints() +
            readCheckpoints() +
            readMissions()
    }

    private fun TiledMap.verify(): TiledMap {
        layers.flatMap { it.objects }.forEach {
            TiledObjectType.valueOf(it.type)
        }
        return this
    }

    private fun TiledMapObject.toPixelPoint() = PixelCoordinate(x.toInt(), y.toInt())

    private fun TiledMapObject.getPolygonCoordinates(tileSize: PixelSize): List<GridCoordinate> {
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
        val nameToPoint: Map<String, TiledMapObject> = objects
            .filter { it.type == TiledObjectType.GameMapPoint.toString() }
            .map { it.name to it }
            .toMap()
        val idToPoint: Map<Long, TiledMapObject> = objects
            .filter { it.type == TiledObjectType.GameMapPoint.toString() }
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

    private fun TiledMapObject.toPoint() = PixelCoordinate(x.toInt(), y.toInt()) / tileSize

    private fun <T> TiledMap.readObjects(type: TiledObjectType, fn: (TiledMapLayer, TiledMapObject) -> T): List<T> = layers.flatMap { layer ->
        layer.objects.filter { it.type == type.toString() }
            .map { fn(layer, it) }
    }

    private fun TiledMap.readCheckpoints(): List<GameMapObject> {
        val idToRawCheckpoints: Map<String, TiledMapObject> =
            readObjects(TiledObjectType.GameMapCheckpoint) { _, obj -> obj }
                .map { it.name to it }
                .toMap()
        return checkpointDataReader.getCheckpointsOnMap(mapId).map {
            GameMapCheckpoint(
                it.id,
                it.title,
                idToRawCheckpoints[it.id]?.toPoint() ?: GridCoordinate(-1, -1)
            )
        }
    }

    private fun TiledMap.readMissions(): List<GameMapObject> {
        val idToRawMissions: Map<String, TiledMapObject> =
            readObjects(TiledObjectType.GameMapMission) { _, obj -> obj }
                .map { it.name to it }
                .toMap()
        return checkpointDataReader.getMissionsOnMap(mapId).map {
            GameMapMission(
                it.id,
                it.type,
                it.title,
                idToRawMissions[it.id]?.toPoint() ?: GridCoordinate(-1, -1)
            )
        }
    }

    private fun TiledMap.readPoints(): List<GameMapObject> = readObjects(TiledObjectType.GameMapPoint) { layer, obj ->
        com.bytelegend.app.shared.objects.GameMapPoint(
            obj.name,
            rawLayerIdToIndexMap.getValue(layer.id.toInt()),
            obj.toPoint()
        )
    }

    private fun TiledMap.readCurves(): List<GameMapCurve> {
        return layers.flatMap { layer ->
            layer.objects.filter { it.type == TiledObjectType.GameMapCurvePoint.toString() }
                .groupBy { it.name }
                .map { it.value.toCurveObject(it.key, rawLayerIdToIndexMap.getValue(layer.id.toInt())) }
        }
    }

    private fun TiledMap.readTexts(): List<GameMapObject> = readObjects(TiledObjectType.GameMapText) { layer, obj ->
        obj.toTextObject(rawLayerIdToIndexMap.getValue(layer.id.toInt()))
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
