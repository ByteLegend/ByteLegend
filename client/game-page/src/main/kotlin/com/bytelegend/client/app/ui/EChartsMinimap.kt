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

package com.bytelegend.client.app.ui

import com.bytelegend.app.client.misc.uuid
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.client.app.engine.logger
import com.bytelegend.client.app.ui.minimap.getMinimapEChartsOptions
import com.bytelegend.client.app.ui.minimap.getMinimapMapFeatures
import kotlinx.js.jso
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.events.MouseEvent
import react.Fragment
import react.State
import react.create

interface EChartsMinimapProps : GameProps {
    var zIndex: Int
    var width: Int
    var height: Int
    var top: Int
    var left: Int

    // light/dark
    var theme: String

    // svg/canvas
    var renderer: String
}

val minimapGraphSeries: dynamic = JSON.parse(
    """
{
  "left": 0,
  "right": 0,
  "top": 0,
  "bottom": 0,
  "type": "graph",
  "zoom": 1,
  "layout": "none",
  "edgeSymbol": ["none", "none"],
  "edgeSymbolSize": [0, 10],
  "tooltip": { "show": false},
  "lineStyle": {
    "type": [10, 3],
    "color": "#423019",
    "curveness": 0.2,
    "width": 5
  }
}
""".trimIndent()
)

interface EChartsRoadmapState : State {
    var hoveredRegionName: String?
    var hoveredPosition: PixelCoordinate?
}

class EChartsMinimap : GameUIComponent<EChartsMinimapProps, EChartsRoadmapState>() {
    init {
        state = jso()
    }
    private val echartsContainerElementId = "echarts-container-${uuid()}"

    private var mapId: String = ""

    // https://echarts.apache.org/en/api.html#echarts.init
    private var echarts: dynamic = undefined
    private var options: dynamic = undefined

    override fun render() = Fragment.create {
        absoluteDiv(left = props.left, top = props.top, width = props.width, height = props.height) {
            it.id = echartsContainerElementId
        }

        if (state.hoveredRegionName != undefined) {
            if (state.hoveredPosition!!.y > props.height / 2) {
                absoluteDiv(
                    left = state.hoveredPosition!!.x,
                    bottom = props.height - state.hoveredPosition!!.y + 20,
                    className = "minimap-area-label"
                ) {
                    +game.i(state.hoveredRegionName!!)
                }
            } else {
                absoluteDiv(
                    left = state.hoveredPosition!!.x,
                    top = state.hoveredPosition!!.y + 20,
                    className = "minimap-area-label"
                ) {
                    +game.i(state.hoveredRegionName!!)
                }
            }
        }
    }

    private fun onMouseMoveOnMinimap(event: MouseEvent) {
        document.getElementById(echartsContainerElementId)?.firstChild?.dispatchEvent(MouseEvent("mousemove", event.asDynamic()))
    }

    private fun onMouseout() {
        setState {
            hoveredRegionName = undefined
            hoveredPosition = undefined
        }
    }

    private fun onMousemove(event: dynamic) {
        setState {
            hoveredRegionName = "${event.data.name}Name"
            hoveredPosition = PixelCoordinate(event.event.offsetX, event.event.offsetY)
        }
    }

    override fun componentDidUpdate(prevProps: EChartsMinimapProps, prevState: EChartsRoadmapState, snapshot: Any) {
        refreshEcharts()
    }

    private fun refreshEcharts() {
        if (echarts != undefined && mapId == activeScene.map.id) {
            return
        }

        unmountEcharts()

        document.getElementById(echartsContainerElementId)?.apply {
            echarts = window.asDynamic().echarts.init(this, props.theme, jso {
                renderer = props.renderer
            })
            val features = activeScene.getMinimapMapFeatures()
            window.asDynamic().echarts.registerMap("minimap", features)
        }

        options = activeScene.getMinimapEChartsOptions()
        if (logger.debugEnabled) {
            logger.debug("Set echarts options for minimap: ${JSON.stringify(options)}")
        }
        echarts.setOption(options)
        echarts.on("mouseout", "series.map", this::onMouseout)
        echarts.on("mousemove", "series.map", this::onMousemove)
        mapId = activeScene.map.id
    }

    private fun unmountEcharts() {
        if (echarts != undefined) {
            echarts.off("mousemove", this::onMousemove)
            echarts.off("mouseout", this::onMousemove)
            echarts = undefined
            options = undefined
            mapId = ""
        }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        refreshEcharts()
        game.eventBus.on(MINIMAP_MOUSE_MOVE_EVENT, this::onMouseMoveOnMinimap)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        unmountEcharts()
    }

    override fun shouldComponentUpdate(nextProps: EChartsMinimapProps, nextState: EChartsRoadmapState): Boolean {
        return state.hoveredPosition != nextState.hoveredPosition ||
            state.hoveredRegionName != nextState.hoveredRegionName ||
            mapId != nextProps.game.activeScene.map.id
    }
}
