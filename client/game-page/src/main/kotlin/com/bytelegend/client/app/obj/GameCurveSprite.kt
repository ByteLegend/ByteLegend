package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.Sprite
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.objects.GameMapCurve
import com.bytelegend.app.shared.objects.GameObjectRole
import org.w3c.dom.CanvasRenderingContext2D

class GameCurveSprite(
    override val gameScene: GameScene,
    private val obj: GameMapCurve
) : Sprite {
    override val id: String = "${obj.id}-sprite"
    override val layer: Int = obj.layer
    override val roles: Set<GameObjectRole> = setOf(GameObjectRole.Sprite)
    val points: List<PixelCoordinate> = obj.points

    override fun outOfCanvas() = obj.points.all { it.outOfCanvas(gameScene) }

    override fun draw(canvas: CanvasRenderingContext2D) {
        canvas.drawCurve(this, gameScene)
    }
}
