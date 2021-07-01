package com.bytelegend.client.app.ui.mission

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.client.app.engine.GameMission
import com.bytelegend.client.app.engine.MOUSE_MOVE_EVENT
import com.bytelegend.client.app.engine.MOUSE_OUT_OF_MAP_EVENT
import com.bytelegend.client.app.engine.MouseEventListener
import com.bytelegend.client.app.engine.util.jsObjectBackedSetOf
import com.bytelegend.client.app.page.game
import com.bytelegend.client.app.ui.Layer
import com.bytelegend.client.app.ui.absoluteDiv
import com.bytelegend.client.app.ui.unsafeSpan
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onMouseMoveFunction
import kotlinx.html.js.onMouseOutFunction
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.jsStyle
import react.setState

interface MissionTitleProps : RProps {
    // coordinate in game container
    var left: Int
    var bottom: Int

    // for animation
    var offsetY: Int
    var title: String
    var tileCoordinate: GridCoordinate
    var eventBus: EventBus

    var totalStar: Int
    var currentStar: Int
    var mission: GameMission
}

interface MissionTitleState : RState {
    var hovered: Boolean
}

class MissionTitle : RComponent<MissionTitleProps, MissionTitleState>() {
    private val mouseMoveListener: MouseEventListener = {
        if (it.mapCoordinate == props.tileCoordinate) {
            setState { hovered = true }
        } else {
            setState { hovered = false }
        }
    }
    private val mouseOutOfMapListener: EventListener<Any> = {
        setState {
            hovered = false
        }
    }

    override fun MissionTitleState.init() {
        hovered = false
    }

    private fun getOffsetY() = if (state.hovered) 0 else props.offsetY

    override fun RBuilder.render() {
        absoluteDiv(
            left = props.left,
            bottom = props.bottom + getOffsetY(),
            zIndex = Layer.MissionTitle.zIndex() + if (state.hovered) 1 else 0,
            classes = jsObjectBackedSetOf("mission-title")
        ) {
            unsafeSpan(props.title)
            attrs.onClickFunction = {
                props.mission.onClick()
            }
            attrs.onMouseOutFunction = {
                setState { hovered = false }
            }
            attrs.onMouseMoveFunction = {
                setState { hovered = true }
            }
            if (state.hovered) {
                attrs.jsStyle {
                    boxShadow = "0 0 20px white"
                }
            }
            absoluteDiv(
                zIndex = Layer.MissionTitle.zIndex(),
                classes = jsObjectBackedSetOf("mission-title-bottom-border", "mission-title-bottom-border-left")
            )
            absoluteDiv(
                zIndex = Layer.MissionTitle.zIndex(),
                classes = jsObjectBackedSetOf("mission-title-bottom-border", "mission-title-bottom-border-right")
            )

            absoluteDiv(
                zIndex = Layer.MissionTitle.zIndex() + 2,
                classes = jsObjectBackedSetOf("mission-title-triangle-container")
            ) {
                absoluteDiv(
                    left = 0,
                    top = 0,
                    width = 0,
                    height = 0,
                    classes = jsObjectBackedSetOf("mission-title-triangle")
                )
            }

            absoluteDiv(
                zIndex = Layer.MissionTitle.zIndex() + 3,
                classes = jsObjectBackedSetOf("title-star-answer-box")
            ) {
                div {
                    child(TitleStarCounter::class) {
                        attrs.total = props.totalStar
                        attrs.current = props.currentStar
                    }

                    child(MissionTitleAnswers::class) {
                        attrs.game = game
                        attrs.mission = props.mission
                    }
                }
            }
        }
    }

    override fun shouldComponentUpdate(nextProps: MissionTitleProps, nextState: MissionTitleState): Boolean {
        return props.left != nextProps.left ||
            props.bottom != nextProps.bottom ||
            props.title != nextProps.title ||
            props.offsetY != nextProps.offsetY ||
            state.hovered != nextState.hovered
    }

    override fun componentDidMount() {
        props.eventBus.on(MOUSE_MOVE_EVENT, mouseMoveListener)
        props.eventBus.on(MOUSE_OUT_OF_MAP_EVENT, mouseOutOfMapListener)
    }

    override fun componentWillUnmount() {
        props.eventBus.remove(MOUSE_MOVE_EVENT, mouseMoveListener)
        props.eventBus.remove(MOUSE_OUT_OF_MAP_EVENT, mouseOutOfMapListener)
    }
}
