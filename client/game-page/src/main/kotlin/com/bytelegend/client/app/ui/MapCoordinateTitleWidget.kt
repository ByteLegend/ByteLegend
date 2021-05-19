package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.shared.Direction
import com.bytelegend.app.shared.HumanReadableCoordinate
import com.bytelegend.client.app.engine.GAME_CLOCK_10HZ_EVENT
import com.bytelegend.client.app.engine.MAP_SCROLL_EVENT
import com.bytelegend.client.app.engine.MOUSE_MOVE_EVENT
import com.bytelegend.client.app.engine.MOUSE_OUT_OF_MAP_EVENT
import com.bytelegend.client.app.engine.MouseEventListener
import kotlinx.html.classes
import kotlinx.html.id
import react.RBuilder
import react.RState
import react.dom.jsStyle
import react.dom.span
import react.setState

const val COORDINATE_BORDER_FLICKER = "coordinate.border.flicker.end"

interface MapCoordinateWidgetState : RState {
    var humanReadableCoordinate: HumanReadableCoordinate?
    var flickering: Boolean
}

/**
 * Display current coordinate of mouse cursor at left-top side.
 */
class MapCoordinateTitleWidget : GameUIComponent<GameProps, MapCoordinateWidgetState>() {
    private var counter: Int = 0
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
    private val on10HzListener: EventListener<Nothing> = {
        val oldState = (counter / 5) % 2
        val newState = (counter + 1 / 5) % 2
        counter++
        if (oldState != newState) {
            setState { }
        }
    }

    private val onBorderFlickerEventListener: EventListener<Boolean> = {
        setState { flickering = it }
    }

    override fun MapCoordinateWidgetState.init() {
        flickering = false
    }

    override fun componentDidMount() {
        props.game.eventBus.on(MOUSE_MOVE_EVENT, mouseMoveListener)
        props.game.eventBus.on(MAP_SCROLL_EVENT, scrollEventListener)
        props.game.eventBus.on(MOUSE_OUT_OF_MAP_EVENT, mouseOutOfMapListener)
        props.game.eventBus.on(GAME_CLOCK_10HZ_EVENT, on10HzListener)
        props.game.eventBus.on(COORDINATE_BORDER_FLICKER, onBorderFlickerEventListener)
    }

    override fun componentWillUnmount() {
        props.game.eventBus.remove(MOUSE_MOVE_EVENT, mouseMoveListener)
        props.game.eventBus.remove(MAP_SCROLL_EVENT, scrollEventListener)
        props.game.eventBus.remove(MOUSE_OUT_OF_MAP_EVENT, mouseOutOfMapListener)
        props.game.eventBus.remove(GAME_CLOCK_10HZ_EVENT, on10HzListener)
        props.game.eventBus.remove(COORDINATE_BORDER_FLICKER, onBorderFlickerEventListener)
    }

    override fun RBuilder.render() {
        span {
            attrs.id = "map-coordinate"
            if (!state.flickering || (counter / 5) % 2 == 0) {
                attrs.classes = setOf("map-title-widget", "map-title-text", "map-coordinate")
            } else {
                attrs.classes = setOf("map-title-widget", "map-title-text", "bordered-map-coordinate")
            }

            val z = Layer.MapTitle.zIndex().toString()
            attrs.jsStyle {
                zIndex = z
            }

            if (state.humanReadableCoordinate == undefined) {
                +" - "
            } else {
                +state.humanReadableCoordinate.toString()
            }
        }
    }
}
