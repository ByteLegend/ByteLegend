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
import com.bytelegend.app.shared.entities.PullRequestAnswer
import com.bytelegend.client.app.engine.GAME_CLOCK_20MS_EVENT
import com.bytelegend.client.app.ui.jsStyle
import com.bytelegend.client.app.ui.setState
import csstype.ClassName
import kotlinx.js.jso
import org.w3c.dom.Element
import react.Component
import react.Fragment
import react.State
import react.create
import react.dom.events.MouseEvent
import react.dom.events.NativeMouseEvent
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import react.dom.html.ReactHTML.span

private const val ROTATION_SPEED_DEG_PER_SECOND = 180

const val ANSWER_BUTTON_CONTROL_EVENT = "answer.button.control"

interface SubmitAnswerButtonProps : AbstractWebEditorButtonProps {
    var challengeId: String
}

interface SubmitAnswerButtonState : State {
    // the button is spinning or not
    var spinning: Boolean
    var textId: String

    // 0~3
    var dotNumber: Int

    // 0~360
    var rotationDegree: Int
    var glow: Boolean
}

class SubmitAnswerButton(props: SubmitAnswerButtonProps) : Component<SubmitAnswerButtonProps, SubmitAnswerButtonState>(props) {
    private val on50HzClockListener: EventListener<Nothing> = {
        if (state.spinning) {
            val rotationDegree = (state.rotationDegree + (ROTATION_SPEED_DEG_PER_SECOND / 1000.0 * 20).toInt()) % 360
            setState {
                this.rotationDegree = rotationDegree
                this.dotNumber = rotationDegree / 90
            }
        }
    }

    private val answerButtonControlListener: EventListener<dynamic> = {
        setState {
            spinning = it.spinning
            textId = it.textId
            if (it.textId == "SubmitAnswer") {
                dotNumber = 0
            }
        }
    }

    init {
        state = init(props.challengeId)
    }

    override fun componentWillReceiveProps(nextProps: SubmitAnswerButtonProps) {
        if (nextProps.challengeId != props.challengeId) {
            setState(init(nextProps.challengeId))
        }
    }

    private fun init(challengeId: String): SubmitAnswerButtonState {
        return jso {
            glow = false
            dotNumber = 0

            if (props.game.activeScene.challengeAnswers.getPullRequestChallengeAnswersByChallengeId(challengeId).anyCheckRunning()) {
                spinning = true
                textId = "CheckingAnswer"
            } else {
                spinning = false
                textId = "SubmitAnswer"
                dotNumber = 0
            }
        }
    }

    override fun render() = Fragment.create {
        div {
            className = ClassName("submit-answer-button")
            jsStyle {
                left = "${props.left}px"
                top = "${props.top}px"
            }
            img {
                draggable = true
                if (state.glow) {
                    className = ClassName("submit-answer-button-border submit-answer-button-border-hover")
                } else {
                    className = ClassName("submit-answer-button-border")
                }
                src =
                    "data:image/png;base64,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"
                jsStyle {
                    cursor = props.borderCursor
                }
                onMouseDown = props.onMouseDown
                onMouseUp = props.onMouseUp
                onMouseOut = props.onMouseUp
                onBlur = {
                    props.onMouseUp(it.unsafeCast<MouseEvent<Element, NativeMouseEvent>>())
                }
                onMouseMove = props.onMouseMove
            }
            div {
                className = ClassName("webeditor-button-button")
                span {
                    className = ClassName("webeditor-button-text")
                    +(props.game.i(state.textId) + ".".repeat(state.dotNumber))
                }
                onMouseOut = { setState { glow = false } }
                onMouseOver = {
                    if (!state.spinning) {
                        setState { glow = true }
                    }
                }
                onClick = { this@SubmitAnswerButton.onClick() }
            }
            div {
                className = ClassName("submit-answer-button-animation")
                jsStyle {
                    zIndex = "1"
                    transform = "rotate(${state.rotationDegree}deg)"
                }
            }
        }
    }

    private fun onClick() {
        if (!state.spinning) {
            setState {
                textId = "SubmittingAnswer"
                spinning = true
            }
            props.onClick()
        }
    }

    override fun componentDidMount() {
        props.game.eventBus.on(ANSWER_BUTTON_CONTROL_EVENT, answerButtonControlListener)
        props.game.eventBus.on(GAME_CLOCK_20MS_EVENT, on50HzClockListener)
    }

    override fun componentWillUnmount() {
        props.game.eventBus.remove(ANSWER_BUTTON_CONTROL_EVENT, answerButtonControlListener)
        props.game.eventBus.remove(GAME_CLOCK_20MS_EVENT, on50HzClockListener)
    }
}

fun List<PullRequestAnswer>.anyCheckRunning(): Boolean {
    return any {
        // when the PR is just created, check runs haven't started yet.
        it.latestCheckRun == null || it.running
    }
}
