package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.shared.PixelCoordinate
import org.w3c.dom.CanvasRenderingContext2D

val OUTER_GLOW_ANIMATION_FPS = 2

interface Effect {
    fun draw(origin: PixelCoordinate, gameScene: GameScene, canvas: CanvasRenderingContext2D)
}

object NoEffect : Effect {
    override fun draw(origin: PixelCoordinate, gameScene: GameScene, canvas: CanvasRenderingContext2D) {
    }
}

class RectangleOuterGlowEffect(
    private val x: Int,
    private val y: Int,
    private val w: Int,
    private val h: Int,
    private val blur: Int,
    private val color: String
) : Effect {
    override fun draw(origin: PixelCoordinate, gameScene: GameScene, canvas: CanvasRenderingContext2D) {
        val frameIndex = (gameScene.gameRuntime.elapsedTimeSinceStart * OUTER_GLOW_ANIMATION_FPS / 1000).toInt() % 2
        if (frameIndex == 0) {
            canvas.save()
            canvas.setShadow(color, 0, 0, blur)
            canvas.fillRect(origin.x.toDouble() + x, origin.y.toDouble() + y, w.toDouble(), h.toDouble())
            canvas.restore()
        }
    }
}
