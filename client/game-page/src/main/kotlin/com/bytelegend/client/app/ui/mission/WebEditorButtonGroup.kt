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

import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.client.misc.uuid
import com.bytelegend.app.shared.entities.MissionModalData
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.jsStyle
import com.bytelegend.client.app.ui.setState
import csstype.ClassName
import kotlinx.browser.document
import kotlinx.browser.localStorage
import kotlinx.js.jso
import org.w3c.dom.Element
import org.w3c.dom.HTMLDivElement
import react.Component
import react.Fragment
import react.State
import react.create
import react.dom.events.MouseEvent
import react.dom.events.NativeMouseEvent
import react.dom.html.ReactHTML.div
import react.react

private const val DEFAULT_TOP = "60vh"
private const val DEFAULT_LEFT = "10%"

interface WebEditorButtonGroupProps : GameProps {
    var onClickSubmitAnswerButton: UnitFunction
    var missionModalData: MissionModalData
    var challengeId: String
}

interface WebEditorButtonGroupState : State {
    // Note: this is relative to default top/left, i.e.
    // the actual top is calc(DEFAULT_TOP + top), the actual left is calc(DEFAULT_LEFT + left)
    var top: Int
    var left: Int

    // grab/grabbing
    var borderCursor: String

    // For dragging, non-null dragging currently
    var lastMouseDownClientX: Int?
    var lastMouseDownClientY: Int?
    var lastMouseDownLeft: Int?
    var lastMouseDownTop: Int?
}

class WebEditorButtonGroup(props: WebEditorButtonGroupProps) : Component<WebEditorButtonGroupProps, WebEditorButtonGroupState>(props) {
    private fun loadLeft() = localStorage.getItem("SubmitAnswerButtonLeft")?.toIntOrNull()
    private fun loadTop() = localStorage.getItem("SubmitAnswerButtonTop")?.toIntOrNull()
    private fun saveLeft() = localStorage.setItem("SubmitAnswerButtonLeft", state.left.toString())
    private fun saveTop() = localStorage.setItem("SubmitAnswerButtonTop", state.top.toString())
    private val elementId = "webeditor-button-group-${uuid()}"

    init {
        state = jso {
            top = loadTop() ?: 0
            left = loadLeft() ?: 0
            lastMouseDownClientX = null
            lastMouseDownClientY = null
            lastMouseDownTop = null
            lastMouseDownLeft = null
        }
    }

    private fun onMouseUp(mouseEvent: MouseEvent<Element, NativeMouseEvent>) {
        if (state.lastMouseDownClientX == null) {
            return
        }
        val event = mouseEvent.nativeEvent

        setState({
            it.left = it.lastMouseDownLeft!! + event.clientX - it.lastMouseDownClientX!!
            it.top = it.lastMouseDownTop!! + event.clientY - it.lastMouseDownClientY!!

            it.lastMouseDownClientX = null
            it.lastMouseDownClientY = null
            it.lastMouseDownLeft = null
            it.lastMouseDownTop = null
            it
        }, {
            saveTop()
            saveLeft()
        })
    }

    private fun onMouseDown(mouseEvent: MouseEvent<Element, NativeMouseEvent>) {
        val event = mouseEvent.nativeEvent
        setState {
            borderCursor = "grabbing"
            lastMouseDownClientX = event.clientX
            lastMouseDownClientY = event.clientY
            lastMouseDownLeft = state.left
            lastMouseDownTop = state.top
        }
    }

    private fun onMouseMove(mouseEvent: MouseEvent<Element, NativeMouseEvent>) {
        if (state.lastMouseDownClientX != null) {
            val event = mouseEvent.nativeEvent
            val deltaX = event.clientX - state.lastMouseDownClientX!!
            val deltaY = event.clientY - state.lastMouseDownClientY!!

            // Don't use setState in mouse move, it's slow and may not catch up with mouse moving
            val newLeft = "calc($DEFAULT_LEFT + ${state.lastMouseDownLeft!! + deltaX}px)"
            val newTop = "calc($DEFAULT_TOP + ${state.lastMouseDownTop!! + deltaY}px)"
            document.getElementById(elementId)?.apply {
                val div = unsafeCast<HTMLDivElement>()
                div.style.left = newLeft
                div.style.top = newTop
            }
        }
    }

    override fun render() = Fragment.create {
        div {
            className = ClassName("webeditor-button-group")
            id = elementId
            jsStyle {
                position = "absolute"
                left = "calc($DEFAULT_LEFT + ${state.left}px)"
                top = "calc($DEFAULT_TOP + ${state.top}px)"
            }
            child(SubmitAnswerButton::class.react, jso {
                challengeId = props.challengeId
                game = props.game
                onClick = props.onClickSubmitAnswerButton
                left = 0
                top = 0
                borderCursor = state.borderCursor
                onMouseDown = ::onMouseDown
                onMouseUp = ::onMouseUp
                onMouseMove = ::onMouseMove
            })
            child(OpenTutorialsButton::class.react, jso {
                game = props.game
                missionModalData = props.missionModalData
                left = -64
                top = 100
                textId = "Tutorials"
                imageName = if (props.missionModalData.tutorialsUnlocked) "tutorial-open" else "tutorial-locked"
                borderCursor = "grab"
                onMouseDown = ::onMouseDown
                onMouseUp = ::onMouseUp
                onMouseMove = ::onMouseMove
            })
            child(AskForHelpButton::class.react, jso {
                game = props.game
                missionModalData = props.missionModalData
                left = 60
                top = 100
                textId = "AskForHelp"
                imageName = "help"
                borderCursor = "grab"
                onMouseDown = ::onMouseDown
                onMouseUp = ::onMouseUp
                onMouseMove = ::onMouseMove
            })
        }
    }
}
