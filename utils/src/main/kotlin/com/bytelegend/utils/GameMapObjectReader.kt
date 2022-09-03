/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bytelegend.utils

import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.entities.mission.MissionSpec
import com.bytelegend.app.shared.objects.GameMapCurve
import com.bytelegend.app.shared.objects.GameMapEntrancePoint
import com.bytelegend.app.shared.objects.GameMapMission
import com.bytelegend.app.shared.objects.GameMapObject
import com.bytelegend.app.shared.objects.GameMapObjectType
import com.bytelegend.app.shared.objects.GameMapPoint
import com.bytelegend.app.shared.objects.GameMapRegion
import com.bytelegend.app.shared.objects.GameMapText
import com.bytelegend.github.utils.generated.TiledMap
import java.awt.Point
import java.awt.Polygon
import com.bytelegend.github.utils.generated.TiledMap.Layer as TiledMapLayer
import com.bytelegend.github.utils.generated.TiledMap.Object as TiledMapObject

enum class SpecialMapGroup(val tilesetImgDir: String, val tilesetJsonDir: String) {
    DynamicSprites("tilesets", "tileset-jsons"),
    Animations("animations", "animation-jsons")
}

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
    GameMapMission,
    GameMapEntrancePoint,
    GameMapEntranceDestinationPoint;
}

