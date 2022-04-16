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
import com.bytelegend.app.shared.entities.MissionModalData
import com.bytelegend.app.shared.util.currentTimeMillis
import com.bytelegend.client.app.engine.GAME_CLOCK_20MS_EVENT
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.jsStyle
import com.bytelegend.client.app.ui.setState
import csstype.ClassName
import kotlinx.js.jso
import org.w3c.dom.Element
import react.ChildrenBuilder
import react.Component
import react.Fragment
import react.ReactNode
import react.State
import react.create
import react.dom.events.MouseEvent
import react.dom.events.NativeMouseEvent
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.span

const val MAX_ROTATION_DEGREE = 60
const val WEBEDITOR_BUTTON_ROTATION_SPEED_DEG_PER_SECOND = 270

interface AbstractWebEditorButtonState : State {
    var showBubble: Boolean
    var imageName: String

    // 0~360.
    var rotationDegree: Int

    // The epoch time when rotation starts. -1 means no rotation
    var rotationStartMs: Long
}

interface AbstractWebEditorButtonProps : GameProps {
    var missionModalData: MissionModalData
    var onClick: () -> Unit
    var textId: String
    var left: Int
    var top: Int
    var imageName: String

    var borderCursor: String

    var onMouseDown: WebEditorButtonHandler
    var onMouseUp: WebEditorButtonHandler
    var onMouseMove: WebEditorButtonHandler
}

typealias WebEditorButtonHandler = (MouseEvent<Element, NativeMouseEvent>) -> Unit

const val WEBEDITOR_POPUP_OPENED = "webeditor.popup.opened"

abstract class AbstractWebEditorButton<P : AbstractWebEditorButtonProps, S : AbstractWebEditorButtonState>(props: P) : Component<P, S>(props) {
    init {
        state = jso {
            rotationDegree = 0
            rotationStartMs = -1
            showBubble = false
            imageName = props.imageName
        }
    }

    private val onWebEditorPopupOpened: EventListener<String> = this::onWebEditorPopupOpened

    private val on50HzClockListener: EventListener<Nothing> = {
        if (state.rotationStartMs > 0) {
            val elapsedMs = currentTimeMillis() - state.rotationStartMs
            val degree = elapsedMs / 1000.0 * WEBEDITOR_BUTTON_ROTATION_SPEED_DEG_PER_SECOND
            if (degree > 2 * MAX_ROTATION_DEGREE) {
                setState {
                    rotationDegree = 0
                    rotationStartMs = -1
                }
            } else if (degree > MAX_ROTATION_DEGREE) {
                setState { rotationDegree = (2 * MAX_ROTATION_DEGREE - degree).toInt() }
            } else {
                setState { rotationDegree = degree.toInt() }
            }
        }
    }

    protected fun startRotation() {
        setState {
            rotationStartMs = currentTimeMillis()
            rotationDegree = 0
        }
    }

    private fun onWebEditorPopupOpened(id: String) {
        if (id != props.textId) {
            setState {
                showBubble = false
            }
        }
    }

    override fun componentDidMount() {
        props.game.eventBus.on(WEBEDITOR_POPUP_OPENED, onWebEditorPopupOpened)
        props.game.eventBus.on(GAME_CLOCK_20MS_EVENT, on50HzClockListener)
    }

    override fun componentWillUnmount() {
        props.game.eventBus.remove(WEBEDITOR_POPUP_OPENED, onWebEditorPopupOpened)
        props.game.eventBus.remove(GAME_CLOCK_20MS_EVENT, on50HzClockListener)
    }

    protected fun closeAllPopups() {
        // a tricky way to close all popups
        props.game.eventBus.emit(WEBEDITOR_POPUP_OPENED, "")
    }

    abstract fun ChildrenBuilder.renderPopup()
    abstract fun onClick()

    override fun render(): ReactNode = Fragment.create {
        if (state.showBubble) {
            div {
                className = ClassName("webeditor-button-popup speech-bubble")
                jsStyle {
                    transform = "translate(${props.left}px, ${props.top}px)"
                }
                renderPopup()
            }
        }
        div {
            className = ClassName("webeditor-button-bg")
            onMouseDown = props.onMouseDown
            onMouseUp = props.onMouseUp
            onMouseMove = props.onMouseMove

            jsStyle {
                left = "${props.left}px"
                top = "${props.top}px"
                cursor = props.borderCursor
                backgroundImage = """url("${props.game.resolve("/img/ui/webeditor-button-bg.png")}")"""
                transform = "rotate(${state.rotationDegree}deg)"
            }

            div {
                span {
                    className = ClassName("webeditor-button-text")
                    +props.game.i(props.textId)
                }

                jsStyle {
                    backgroundImage = """url("${props.game.resolve("/img/ui/${state.imageName}.png")}")"""
                    transform = "rotate(${-state.rotationDegree}deg)"
                }

                className = ClassName("webeditor-button-button")
                onClick = {
                    startRotation()
                    this@AbstractWebEditorButton.onClick()
                }
            }
        }
    }
}
