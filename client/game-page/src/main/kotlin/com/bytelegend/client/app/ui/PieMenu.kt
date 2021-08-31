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

import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.client.app.obj.onMouseEnterFunction
import com.bytelegend.client.app.obj.onMouseLeaveFunction
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinext.js.assign
import kotlinx.browser.window
import kotlinx.html.classes
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.jsStyle
import react.dom.span
import react.setState
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

private const val MENU_RADIUS_PX = 240
private const val MENU_BUTTON_DISTANCE = 80
private const val MENU_BUTTON_TITLE_DISTANCE = 160
private const val MENU_ITEM_BUTTON_PX = 64
private const val MENU_CLOSE_BUTTON_PX = 32

// how long it takes for the animation to finish
private const val ANIMATION_DURATION_MS = 300
private const val ANIMATION_INTERVAL = 50
const val WHITE_CLOSE_ICON =
    "data:image/svg+xml,%3Csvg class='icon' viewBox='0 0 1024 1024' xmlns='http://www.w3.org/2000/svg' width='200' height='200'%3E%3Cpath d='M512.008 422.3L915.699 18.61a63.437 63.437 0 1 1 89.707 89.707l-403.69 403.691 403.69 403.691a63.437 63.437 0 0 1-89.707 89.707l-403.691-403.69-403.691 403.69A63.437 63.437 0 0 1 18.61 915.7L422.3 512.008 18.61 108.317a63.437 63.437 0 1 1 89.707-89.707L512.008 422.3z' fill='%23fff'/%3E%3C/svg%3E"

interface PieMenuProps : RProps {
    var centerPoint: PixelCoordinate
    var items: List<PieMenuItem>
    var zIndex: Int
    var onClose: UnitFunction
}

interface PieMenuState : RState {
    // 0.0-1.0
    var ratio: Double
    var timerId: Int
}

class PieMenuItem(
    val title: String,
    val iconClass: String,
    val onClick: UnitFunction
)

interface PieMenuCloseButtonProps : RProps {
    //    var centerPoint: PixelCoordinate
    var zIndex: Int

    // in degree, -45~0
    var angle: Int
    var onCloseButtonClicked: UnitFunction
}

class PieMenuCloseButton : RComponent<PieMenuCloseButtonProps, PieMenuButtonState>() {
    override fun PieMenuButtonState.init() {
        hovered = false
    }

    override fun RBuilder.render() {
        val size = if (state.hovered) MENU_CLOSE_BUTTON_PX * 1.5 else MENU_CLOSE_BUTTON_PX
        div {
            attrs.jsStyle {
                position = "absolute"
                zIndex = props.zIndex.toString()
                width = "${size}px"
                height = "${size}px"
                backgroundImage = "url(\"${WHITE_CLOSE_ICON}\")"
                backgroundSize = "100% 100%"
                left = "0"
                top = "0"
                borderRadius = "50%"
                transform = "translate(-50%, -50%) rotate(${props.angle}deg)"
                cursor = "pointer"
            }
            attrs.onMouseEnterFunction = {
                setState { hovered = true }
            }
            attrs.onMouseLeaveFunction = {
                setState { hovered = false }
            }
            attrs.onClickFunction = {
                props.onCloseButtonClicked()
            }
        }
    }
}

interface PieMenuButtonProps : RProps {
    // pixel size to the origin
    var radius: Int

    // 0~360
    var angle: Int
    var size: Int
    var zIndex: Int
    var item: PieMenuItem
}

interface PieMenuButtonState : RState {
    var hovered: Boolean
}

class PieMenuButton : RComponent<PieMenuButtonProps, PieMenuButtonState>() {
    override fun PieMenuButtonState.init() {
        hovered = false
    }

    // Based on current radius and angle, calculate a
    private fun calculateCoordinate(radius: Int): PixelCoordinate {
        val x = radius * sin(PI * props.angle / 180)
        val y = -radius * cos(PI * props.angle / 180)
        return PixelCoordinate(x.toInt(), y.toInt())
    }