class TiledObjectReader(
    private val mapId: String,
    private val tiledMap: TiledMap,
    private val missionDataReader: MissionDataReader,
    private val rawLayerIdToIndexMap: Map<Int, Int>,
    private val allMapIds: Set<String> = emptySet()
) {
    private val tileSize = tiledMap.getTileSize()
    val regions: List<GameMapRegion> by lazy {
        readRegions()
    }
    val missions: List<GameMapMission> by lazy {
        readMissions()
    }
    val points: List<GameMapPoint> by lazy {
        readPoints()
    }
    val entrancePoints: List<GameMapEntrancePoint> by lazy {
        readMapEntrancePoints(TiledObjectType.GameMapEntrancePoint) +
            readMapEntrancePoints(TiledObjectType.GameMapEntranceDestinationPoint)
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
            missions +
            points +
            entrancePoints
    }

    private fun TiledMap.verify(): TiledMap {
        layers.flatMap { it.objects }.forEach {
            require(it.type.isNotBlank()) {
                "type not set for object with id ${it.id} on map $mapId"
            }
            try {
                TiledObjectType.valueOf(it.type)
            } catch (e: Exception) {
                throw IllegalArgumentException("Illegal type of ${it.name}", e)
            }
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
    private fun readRegions(): List<GameMapRegion> {
        // Two passes,
        // 1st, generate all instances
        val tiledIdToRegionId = mutableMapOf<String, String>()
        readObjects(TiledObjectType.GameMapRegion) { _, obj ->
            tiledIdToRegionId[obj.id.toString()] = obj.name
        }
        // 2nd, populate the "next"
        return readObjects(TiledObjectType.GameMapRegion) { _, obj ->
            val vertices = obj.getPolygonCoordinates()
            val nexts = obj.properties
                .filter { it.name.startsWith("next") }
                .map {
                    (it.name.substringAfter("next").toIntOrNull() ?: 1) to it.value
                }.sortedBy {
                    it.first
                }.map {
                    it.second
                }
            GameMapRegion(
                obj.name,
                vertices.getOrCalculateCenterPoint(obj.name),
                vertices,
                nexts.map { tiledIdToRegionId.getValue(it) }
            )
        }
    }

    private fun List<PixelCoordinate>.getOrCalculateCenterPoint(regionId: String): PixelCoordinate {
        val existingCenterPoint = points.firstOrNull { it.id == "${regionId}CenterPoint" }
        return if (existingCenterPoint != null) {
            existingCenterPoint.gridCoordinate * tileSize
        } else {
            PixelCoordinate(
                sumOf { it.x } / size,
                sumOf { it.y } / size
            )
        }
    }

    private fun TiledMapObject.toPoint() = PixelCoordinate(x.toInt(), y.toInt()) / tileSize

    @Suppress("UNCHECKED_CAST")
    fun <T> readObjects(type: TiledObjectType, fn: (TiledMapLayer, TiledMapObject) -> T = { _, obj -> obj as T }): List<T> = tiledMap.layers.flatMap { layer ->
        layer.objects.filter { it.type == type.toString() }
            .map { fn(layer, it) }
    }

    private fun readMissions(): List<GameMapMission> {
        val rawMissionObjects: List<TiledMapObject> = readObjects(TiledObjectType.GameMapMission) { _, obj -> obj }
        val tiledNumberIdToRawMissionObjects: Map<Long, TiledMapObject> = rawMissionObjects.associateBy { it.id }

        val dynamicSpriteLayers: List<TiledMap.Layer2> = tiledMap.layers.find { it.type == "group" && it.name == SpecialMapGroup.DynamicSprites.name }?.layers
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
                .sortedBy { it.name.substringAfter("next").toIntOrNull() ?: 0 }
                .map { it.value }
            val regionId: String? = idToPolygons.entries.firstOrNull {
                it.value.contains(Point(gridCoordinate.x * tileSize.width, gridCoordinate.y * tileSize.height))
            }?.key

            GameMapMission(
                it.name,
                idToMissionSpecs.getValue(it.name).title,
                idToMissionSpecs.getValue(it.name).challenges.sumOf { it.star },
                idToMissionSpecs.getValue(it.name).challenges.map { it.id },
                idToMissionSpecs.getValue(it.name).tutorialsPrice,
                mapId,
                tileIdToSpriteIdMap[it.gid] ?: throw IllegalStateException("Sprite for mission ${it.name} is missing, did you forget to add `dynamicSprites`?"),
                it.toPoint(),
                nextIds.map { nextId -> tiledNumberIdToRawMissionObjects.getValue(nextId.toLong()).name },
                regionId
            )
        }
    }

    private fun readPoints(): List<GameMapPoint> = readObjects(TiledObjectType.GameMapPoint) { layer, obj ->
        GameMapPoint(
            obj.name,
            rawLayerIdToIndexMap.getValue(layer.id.toInt()),
            obj.toPoint()
        )
    }

    private fun readMapEntrancePoints(type: TiledObjectType): List<GameMapEntrancePoint> = readObjects(type) { layer, obj ->
        val objId = obj.name
        // {src}-{dest}-entrance-point
        // {src}-{dest}-entrance-destination-point
        // For 2-tile-wide entrance:
        // {src}-{dest}-left-point
        // {src}-{dest}-right-point
        val srcMap = objId.split("-")[0]
        val destMap = objId.split("-")[1]
        require(allMapIds.contains(srcMap)) {
            "Invalid $srcMap in $objId"
        }
        require(allMapIds.contains(destMap)) {
            "Invalid $destMap in $objId"
        }
        GameMapEntrancePoint(
            obj.name,
            rawLayerIdToIndexMap.getValue(layer.id.toInt()),
            obj.toPoint(),
            srcMap,
            destMap,
            GameMapObjectType.valueOf(type.toString())
        )
    }

    private fun TiledMap.readCurves(): List<GameMapCurve> {
        return layers.flatMap { layer ->
            layer.objects.filter { it.type == TiledObjectType.GameMapCurvePoint.toString() }
                .groupBy { it.name }
                .map { it.value.toCurveObject(it.key, rawLayerIdToIndexMap.getValue(layer.id.toInt())) }
        }
    }

    private fun readTexts(): List<GameMapObject> = readObjects(TiledObjectType.GameMapText) { layer, obj ->
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
            this.sortedBy { it.properties.findPropertyOrNull("order")?.toDouble() ?: throw IllegalArgumentException("Can't property 'order' in $curveId") }
                .map { PixelCoordinate(it.x.toInt(), it.y.toInt()) }
        )
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
