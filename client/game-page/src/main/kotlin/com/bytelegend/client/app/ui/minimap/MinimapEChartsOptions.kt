@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.client.app.ui.minimap

import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.shared.objects.GameMapRegion
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.engine.GameMission
import com.bytelegend.client.app.obj.htmlToText
import com.bytelegend.client.app.ui.minimapGraphSeries
import com.bytelegend.client.app.ui.mission.HOLLOW_STAR_PNG_BASE64
import com.bytelegend.client.app.ui.mission.STAR_PNG_BASE64
import kotlinext.js.assign
import kotlinext.js.jsObject

// https://echarts.apache.org/examples/en/index.html#chart-type-map
fun GameScene.getMinimapMapFeatures(): dynamic {
    val regions: List<GameMapRegion> = objects.getByRole(GameObjectRole.MapRegion)
    val features = js("[]")
    val width = map.pixelSize.width
    val height = map.pixelSize.height
    for (region in regions) {
        // https://cdn.jsdelivr.net/gh/apache/echarts-website@asf-site/examples/data/asset/geo/HK.json
        // https://datatracker.ietf.org/doc/html/rfc7946
        val properties: dynamic = jsObject {
            id = region.id
        }
        val coordinateArray: dynamic = js("[]")
        for (point in region.vertices) {
            coordinateArray.push(nativeJsArrayOf(point.x, height - point.y))
        }
        val coordinateArrayArray = nativeJsArrayOf(coordinateArray)
        val geometry: dynamic = jsObject {
            type = "Polygon"
            coordinates = coordinateArrayArray
        }
        val feature: dynamic = jsObject {
            id = region.id
            type = "Feature"
            this.properties = properties
            this.geometry = geometry
        }

        features.push(feature)
    }

    // Placeholders at corners. We didn't find a good way to configure echarts NOT filling the container
    // This is a workaround
    features.push(
        JSON.parse(
            """
{"type":"Feature","id":"placeholder-1","properties":{"name":""},"geometry":{"type":"Polygon","coordinates":[[[0,0],[1,0],[0,1]]]}}
    """.trimIndent()
        )
    )
    features.push(
        JSON.parse(
            """
{"type":"Feature","id":"placeholder-2","properties":{"name":""},"geometry":{"type":"Polygon","coordinates":[[[0,${height - 1}],[1,${height - 1}],[0,${height - 2}]]]}}
    """.trimIndent()
        )
    )
    features.push(
        JSON.parse(
            """
{"type":"Feature","id":"placeholder-3","properties":{"name":""},"geometry":{"type":"Polygon","coordinates":[[[${width - 1},${height - 1}],[${width - 1},${height - 2}],[${width - 2},${height - 1}]]]}}
    """.trimIndent()
        )
    )
    features.push(
        JSON.parse(
            """
{"type":"Feature","id":"placeholder-4","properties":{"name":""},"geometry":{"type":"Polygon","coordinates":[[[${width - 1},0],[${width - 1},1],[${width - 2},0]]]}}
    """.trimIndent()
        )
    )

    return jsObject {
        type = "FeatureCollection"
        this.features = features
    }
}

val mapAreaColorNumber = 4
val minimapVisualMapBaseOptions: dynamic = JSON.parse(
    """
{
  "show": false,
  "type": "piecewise",
  "pieces": [
    {
      "min": 0,
      "max": 0,
      "color": "#fbd7d6"
    },
    {
      "min": 1,
      "max": 1,
      "color": "#e4e4f7"
    },
    {
      "min": 2,
      "max": 2,
      "color": "#f3edd9"
    },
    {
      "min": 3,
      "max": 3,
      "color": "#D8E4FD"
    }
 ]
}
    """.trimIndent()
)