    override fun RBuilder.render() {
        val size = if (state.hovered) props.size * 1.2 else props.size
        val center = calculateCoordinate(props.radius)
        val commonStyle: dynamic = kotlinext.js.jsObject {
            position = "absolute"
            width = "${size}px"
            height = "${size}px"
            left = "${center.x}px"
            top = "${center.y}px"
            borderRadius = "50%"
            transform = "translate(-50%, -50%)"
            cursor = "pointer"
            userSelect = "none"
        }

        div {
            attrs.jsStyle = assign(commonStyle) {
                zIndex = (props.zIndex + 1).toString()
                backgroundColor = "rgba(0,0,0,0.7)"
                if (state.hovered) {
                    border = "2px solid white"
                }
            }
        }
        div {
            attrs.classes = jsObjectBackedSetOf(props.item.iconClass)
            attrs.jsStyle = assign(commonStyle) {
                zIndex = (props.zIndex + 2).toString()
                backgroundSize = "100% 100%"
                cursor = "pointer"
            }
            attrs.onMouseEnterFunction = {
                setState { hovered = true }
            }
            attrs.onMouseLeaveFunction = {
                setState { hovered = false }
            }
            attrs.onClickFunction = {
                props.item.onClick()
            }
        }

        if (state.hovered) {
            val titleCenter = calculateCoordinate(MENU_BUTTON_TITLE_DISTANCE)
            span {
                attrs.jsStyle {
                    position = "absolute"
                    zIndex = (props.zIndex + 3).toString()
                    backgroundColor = "rgba(0,0,0,0.7)"
                    color = "white"
                    left = "${titleCenter.x}px"
                    top = "${titleCenter.y}px"
                    borderRadius = "5px"
                    padding = "10px"
                    transform = "translate(-50%, -50%)"
                    whiteSpace = "nowrap"
                }
                +props.item.title
            }
        }
    }
}

class PieMenu : RComponent<PieMenuProps, PieMenuState>() {
    override fun PieMenuState.init() {
        ratio = 0.0
        timerId = window.setInterval({
            var newRatio = state.ratio + 1.0 / (ANIMATION_DURATION_MS / ANIMATION_INTERVAL)
            if (newRatio >= 1.0) {
                newRatio = 1.0
                window.clearInterval(timerId)
            }
            setState {
                ratio = newRatio
            }
        }, ANIMATION_INTERVAL)
    }

    override fun RBuilder.render() {
        div {
            // this is a virtual div used to locate the child elements
            // so that they can use origin (0,0)
            attrs.jsStyle {
                position = "absolute"
                left = "${props.centerPoint.x}px"
                top = "${props.centerPoint.y}px"
            }

            div {
                attrs.jsStyle {
                    position = "absolute"
                    zIndex = props.zIndex.toString()
                    width = "${MENU_RADIUS_PX * state.ratio}px"
                    height = "${MENU_RADIUS_PX * state.ratio}px"
                    backgroundColor = "rgba(0,0,0,0.5)"
                    borderRadius = "50%"
                    transform = "translate(-50%, -50%)"
                }
            }
            child(PieMenuCloseButton::class) {
                attrs.zIndex = props.zIndex + 1
                attrs.angle = (-45 + state.ratio * 45).toInt()
                attrs.onCloseButtonClicked = props.onClose
            }

            val anglePerButton = 360 / props.items.size
            props.items.forEachIndexed { index, item ->
                child(PieMenuButton::class) {
                    attrs.zIndex = props.zIndex + 1
                    attrs.item = item
                    attrs.size = MENU_ITEM_BUTTON_PX
                    attrs.angle = index * anglePerButton
                    attrs.radius = (state.ratio * MENU_BUTTON_DISTANCE).toInt()
                }
            }
        }
    }
}
