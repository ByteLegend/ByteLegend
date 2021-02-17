package com.bytelegend.app.client.api

import com.bytelegend.app.shared.PixelBlock
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.imageBlockOnCanvas
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.app.shared.outOfCanvas

abstract class AbstractMovableSprite : Sprite {
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
    protected val coordinateInMap: PixelCoordinate
) : Sprite {
    override val roles: Set<GameObjectRole> = setOf(GameObjectRole.Sprite)
    open fun outOfCanvas(gameScene: GameScene) = getImageBlockOnCanvas(gameScene).outOfCanvas(gameScene.canvasState.getCanvasPixelSize())
    open fun getImageBlockOnCanvas(gameScene: GameScene) =
        imageBlockOnCanvas(coordinateInMap, gameScene.canvasState.getCanvasCoordinateInMap(), gameScene.map.tileSize)
}
