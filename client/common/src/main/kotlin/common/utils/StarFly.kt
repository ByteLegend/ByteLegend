package common.utils

import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import kotlinx.browser.document
import org.w3c.dom.HTMLCanvasElement

fun start(
    gameContanerSize: PixelSize,
    from: PixelCoordinate,
    to: PixelCoordinate,
    durationSecond: Int
) {
    val particlesCanvas = document.createElement("canvas").unsafeCast<HTMLCanvasElement>()
    particlesCanvas.id = "particles-canvas"
    particlesCanvas.width = gameContanerSize.width
    particlesCanvas.height = gameContanerSize.height
//    particlesCanvas.style.zIndex = Layer.Game

    val starDiv = document.createElement("div")
    starDiv.id = "star"
}
