package common.utils

import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import kotlinx.browser.document
import org.w3c.dom.HTMLCanvasElement

fun start(
    gameContainerSize: PixelSize,
    from: PixelCoordinate,
    to: PixelCoordinate,
    durationSecond: Int
) {
    val particlesCanvas = document.createElement("canvas").unsafeCast<HTMLCanvasElement>()
    particlesCanvas.id = "particles-canvas"
    particlesCanvas.width = gameContainerSize.width
    particlesCanvas.height = gameContainerSize.height
//    particlesCanvas.style.zIndex = Layer.Game

    val starDiv = document.createElement("div")
    starDiv.id = "star"
}
