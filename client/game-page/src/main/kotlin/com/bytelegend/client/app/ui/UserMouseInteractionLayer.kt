package com.bytelegend.client.app.ui

import com.bytelegend.client.app.engine.MOUSE_CLICK_EVENT
import com.bytelegend.client.app.engine.MOUSE_DOUBLE_CLICK_EVENT
import com.bytelegend.client.app.engine.MOUSE_MOVE_EVENT
import com.bytelegend.client.app.engine.MOUSE_OUT_OF_MAP_EVENT
import com.bytelegend.client.app.engine.toGameMouseEvent
import kotlinx.browser.window
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onMouseMoveFunction
import kotlinx.html.js.onMouseOutFunction
import react.RBuilder
import react.RState

/*
 This layer is a transparent layer on top of map canvas layer, it responds to user mouse interaction
 and converts game events.


  -------------------------------------------------------------------------------------------------------------
  |<---------------------------------------------  window.innerWidth ----------------------------------------->|
  |  --------------------------------------------------------------------------------------------------------  |
  |  |                                               gap                                                    |  |
  |  --------------------------------------------------------------------------------------------------------  |
  |                                                                                                            |
  |            ----------------------------------------------------------------------------------              |
  |            |<-------------- map canvas, must be integer multiple of tile size -------------->|             |
  |  -------   |                                                                                 |  ---------  |
  |  | gap |   |                                                                                 |  |  gap  |  |
  |  |     |   |                                                                                 |  |       |  |
  |  |     |   |                                                                                 |  |       |  |
  |  |     |   |                                                                                 |  |       |  |
  |  |     |   |                                                                                 |  |       |  |
  |  |     |   |                                                                                 |  |       |  |
  |  |     |   |                                                                                 |  |       |  |
  |  |     |   |                                 map canvas                                      |  |       |  |
  |  |     |   |          only mouse events in this area are responded by this class             |  |       |  |
  |  |     |   |                                                                                 |  |       |  |
  |  |     |   |                                                                                 |  |       |  |
  |  |     |   |                                                                                 |  |       |  |
  |  |     |   |                                                                                 |  |       |  |
  |  |     |   |                                                                                 |  |       |  |
  |  |     |   |                                                                                 |  |       |  |
  |  |     |   |                                                                                 |  |       |  |
  |  -------   |                                                                                 |  ---------  |
  |            |                                                                                 |             |
  |            -----------------------------------------------------------------------------------             |
  |                                                                                                            |
  |  --------------------------------------------------------------------------------------------------------  |
  |  |                                               gap                                                    |  |
  |  --------------------------------------------------------------------------------------------------------  |
  |------------------------------------------------------------------------------------------------------------|

  Map canvas is in center of browser window, mouse events inside this area will be converted to GameMouseEvent (a special
  kind of event which carries game coordinate information):
  1. mouse.move -> GameMouseEvent
  2. mouse.click -> GameMouseEvent

  Note that you don't need to respond to window.onresize event, Game.viewport responds to that event.
 */

interface UserMouseInteractionLayerProps : GameProps {
    /**
     * If true, the double click events will be omitted, with a 300ms delay to avoid mixing with click events.
     */
    var allowDoubleClick: Boolean
}

const val DOUBLE_CLICK_MAX_INTERVAL_MS = 300

class UserMouseInteractionLayer : LayeredGameUIComponent<UserMouseInteractionLayerProps, RState>() {
    private var clicks = 0
    override fun RBuilder.render() {
        absoluteDiv(
            canvasCoordinateInGameContainer.x, canvasCoordinateInGameContainer.y,
            canvasPixelSize.width, canvasPixelSize.height,
            Layer.UserMouseInteraction.zIndex(), "1", setOf("user-mouse-interaction-layer")
        ) {
            attrs {
                id = "user-mouse-interaction-layer"
                onClickFunction = {
                    val gameMouseEvent = toGameMouseEvent(it)
                    if (!props.allowDoubleClick) {
                        props.game.eventBus.emit(MOUSE_CLICK_EVENT, gameMouseEvent)
                    } else {
                        clicks++
                        if (clicks == 1) {
                            window.setTimeout(
                                {
                                    if (clicks == 1) {
                                        props.game.eventBus.emit(MOUSE_CLICK_EVENT, gameMouseEvent)
                                    } else {
                                        props.game.eventBus.emit(MOUSE_DOUBLE_CLICK_EVENT, gameMouseEvent)
                                    }
                                    clicks = 0
                                },
                                DOUBLE_CLICK_MAX_INTERVAL_MS
                            )
                        }
                    }
                }
                onMouseMoveFunction = {
                    props.game.eventBus.emit(MOUSE_MOVE_EVENT, props.game.toGameMouseEvent(it))
                }
                onMouseOutFunction = {
                    props.game.eventBus.emit(MOUSE_OUT_OF_MAP_EVENT, null)
                }
            }
        }
    }
}
