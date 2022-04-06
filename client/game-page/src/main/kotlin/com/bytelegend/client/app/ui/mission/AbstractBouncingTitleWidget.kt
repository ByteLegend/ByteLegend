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
package com.bytelegend.client.app.ui.mission

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.client.app.engine.MOUSE_OUT_OF_MAP_EVENT
import com.bytelegend.client.app.ui.Layer
import com.bytelegend.client.app.ui.absoluteDiv
import com.bytelegend.client.app.ui.jsStyle
import com.bytelegend.client.app.ui.setState
import com.bytelegend.client.app.ui.unsafeSpan
import kotlinx.js.jso
import react.ChildrenBuilder
import react.Component
import react.Props
import react.State

interface BouncingTitleProps : Props {
    var title: String
    var backgroundColor: String
    var color: String
    var gameScene: GameScene
    var onClickFunction: UnitFunction?

    // The coordinate to put the bouncing title
    var pixelCoordinate: PixelCoordinate
}

interface BouncingTitleState : State {
    var hovered: Boolean
}

abstract class AbstractBouncingTitleWidget<R : BouncingTitleProps, S : BouncingTitleState>(props: R) : Component<R, S>(props) {
    private val mouseOutOfMapListener: EventListener<Any> = {
        setState { hovered = false }
    }

    init {
        state = jso { hovered = false }
    }

    protected fun ChildrenBuilder.renderTitle(
        block: ChildrenBuilder.() -> Unit
    ) {
        val backgroundColor = props.backgroundColor
        val color = props.color
        val borderStyle = "1px solid $color"
        absoluteDiv(
            left = props.pixelCoordinate.x,
            bottom = props.gameScene.map.pixelSize.height - props.pixelCoordinate.y + 4, // extra 4px to avoid misclicking
            zIndex = Layer.BouncingTitle.zIndex() + if (state.hovered) 1 else 0,
            className = "bouncing-title"
        ) {
            unsafeSpan(props.title)
            it.onClick = {
                if (props.onClickFunction != null) {
                    props.onClickFunction!!()
                }
                it.stopPropagation()
            }
            it.onMouseOut = {
                setState { hovered = false }
                it.stopPropagation()
            }
            it.onMouseMove = {
                setState { hovered = true }
                it.stopPropagation()
            }

            it.jsStyle {
                this.color = color
                this.backgroundColor = backgroundColor
                borderLeft = borderStyle
                borderRight = borderStyle
                borderTop = borderStyle
            }

            if (state.hovered) {
                it.jsStyle {
                    boxShadow = "0 0 20px white"
                    this.color = color
                    this.backgroundColor = backgroundColor
                    borderLeft = borderStyle
                    borderRight = borderStyle
                    borderTop = borderStyle
                }
            } else {
                it.jsStyle {
                    this.color = color
                    this.backgroundColor = backgroundColor
                    borderLeft = borderStyle
                    borderRight = borderStyle
                    borderTop = borderStyle
                }
            }
            absoluteDiv(
                zIndex = Layer.BouncingTitle.zIndex(),
                className = "bouncing-title-bottom-border bouncing-title-bottom-border-left"
            ) {
                it.jsStyle {
                    borderBottom = borderStyle
                }
            }
            absoluteDiv(
                zIndex = Layer.BouncingTitle.zIndex(),
                className = "bouncing-title-bottom-border bouncing-title-bottom-border-right"
            ) {
                it.jsStyle {
                    borderBottom = borderStyle
                }
            }

            absoluteDiv(
                zIndex = Layer.BouncingTitle.zIndex() + 2,
                className = "bouncing-title-triangle-container"
            ) {
                absoluteDiv(
                    left = 0,
                    top = 0,
                    width = 0,
                    height = 0,
                    className = "bouncing-title-triangle"
                ) {
                    it.jsStyle {
                        borderBottom = "8px solid $backgroundColor"
                    }
                }
            }

            block()
        }
    }

    override fun shouldComponentUpdate(nextProps: R, nextState: S): Boolean {
        return props.pixelCoordinate != nextProps.pixelCoordinate ||
            props.title != nextProps.title ||
            state.hovered != nextState.hovered
    }

    override fun componentDidMount() {
        props.gameScene.gameRuntime.eventBus.on(MOUSE_OUT_OF_MAP_EVENT, mouseOutOfMapListener)
    }

    override fun componentWillUnmount() {
        props.gameScene.gameRuntime.eventBus.remove(MOUSE_OUT_OF_MAP_EVENT, mouseOutOfMapListener)
    }
}
