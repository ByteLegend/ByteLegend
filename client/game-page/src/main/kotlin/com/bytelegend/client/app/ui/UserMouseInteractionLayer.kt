package com.bytelegend.client.app.ui

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

interface UserMouseInteractionLayerProps : GameProps

class UserMouseInteractionLayer : LayeredGameUIComponent<UserMouseInteractionLayerProps, RState>() {
    override fun RBuilder.render() {
        absoluteDiv(
            canvasCoordinateInGameContainer.x, canvasCoordinateInGameContainer.y,
            canvasPixelSize.width, canvasPixelSize.height,
            Layer.UserMouseInteraction.zIndex(), "1", setOf("user-mouse-interaction-layer")
        ) {
            attrs {
                id = "user-mouse-interaction-layer"
                onClickFunction = {
                    gameControl.onMouseClickOnCanvas(toGameMouseEvent(it))
                }
                onMouseMoveFunction = {
                    gameControl.onMouseMoveOnCanvas(toGameMouseEvent(it))
                }
                onMouseOutFunction = {
                    gameControl.onMouseMoveOutOfCanvas()
                }
            }
        }
    }
}
