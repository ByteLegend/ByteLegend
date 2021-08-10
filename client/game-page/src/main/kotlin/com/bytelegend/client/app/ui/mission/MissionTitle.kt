package com.bytelegend.client.app.ui.mission

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.protocol.ChallengeUpdateEventData
import com.bytelegend.client.app.engine.GameMission
import com.bytelegend.client.app.engine.MISSION_REPAINT_EVENT
import com.bytelegend.client.app.engine.MOUSE_MOVE_EVENT
import com.bytelegend.client.app.engine.MOUSE_OUT_OF_MAP_EVENT
import com.bytelegend.client.app.engine.MouseEventListener
import com.bytelegend.client.app.page.game
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
import react.dom.div
import react.dom.jsStyle
import react.setState

interface FloatingTitleProps : RProps {
    // coordinate in game container
    var left: Int
    var bottom: Int

    // for animation
    var offsetY: Int
    var title: String
    var tileCoordinate: GridCoordinate
    var eventBus: EventBus
}

interface MissionTitleProps : FloatingTitleProps {
    var totalStar: Int
    var currentStar: Int
    var mission: GameMission
}

interface FloatingTitleState : RState {
    var hovered: Boolean
}

abstract class FloatingTitle<R : FloatingTitleProps> : RComponent<R, FloatingTitleState>() {
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

    private fun getOffsetY() = if (state.hovered) 0 else props.offsetY

    abstract fun onClick()

    override fun FloatingTitleState.init() {
        hovered = false
    }

    protected fun RBuilder.renderTitle(
        backgroundColor: String,
        block: RBuilder.() -> Unit
    ) {
        absoluteDiv(
            left = props.left,
            bottom = props.bottom + getOffsetY(),
            zIndex = Layer.FloatingTitle.zIndex() + if (state.hovered) 1 else 0,
            classes = jsObjectBackedSetOf("floating-title")
        ) {
            unsafeSpan(props.title)
            attrs.onClickFunction = {
                this@FloatingTitle.onClick()
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
                    this.backgroundColor = backgroundColor
                }
            } else {
                attrs.jsStyle {
                    this.backgroundColor = backgroundColor
                }
            }
            absoluteDiv(
                zIndex = Layer.FloatingTitle.zIndex(),
                classes = jsObjectBackedSetOf("floating-title-bottom-border", "floating-title-bottom-border-left")
            )
            absoluteDiv(
                zIndex = Layer.FloatingTitle.zIndex(),
                classes = jsObjectBackedSetOf("floating-title-bottom-border", "floating-title-bottom-border-right")
            )

            absoluteDiv(
                zIndex = Layer.FloatingTitle.zIndex() + 2,
                classes = jsObjectBackedSetOf("floating-title-triangle-container")
            ) {
                absoluteDiv(
                    left = 0,
                    top = 0,
                    width = 0,
                    height = 0,
                    classes = jsObjectBackedSetOf("floating-title-triangle")
                ) {
                    attrs.jsStyle {
                        borderBottom = "8px solid $backgroundColor"
                    }
                }
            }

            block()
        }
    }

    override fun shouldComponentUpdate(nextProps: R, nextState: FloatingTitleState): Boolean {
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

class MissionTitle : FloatingTitle<MissionTitleProps>() {
    private val onMissionRepaintListener: EventListener<ChallengeUpdateEventData> = this::onMissionRepaint

    private fun onMissionRepaint(eventData: ChallengeUpdateEventData) {
        // Refresh upon mission update event
        if (eventData.newValue.missionId == props.mission.id) {
            setState {}
        }
    }

    override fun onClick() {
        props.mission.onClick()
    }

    override fun RBuilder.render() {
        renderTitle("rgba(0,0,0,0.7)") {
            absoluteDiv(
                zIndex = Layer.FloatingTitle.zIndex() + 3,
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

    override fun componentDidMount() {
        super.componentDidMount()
        props.eventBus.on(MISSION_REPAINT_EVENT, onMissionRepaintListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.eventBus.remove(MISSION_REPAINT_EVENT, onMissionRepaintListener)
    }
}
