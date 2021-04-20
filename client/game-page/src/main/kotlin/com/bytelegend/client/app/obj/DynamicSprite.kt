package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.AbstractStaticLocationSprite
import com.bytelegend.app.client.api.Animation
import com.bytelegend.app.client.api.AnimationFrame
import com.bytelegend.app.client.api.CoordinateAware
import com.bytelegend.app.client.api.FramePlayingAnimation
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.Static
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelBlock
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.objects.GameMapDynamicSprite
import kotlinx.browser.window
import org.w3c.dom.CanvasRenderingContext2D

var globalCounter = 0

fun createMissionSprite(
    scene: GameScene,
    gridCoordinate: GridCoordinate,
    dynamicSpriteId: String
): DynamicSprite = when (dynamicSpriteId) {
    "Book" -> BookSprite(
        "$dynamicSpriteId-${globalCounter++}",
        scene,
        gridCoordinate,
        scene.objects.getById(dynamicSpriteId)
    )
    "Gate" -> GateSprite(
        "$dynamicSpriteId-${globalCounter++}",
        scene,
        gridCoordinate,
        scene.objects.getById(dynamicSpriteId)
    )
    else -> DynamicSprite(
        "$dynamicSpriteId-${globalCounter++}",
        scene,
        gridCoordinate,
        scene.objects.getById(dynamicSpriteId)
    )
}

class BookSprite(
    id: String,
    gameScene: GameScene,
    gridCoordinate: GridCoordinate,
    bookSprite: GameMapDynamicSprite
) : DynamicSprite(id, gameScene, gridCoordinate, bookSprite) {
    override fun onClick(): Boolean {
        animation = FramePlayingAnimation(listOf(AnimationFrame(1, 500)), false)
        return true
    }

    override fun onMissionModalClosed() {
        window.setTimeout(
            {
                animation = Static
            },
            500
        )
    }
}

class GateSprite(
    id: String,
    gameScene: GameScene,
    gridCoordinate: GridCoordinate,
    gateSprite: GameMapDynamicSprite
) : DynamicSprite(id, gameScene, gridCoordinate, gateSprite)

/**
 * A DynamicSprite is added to the map and controlled by game script,
 * which is much more flexible.
 */
open class DynamicSprite(
    override val id: String,
    override val gameScene: GameScene,
    override val gridCoordinate: GridCoordinate,
    protected val dynamicSprite: GameMapDynamicSprite
) : CoordinateAware, AbstractStaticLocationSprite(
    gridCoordinate,
    gridCoordinate * gameScene.map.tileSize,
    PixelSize(
        gameScene.map.tileSize.width * dynamicSprite.width,
        gameScene.map.tileSize.height * dynamicSprite.height
    )
) {
    protected var animation: Animation = Static
    override val layer: Int = dynamicSprite.layer
    override fun draw(canvas: CanvasRenderingContext2D) {
        val coordinateInCanvas = pixelCoordinate - gameScene.canvasState.getCanvasCoordinateInMap()

        for (y in 0 until dynamicSprite.height) {
            for (x in 0 until dynamicSprite.width) {
                val frame = dynamicSprite.frames[y][x][animation.getNextFrameIndex()]
                canvas.drawImage(
                    gameScene.tileset.htmlElement,
                    PixelBlock(frame * gameScene.map.tileSize, gameScene.map.tileSize),
                    PixelBlock(coordinateInCanvas + gameScene.map.tileSize * GridCoordinate(x, y), gameScene.map.tileSize),
                )
            }
        }
    }

    open fun onMissionModalClosed() {
    }
}
