package com.bytelegend.client.app.ui.script

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.client.app.engine.GAME_CLOCK_10HZ_EVENT
import com.bytelegend.client.app.engine.GAME_CLOCK_50HZ_EVENT
import com.bytelegend.client.app.engine.GAME_SCRIPT_NEXT
import com.bytelegend.client.app.script.MAIN_CHANNEL
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.Layer
import com.bytelegend.client.app.ui.unsafeHtml
import kotlinx.html.classes
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RHandler
import react.RState
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
    var speakerCoordinate: PixelCoordinate
    var contentHtml: String
    var arrow: Boolean
}

interface SpeechBubbleWidgetState : RState {
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
        props.game.eventBus.on(GAME_CLOCK_50HZ_EVENT, gameUiUpdateEventListener)
        props.game.eventBus.on(GAME_CLOCK_10HZ_EVENT, arrowUpDownListener)
    }

    override fun componentWillUnmount() {
        props.game.eventBus.remove(GAME_CLOCK_50HZ_EVENT, gameUiUpdateEventListener)
        props.game.eventBus.remove(GAME_CLOCK_10HZ_EVENT, arrowUpDownListener)
    }

    override fun RBuilder.render() {
        // bubble's parent is game container, which is absolute-positioned.
        val bubbleLeft = props.speakerCoordinate.x - canvasCoordinateInMap.x +
            canvasCoordinateInGameContainer.x -
            SPEECH_BUBBLE_OFFSET_X
        val bubbleBottom = gameContainerHeight -
            (props.speakerCoordinate.y - canvasCoordinateInMap.y + canvasCoordinateInGameContainer.y)
        p {
            attrs.classes = setOf("speech-bubble")
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
            unsafeHtml(props.contentHtml)
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
