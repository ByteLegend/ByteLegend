package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.Sprite
import com.bytelegend.app.shared.objects.GameMapText
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.engine.util.jsObjectBackedSetOf
import com.bytelegend.client.app.page.game
import org.w3c.dom.CanvasRenderingContext2D
import kotlin.math.PI

class GameTextSprite(
    override val gameScene: GameScene,
    private val obj: GameMapText
) : Sprite {
    override val id: String = "${obj.id}-sprite"
    override val layer: Int = obj.layer
    override val roles: Set<String> = jsObjectBackedSetOf(GameObjectRole.Sprite)

    override fun outOfCanvas() = obj.coordinate.outOfCanvas(gameScene)

    override fun draw(canvas: CanvasRenderingContext2D) {
        val coordinateInCanvas = obj.coordinate - gameScene.canvasState.getCanvasCoordinateInMap()
        canvas.save()
        canvas.translate(coordinateInCanvas.x.toDouble(), coordinateInCanvas.y.toDouble())
        // Firefox has known issue when rendering shadows.
        // It's INCREDIBLY SLOW when enabling shadow: 30 fps -> 1 fps in the profiling.
        if (!isFirefox()) {
            canvas.setShadow("rgba(0,0,0,0.8)", 10, 10, 4)
        }
        canvas.fillStyle = "white"
        canvas.rotate(obj.rotation.toDouble() * PI / 180)
        canvas.font = "bold ${obj.fontSize}px sans-serif"
        canvas.fillText(game.i(obj.id), 0.0, 0.0)
        canvas.restore()
    }
}