fun GameScene.getMinimapMapSeries() {
    val mapSeries: dynamic = JSON.parse(
        """
{
  "name": "minimap-map",
  "type": "map",
  "map": "minimap",
  "itemStyle": {"borderWidth":2, "borderColor": "#828282"},
  "left": 0,
  "right": 0,
  "top": 0,
  "bottom": 0,
  "zoom": 1,
  "nameProperty" : "id",
  "label": {
    "formatter": ""
  },
  "data": []
}
    """
    )
    val regions: List<GameMapRegion> = objects.getByRole(GameObjectRole.MapRegion)
    regions.forEachIndexed { index, region ->
        mapSeries.data.push(jsObject {
            name = region.id
            value = index % mapAreaColorNumber
        })
    }
    return mapSeries
}

fun GameScene.getRoadmapMapSeries() {
    val mapSeries: dynamic = JSON.parse(
        """
{
  "name": "roadmap-map",
  "animation": false,
  "type": "map",
  "map": "minimap",
  "itemStyle": {"borderWidth":2, "borderColor": "#828282"},
  "left": 0,
  "right": 0,
  "top": 0,
  "bottom": 0,
  "label": {
    "show": true,
    "fontSize": 20
  },
  "nameProperty" : "id",
  "data": []
}
    """
    )
    val regions: List<GameMapRegion> = objects.getByRole(GameObjectRole.MapRegion)
    regions.forEachIndexed { index, region ->
        val regionLabel: dynamic = jsObject {
            formatter = gameRuntime.i("${region.id}Name")
        }
        mapSeries.data.push(jsObject {
            name = region.id
            value = index % mapAreaColorNumber
            label = regionLabel
        })
    }
    return mapSeries
}

val roadmapGraphSeries: dynamic = JSON.parse(
    """
{
  "left": 0,
  "right": 0,
  "top": 0,
  "bottom": 0,
  "animation": false,
  "type": "graph",
  "layout": "none",
  "edgeSymbol": ["none", "arrow"],
  "edgeSymbolSize": [0, 10],
  "tooltip": { "show": false},
  "lineStyle": {
    "type": [10, 3],
    "color": "red",
    "curveness": 0.3,
    "width": 2
  },
  "labelLayout": {
    "moveOverlap": "shiftY",
    "fontSize": 12
  }
}
""".trimIndent()
)

val richStarAndHollowStar: dynamic = JSON.parse(
    """
{
  "Star": {
    "width": 12,
    "height": 12,
    "backgroundColor": {
      "image": "$STAR_PNG_BASE64"
    }
  },
  "HollowStar": {
    "width": 12,
    "height": 12,
    "backgroundColor": {
      "image": "$HOLLOW_STAR_PNG_BASE64"
    }
  }
}
"""
)

fun GameRuntime.calculateRoughTextWidth(text: String): Int {
    var asciiCharNum = 0
    var localeCharNum = 0
    text.forEach {
        if (it.code in 0..128) {
            asciiCharNum++
        } else {
            localeCharNum++
        }
    }
    return (asciiCharNum * 5 + localeCharNum * locale.roughCharacterWidthCoefficient * 5).toInt()
}

private const val MAX_LABEL_WIDTH = 100
private val TITLE_ON_SYMBOL_SIZE = nativeJsArrayOf(MAX_LABEL_WIDTH, 50)
private const val TITLE_OFF_SYMBOL_SIZE = 12

fun GameMission.labelOptions(zoom: Double): dynamic {
    val totalStars = gameMapMission.totalStar
    val missionStars = gameScene.playerChallenges.missionStar(id)
    val starsRichText = if (totalStars >= 5) {
        "$missionStars/$totalStars{Star|}"
    } else {
        "{Star|}".repeat(missionStars) + "{HollowStar|}".repeat(totalStars - missionStars)
    }
    val title = htmlToText(gameScene.gameRuntime.i(gameMapMission.title))
    val labelFormatter = if (totalStars == 0) title else """
                $starsRichText
                $title
            """.trimIndent()
    val roughLabelWidthPx = gameScene.gameRuntime.calculateRoughTextWidth(title)
    val labelWidth = if (roughLabelWidthPx > MAX_LABEL_WIDTH) MAX_LABEL_WIDTH else roughLabelWidthPx
    return jsObject {
        show = true
        position = "insideBottom"
        distance = 0
        align = "center"
        formatter = labelFormatter
        backgroundColor = "#eee"
        borderColor = "#555"
        borderWidth = 2
        borderRadius = 5
        padding = 5
        shadowBlur = 3
        shadowColor = "#888"
        shadowOffsetX = 0
        shadowOffsetY = 3
        color = "black"
        width = labelWidth
        overflow = "break"
        rich = richStarAndHollowStar
    }
}

