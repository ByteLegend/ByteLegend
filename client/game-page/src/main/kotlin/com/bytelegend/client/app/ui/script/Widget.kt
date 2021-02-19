package com.bytelegend.client.app.ui.script

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.client.app.ui.Layer
import kotlinx.html.classes
import react.RBuilder
import react.dom.jsStyle
import react.dom.p

interface Widget {
    fun render(builder: RBuilder)
}

// See p.speech-bubble::after left
val SPEECH_BUBBLE_OFFSET_X = 36

class SpeechBubbleWidget(
    private val gameScene: GameScene,
    private val speakerCoordinate: PixelCoordinate,
    private val contentHtml: String
) : Widget {
    override fun render(builder: RBuilder) {
        // bubble's parent is game container, which is absolute-positioned.
        val canvasState = gameScene.canvasState
        val bubbleLeft = speakerCoordinate.x - canvasState.getCanvasCoordinateInMap().x +
            canvasState.getCanvasCoordinateInGameContainer().x -
            SPEECH_BUBBLE_OFFSET_X
        val bubbleBottom = canvasState.gameContainerSize.height -
            (speakerCoordinate.y - gameScene.canvasState.getCanvasCoordinateInMap().y + canvasState.getCanvasCoordinateInGameContainer().y)
        builder.p {
            attrs.classes = setOf("speech-bubble")
            val z = Layer.ScriptWidget.zIndex()
            attrs.jsStyle {
                zIndex = z
                position = "absolute"
                left = "${bubbleLeft}px"
                bottom = "${bubbleBottom}px"
            }
            consumer.onTagContentUnsafe {
                +contentHtml
            }
        }
    }
}