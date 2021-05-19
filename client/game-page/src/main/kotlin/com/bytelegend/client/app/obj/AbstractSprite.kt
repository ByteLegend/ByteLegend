package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.CoordinateAware
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.Sprite
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelBlock
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.math.imageBlockOnCanvas
import com.bytelegend.app.shared.math.outOfCanvas
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.engine.util.jsObjectBackedSetOf

abstract class AbstractSprite : Sprite {
    abstract var pixelCoordinate: PixelCoordinate

    override fun outOfCanvas() = getImageBlockOnCanvas().run {
        x > gameScene.canvasState.getCanvasPixelSize().width ||
            y > gameScene.canvasState.getCanvasPixelSize().height ||
            x + width < 0 ||
            y + height < 0
    }

    open fun getImageBlockOnCanvas() = PixelBlock(
        pixelCoordinate.x - gameScene.canvasState.getCanvasCoordinateInMap().x,
        pixelCoordinate.y - gameScene.canvasState.getCanvasCoordinateInMap().y,
        gameScene.map.tileSize.width,
        gameScene.map.tileSize.height
    )
}

/**
 * A sprite with static location (but might have animation frames,
 * like running river, waterfall, etc.)
 */
abstract class AbstractStaticLocationSprite(
    override val gridCoordinate: GridCoordinate,
    override val pixelCoordinate: PixelCoordinate,
    private val spriteSize: PixelSize
) : CoordinateAware, Sprite {
    override val roles: Set<String> = jsObjectBackedSetOf(GameObjectRole.Sprite.toString())
    override fun outOfCanvas() = getImageBlockOnCanvas(gameScene).outOfCanvas(gameScene.canvasState.getCanvasPixelSize())
    open fun getImageBlockOnCanvas(gameScene: GameScene) =
        imageBlockOnCanvas(pixelCoordinate, gameScene.canvasState.getCanvasCoordinateInMap(), spriteSize)
}
