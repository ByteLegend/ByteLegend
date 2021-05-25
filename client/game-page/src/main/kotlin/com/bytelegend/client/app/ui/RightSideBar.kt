package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.GameCanvasState
import com.bytelegend.app.client.ui.bootstrap.BootstrapListGroup
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.client.app.engine.util.jsObjectBackedSetOf
import react.RBuilder
import react.RState

interface RightSideBarProps : GameProps

// Note this returns (right, top), not (left, top)
fun GameCanvasState.determineRightSideBarCoordinateInGameContainerRightTop() = PixelCoordinate(
    getUICoordinateInGameContainer().x,
    getUICoordinateInGameContainer().y + AVATAR_HEIGHT
)

fun GameCanvasState.determineRightSideBarTopLeftCornerCoordinateInGameContainer() =
    determineRightSideBarCoordinateInGameContainerRightTop().let {
        PixelCoordinate(gameContainerSize.width - it.x - 80, it.y)
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
            classes = jsObjectBackedSetOf("right-sidebar")
        ) {
            BootstrapListGroup {
                children()
            }
        }
    }
}
