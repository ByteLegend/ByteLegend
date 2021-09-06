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
import com.bytelegend.client.app.ui.unsafeSpan
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.html.classes
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RHandler
import react.State
import react.dom.jsStyle
import react.dom.p
import react.dom.span
import react.setState
import kotlin.reflect.KClass

data class Widget<P : GameProps>(
    val klass: KClass<out GameUIComponent<P, *>>,
    val handler: RHandler<P>
)

// See p.speech-bubble::after left
val SPEECH_BUBBLE_OFFSET_X = 36

interface SpeechBubbleWidgetProps : GameProps {
    var speakerId: String?
    var speakerCoordinate: PixelCoordinate?
    var contentHtml: String
    var arrow: Boolean
}

interface SpeechBubbleWidgetState : State {
    var arrowUp: Boolean
}

class SpeechBubbleWidget : GameUIComponent<SpeechBubbleWidgetProps, SpeechBubbleWidgetState>() {
    private val arrowUpDownListener: EventListener<Nothing> = {
        setState { arrowUp = !arrowUp }
    }

    override fun SpeechBubbleWidgetState.init() {
        arrowUp = true
    }

    override fun componentDidMount() {
        props.game.eventBus.on(GAME_CLOCK_20MS_EVENT, gameUiUpdateEventListener)
        props.game.eventBus.on(GAME_CLOCK_100MS_EVENT, arrowUpDownListener)
    }

    override fun componentWillUnmount() {
        props.game.eventBus.remove(GAME_CLOCK_20MS_EVENT, gameUiUpdateEventListener)
        props.game.eventBus.remove(GAME_CLOCK_100MS_EVENT, arrowUpDownListener)
    }

    override fun RBuilder.render() {
        val speakerCoordinate = props.speakerCoordinate ?: activeScene.objects.getById<GameObject>(props.speakerId!!).unsafeCast<CoordinateAware>().pixelCoordinate
        // bubble's parent is game container, which is absolute-positioned.
        val bubbleLeft = speakerCoordinate.x - canvasCoordinateInMap.x +
            canvasCoordinateInGameContainer.x -
            SPEECH_BUBBLE_OFFSET_X
        val bubbleBottom = gameContainerHeight -
            (speakerCoordinate.y - canvasCoordinateInMap.y + canvasCoordinateInGameContainer.y)
        p {
            attrs.classes = jsObjectBackedSetOf("speech-bubble")
            val z = Layer.ScriptWidget.zIndex()
            attrs.jsStyle {
                zIndex = z
                position = "absolute"
                left = "${bubbleLeft}px"
                bottom = "${bubbleBottom}px"
            }
            attrs.onClickFunction = {
                props.game.eventBus.emit(GAME_SCRIPT_NEXT, MAIN_CHANNEL)
            }
            // Can only set one of 'children' or props.dangerouslySetInnerHTML'
            unsafeSpan(props.contentHtml)
            if (props.arrow) {
                span {
                    attrs.jsStyle {
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
