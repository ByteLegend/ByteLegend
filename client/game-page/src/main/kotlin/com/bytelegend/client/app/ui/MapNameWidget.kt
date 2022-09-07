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

import com.bytelegend.app.shared.Direction
import react.Fragment
import react.State
import react.create
import react.dom.html.ReactHTML.div

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

class MapTitleWidgets : GameUIComponent<MapTitleWidgetsProps, State>() {
    override fun render() = Fragment.create {
        div {
            val z = Layer.MapTitle.zIndex()
            jsStyle {
                zIndex = z
                position = "absolute"
                if (props.direction.name == "LEFT") {
                    left = "${uiContainerCoordinateInGameContainer.x + 10}px"
                } else {
                    right = "${uiContainerCoordinateInGameContainer.x + AVATAR_WIDTH}px"
                }
                top = "${uiContainerCoordinateInGameContainer.y + 10}px"
            }
            child(props.children)
        }
    }
}
