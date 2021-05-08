package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.GameCanvasState
import com.bytelegend.app.client.ui.bootstrap.BootstrapListGroup
import com.bytelegend.app.shared.PixelCoordinate
import react.RBuilder
import react.RState

interface RightSideBarProps : GameProps

// Note this returns (right, top), not (left, top)
fun GameCanvasState.determineRightSideBarCoordinateInGameContainerRightTop() = PixelCoordinate(
    getUICoordinateInGameContainer().x,
    getUICoordinateInGameContainer().y + AVATAR_HEIGHT
)

fun GameCanvasState.determineRightSideBarCoordinateInGameContainerLeftTop() =
    determineRightSideBarCoordinateInGameContainerRightTop().let {
        PixelCoordinate(gameContainerSize.width - it.x, it.y)
    }

class RightSideBar : GameUIComponent<RightSideBarProps, RState>() {
    override fun RBuilder.render() {
        if (game.heroPlayer.isAnonymous) {
            return
        }
        absoluteDiv(
            right = gameCanvasState.determineRightSideBarCoordinateInGameContainerRightTop().x,
            top = gameCanvasState.determineRightSideBarCoordinateInGameContainerRightTop().y,
            zIndex = Layer.RightSideBar.zIndex(),
            classes = setOf("right-sidebar")
        ) {
            BootstrapListGroup {
                children()
            }
        }
    }
}
