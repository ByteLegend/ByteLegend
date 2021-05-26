package com.bytelegend.client.app.ui

import com.bytelegend.app.shared.Direction
import com.bytelegend.app.shared.Direction.DOWN
import com.bytelegend.app.shared.Direction.LEFT
import com.bytelegend.app.shared.Direction.LEFT_DOWN
import com.bytelegend.app.shared.Direction.LEFT_UP
import com.bytelegend.app.shared.Direction.NONE
import com.bytelegend.app.shared.Direction.RIGHT
import com.bytelegend.app.shared.Direction.RIGHT_DOWN
import com.bytelegend.app.shared.Direction.RIGHT_UP
import com.bytelegend.app.shared.Direction.UP
import com.bytelegend.client.app.engine.util.jsObjectBackedSetOf
import kotlinx.html.js.onBlurFunction
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onFocusFunction
import kotlinx.html.js.onMouseMoveFunction
import kotlinx.html.js.onMouseOutFunction
import kotlinx.html.js.onMouseOverFunction
import org.w3c.dom.events.Event
import react.RBuilder
import react.RState
import react.dom.attrs
import react.setState

/*
  ----------------------------------------------------------------------------------------------------------------------------
  |<---------------------------------------------  containerWidth ----------------------------------------------------------->|
  |  --------------------------------------------------------------------------------------------------------  -------------| |
  |  |                                               gap                                                    |  | sidebar    | |
  |  --------------------------------------------------------------------------------------------------------  | (optional) | |
  |                                                                                                            |            | |
  |            ----------------------------------------------------------------------------------              |            | |
  |            |<-------------- map canvas, must be integer multiple of tile size -------------->|             |            | |
  |  -------   |                                                                                 |  ---------  |            | |
  |  | gap |   |                                                                                 |  |  gap  |  |            | |
  |  |     |   |                                                                                 |  |       |  |            | |
  |  |     |   |                                                                                 |  |       |  |            | |
  |  |     |   |                                                                                 |  |       |  |            | |
  |  |     |   |                                                                                 |  |       |  |            | |
  |  |     |   |                                                                                 |  |       |  |            | |
  |  |     |   |                                                                                 |  |       |  |            | |
  |  |     |   |                                                                                 |  |       |  |            | |
  |  |     |   |                                                                                 |  |       |  |            | |
  |  |     |   |                                                                                 |  |       |  |            | |
  |  |     |   |                                                                                 |  |       |  |            | |
  |  |     |   |                                                                                 |  |       |  |            | |
  |  |     |   |                                                                                 |  |       |  |            | |
  |  |     |   |                                                                                 |  |       |  |            | |
  |  |     |   |                                                                                 |  |       |  |            | |
  |  |     |   |                                                                                 |  |       |  |            | |
  |  -------   |                                                                                 |  ---------  |            | |
  |            |                                                                                 |             |            | |
  |            -----------------------------------------------------------------------------------             |            | |
  |                                                                                                            |            | |
  |  --------------------------------------------------------------------------------------------------------  |            | |
  |  |                                               gap                                                    |  |            | |
  |  --------------------------------------------------------------------------------------------------------   ------------  |
  |---------------------------------------------------------------------------------------------------------------------------|

  There are gaps (aka. scroll buttons) between map canvas and browser window, filled by transparent divs, which can
  respond to mouse hover events and scroll the map.

  This layer triggers:
  1. "map.scroll" event with corresponding Direction when mouse cursor moves into these areas.
  2. "map.scroll" event with Direction.NONE when mouse cursor moves out of these areas.

  Note that when mouse cursor moves across the scroll button borders, the following events are triggered in sequence:
  map.scroll.start, UP -> map.scroll.end -> map.scroll.start, RIGHT_UP -> ...

  The gap width is calculated by:

  1. At least one tile size (TODO: at least 2~3 tile size on mobile devices)
  2. containerWidth - 2 * vertical scroll button width == M * tile width
  3. containerHeight - 2 * horizontal scroll button width == N * tile height

  i.e. the map canvas size must always be integer multiple of tile size
 */

const val MAP_SCROLL_EVENT = "map.scroll"

interface ScrollButtonsProps : GameProps {
    var includeDirections: List<Direction>
}

interface ScrollButtonsState : RState {
    var direction: Direction
}

