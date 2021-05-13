package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.CoordinateAware
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.Sprite
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PLAYER_LAYER
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.RawAnimationLayer
import com.bytelegend.app.shared.RawGameMapTileLayer
import com.bytelegend.app.shared.RawStaticImageLayer
import com.bytelegend.app.shared.RawTileAnimationFrame
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.page.game
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLImageElement

fun RawGameMapTileLayer.toSprite(
    gameScene: GameScene,
    coordinate: GridCoordinate,
    tileset: HTMLImageElement
): BackgroundSpriteLayer =
    if (this is RawStaticImageLayer) {
        StaticImageBlockSprite(gameScene, coordinate, tileset, this)
    } else {
        AnimationSprite(gameScene, coordinate, tileset, this as RawAnimationLayer)
    }

interface BackgroundSpriteLayer : Sprite {
    /**
     * Upon the following cases, we must fall back to realtime rendering:
     * - If an animation tile contain more than 2 frames.
     * - If a layer is above player.
     */
    fun supportPrerender(): Boolean

    fun prerenderFrame(frameIndex: Int, canvas: CanvasRenderingContext2D)
}

class StaticImageBlockSprite(
    override val gameScene: GameScene,
    override val gridCoordinate: GridCoordinate,
    private val tileset: HTMLImageElement,
    private val imageLayer: RawStaticImageLayer
) : BackgroundSpriteLayer, CoordinateAware {
    override val id: String = "${gridCoordinate.x}-${gridCoordinate.y}-${imageLayer.layer}"
    override val layer: Int = imageLayer.layer
    override val roles: Set<GameObjectRole> = setOf(GameObjectRole.Sprite, GameObjectRole.CoordinateAware)
    override val pixelCoordinate: PixelCoordinate = gridCoordinate * gameScene.map.tileSize
    private val tileWidth = gameScene.map.tileSize.width
    private val tileHeight = gameScene.map.tileSize.width
    private val canvasState = gameScene.canvasState

    override fun supportPrerender() = imageLayer.layer < PLAYER_LAYER

    override fun prerenderFrame(frameIndex: Int, canvas: CanvasRenderingContext2D) {
        draw(canvas, pixelCoordinate.x, pixelCoordinate.y)
    }

    override fun outOfCanvas(): Boolean {
        return pixelCoordinate.x > canvasState.getCanvasCoordinateInMap().x ||
            pixelCoordinate.y > canvasState.getCanvasCoordinateInMap().y ||
            pixelCoordinate.x + tileWidth < 0 ||
            pixelCoordinate.y + tileHeight < 0
    }

    override fun draw(canvas: CanvasRenderingContext2D) {
        draw(
            canvas,
            pixelCoordinate.x - canvasState.getCanvasCoordinateInMap().x,
            pixelCoordinate.y - canvasState.getCanvasCoordinateInMap().y
        )
    }

    private fun draw(canvas: CanvasRenderingContext2D, dx: Int, dy: Int) {
        val sx = imageLayer.coordinate.x * tileWidth
        val sy = imageLayer.coordinate.y * tileHeight
        canvas.drawImage(
            tileset,
            sx.toDouble(), sy.toDouble(), tileWidth.toDouble(), tileHeight.toDouble(),
            dx.toDouble(), dy.toDouble(), tileWidth.toDouble(), tileHeight.toDouble()
        )
    }
}

class AnimationSprite(
    override val gameScene: GameScene,
    override val gridCoordinate: GridCoordinate,
    private val tileset: HTMLImageElement,
    private val animationLayer: RawAnimationLayer
) : BackgroundSpriteLayer, CoordinateAware {
    override val id: String = "${gridCoordinate.x}-${gridCoordinate.y}-${animationLayer.layer}"
    override val layer: Int = animationLayer.layer
    override val roles: Set<GameObjectRole> = setOf(GameObjectRole.Sprite, GameObjectRole.CoordinateAware)
    override val pixelCoordinate: PixelCoordinate = gridCoordinate * gameScene.map.tileSize
    private val tileWidth = gameScene.map.tileSize.width
    private val tileHeight = gameScene.map.tileSize.width
    private val canvasState = gameScene.canvasState
    private val frames = animationLayer.frames.toTypedArray()
    private val duration = animationLayer.frames[0].duration

    override fun supportPrerender() = animationLayer.layer < PLAYER_LAYER

    override fun prerenderFrame(frameIndex: Int, canvas: CanvasRenderingContext2D) {
        drawFrame(frameIndex, canvas, pixelCoordinate.x, pixelCoordinate.y)
    }

    override fun outOfCanvas(): Boolean {
        return pixelCoordinate.x > canvasState.getCanvasCoordinateInMap().x ||
            pixelCoordinate.y > canvasState.getCanvasCoordinateInMap().y ||
            pixelCoordinate.x + tileWidth < 0 ||
            pixelCoordinate.y + tileHeight < 0
    }

    override fun draw(canvas: CanvasRenderingContext2D) {
        drawFrame(
            getCurrentFrameIndex(frames, duration),
            canvas,
            pixelCoordinate.x - canvasState.getCanvasCoordinateInMap().x,
            pixelCoordinate.y - canvasState.getCanvasCoordinateInMap().y
        )
    }

    private fun drawFrame(
        frameIndex: Int,
        canvas: CanvasRenderingContext2D,
        dx: Int,
        dy: Int
    ) {
        val frame = frames[frameIndex]
        val sx = frame.coordinate.x * tileWidth
        val sy = frame.coordinate.y * tileHeight
        canvas.drawImage(
            tileset,
            sx.toDouble(), sy.toDouble(), tileWidth.toDouble(), tileHeight.toDouble(),
            dx.toDouble(), dy.toDouble(), tileWidth.toDouble(), tileHeight.toDouble()
        )
    }

    private fun getCurrentFrameIndex(frames: Array<RawTileAnimationFrame>, duration: Int): Int {
        return ((game.currentTimeMillis / duration) % frames.size).toInt()
    }
}
