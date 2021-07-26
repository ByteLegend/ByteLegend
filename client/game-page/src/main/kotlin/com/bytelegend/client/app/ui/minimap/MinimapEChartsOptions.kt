@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.client.app.ui.minimap

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
    "show": false,
    "fontSize": 20,
    "color": "red",
    "textShadowBlur": 2,
    "textShadowColor": "black"
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
    "width": 100
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

fun GameScene.getRoadmapMissionGraphSeries(zoom: Double): dynamic {
    val mapWidth = map.pixelSize.width
    val mapHeight = map.pixelSize.height

    val nodes: dynamic = js("[]")
    val edges: dynamic = js("[]")
    objects.getByRole<GameMission>(GameObjectRole.Mission).forEach { mission ->
        val coordinate = mission.gridCoordinate * map.tileSize
        val title = htmlToText(gameRuntime.i(mission.gameMapMission.title))
        val totalStars = mission.gameMapMission.totalStar
        val missionStars = playerChallenges.missionStar(mission.id)
        val starsRichText = if (totalStars >= 5) {
            "$missionStars/$totalStars{Star|}"
        } else {
            "{Star|}".repeat(missionStars) + "{HollowStar|}".repeat(totalStars - missionStars)
        }
        val labelOptions: dynamic = jsObject {
            show = true
            position = "inside"
            distance = 0
            align = "center"
            formatter = """
                $starsRichText
                $title
            """.trimIndent()
            backgroundColor = "#eee"
            borderColor = "#555"
            borderWidth = 2
            borderRadius = 5
            padding = 5
            fontSize = 12
            shadowBlur = 3
            shadowColor = "#888"
            shadowOffsetX = 0
            shadowOffsetY = 3
            color = "black"
            overflow = "break"
            rich = richStarAndHollowStar
        }

        val symbolSize = nativeJsArrayOf(120, 40)
        nodes.push(jsObject {
            id = mission.id
            x = coordinate.x
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

        if (region.next != null) {
            edges.push(jsObject {
                source = region.id
                target = region.next
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