fun GameScene.getRoadmapMissionGraphSeries(zoom: Double): dynamic {
    val mapWidth = map.pixelSize.width
    val mapHeight = map.pixelSize.height

    val nodes: dynamic = js("[]")
    val edges: dynamic = js("[]")
    objects.getByRole<GameMission>(GameObjectRole.Mission).forEach { mission ->
        val coordinate = mission.gridCoordinate * map.tileSize
        val labelOptions: dynamic = mission.labelOptions(zoom)

        val symbolSize = nativeJsArrayOf(MAX_LABEL_WIDTH, 50)
        nodes.push(jsObject {
            id = mission.id
            // To avoid the label out of left border
            x = if (coordinate.x < 120) 120 else coordinate.x
            y = coordinate.y
            value = mission.id
            category = 0
            symbol = "roundRect"
            label = labelOptions
            this.symbolSize = symbolSize
        })

        mission.gameMapMission.next.forEach {
            edges.push(jsObject {
                source = mission.id
                target = it
            })
        }
    }
    addCornerPlaceHoldersToNodes(nodes, mapWidth, mapHeight)

    return assign(roadmapGraphSeries) {
        this.nodes = nodes
        this.edges = edges
    }
}

fun addCornerPlaceHoldersToNodes(nodes: dynamic, mapWidth: Int, mapHeight: Int) {
    nodes.push(JSON.parse("""{"id": "placeholder1", "x": 0, "y": 0, "symbolSize": 0 }"""))
    nodes.push(JSON.parse("""{"id": "placeholder2", "x": ${mapWidth - 1}, "y": 0, "symbolSize": 0 }"""))
    nodes.push(JSON.parse("""{"id": "placeholder3", "x": 0, "y": ${mapHeight - 1}, "symbolSize": 0 }"""))
    nodes.push(JSON.parse("""{"id": "placeholder4", "x": ${mapWidth - 1}, "y": ${mapHeight - 1}, "symbolSize": 0 }"""))
}

fun GameScene.getMinimapRegionConnectionGraphSeries() {
    val mapWidth = map.pixelSize.width
    val mapHeight = map.pixelSize.height

    val regions: List<GameMapRegion> = objects.getByRole(GameObjectRole.MapRegion)
    val nodes: dynamic = js("[]")
    val edges: dynamic = js("[]")
    regions.forEach { region ->
        nodes.push(jsObject {
            id = region.id
            x = region.center.x
            y = region.center.y
            value = 0
            category = 0
            symbolSize = 0
        })

        region.next.forEach { nextId ->
            edges.push(jsObject {
                source = region.id
                target = nextId
            })
        }
    }

    addCornerPlaceHoldersToNodes(nodes, mapWidth, mapHeight)
    return assign(minimapGraphSeries) {
        this.nodes = nodes
        this.edges = edges
    }
}

fun GameScene.getMinimapEChartsOptions(): dynamic {
    val series = nativeJsArrayOf(
        getMinimapMapSeries(),
        getMinimapRegionConnectionGraphSeries()
    )
    return jsObject {
        this.visualMap = minimapVisualMapBaseOptions
        this.series = series
    }
}

fun GameScene.getRoadmapEChartsOptions(zoom: Double): dynamic {
    val series = nativeJsArrayOf(
        getRoadmapMapSeries(),
        getRoadmapMissionGraphSeries(zoom)
    )
    return jsObject {
        this.visualMap = minimapVisualMapBaseOptions
        this.series = series
        this.animation = false
    }
}

fun nativeJsArrayOf(vararg args: dynamic): dynamic {
    val ret = js("[]")
    for (arg in args) {
        ret.push(arg)
    }
    return ret
}
