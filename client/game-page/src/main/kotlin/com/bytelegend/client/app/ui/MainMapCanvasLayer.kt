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
@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.Timestamp
import com.bytelegend.app.client.misc.getImageElement
import com.bytelegend.client.app.engine.GAME_ANIMATION_EVENT
import com.bytelegend.client.app.engine.GameAnimationEventListener
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.html.CANVAS
import kotlinx.html.classes
import kotlinx.html.id
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import react.RBuilder
import react.RefObject
import react.State
import react.createRef
import react.dom.RDOMBuilder
import react.dom.attrs
import react.dom.canvas
import react.dom.jsStyle

// https://codepen.io/vasilly/pen/NRKyWL
interface MapCanvasProps : GameProps {
    var id: String
    var classes: Set<String>
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

abstract class AbstractMapCanvas<S : State> : GameUIComponent<MapCanvasProps, S>() {
    private val canvasRef: RefObject<HTMLCanvasElement> = createRef()
    protected val canvas: CanvasRenderingContext2D by lazy {
        canvasRef.current!!.getContext("2d").unsafeCast<CanvasRenderingContext2D>()
    }
    private val windowAnimationEventListener: GameAnimationEventListener = { onPaint(it) }

    protected abstract fun onPaint(lastAnimationTime: Timestamp)

    protected fun RBuilder.mapCanvas(canvasConfig: RDOMBuilder<CANVAS>.() -> Unit) {
        canvas {
            canvasConfig()

            +"Canvas not supported"
            ref = canvasRef
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

fun CanvasRenderingContext2D.drawImage(
    imageId: String,
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
        dx.toDouble(), dy.toDouble(), dw.toDouble(), dh.toDouble()
    )
    restore()
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

class MainMapCanvasLayer : GameUIComponent<GameProps, State>() {
    private val objectCanvasRef: RefObject<HTMLCanvasElement> = createRef()
    private val windowAnimationEventListener: GameAnimationEventListener = {
        game.mainMapCanvasRenderer.onAnimation()
    }

    private fun RDOMBuilder<CANVAS>.canvasAttr(canvasId: String, canvasZIndex: Int) {
        attrs {
            id = canvasId
            if (!mapCoveredByCanvas) {
                classes = jsObjectBackedSetOf("canvas-border")
            }
            width = canvasPixelSize.width.toString()
            height = canvasPixelSize.height.toString()
            jsStyle {
                zIndex = canvasZIndex
                position = "absolute"
                left = "${canvasCoordinateInGameContainer.x}px"
                top = "${canvasCoordinateInGameContainer.y}px"
            }
        }
    }

    override fun RBuilder.render() {
        absoluteDiv(
            left = canvasCoordinateInGameContainer.x,
            top = canvasCoordinateInGameContainer.y,
            width = canvasPixelSize.width,
            height = canvasPixelSize.height,
            zIndex = Layer.MapCanvas.zIndex()
        ) {
            attrs.id = "background-canvas-layer"
        }
        canvas {
            canvasAttr("objects-canvas-layer", Layer.MapCanvas.zIndex() + 2)

            +"Canvas not supported"
            ref = objectCanvasRef
            game.mainMapCanvasRenderer.mapObjectsLayerRef = objectCanvasRef
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
