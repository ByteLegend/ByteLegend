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
                try {
                    canvas = (it as HTMLCanvasElement).getContext("2d") as CanvasRenderingContext2D
                } catch (ignore: ClassCastException) {
                    // resizing
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

class MapCanvasLayer : AbstractMapCanvas<RState>() {
    override fun RBuilder.render() {
        mapCanvas {
            val mapCanvasZIndex = Layer.MapCanvas.zIndex()
            attrs {
                id = "map-canvas-layer"
                if (!mapCoveredByCanvas) {
                    classes = setOf("canvas-border")
                }
                width = canvasPixelSize.width.toString()
                height = canvasPixelSize.height.toString()
                style = js {
                    zIndex = mapCanvasZIndex
                    position = "absolute"
                    top = "${canvasCoordinateInGameContainer.y}px"
                    left = "${canvasCoordinateInGameContainer.x}px"
                }
            }
        }
    }

    override fun onPaint(lastAnimationTime: Timestamp) {
//        val start = Timestamp.now()
        canvas.clearRect(0.0, 0.0, canvasPixelSize.width.toDouble(), canvasPixelSize.height.toDouble())

        game.activeScene.objects.getDrawableSprites().forEach {
            it.draw(canvas)
        }
//        console.log("paint takes ${Timestamp.now() -start}ms")
    }

    private fun drawGrid() {
        // Every 5 grid
        val gridGapX = tileSize.width * 5
        val gridGapY = tileSize.height * 5

        var lineXInCanvas = ((canvasCoordinateInMap.x / tileSize.width) + 1) * tileSize.width - canvasCoordinateInMap.x
        var lineYInCanvas = ((canvasCoordinateInMap.y / tileSize.height) + 2) * tileSize.height - canvasCoordinateInMap.y

        canvas.strokeStyle = "white"
        canvas.lineWidth = 1.0

        while (lineXInCanvas < canvasPixelSize.width) {
            canvas.beginPath()
            canvas.moveTo(lineXInCanvas.toDouble(), 0.0)
            canvas.lineTo(lineXInCanvas.toDouble(), canvasPixelSize.height.toDouble())
            canvas.stroke()

            lineXInCanvas += gridGapX
        }

        while (lineYInCanvas < canvasPixelSize.height) {
            canvas.beginPath()
            canvas.moveTo(0.0, lineYInCanvas.toDouble())
            canvas.lineTo(canvasPixelSize.width.toDouble(), lineYInCanvas.toDouble())
            canvas.stroke()

            lineYInCanvas += gridGapY
        }
    }
}
