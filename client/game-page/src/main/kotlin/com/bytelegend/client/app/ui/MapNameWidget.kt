package com.bytelegend.client.app.ui

import kotlinx.css.Position
import kotlinx.css.left
import kotlinx.css.position
import kotlinx.css.px
import kotlinx.css.top
import kotlinx.css.zIndex
import kotlinx.html.classes
import kotlinx.html.id
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
interface MapTitleWidgetsProps : GameProps

class MapTitleWidgets : GameUIComponent<MapTitleWidgetsProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            attrs.id = "map-title"
            attrs.classes = setOf("map-title")
            css {
                zIndex = Layer.MapTitle.zIndex()
                position = Position.absolute
                left = (uiContainerCoordinateInGameContainer.x + 10).px
                top = (uiContainerCoordinateInGameContainer.y + 10).px
//                fontSize = props.game.tileSize.height.px
//                fontWeight = FontWeight.bold
//                declarations["textShadow"] = "-1px 0 white, 0 1px white, 1px 0 white, 0 -1px white;"
            }
            children()
        }
    }
}
