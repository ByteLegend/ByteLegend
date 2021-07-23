package com.bytelegend.utils

import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.entities.mission.MissionSpec
import com.bytelegend.app.shared.objects.GameMapCurve
import com.bytelegend.app.shared.objects.GameMapMission
import com.bytelegend.app.shared.objects.GameMapObject
import com.bytelegend.app.shared.objects.GameMapPoint
import com.bytelegend.app.shared.objects.GameMapRegion
import com.bytelegend.app.shared.objects.GameMapText
import com.bytelegend.github.utils.generated.TiledMap
import java.awt.Point
import java.awt.Polygon
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
     * A reference to Mission defined in `game-data/` directory.
     */
    GameMapMission
}

class TiledObjectReader(
    private val mapId: String,
    private val tiledMap: TiledMap,
    private val missionDataReader: MissionDataReader,
    private val rawLayerIdToIndexMap: Map<Int, Int>
) {
    private val tileSize = tiledMap.getTileSize()
    val regions: List<GameMapRegion> by lazy {
        tiledMap.readRegions()
    }
    val missions: List<GameMapMission> by lazy {
        tiledMap.readMissions()
    }
    val points: List<GameMapPoint> by lazy {
        tiledMap.readPoints()
    }

    /**
     * Read mission specs from game-data/missions and merge mission information on map (`MapMissionSpec`)
     */
    fun readAndMergeMissionSpecs(): List<MissionSpec> {
        val idToMapMission = missions.associateBy { it.id }
        return missionDataReader.getMissionsOnMap(mapId).map {
            require(it.mapMissionSpec == null) {
                "mapMissionSpec found in ${it.id}!"
            }
            it.copy(mapMissionSpec = idToMapMission.getValue(it.id))
        }
    }

    fun readRawObjects(): List<GameMapObject> {
        tiledMap.verify()
        return tiledMap.readRawObjects()
    }

    private fun TiledMap.readRawObjects(): List<GameMapObject> {
        return readCurves() +
            readTexts() +
            regions +
            points +
            missions
    }

    private fun TiledMap.verify(): TiledMap {
        layers.flatMap { it.objects }.forEach {
            TiledObjectType.valueOf(it.type)
        }
        return this
    }

    private fun TiledMapObject.toPixelPoint() = PixelCoordinate(x.toInt(), y.toInt())

    private fun TiledMapObject.getPolygonCoordinates(): List<PixelCoordinate> {
        return polygon.map { relativePoint ->
            // Relative point to absolute pixel point
            toPixelPoint() + PixelCoordinate(relativePoint.x.toInt(), relativePoint.y.toInt())
        }
    }

    /**
     * Convention: if a region name is "XRegion",
     * then "XRegionName" is its name,
     * "XRegionCenterPoint" is its center point
     */
    private fun TiledMap.readRegions(): List<GameMapRegion> {
        // Two passes,
        // 1st, generate all instances
        val tiledIdToRegionId = mutableMapOf<String, String>()
        readObjects(TiledObjectType.GameMapRegion) { _, obj ->
            tiledIdToRegionId[obj.id.toString()] = obj.name
        }
        // 2nd, populate the "next"
        return readObjects(TiledObjectType.GameMapRegion) { _, obj ->
            val vertices = obj.getPolygonCoordinates()
            val nextId = obj.properties.findPropertyOrNull("next")
            GameMapRegion(
                obj.name,
                vertices.getOrCalculateCenterPoint(obj.name),
                vertices,
                tiledIdToRegionId[nextId]
            )
        }
    }

    private fun List<PixelCoordinate>.getOrCalculateCenterPoint(regionId: String): PixelCoordinate {
        val existingCenterPoint = points.firstOrNull { it.id == "${regionId}CenterPoint" }
        if (existingCenterPoint != null) {
            return existingCenterPoint.gridCoordinate * tileSize
        } else {
            return PixelCoordinate(
                sumOf { it.x } / size,
                sumOf { it.y } / size
            )
        }
    }

    private fun TiledMapObject.toPoint() = PixelCoordinate(x.toInt(), y.toInt()) / tileSize

    private fun <T> TiledMap.readObjects(type: TiledObjectType, fn: (TiledMapLayer, TiledMapObject) -> T): List<T> = layers.flatMap { layer ->
        layer.objects.filter { it.type == type.toString() }
            .map { fn(layer, it) }
    }

    private fun TiledMap.readMissions(): List<GameMapMission> {
        val rawMissionObjects: List<TiledMapObject> = readObjects(TiledObjectType.GameMapMission) { _, obj -> obj }
        val tiledNumberIdToRawMissionObjects: Map<Long, TiledMapObject> = rawMissionObjects.associateBy { it.id }

        val dynamicSpriteLayers: List<TiledMap.Layer2> = tiledMap.layers.find { it.type == "group" && it.name == "DynamicSprites" }?.layers
            ?: emptyList()
        // key: the tile id (gid)
        // value: the dynamic sprite id
        val tileIdToSpriteIdMap: Map<Long, String> = dynamicSpriteLayers.flatMap { layer ->
            // find out all non-zero id in this layer, and map it to the layer name (dynamic sprite id)
            val nonZeroIds = layer.data.filter { it != 0L }
            nonZeroIds.map { it to layer.name }
        }.toMap()

        val idToMissionSpecs = missionDataReader.getMissionsOnMap(mapId).associateBy { it.id }
        val idToPolygons = regions.associate { it.id to it.toPolygon() }

        return rawMissionObjects.map {
            val gridCoordinate = it.toPoint()
            // If this object is a tile, `gid` points to a tile id
            // Optionally, this object may have next1/next2/.../nextN, which point to the next objects
            val nextIds: List<String> = it.properties.filter { it.name.startsWith("next") }
                .sortedBy { it.name.substringAfter("next").toInt() }
                .map { it.value }
            val regionId: String? = idToPolygons.entries.firstOrNull {
                it.value.contains(Point(gridCoordinate.x * tileSize.width, gridCoordinate.y * tileSize.height))
            }?.key

            GameMapMission(
                it.name,
                idToMissionSpecs.getValue(it.name).title,
                idToMissionSpecs.getValue(it.name).challenges.sumOf { it.star },
                idToMissionSpecs.getValue(it.name).challenges.map { it.id },
                mapId,
                tileIdToSpriteIdMap.getValue(it.gid),
                it.toPoint(),
                nextIds.map { nextId -> tiledNumberIdToRawMissionObjects.getValue(nextId.toLong()).name },
                regionId
            )
        }
    }

    private fun TiledMap.readPoints(): List<GameMapPoint> = readObjects(TiledObjectType.GameMapPoint) { layer, obj ->
        GameMapPoint(
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

    private fun List<TiledMap.Property>.findPropertyOrNull(name: String): String? {
        return firstOrNull { it.name == name }?.value
    }

    private fun GameMapRegion.toPolygon(): Polygon {
        return Polygon(
            vertices.map { it.x }.toIntArray(),
            vertices.map { it.y }.toIntArray(),
            vertices.size
        )
    }
}
