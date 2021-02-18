package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.AbstractStaticLocationSprite
import com.bytelegend.app.client.api.CoordinateAware
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelBlock
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.objects.GameMapDynamicSprite
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import org.w3c.dom.CanvasRenderingContext2D

/**
 * A DynamicSprite is added to the map and controlled by game script,
 * which is much more flexible.
 */
open class DynamicSprite(
    override val id: String,
    override val gameScene: GameScene,
    protected val dynamicSprite: GameMapDynamicSprite,
    private val effect: Effect = NoEffect,
    private val onInitFunction: () -> Unit = {},
    private val onTouchFunction: (GameObject) -> Unit = {},
    private val onClickFunction: () -> Unit = {},
    override val roles: Set<GameObjectRole> = setOf(GameObjectRole.Sprite, GameObjectRole.CoordinateAware)
) : AbstractStaticLocationSprite(
    dynamicSprite.topLeftCorner * gameScene.map.tileSize,
    PixelSize(
        gameScene.map.tileSize.width * dynamicSprite.width,
        gameScene.map.tileSize.height * dynamicSprite.height
    )
),
    CoordinateAware {
    override val layer: Int = dynamicSprite.layer
    override val gridCoordinate: GridCoordinate = dynamicSprite.topLeftCorner
    override val pixelCoordinate: PixelCoordinate = coordinateInMap

    override fun init() {
        gameScene.objects.add(this)
        onInitFunction()
    }

    override fun close() {
        gameScene.objects.remove<GameObject>(this.id)
    }

    override fun onClick() {
        onClickFunction()
    }

    override fun draw(canvas: CanvasRenderingContext2D) {
        val coordinateInCanvas = coordinateInMap - gameScene.canvasState.getCanvasCoordinateInMap()
        effect.draw(coordinateInCanvas, gameScene, canvas)

        for (y in 0 until dynamicSprite.height) {
            for (x in 0 until dynamicSprite.width) {
                val frame = dynamicSprite.frames[y][x][0]
                canvas.drawImage(
                    gameScene.tileset.htmlElement,
                    PixelBlock(frame * gameScene.map.tileSize, gameScene.map.tileSize),
                    PixelBlock(coordinateInCanvas + gameScene.map.tileSize * GridCoordinate(x, y), gameScene.map.tileSize),
                )
            }
        }
    }
}
