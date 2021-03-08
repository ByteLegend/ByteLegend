package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.shared.Direction
import com.bytelegend.app.shared.HumanReadableCoordinate
import com.bytelegend.client.app.engine.MOUSE_MOVE_EVENT
import com.bytelegend.client.app.engine.MOUSE_OUT_OF_MAP_EVENT
import com.bytelegend.client.app.engine.MouseEventListener
import kotlinx.css.TextAlign
import kotlinx.css.minWidth
import kotlinx.css.px
import kotlinx.css.textAlign
import kotlinx.css.zIndex
import kotlinx.html.classes
import kotlinx.html.id
import react.RBuilder
import react.RState
import react.setState
import styled.css
import styled.styledSpan

interface MapCoordinateWidgetProps : GameProps

interface MapCoordinateWidgetState : RState {
    var humanReadableCoordinate: HumanReadableCoordinate?
}

/**
 * Display current coordinate of mouse cursor at left-top side.
 */
class MapCoordinateTitleWidget : GameUIComponent<MapCoordinateWidgetProps, MapCoordinateWidgetState>() {
    private val mouseMoveListener: MouseEventListener = {
        setState {
            humanReadableCoordinate = HumanReadableCoordinate(it.mapCoordinate)
        }
    }
    private val scrollEventListener: EventListener<Direction> = {
        if (it != Direction.NONE) {
            // mouse moves to scroll button area
            setState {
                humanReadableCoordinate = undefined
            }
        }
    }
    private val mouseOutOfMapListener: EventListener<Any> = {
        setState {
            humanReadableCoordinate = undefined
        }
    }

    override fun componentDidMount() {
        props.game.eventBus.on(MOUSE_MOVE_EVENT, mouseMoveListener)
        props.game.eventBus.on(com.bytelegend.client.app.engine.MAP_SCROLL_EVENT, scrollEventListener)
        props.game.eventBus.on(MOUSE_OUT_OF_MAP_EVENT, mouseOutOfMapListener)
    }

    override fun componentWillUnmount() {
        props.game.eventBus.remove(MOUSE_MOVE_EVENT, mouseMoveListener)
        props.game.eventBus.remove(com.bytelegend.client.app.engine.MAP_SCROLL_EVENT, scrollEventListener)
        props.game.eventBus.remove(MOUSE_OUT_OF_MAP_EVENT, mouseOutOfMapListener)
    }

    override fun RBuilder.render() {
        styledSpan {
            attrs.id = "map-coordinate"
            attrs.classes = setOf("map-title-widget", "map-title-text")
            css {
                zIndex = Layer.MapTitle.zIndex()
                minWidth = 60.px
                textAlign = TextAlign.center
            }

            if (state.humanReadableCoordinate == undefined) {
                +" - "
            } else {
                +state.humanReadableCoordinate.toString()
            }
        }
    }
}
