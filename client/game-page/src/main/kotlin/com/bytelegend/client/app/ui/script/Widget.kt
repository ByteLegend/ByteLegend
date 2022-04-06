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
package com.bytelegend.client.app.ui.script

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.client.ui.bootstrap.BootstrapButton
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.objects.CoordinateAware
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.client.app.engine.GAME_CLOCK_100MS_EVENT
import com.bytelegend.client.app.engine.GAME_CLOCK_20MS_EVENT
import com.bytelegend.client.app.engine.GAME_SCRIPT_NEXT
import com.bytelegend.client.app.script.MAIN_CHANNEL
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.Layer
import com.bytelegend.client.app.ui.jsStyle
import com.bytelegend.client.app.ui.setState
import com.bytelegend.client.app.ui.unsafeDiv
import csstype.ClassName
import kotlinx.js.jso
import react.ElementType
import react.Fragment
import react.State
import react.create
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.span

data class Widget<P : GameProps>(
    val type: ElementType<P>,
    val props: P
)

// See p.speech-bubble::after left
val SPEECH_BUBBLE_OFFSET_X = 36

interface SpeechBubbleWidgetProps : GameProps {
    var speakerId: String?
    var speakerCoordinate: PixelCoordinate?
    var contentHtml: String
    var arrow: Boolean
    var showYesNo: Boolean
    var onYes: UnitFunction
    var onNo: UnitFunction
}

interface SpeechBubbleWidgetState : State {
    var arrowUp: Boolean
}

class SpeechBubbleWidget : GameUIComponent<SpeechBubbleWidgetProps, SpeechBubbleWidgetState>() {
    private val arrowUpDownListener: EventListener<Nothing> = {
        setState { arrowUp = !arrowUp }
    }

    init {
        state = jso { arrowUp = true }
    }

    override fun componentDidMount() {
        props.game.eventBus.on(GAME_CLOCK_20MS_EVENT, gameUiUpdateEventListener)
        props.game.eventBus.on(GAME_CLOCK_100MS_EVENT, arrowUpDownListener)
    }

    override fun componentWillUnmount() {
        props.game.eventBus.remove(GAME_CLOCK_20MS_EVENT, gameUiUpdateEventListener)
        props.game.eventBus.remove(GAME_CLOCK_100MS_EVENT, arrowUpDownListener)
    }

    override fun render() = Fragment.create {
        val speakerCoordinate = props.speakerCoordinate ?: activeScene.objects.getById<GameObject>(props.speakerId!!).unsafeCast<CoordinateAware>().pixelCoordinate
        // bubble's parent is game container, which is absolute-positioned.
        val bubbleLeft = speakerCoordinate.x - canvasCoordinateInMap.x +
            canvasCoordinateInGameContainer.x -
            SPEECH_BUBBLE_OFFSET_X
        val bubbleBottom = gameContainerHeight -
            (speakerCoordinate.y - canvasCoordinateInMap.y + canvasCoordinateInGameContainer.y)
        div {
            className = ClassName("speech-bubble")
            val z = Layer.ScriptWidget.zIndex()
            jsStyle {
                zIndex = z
                position = "absolute"
                left = "${bubbleLeft}px"
                bottom = "${bubbleBottom}px"
            }
            onClick = {
                props.game.eventBus.emit(GAME_SCRIPT_NEXT, MAIN_CHANNEL)
            }
            // Can only set one of 'children' or props.dangerouslySetInnerHTML'
            unsafeDiv(props.contentHtml)

            if (props.showYesNo) {
                BootstrapButton {
                    className = "speech-bubble-button"
                    size = "sm"
                    onClick = { it: dynamic ->
                        it.stopPropagation()
                        props.onYes()
                    }
                    +"Yes"
                }
                BootstrapButton {
                    className = "speech-bubble-button"
                    size = "sm"
                    onClick = { it: dynamic ->
                        it.stopPropagation()
                        props.onNo()
                    }
                    +"No"
                }
            }

            if (props.arrow) {
                span {
                    jsStyle {
                        position = "absolute"
                        right = "6px"
                        if (state.arrowUp) {
                            bottom = "6px"
                        } else {
                            bottom = "3px"
                        }
                    }
                    +"\uD83D\uDD3B"
                }
            }
        }
    }
}
