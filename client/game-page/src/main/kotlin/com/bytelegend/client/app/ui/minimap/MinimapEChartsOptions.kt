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
@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.client.app.ui.minimap

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.misc.getOrCreateSpanElement
import com.bytelegend.app.client.utils.JSArrayBackedList
import com.bytelegend.app.shared.objects.GameMapRegion
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.app.shared.objects.GridCoordinateAware
import com.bytelegend.client.app.engine.DefaultGameMission
import com.bytelegend.client.app.obj.htmlToText
import com.bytelegend.client.app.ui.minimapGraphSeries
import kotlinext.js.assign
import kotlinx.js.jso
import kotlin.math.max

private const val JAVA_LOGO_IMAGE =
    "image://data:image/png;base64,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"
private val REALWORLD_PROJECTS: List<String> = JSArrayBackedList<String>().apply {
    add("java-todo-webapp")
    add("java-blog-platform")
    add("java-distributed-crawler")
    add("java-e-commerce-website")
}

private const val JAVA_CASTLE_REGION_CENTER = "JavaIsland-JavaIslandSeniorJavaCastle-left"

// https://echarts.apache.org/examples/en/index.html#chart-type-map
fun GameScene.getMinimapMapFeatures(): dynamic {
    val regions: List<GameMapRegion> = objects.getByRole(GameObjectRole.MapRegion)
    val features = js("[]")
    val width = map.pixelSize.width
    val height = map.pixelSize.height
    for (region in regions) {
        // https://cdn.jsdelivr.net/gh/apache/echarts-website@asf-site/examples/data/asset/geo/HK.json
        // https://datatracker.ietf.org/doc/html/rfc7946
        val properties: dynamic = jso {
            id = region.id
        }
        val coordinateArray: dynamic = js("[]")
        for (point in region.vertices) {
            coordinateArray.push(nativeJsArrayOf(point.x, height - point.y))
        }
        val coordinateArrayArray = nativeJsArrayOf(coordinateArray)
        val geometry: dynamic = jso {
            type = "Polygon"
            coordinates = coordinateArrayArray
        }
        val feature: dynamic = jso {
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

    return jso {
        type = "FeatureCollection"
        this.features = features
    }
}

const val mapAreaColorNumber = 4
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
        mapSeries.data.push(jso {
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
        val regionLabel: dynamic = jso {
            formatter = gameRuntime.i("${region.id}Name")
        }
        mapSeries.data.push(jso {
            name = region.id
            value = index % mapAreaColorNumber
            label = regionLabel
        })
    }
    return mapSeries
}

const val ROADMAP_FONT_SIZE = 12

val ROADMAP_GRAPH_SERIES: dynamic = JSON.parse(
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
    "curveness": 0.2,
    "width": 2
  },
  "labelLayout": {
    "moveOverlap": "shiftY",
    "fontSize": $ROADMAP_FONT_SIZE
  }
}
""".trimIndent()
)

private const val STAR_WIDTH = 12

private const val STAR_PNG_BASE64 =
    "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IB2cksfwAAAAlwSFlzAAALEwAACxMBAJqcGAAACPdJREFUeJy9V2lsXOUVPd/b5s0+41lsx3viNU6ceAkpEJQoQGlLA9QhCAJV1YJooaVSq1JVqvqDVkioqKAuqItEVSi0KU5SQhsKBKqWLWAnbuwkdmI7duyM49k8+/7mva93Aj9QMcIgpU960sy85Z57z7nn3pHwKY+9fWBlQbZzbvCh43r6075H+jQP7eoFK0jiOrvofDGdKi4NbsncfHCEJ/5vABQB9kxceug7376q7fi/5lteOHn664Nb9ccPvstLlx3ALVdAWA6zm27e2bCns78TQkyWTpxb/EEkG3vz1gH+9v5j4JcVANPgaar337/3zp0yRBm1jU5c39vueur10Qes5tIJwMheNgCDvaKQysq3333vjVtM3rWIxDLg5ovo7mnHdenE5189NbWDABy+bAAKZb2q7zPXfGvrVd1SJJZG/frtiBf3waQ1oX/DWsfobOC+PVtSR4ZGsGotrBrA4IAoJ+Li/Z+7/upWLZdFEU1QrR54bVlMhf6DptpNaFvj2zkRyGzc226M/mlqdVpYFYCbNoPl86y3a23td7vrikI0ZkLXZ+8AL05AMHRU14SQCmVxZUeT+Z2zgR/ZHKV77ujhy38e/3gQKwK4pQ+iKEDhIgRooqNc4h2CoH7/3i+tdxQzJbjaroUgmQgAA3SGKpeGZGEOzS0d6G2Y++JbM4FHLGbtb7s3YZLJiMBArmyF9vwb9GklAHv6IBNUWePw53NozOSwDrp5k8/v6vd6lAa/zdR0ZX8jBahHIO9DZ9uW9x8XUMlREAG3fxnJdAm3f2GjuCtlv/v4TPJr0+FiejmTnw3Fcm8LaWP4xm591m7CeRJI7OAoLnWLdMNGoUNSlK/6q2w7mprcnQ01Vbba5lahdUM/s1sALReDrpfBJIZ0rgh/13YKWMGt0UnvEKgKigK3hargOY81DZ1QTPXoypcZ03QHk8qbY7HY5qmp5P3BaFYPLEYz4Whm/o7+4ng8n39MohLvvm1r44MDV3YJ9vZuFLiEMmwoixYkdAWiwwfVJkCq2J/NTgnnKevIJUMAku8BoMoyQ0LLFT6UpTIKhSS0go58UqfWEWBWnNjg8aMfioiy6pwaPtvz5tFzPa+cmR2TtLI+9tyRYMbPqxzmiBtSdQEtvT2w1W4HzGsoEL2E5cG5DsYqmVc6bJG4LxCQ5PvxiQeHH7DVQKJ7bObKjxzuOrq/xMHji4jMTuPcKSd4yo+JsRBePjk/abbzf0hul/FaIJn54f7j0z+502FxcaURCyP70NHwY4hSF7hjG1BzE5hjAxHmeJ97N2U+RqfwngAs9N3fQdeddE0mdkrgiRD0wGnw6ALy+RIuhK+ArNdgdmYKfz8xdcpiKd9jUctnpQPHUNi9Bb8fXYqr/rHph2/xexRR6sdCTENz/SyQmQBOPomyuRnMuQ3MdysEF4FiFBBhqpIC1PVS8GoYkSVg/jT0cADcyEA0yVQACbPLPSTXeqTTEbwwOhPMiblvuOzGsf3D0C91wYERIzfYx558dSp8TdeGyK7OFpkZqT4EsyJqfCkwOQkRAQLzFyrhizCsm6gi28BNKmByQ5+ZhhH9J3hsmbRAgrWYINBZpuCBINGCVqTSSbz8zkwhkEg+7nUZwweGidMP+sDBUR7ftbn8zZ8PHRX2Xrv5hqs76uWyvgGl/DQam2XqAhNlLZEkdHCRMudvIzsehJiZh0wssGKRgDIKbgUjYejkD3OhdgLegVQ0hj8cHo6dXgg+WuMzfkFV11Y0Iiv0Rc2MB/74yuhDjmLsy/2b1pPu1iOgTqKu1QOm1ILLXnrKg/KyjszYI3BWaRDWrwVXSIjkirQggefyOHexFnK5DRfnLuCZ107lp8LBB2t9/LkK5R/phPtOgO8e0BckGA/vOxro8ddVbe6udyOpr6OSt4EzBZQjjLKC6OHfQjaK5IgcxfMhmDobK2UApYySqiJfbENgPo6R8bnydCj8a6eTD1HwzMdaMd1kDF7FZ2Ih7Vdare13Fr9FSCkEiTwVle4qmxF/5RCEyDwktwhmNSBqSehBaslaB1FUgqQylBnHqdkEXj9zcczp4j89dBwr7o0rzgJ+iSHusVbZmU5tpqgUiAkkMBXZyTPQZt6F6mSkcnqcSs9EA0Y8AMFJrUq/iZIEVc3BblXhMJlZiRe1leJ8JIBSkSiVFJ/d5kGuwGGuUchWTDAKDPG3DkElW5ZNNKlogogkOEbWzMiw9OVFiC3tVKg8ubMOq0WCIsu2UoW7TwLAbCJBS1afamlmqXQAjRWXy5sQHnoMplICZg9xTIYYibvgpE70eJchV8ZZKQZEgxCq62lX0KiFfWiscTjHA1FyKgRXXwEB3GaxW2TFxrO8xMiZEDnyLJTkBWiqGWdiDUiTSxZLFlQnBSSKYVjUKdS4A5DzF6gLXKQDJ6w2GV631Zqf5tX02snVV8CAYLOqtbKsMU5Dp7Awicz0GBJ8DQ2ojcgaXmSiJRi5CLzmKuTLtWDcg4ViAFbzSfiUBZoRbdTsRIEiq1wXCYC+egpKumA1W7HGKS+jQENn+KV5xLSbkdecEEsR5BILOLMQMSYWL4brJhzKjr7Wqta2JijWHpQy7Ygl54hDGliVAcZliZabtStG/ygAsgRBNRk05fNIxHxYStpRTMfhdUZxbGbWeOPUTEAXis/brcbTF5ZTjt88v3Sf1zW+8yt3XVfVva6ZpYMMit50aXrKTAUhaNjdD/HA8Q+XYUUAmm6IiiyaFgIpTI4vIx1PYC4S42cXlxYThcRTFpU9a7UYszSjNc45IxZG0tlo78+eOHBv+9q6weu2dpnbqm1MoH3CojBIguwTUCAjWSUASSJhhfPikUPTfHxiqXQ6uDgiKqWn3B5+eI0NoX3v/u9uZ1Qc7o09A9pIKHz+0V8+s3Cb12G/dXtfS6vP7BDIQkyGUNnfVkkBl43EiYnAs6MnAxslRT/o9Rr7yXFDQ+98eKn84DH0ns+P7+kzzuSNzNMH/z1+FzekbYKov+Rx0Ca/WgD734Q+OKB9jzFYyFFz9H/vE/3pHBqt3K9PDQ7gYWaUVNrWin89unIb/BfD0uDLmcThogAAAABJRU5ErkJggg=="
private const val HOLLOW_STAR_PNG_BASE64 =
    "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IB2cksfwAAAAlwSFlzAAALEwAACxMBAJqcGAAABzFJREFUeJy9V2tsXFcRnnPuYx/27tq7fq2NHYekjUjTYBUaokpQ8RBQVAR100RKf0CoqlDRqiBVKlIVhAT8gB9IFSohCqKRgApaCkWgFBUJkCVQebRK3aZV0zaJG7t+e9f7unvveTFz7nUqiIPsSOasV9577tmZb76Z+eauC9e4jn5qlCkJH+YAXDts4sfPXdDXYse9VgAgnO1SstPotZ1l+pO48+L/DcBXbt/OtXIenp5vZcHojuuGc1978Pbrjzz6+3NyywE8/LldTEjn1oWGOLy4UmHGMOjt7ri73O2eOnLbwJ8ef3bObCkAZaAsGXx7eqnWKY0Ehq+ZxQorF/u+UfIKLwPMLWwpAMfxD9YbYl+z2QLH7hhoBgGsNtXNPXnvEG78YMsAPDI+1tuI2NenFxZdjS3getY/LgXvLDcyXYXyA4+M3/Tkd3794vyWADDce2i2strfoOip/zgDqgHMAtQaDVistnaM9OYOwiZY2DCAbx76YLklvQPL1RoWvsJUkF9unRMJCveWVxt8qLfryLGDHzr+rSf/vqGOuALAfbfuZpwx8FPGc1y34DpOyfG93dK4h6cXKtvbQQvwNnDGAf+SFBhkwkCtXofZSnNsuJj/5bG79v9FiuiskPqCknJBa9lqh1iyYQt+9PyU+Q8AB/b385RID/ku3yeUeJ+XSu0KtLdLCdMnA9kdqTDXDEO2XFkGbYR1zKn+YwLQOeC+BqVCOD91iS2v5MfTaW885Xkq5fAqBjHrGPY6QHQmYvzcF24efSU08s1f/Gs6cu/cM/gJJ/C+F7rO3npknEa9Ca2gApESaFjZAAkldzQ61Rg9B2KIJ9TbxagZDTDciKI2zC6EoBlqpMZvMV5CSku+6+3JZTLjWd9jHn0fnNZdN46cQNv843OBHJtZXmRtdIrKYul1uQHXYeBRrvGaio25PI7cxg22DQ15NSa+TyBsepANaWxdCAxCKo3vNsyZGp5Gm54HI6VCtpQygy5yd7KUdj5fz2Z3BdVVwo3VTsZZQi3DQGKqQcdeKd82ejJnmL2m+9piwfN0N2HIaG33NZ6RCIraZzCfg960P2G0eNB9+tWZ84f2Dh/Y2VP4YyBEuYotZoxOjGHkoC0IZrj1z615HhuHtQKMHdgXflHr+FrHUOL7No0O9BRyMNrVed4V4s6fTc4s2SL0wX9VKnX37oHeZ16bW8zXgpaNiiqAkzHKMRqRhAooPUR9nH5uWzB2QEAlQdZEP73xmoAQIWijlM/DDf3Ft90ovOPU5NTS5S746eRbZPnPX7xp5/17R8onzr4zn6misFA+lHUS5zhmVlvvDo9TBGvU01miGZ0KnUSNVGAJ2BrqI+cDPYtcynsef+ni5Lo6wHX486xyzdjQ0Il/XpzKtqIIDWqba64T6tGwojYk5+xyHhKHBCBmg4AoHXPUlSvAnnJfw4vCj5184Y1XripEPzlzSd/7gZ1PeMr03TA88N3X5xbderOBFUxtZ+K2ZBYPcLsXX1AJKqSAWNAJE4rYwsOlrjzsLg+spkF/+Xx16ex/C98VSnjyhTf1fWPXPVrKZsqjfb0PvXShBRpD4dyOYnCozXTcchZUokRx0cbRU+QEKp1KwftH3xN5kTpqWuqp596qXPGssO4sOH7mDXXv/j2TDOUNqeRUTE5SC5rF1DOWtCZ7NwX2rePoSRnTKQ6+ggarqqd+ePa1dZ8ZrzqMMPc9TSGZUAoZoMpmlgWeiM3aos9mbR5Qd+A5nQhTGEkIhMx5rsjhgdVNAVBCFdsEgMTjshbEEmwFCJIqtAqUAEDnxICxKTHQxopsh8I1QvRvGkBL6HIrjLvAlhkl21a/SehPPLN3hwKd05e7Aj8ICXUhGDK4E7fObQpAW8pCEKGOWylLdF6tFV0cOEsEKpZmPMFY8llbdqgrGpgGH9jg1fxcFYDQapgYWIuIm1hwqPId7kAq5UM/thgWCCw0cIK2o0T31wSaZoqGSrsNhVRmx2e7u/nvKpUrCnFdAJ8pFp2WUsV6ENmc0lKMJqMDhc40DBZyeltHbqmDO79CZNmgp/jpC7VG32ytxqv1AISK00aasFRtwbZyx/Z2Z5pBZYMMCA/rVqqcJHEh2cUJlvZ9GCl0wHBXYaGTswndjh5bCaKJdCaFzcFueW+246v96fRHplKN4jRO1UBGtntCEUEo5aCMxMZTEErBQ+lmafrnMmnY1p03Rd9byQA/5QTiOLJy8em3L6nkONE6ccfo0POe5tfvyGSO9mf8L81HQXamQioKEEjZg5M1jeeaGwKAk8w4qLvbu/Ky4Lsv87Z+zA3bz5xeWlpeNwxcv7k4E+E/0vkHbusfODbI3Xt6ekuH66G4UUY4YIzxNwzgr5VKex90359lTKlI/6EzaNVOB8GGf3I9Oz9XvaVY/D5SeRIz/1F88MBnUrU5HfhHpfLERh2ut/62smLQeB2fzX/7v879G60qBRDOj/rqAAAAAElFTkSuQmCC"

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
    "width": $STAR_WIDTH,
    "height": $STAR_WIDTH,
    "backgroundColor": {
      "image": "$HOLLOW_STAR_PNG_BASE64"
    }
  }
}
"""
)

