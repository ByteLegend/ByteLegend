@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.Timestamp
import com.bytelegend.app.client.api.getImageElement
import com.bytelegend.app.shared.PixelBlock
import com.bytelegend.client.app.engine.GAME_ANIMATION_EVENT
import com.bytelegend.client.app.engine.GameAnimationEventListener
import kotlinext.js.js
import kotlinx.html.CANVAS
import kotlinx.html.classes
import kotlinx.html.id
import kotlinx.html.style
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import react.RBuilder
import react.RState
import react.dom.RDOMBuilder
import react.dom.canvas
import react.dom.jsStyle

// https://codepen.io/vasilly/pen/NRKyWL
interface MapCanvasProps : GameProps {
    var id: String
    var classes: Set<String>
    var pixelBlock: PixelBlock
}

/*
-----------------------------------------------------------------------------------------------
|    The whole game map                                                                       |
|                                                                                             |
|      ----------------------------------------------------------------------------------     |
|      |   The browser window                                                            |    |
|      |                                                                                 |    |
|      |                                                                                 |    |
|      |      ----------------------------------------------------------------------     |    |
|      |      |  The map canvas                                                    |     |    |
|      |      |                                                                    |     |    |
|      |      |                                                                    |     |    |
|      |      |                                                                    |     |    |
|      |      |                                                                    |     |    |
|      |      |                                                                    |     |    |
|      |      |                                                                    |     |    |
|      |      ----------------------------------------------------------------------     |    |
|      |                                                                                 |    |
|      -----------------------------------------------------------------------------------    |
|                                                                                             |
|---------------------------------------------------------------------------------------------|

Map canvas (aka. viewport) locates in the middle of the browser windows, and acts as the main UI of the game.
Basically, it draws part of the game map and responds to various game events:

1. "window.animate": this is the main loop of HTML canvas animation.

 */

abstract class AbstractMapCanvas<S : RState> : GameUIComponent<MapCanvasProps, S>() {
    lateinit var canvas: CanvasRenderingContext2D
    private val windowAnimationEventListener: GameAnimationEventListener = { onPaint(it) }

    protected abstract fun onPaint(lastAnimationTime: Timestamp)

    protected fun RBuilder.mapCanvas(canvasConfig: RDOMBuilder<CANVAS>.() -> Unit) {
        canvas {
            canvasConfig()

            +"Canvas not supported"
            ref {
                if (it != null) {
                    canvas = (it as HTMLCanvasElement).getContext("2d") as CanvasRenderingContext2D
                }
            }
        }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        props.game.eventBus.on(GAME_ANIMATION_EVENT, windowAnimationEventListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.game.eventBus.remove(GAME_ANIMATION_EVENT, windowAnimationEventListener)
    }

    fun CanvasRenderingContext2D.drawImage(
        imageId: String,
        sx: Int,
        sy: Int,
        sw: Int,
        sh: Int,
        dx: Int,
        dy: Int,
        dw: Int,
        dh: Int,
        opacity: Double = 1.0
    ) {
        save()
        globalAlpha = opacity
        drawImage(
            getImageElement(imageId),
            sx.toDouble(), sy.toDouble(), sw.toDouble(), sh.toDouble(),
            dx.toDouble(), dy.toDouble(), dw.toDouble(), dh.toDouble()
        )
        restore()
    }
}

class MainMapCanvasLayer : GameUIComponent<MapCanvasProps, RState>() {
    private val windowAnimationEventListener: GameAnimationEventListener = {
        game.mainMapCanvasRenderer.onAnimation()
    }

    override fun RBuilder.render() {
        val mapCanvasZIndex = Layer.MapCanvas.zIndex()
        canvas {
            attrs {
                id = "background-canvas-layer"
                width = canvasPixelSize.width.toString()
                height = canvasPixelSize.height.toString()
                jsStyle {
                    zIndex = mapCanvasZIndex
                    position = "absolute"
                    top = "${canvasCoordinateInGameContainer.y}px"
                    left = "${canvasCoordinateInGameContainer.x}px"
                }
            }

            +"Canvas not supported"
            ref {
                if (it != null) {
                    game.mainMapCanvasRenderer.mapBackgroundLayer = it.getContext("2d")
                }
            }
        }
        canvas {
            attrs {
                id = "objects-canvas-layer"
                if (!mapCoveredByCanvas) {
                    classes = setOf("canvas-border")
                }
                width = canvasPixelSize.width.toString()
                height = canvasPixelSize.height.toString()
                jsStyle {
                    zIndex = mapCanvasZIndex + 1
                    position = "absolute"
                    top = "${canvasCoordinateInGameContainer.y}px"
                    left = "${canvasCoordinateInGameContainer.x}px"
                }
            }

            +"Canvas not supported"
            ref {
                if (it != null) {
                    game.mainMapCanvasRenderer.mapObjectsLayer = it.getContext("2d")
                }
            }
        }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        props.game.eventBus.on(GAME_ANIMATION_EVENT, windowAnimationEventListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.game.eventBus.remove(GAME_ANIMATION_EVENT, windowAnimationEventListener)
    }

}
