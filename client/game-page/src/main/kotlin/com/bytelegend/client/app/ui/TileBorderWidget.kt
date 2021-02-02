package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventBusAware
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.client.app.engine.MouseEventListener
import common.dashedBorderCss
import kotlinx.css.Color
import kotlinx.css.zIndex
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.setState
import styled.css
import styled.styledDiv

external interface TileBorderWidgetState : RState {
    var mapLocation: GridCoordinate
    var reachable: Boolean
}

interface TileBorderWidgetProps : RProps, EventBusAware, ZIndexed

class TileBorderWidget : RComponent<TileBorderWidgetProps, TileBorderWidgetState>() {
    var mouseMoveListener: MouseEventListener = {
        if (state.mapLocation != it.mapCoordinate) {
            setState {
                mapLocation = it.mapCoordinate
            }
        }
    }

    override fun componentDidMount() {
        props.eventBus.on("mouseMove", mouseMoveListener)
    }

    override fun componentWillUnmount() {
        props.eventBus.remove("mouseMove", mouseMoveListener)
    }

    override fun RBuilder.render() {
        styledDiv {
            dashedBorderCss(color = if (state.reachable) Color.green else Color.red)
            css {
                zIndex = props.zIndex
            }
        }
    }
}
