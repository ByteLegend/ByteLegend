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
import csstype.ClassName
import kotlinext.js.assign
import kotlinx.js.jso
import kotlinx.browser.window
import react.Component
import react.Fragment
import react.Props
import react.State
import react.create
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.span
import react.react
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

interface PieMenuProps : Props {
    var centerPoint: PixelCoordinate
    var items: List<PieMenuItem>
    var zIndex: Int
    var onClose: UnitFunction
}

interface PieMenuState : State {
    // 0.0-1.0
    var ratio: Double
    var timerId: Int
}

class PieMenuItem(
    val title: String,
    val iconClass: String,
    val onClick: UnitFunction
)

interface PieMenuCloseButtonProps : Props {
    //    var centerPoint: PixelCoordinate
    var zIndex: Int

    // in degree, -45~0
    var angle: Int
    var onCloseButtonClicked: UnitFunction
}

class PieMenuCloseButton : Component<PieMenuCloseButtonProps, PieMenuButtonState>() {
    init {
        state = jso { hovered = false }
    }

    override fun render() = Fragment.create {
        val size = if (state.hovered) MENU_CLOSE_BUTTON_PX * 1.5 else MENU_CLOSE_BUTTON_PX
        div {
            className = ClassName("pie-menu-white-close-button")
            jsStyle {
                zIndex = props.zIndex.toString()
                width = "${size}px"
                height = "${size}px"
                transform = "translate(-50%, -50%) rotate(${props.angle}deg)"
            }
            onMouseEnter = {
                setState { hovered = true }
            }
            onMouseLeave = {
                setState { hovered = false }
            }
            onClick = {
                props.onCloseButtonClicked()
            }
        }
    }
}

interface PieMenuButtonProps : Props {
    // pixel size to the origin
    var radius: Int

    // 0~360
    var angle: Int
    var size: Int
    var zIndex: Int
    var item: PieMenuItem
}

interface PieMenuButtonState : State {
    var hovered: Boolean
}

class PieMenuButton : Component<PieMenuButtonProps, PieMenuButtonState>() {
    init {
        state = jso { hovered = false }
    }

    // Based on current radius and angle, calculate a
    private fun calculateCoordinate(radius: Int): PixelCoordinate {
        val x = radius * sin(PI * props.angle / 180)
        val y = -radius * cos(PI * props.angle / 180)
        return PixelCoordinate(x.toInt(), y.toInt())
    }

    override fun render() = Fragment.create {
        val size = if (state.hovered) props.size * 1.2 else props.size
        val center = calculateCoordinate(props.radius)
        val commonStyle: dynamic = jso {
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
            setJsStyle(assign(commonStyle) {
                zIndex = (props.zIndex + 1).toString()
                backgroundColor = "rgba(0,0,0,0.7)"
                if (state.hovered) {
                    border = "2px solid white"
                }
            })
        }
        div {
            className = ClassName(props.item.iconClass)
            setJsStyle(assign(commonStyle) {
                zIndex = (props.zIndex + 2).toString()
                backgroundSize = "100% 100%"
                cursor = "pointer"
            })
            onMouseEnter = {
                setState { hovered = true }
            }
            onMouseLeave = {
                setState { hovered = false }
            }
            onClick = {
                props.item.onClick()
            }
        }

        if (state.hovered) {
            val titleCenter = calculateCoordinate(MENU_BUTTON_TITLE_DISTANCE)
            span {
                jsStyle {
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

class PieMenu : Component<PieMenuProps, PieMenuState>() {
    init {
        state = jso {
            ratio = 0.0
            timerId = window.setInterval({
                var newRatio = state.ratio + 1.0 / (ANIMATION_DURATION_MS / ANIMATION_INTERVAL)
                if (newRatio >= 1.0) {
                    newRatio = 1.0
                    window.clearInterval(state.timerId)
                }
                setState {
                    ratio = newRatio
                }
            }, ANIMATION_INTERVAL)
        }
    }

    override fun render() = Fragment.create {
        div {
            // this is a virtual div used to locate the child elements
            // so that they can use origin (0,0)
            jsStyle {
                position = "absolute"
                left = "${props.centerPoint.x}px"
                top = "${props.centerPoint.y}px"
            }

            div {
                jsStyle {
                    position = "absolute"
                    zIndex = props.zIndex.toString()
                    width = "${MENU_RADIUS_PX * state.ratio}px"
                    height = "${MENU_RADIUS_PX * state.ratio}px"
                    backgroundColor = "rgba(0,0,0,0.5)"
                    borderRadius = "50%"
                    transform = "translate(-50%, -50%)"
                }
            }
            child(PieMenuCloseButton::class.react, jso {
                zIndex = props.zIndex + 1
                angle = (-45 + state.ratio * 45).toInt()
                onCloseButtonClicked = props.onClose
            })

            val anglePerButton = 360 / props.items.size
            props.items.forEachIndexed { index, item ->
                child(PieMenuButton::class.react, jso {
                    zIndex = props.zIndex + 1
                    this.item = item
                    size = MENU_ITEM_BUTTON_PX
                    angle = index * anglePerButton
                    radius = (state.ratio * MENU_BUTTON_DISTANCE).toInt()
                })
            }
        }
    }
}
