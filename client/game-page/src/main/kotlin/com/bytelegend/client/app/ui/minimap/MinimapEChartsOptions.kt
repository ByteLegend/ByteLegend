package com.bytelegend.client.app.ui.minimap

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.shared.objects.GameMapRegion
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.ui.minimapGraphSeries
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
            name = region.id
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

fun GameScene.getMinimapEChartsOptions(): dynamic {
    val mapWidth = map.pixelSize.width
    val mapHeight = map.pixelSize.height
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
  "label": {
    "formatter": ""
  },
  "data": []
}
    """
    )

    val seriesArray = nativeJsArrayOf(mapSeries)
    val regions: List<GameMapRegion> = objects.getByRole(GameObjectRole.MapRegion)
    val nodes: dynamic = js("[]")
    val edges: dynamic = js("[]")
    regions.forEachIndexed { index, region ->
        mapSeries.data.push(jsObject {
            name = region.id
            value = index % mapAreaColorNumber
        })

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

    nodes.push(JSON.parse("""{"id": "placeholder1", "x": 0, "y": 0, "symbolSize": 0 }"""))
    nodes.push(JSON.parse("""{"id": "placeholder2", "x": ${mapWidth - 1}, "y": 0, "symbolSize": 0 }"""))
    nodes.push(JSON.parse("""{"id": "placeholder3", "x": 0, "y": ${mapHeight - 1}, "symbolSize": 0 }"""))
    nodes.push(JSON.parse("""{"id": "placeholder4", "x": ${mapWidth - 1}, "y": ${mapHeight - 1}, "symbolSize": 0 }"""))

    seriesArray.push(assign(minimapGraphSeries) {
        this.nodes = nodes
        this.edges = edges
    })

    return jsObject {
        this.visualMap = minimapVisualMapBaseOptions
        this.series = seriesArray
    }
}

fun nativeJsArrayOf(vararg args: dynamic): dynamic {
    val ret = js("[]")
    for (arg in args) {
        ret.push(arg)
    }
    return ret
}
