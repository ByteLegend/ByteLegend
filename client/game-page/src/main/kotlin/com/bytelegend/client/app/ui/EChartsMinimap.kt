@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.client.app.engine.logger
import com.bytelegend.client.app.obj.uuid
import com.bytelegend.client.app.ui.minimap.getMinimapEChartsOptions
import com.bytelegend.client.app.ui.minimap.getMinimapMapFeatures
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinext.js.jsObject
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.id
import org.w3c.dom.events.MouseEvent
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.setState

interface EChartsMinimapProps : RProps {
    var zIndex: Int
    var width: Int
    var height: Int
    var top: Int
    var left: Int

    // light/dark
    var theme: String

    // svg/canvas
    var renderer: String

    var gameScene: GameScene
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

interface EChartsRoadmapState : RState {
    var hoveredRegionName: String?
    var hoveredPosition: PixelCoordinate?
}

class EChartsMinimap : RComponent<EChartsMinimapProps, EChartsRoadmapState>() {
    private val echartsContainerElementId = "echarts-container-${uuid()}"

    // https://echarts.apache.org/en/api.html#echarts.init
    var echarts: dynamic = undefined
    var options: dynamic = undefined

    override fun RBuilder.render() {
        absoluteDiv(left = props.left, top = props.top, width = props.width, height = props.height) {
            attrs.id = echartsContainerElementId
        }

        if (state.hoveredRegionName != undefined) {
            if (state.hoveredPosition!!.y > props.height / 2) {
                absoluteDiv(
                    left = state.hoveredPosition!!.x,
                    bottom = props.height - state.hoveredPosition!!.y + 20,
                    classes = jsObjectBackedSetOf("minimap-area-label")
                ) {
                    +props.gameScene.gameRuntime.i(state.hoveredRegionName!!)
                }
            } else {
                absoluteDiv(
                    left = state.hoveredPosition!!.x,
                    top = state.hoveredPosition!!.y + 20,
                    classes = jsObjectBackedSetOf("minimap-area-label")
                ) {
                    +props.gameScene.gameRuntime.i(state.hoveredRegionName!!)
                }
            }
        }
    }

    private fun initIfNot() {
        if (echarts == undefined) {
            document.getElementById(echartsContainerElementId)?.apply {
                echarts = window.asDynamic().echarts.init(this, props.theme, jsObject {
                    renderer = props.renderer
                })
                val features = props.gameScene.getMinimapMapFeatures()
                window.asDynamic().echarts.registerMap("minimap", features)
            }
        }
        if (options == undefined) {
            options = props.gameScene.getMinimapEChartsOptions()
        }
    }

    private fun onMouseMoveOnMinimap(event: MouseEvent) {
        document.getElementById(echartsContainerElementId)?.firstChild?.dispatchEvent(MouseEvent("mousemove", event.asDynamic()))
    }

    private fun onMouseout(event: dynamic) {
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

    override fun componentDidMount() {
        initIfNot()
        if (logger.debugEnabled) {
            logger.debug("Set echarts options for minimap: ${JSON.stringify(options)}")
        }
        echarts.setOption(options)
        echarts.on("mouseout", "series.map", this::onMouseout)
        echarts.on("mousemove", "series.map", this::onMousemove)
        props.gameScene.gameRuntime.eventBus.on(MINIMAP_MOUSE_MOVE_EVENT, this::onMouseMoveOnMinimap)
    }

    override fun componentWillUnmount() {
        props.gameScene.gameRuntime.eventBus.remove(MINIMAP_MOUSE_MOVE_EVENT, this::onMouseMoveOnMinimap)
        echarts.off("mousemove", this::onMousemove)
        echarts.off("mouseout", this::onMousemove)
    }

    override fun shouldComponentUpdate(nextProps: EChartsMinimapProps, nextState: EChartsRoadmapState): Boolean {
        return state.hoveredPosition != nextState.hoveredPosition || state.hoveredRegionName != nextState.hoveredRegionName
    }
}
