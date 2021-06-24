package com.bytelegend.client.app.ui

import com.bytelegend.app.shared.Direction
import react.RBuilder
import react.RState
import react.dom.div
import react.dom.jsStyle

/**
 * MapTitleWidget is a transparent widget on top-left side of map canvas, displaying important information, e.g:
 *
 * - Map name
 * - FPS
 * - Online player number
 * - Coordinate
 *
 */
interface MapTitleWidgetsProps : GameProps {
    // LEFT/RIGHT
    var direction: Direction
}

class MapTitleWidgets : GameUIComponent<MapTitleWidgetsProps, RState>() {
    override fun RBuilder.render() {
        div {
            val z = Layer.MapTitle.zIndex()
            attrs.jsStyle {
                zIndex = z
                position = "absolute"
                if (props.direction.name == "LEFT") {
                    left = "${uiContainerCoordinateInGameContainer.x + 10}px"
                } else {
                    right = "${uiContainerCoordinateInGameContainer.x + AVATAR_WIDTH}px"
                }
                top = "${uiContainerCoordinateInGameContainer.y + 10}px"
            }
            children()
        }
    }
}
