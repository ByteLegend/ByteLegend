/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.GameCanvasState
import com.bytelegend.app.client.ui.bootstrap.BootstrapListGroup
import com.bytelegend.app.shared.PixelCoordinate
import react.Fragment
import react.State
import react.create

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

class RightSideBar : GameUIComponent<RightSideBarProps, State>() {
    override fun render() = Fragment.create {
        if (game.heroPlayer.isAnonymous) {
            return@create
        }
        absoluteDiv(
            right = gameCanvasState.determineRightSideBarCoordinateInGameContainerRightTop().x,
            top = gameCanvasState.determineRightSideBarCoordinateInGameContainerRightTop().y,
            zIndex = Layer.RightSideBar.zIndex(),
            className = "right-sidebar"
        ) {
            BootstrapListGroup {
                child(props.children)
            }
        }
    }
}
