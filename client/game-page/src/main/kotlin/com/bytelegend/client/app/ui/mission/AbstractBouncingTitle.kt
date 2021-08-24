package com.bytelegend.client.app.ui.mission

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.client.app.engine.MOUSE_OUT_OF_MAP_EVENT
import com.bytelegend.client.app.ui.Layer
import com.bytelegend.client.app.ui.absoluteDiv
import com.bytelegend.client.app.ui.unsafeSpan
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onMouseMoveFunction
import kotlinx.html.js.onMouseOutFunction
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.jsStyle
import react.setState

interface BouncingTitleProps : RProps {
    var title: String
    var backgroundColor: String
    var color: String
    var gameScene: GameScene
    var onClickFunction: UnitFunction?

    // The coordinate to put the bouncing title
    var pixelCoordinate: PixelCoordinate
}

interface BouncingTitleState : RState {
    var hovered: Boolean
}

abstract class AbstractBouncingTitle<R : BouncingTitleProps> : RComponent<R, BouncingTitleState>() {
    private val mouseOutOfMapListener: EventListener<Any> = {
        setState { hovered = false }
    }

    override fun BouncingTitleState.init() {
        hovered = false
    }

    protected fun RBuilder.renderTitle(
        block: RBuilder.() -> Unit
    ) {
        val backgroundColor = props.backgroundColor
        val color = props.color
        val borderStyle = "1px solid $color"
        absoluteDiv(
            left = props.pixelCoordinate.x,
            bottom = props.gameScene.map.pixelSize.height - props.pixelCoordinate.y + 4, // extra 4px to avoid misclicking
            zIndex = Layer.BouncingTitle.zIndex() + if (state.hovered) 1 else 0,
            classes = jsObjectBackedSetOf("bouncing-title")
        ) {
            unsafeSpan(props.title)
            attrs.onClickFunction = {
                if (props.onClickFunction != null) {
                    props.onClickFunction!!()
                }
                it.stopPropagation()
            }
            attrs.onMouseOutFunction = {
                setState { hovered = false }
                it.stopPropagation()
            }
            attrs.onMouseMoveFunction = {
                setState { hovered = true }
                it.stopPropagation()
            }

            attrs.jsStyle {
                this.color = color
                this.backgroundColor = backgroundColor
                borderLeft = borderStyle
                borderRight = borderStyle
                borderTop = borderStyle
            }

            if (state.hovered) {
                attrs.jsStyle {
                    boxShadow = "0 0 20px white"
                    this.color = color
                    this.backgroundColor = backgroundColor
                    borderLeft = borderStyle
                    borderRight = borderStyle
                    borderTop = borderStyle
                }
            } else {
                attrs.jsStyle {
                    this.color = color
                    this.backgroundColor = backgroundColor
                    borderLeft = borderStyle
                    borderRight = borderStyle
                    borderTop = borderStyle
                }
            }
            absoluteDiv(
                zIndex = Layer.BouncingTitle.zIndex(),
                classes = jsObjectBackedSetOf("bouncing-title-bottom-border", "bouncing-title-bottom-border-left")
            ) {
                attrs.jsStyle {
                    borderBottom = borderStyle
                }
            }
            absoluteDiv(
                zIndex = Layer.BouncingTitle.zIndex(),
                classes = jsObjectBackedSetOf("bouncing-title-bottom-border", "bouncing-title-bottom-border-right")
            ) {
                attrs.jsStyle {
                    borderBottom = borderStyle
                }
            }

            absoluteDiv(
                zIndex = Layer.BouncingTitle.zIndex() + 2,
                classes = jsObjectBackedSetOf("bouncing-title-triangle-container")
            ) {
                absoluteDiv(
                    left = 0,
                    top = 0,
                    width = 0,
                    height = 0,
                    classes = jsObjectBackedSetOf("bouncing-title-triangle")
                ) {
                    attrs.jsStyle {
                        borderBottom = "8px solid $backgroundColor"
                    }
                }
            }

            block()
        }
    }

    override fun shouldComponentUpdate(nextProps: R, nextState: BouncingTitleState): Boolean {
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