// cursor-scroll-left-up
fun Direction.cursorCssClass() = if (this == NONE) "" else "cursor-scroll-${name.toLowerCase().replace('_', '-')}"

class ScrollButtonsLayer : GameUIComponent<ScrollButtonsProps, ScrollButtonsState>() {
    private val verticalButtonWidth
        get() = canvasCoordinateInGameContainer.x
    private val horizontalButtonHeight
        get() = canvasCoordinateInGameContainer.y

    override fun ScrollButtonsState.init() {
        direction = NONE
    }

    override fun RBuilder.render() {
        if (mapCoveredByCanvas) {
            return
        }

        // No div here because we're on top of user mouse interaction layer
        up()
        down()
        left()
        right()

        topLeftCorner()
        topRightCorner()
        bottomLeftCorner()
        bottomRightCorner()
    }

    private fun RBuilder.bottomLeftCorner() {
        scrollButton(
            0, gameContainerHeight * 3 / 4,
            verticalButtonWidth, gameContainerWidth / 4, LEFT_DOWN
        )
        scrollButton(
            0, gameContainerHeight - horizontalButtonHeight,
            gameContainerWidth / 4,
            horizontalButtonHeight, LEFT_DOWN
        )
    }

    private fun RBuilder.bottomRightCorner() {
        scrollButton(
            gameContainerWidth - verticalButtonWidth,
            gameContainerHeight * 3 / 4,
            verticalButtonWidth,
            gameContainerHeight / 4,
            RIGHT_DOWN
        )
        scrollButton(
            gameContainerWidth * 3 / 4,
            gameContainerHeight - horizontalButtonHeight,
            gameContainerWidth / 4,
            horizontalButtonHeight,
            RIGHT_DOWN
        )
    }

    private fun RBuilder.topRightCorner() {
        scrollButton(
            gameContainerWidth * 3 / 4, 0, props.game.gameContainerSize.width / 4,
            horizontalButtonHeight, RIGHT_UP
        )
        scrollButton(
            gameContainerWidth - verticalButtonWidth, 0,
            verticalButtonWidth, gameContainerHeight / 4, RIGHT_UP
        )
    }

    private fun RBuilder.topLeftCorner() {
        scrollButton(0, 0, gameContainerWidth / 4, horizontalButtonHeight, LEFT_UP)
        scrollButton(0, 0, verticalButtonWidth, gameContainerHeight / 4, LEFT_UP)
    }

    private fun RBuilder.down() {
        scrollButton(
            gameContainerWidth / 4,
            gameContainerHeight - horizontalButtonHeight,
            gameContainerWidth / 2,
            horizontalButtonHeight,
            DOWN
        )
    }

    private fun RBuilder.left() {
        scrollButton(
            0, gameContainerHeight / 4,
            verticalButtonWidth, gameContainerHeight / 2, LEFT
        )
    }

    private fun RBuilder.right() {
        scrollButton(
            gameContainerWidth - verticalButtonWidth,
            gameContainerHeight / 4,
            verticalButtonWidth,
            gameContainerHeight / 2,
            RIGHT
        )
    }

    private fun RBuilder.up() {
        scrollButton(
            gameContainerWidth / 4, 0, gameContainerWidth / 2,
            horizontalButtonHeight, UP
        )
    }

    private fun scrollEventListener(direction: Direction): (Event) -> Unit = {
        if (direction != state.direction) {
            setState {
                this.direction = direction
            }
        }

        game.eventBus.emit(MAP_SCROLL_EVENT, direction)
    }

    private fun RBuilder.scrollButton(left: Int, top: Int, width: Int, height: Int, direction: Direction) {
        if (props.includeDirections != undefined && !props.includeDirections.contains(direction)) {
            return
        }

        absoluteDiv(
            left = left,
            top = top,
            width = width,
            height = height,
            zIndex = Layer.ScrollButtons.zIndex(),
            opacity = "0",
            classes = jsObjectBackedSetOf(direction.cursorCssClass()),
            block = {
                attrs {
                    onMouseMoveFunction = scrollEventListener(direction)
                    onMouseOverFunction = scrollEventListener(direction)
                    onFocusFunction = scrollEventListener(direction)
                    onClickFunction = scrollEventListener(direction)

                    onMouseOutFunction = scrollEventListener(NONE)
                    onBlurFunction = scrollEventListener(NONE)
                }
            }
        )
    }
}
