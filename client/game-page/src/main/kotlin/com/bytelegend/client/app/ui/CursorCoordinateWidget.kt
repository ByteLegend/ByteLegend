package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.shared.Direction
import com.bytelegend.app.shared.HumanReadableCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.client.app.engine.MAP_SCROLL_EVENT
import com.bytelegend.client.app.engine.MOUSE_MOVE_EVENT
import com.bytelegend.client.app.engine.MOUSE_OUT_OF_MAP_EVENT
import com.bytelegend.client.app.engine.MouseEventListener
import kotlinext.js.js
import kotlinx.html.style
import react.RBuilder
import react.RState
import react.dom.span
import react.setState

val WIDGET_WIDTH_PIXEL = 80
val WIDGET_HEIGHT_PIXEL = 40
val WIDGET_OFFSET_X = 20
val WIDGET_OFFSET_Y = 20

interface CursorCoordinateWidgetState : RState {
    var cursorCoordinateOnCanvas: PixelCoordinate?
    var humanReadableCoordinate: HumanReadableCoordinate
}

interface CursorCoordinateWidgetProps : GameProps

/**
 * The semi-transparent widget moving with mouse cursor.
 */
class CursorCoordinateWidget : GameUIComponent<CursorCoordinateWidgetProps, CursorCoordinateWidgetState>() {
    private val mouseMoveListener: MouseEventListener = {
        setState {
            cursorCoordinateOnCanvas = it.canvasCoordinate
            humanReadableCoordinate = HumanReadableCoordinate(it.mapCoordinate)
        }
    }
    private val scrollEventListener: EventListener<Direction> = {
        if (it != Direction.NONE) {
            // mouse moves to scroll button area
            setState {
                cursorCoordinateOnCanvas = undefined
            }
        }
    }
    private val mouseOutOfMapListener: EventListener<Any> = {
        setState {
            cursorCoordinateOnCanvas = undefined
        }
    }

    override fun componentDidMount() {
        props.game.eventBus.on(MOUSE_MOVE_EVENT, mouseMoveListener)
        props.game.eventBus.on(MAP_SCROLL_EVENT, scrollEventListener)
        props.game.eventBus.on(MOUSE_OUT_OF_MAP_EVENT, mouseOutOfMapListener)
    }

    override fun componentWillUnmount() {
        props.game.eventBus.remove(MOUSE_MOVE_EVENT, mouseMoveListener)
        props.game.eventBus.remove(MAP_SCROLL_EVENT, scrollEventListener)
        props.game.eventBus.remove(MOUSE_OUT_OF_MAP_EVENT, mouseOutOfMapListener)
    }

    @Suppress("UnsafeCastFromDynamic")
    override fun RBuilder.render() {
        if (state.cursorCoordinateOnCanvas != undefined) {
            span {
                attrs {
                    /*
                    To avoid:

                    Over 200 classes were generated for component styled.div. Consider using the `attrs` method, together with a style object for frequently changed styles.
                     */
                    val z = Layer.CursorWidget.zIndex()
                    style = js {
                        position = "absolute"
                        left = "${calculateWidgetCoordinate().x}px"
                        top = "${calculateWidgetCoordinate().y}px"
                        width = "${WIDGET_WIDTH_PIXEL}px"
                        height = "${WIDGET_HEIGHT_PIXEL}px"
                        backgroundColor = "black"
                        color = "white"
                        opacity = "0.8"
                        border = "3px solid"
                        borderRadius = "5px"
                        display = "flex"
                        justifyContent = "center"
                        alignItems = "center"
                        zIndex = "$z"
                    }
                }

                +state.humanReadableCoordinate.toString()
            }
        }
    }

    private fun calculateWidgetCoordinate(): PixelCoordinate {
        var left = state.cursorCoordinateOnCanvas!!.x + WIDGET_OFFSET_X
        if (left > gameContainerWidth - WIDGET_WIDTH_PIXEL) {
            left -= WIDGET_WIDTH_PIXEL
        }

        var top = state.cursorCoordinateOnCanvas!!.y + WIDGET_OFFSET_Y
        if (top > gameContainerHeight - WIDGET_HEIGHT_PIXEL) {
            top -= WIDGET_HEIGHT_PIXEL
        }
        return PixelCoordinate(left, top)
    }
}