private fun calculateTextWidth(text: String, fontSize: Int): Int {
    val span = getOrCreateSpanElement("width-calculate")
    span.style.fontSize = fontSize.toString()
    span.style.position = "absolute"
    span.style.whiteSpace = "nowrap"
    span.style.width = "auto"
    span.style.height = "auto"
    span.style.visibility = "hidden"
    span.innerText = text
    return span.clientWidth
}

private const val MAX_LABEL_WIDTH = 100

private fun labelOptions(
    showMissionTitles: Boolean,
    totalStars: Int,
    missionStars: Int,
    titleHtml: String
): dynamic {
    val starsRichText = if (totalStars >= 5) {
        "$missionStars/$totalStars{Star|}"
    } else {
        "{Star|}".repeat(missionStars) + "{HollowStar|}".repeat(max(totalStars - missionStars, 0))
    }
    val title = htmlToText(titleHtml)
    val labelFormatter = when {
        !showMissionTitles -> starsRichText
        totalStars == 0 -> title
        else -> """
                $starsRichText
                $title
            """.trimIndent()
    }
    val labelWidth = when {
        showMissionTitles -> calculateTextWidth(title, ROADMAP_FONT_SIZE).let {
            if (it > MAX_LABEL_WIDTH) MAX_LABEL_WIDTH else it
        }
        totalStars > 5 -> "$missionStars/$totalStars".length * 5 + STAR_WIDTH + 10
        else -> STAR_WIDTH * totalStars
    }
    return jso {
        show = true
        position = "top"
        distance = 0
        align = "center"
        formatter = labelFormatter
        backgroundColor = "#eee"
        borderColor = "#555"
        borderWidth = 2
        borderRadius = 5
        padding = 2
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

val ITEM_STYLE: dynamic = JSON.parse(
    """
    {
        "color": "#eee",
        "borderWidth": 2,
        "borderColor": "#555",
        "shadowBlur": 3,
        "shadowColor": "#888",
        "shadowOffsetX": 0,
        "shadowOffsetY": 3
    }
""".trimIndent()
)

fun GameScene.getRoadmapMissionGraphSeries(showMissionTitles: Boolean): dynamic {
    val nodes: dynamic = js("[]")
    val edges: dynamic = js("[]")
    objects.getByRole<DefaultGameMission>(GameObjectRole.Mission).forEach { mission ->
        val coordinate = mission.gridCoordinate * map.tileSize
        val labelOptions: dynamic = labelOptions(
            showMissionTitles,
            mission.gameMapMission.totalStar,
            challengeAnswers.missionStar(mission.id),
            gameRuntime.i(mission.gameMapMission.title)
        )

        nodes.push(jso {
            id = mission.id
            // To avoid the label out of left border
            x = if (coordinate.x < 120) 120 else coordinate.x
            y = coordinate.y
            value = mission.id
            category = 0
            symbol = "circle"
            label = labelOptions
            itemStyle = ITEM_STYLE
            this.symbolSize = 10
        })

        mission.gameMapMission.next.forEach {
            edges.push(jso {
                source = mission.id
                target = it
            })
        }
    }
    addCornerPlaceHoldersToNodes(nodes, map.pixelSize.width, map.pixelSize.height)
    addJavaCastleToNodes(nodes, map.tileSize.width, map.tileSize.height, 80)
    addRealworldProjectsToNodes(showMissionTitles, nodes)

    return assign(ROADMAP_GRAPH_SERIES) {
        this.nodes = nodes
        this.edges = edges
    }
}

private fun GameScene.addRealworldProjectsToNodes(showMissionTitles: Boolean, nodes: dynamic) {
    REALWORLD_PROJECTS.forEach {
        val obj = objects.getByIdOrNull<GameObject>(it) ?: return
        val coordinate = obj.unsafeCast<GridCoordinateAware>().gridCoordinate * map.tileSize
        val labelOptions = labelOptions(
            showMissionTitles,
            0,
            0,
            gameRuntime.i(it)
        )

        nodes.push(jso {
            id = it
            // To avoid the label out of left border
            x = coordinate.x
            y = coordinate.y
            value = it
            category = 0
            symbol = "diamond"
            label = labelOptions
            itemStyle = ITEM_STYLE
            this.symbolSize = 20
        })
    }
}

private fun GameScene.addJavaCastleToNodes(nodes: dynamic, tileWidth: Int, tileHeight: Int, logoSize: Int) {
    val obj = objects.getByIdOrNull<GameObject>(JAVA_CASTLE_REGION_CENTER) ?: return
    val gridCoordinate = obj.unsafeCast<GridCoordinateAware>().gridCoordinate
    nodes.push(jso {
        x = gridCoordinate.x * tileWidth
        y = gridCoordinate.y * tileHeight
        symbol = JAVA_LOGO_IMAGE
        symbolSize = logoSize
    })
}

private fun addCornerPlaceHoldersToNodes(nodes: dynamic, mapWidth: Int, mapHeight: Int) {
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
        nodes.push(jso {
            id = region.id
            x = region.center.x
            y = region.center.y
            value = 0
            category = 0
            symbolSize = 0
        })

        region.next.forEach { nextId ->
            edges.push(jso {
                source = region.id
                target = nextId
            })
        }
    }

    addCornerPlaceHoldersToNodes(nodes, mapWidth, mapHeight)
    addJavaCastleToNodes(nodes, map.tileSize.width, map.tileSize.height, 20)
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
    return jso {
        this.visualMap = minimapVisualMapBaseOptions
        this.series = series
    }
}

fun GameScene.getRoadmapEChartsOptions(showMissionTitles: Boolean): dynamic {
    val series = nativeJsArrayOf(
        getRoadmapMapSeries(),
        getRoadmapMissionGraphSeries(showMissionTitles)
    )
    return jso {
        this.visualMap = minimapVisualMapBaseOptions
        this.series = series
        this.animation = false
    }
}

fun <T> Iterable<T>.mapToNativeJsArray(fn: (T) -> dynamic): dynamic {
    val ret = js("[]")
    for (element in this) {
        ret.push(fn(element))
    }
    return ret
}

fun nativeJsArrayOf(vararg args: dynamic): dynamic {
    val ret = js("[]")
    for (arg in args) {
        ret.push(arg)
    }
    return ret
}
