package com.bytelegend.client.app.ui

import com.bytelegend.app.shared.Direction
import kotlinx.css.Position
import kotlinx.css.left
import kotlinx.css.position
import kotlinx.css.px
import kotlinx.css.right
import kotlinx.css.top
import kotlinx.css.zIndex
import react.RBuilder
import react.RState
import styled.css
import styled.styledDiv

/**
 * MapTitleWidget is a transparent widget on top-left side of map canvas, displaying important information, e.g:
 *
 * - Map name
 * - FPS
 * - Coordinate
 *
 */
interface MapTitleWidgetsProps : GameProps {
    // LEFT/RIGHT
    var direction: Direction
}

class MapTitleWidgets : GameUIComponent<MapTitleWidgetsProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            css {
                zIndex = Layer.MapTitle.zIndex()
                position = Position.absolute
                if (props.direction == Direction.LEFT) {
                    left = (uiContainerCoordinateInGameContainer.x + 10).px
                } else {
                    right = (uiContainerCoordinateInGameContainer.x + AVATAR_WIDTH).px
                }
                top = (uiContainerCoordinateInGameContainer.y + 10).px
            }
            children()
        }
    }
}
