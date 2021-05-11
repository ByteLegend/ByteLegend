package com.bytelegend.utils

import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.entities.mission.MissionSpec
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

    /**
     * Read mission specs from game-data/missions and merge mission information on map (`MapMissionSpec`)
     */
    fun readAndMergeMissionSpecs(): List<MissionSpec> {
        val idToMapMission = tiledMap.readMissions().associateBy { it.id }
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

    fun readRegions() = tiledMap.readRegions()

    private fun TiledMap.readRawObjects(): List<GameMapObject> {
        return readCurves() +
            readTexts() +
            readRegions() +
            readPoints() +
            readMissions()
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
     * then "XRegionName" is its name
     */
    private fun TiledMap.readRegions(): List<GameMapRegion> = readObjects(TiledObjectType.GameMapRegion) { layer, obj ->
        GameMapRegion(
            obj.name,
            rawLayerIdToIndexMap.getValue(layer.id.toInt()),
            obj.getPolygonCoordinates()
        )
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

        val idToMissionDefinitions = missionDataReader.getMissionsOnMap(mapId).associateBy { it.id }
        return rawMissionObjects.map {
            // If this object is a tile, `gid` points to a tile id
            // If this object has "next", it has a property named "next"
            val next: Long? = it.properties.findPropertyOrNull("next")?.toLong()
            GameMapMission(
                it.name,
                idToMissionDefinitions.getValue(it.name).title,
                mapId,
                tileIdToSpriteIdMap.getValue(it.gid),
                it.toPoint(),
                emptyList(),
                tiledNumberIdToRawMissionObjects[next]?.name
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

    private fun List<TiledMap.Property>.findPropertyOrNull(name: String): String? {
        return firstOrNull { it.name == name }?.value
    }
}
