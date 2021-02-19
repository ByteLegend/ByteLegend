package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.CoordinateAware
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.Sprite
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.RawAnimationLayer
import com.bytelegend.app.shared.RawGameMapTileLayer
import com.bytelegend.app.shared.RawStaticImageLayer
import com.bytelegend.app.shared.RawTileAnimationFrame
import com.bytelegend.app.shared.objects.GameObjectRole
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLImageElement

fun RawGameMapTileLayer.toSprite(
    gameScene: GameScene,
    coordinate: GridCoordinate,
    tileset: HTMLImageElement
): Sprite =
    if (this is RawStaticImageLayer) {
        StaticImageBlockSprite(gameScene, coordinate, tileset, this)
    } else {
        AnimationSprite(gameScene, coordinate, tileset, this as RawAnimationLayer)
    }

class StaticImageBlockSprite(
    override val gameScene: GameScene,
    override val gridCoordinate: GridCoordinate,
    private val tileset: HTMLImageElement,
    private val imageLayer: RawStaticImageLayer
) : Sprite, CoordinateAware {
    override val id: String = "${gridCoordinate.x}-${gridCoordinate.y}-${imageLayer.layer}"
    override val layer: Int = imageLayer.layer
    override val roles: Set<GameObjectRole> = setOf(GameObjectRole.Sprite, GameObjectRole.CoordinateAware)
    override val pixelCoordinate: PixelCoordinate = gridCoordinate * gameScene.map.tileSize
    private val tileWidth = gameScene.map.tileSize.width
    private val tileHeight = gameScene.map.tileSize.width
    private val canvasState = gameScene.canvasState
    override fun outOfCanvas(): Boolean {
        return pixelCoordinate.x > canvasState.getCanvasCoordinateInMap().x ||
            pixelCoordinate.y > canvasState.getCanvasCoordinateInMap().y ||
            pixelCoordinate.x + tileWidth < 0 ||
            pixelCoordinate.y + tileHeight < 0
    }

    override fun draw(canvas: CanvasRenderingContext2D) {
        val sx = imageLayer.coordinate.x * tileWidth
        val sy = imageLayer.coordinate.y * tileHeight
        val dx = pixelCoordinate.x - canvasState.getCanvasCoordinateInMap().x
        val dy = pixelCoordinate.y - canvasState.getCanvasCoordinateInMap().y
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
) : Sprite, CoordinateAware {
    override val id: String = "${gridCoordinate.x}-${gridCoordinate.y}-${animationLayer.layer}"
    override val layer: Int = animationLayer.layer
    override val roles: Set<GameObjectRole> = setOf(GameObjectRole.Sprite, GameObjectRole.CoordinateAware)
    override val pixelCoordinate: PixelCoordinate = gridCoordinate * gameScene.map.tileSize
    private val tileWidth = gameScene.map.tileSize.width
    private val tileHeight = gameScene.map.tileSize.width
    private val canvasState = gameScene.canvasState
    private val frames = animationLayer.frames.toTypedArray()
    private val duration = animationLayer.frames[0].duration

    override fun outOfCanvas(): Boolean {
        return pixelCoordinate.x > canvasState.getCanvasCoordinateInMap().x ||
            pixelCoordinate.y > canvasState.getCanvasCoordinateInMap().y ||
            pixelCoordinate.x + tileWidth < 0 ||
            pixelCoordinate.y + tileHeight < 0
    }

    override fun draw(canvas: CanvasRenderingContext2D) {
        val frame = getCurrentFrame(frames, duration)
        val sx = frame.coordinate.x * tileWidth
        val sy = frame.coordinate.y * tileHeight
        val dx = pixelCoordinate.x - canvasState.getCanvasCoordinateInMap().x
        val dy = pixelCoordinate.y - canvasState.getCanvasCoordinateInMap().y
        canvas.drawImage(
            tileset,
            sx.toDouble(), sy.toDouble(), tileWidth.toDouble(), tileHeight.toDouble(),
            dx.toDouble(), dy.toDouble(), tileWidth.toDouble(), tileHeight.toDouble()
        )
    }

    private fun getCurrentFrame(frames: Array<RawTileAnimationFrame>, duration: Int): RawTileAnimationFrame {
        // for performance purpose.
        // Comparing to original Kotlin version, the native JS code improves by ~10%
        return js("frames[Math.floor(new Date().getTime()/duration) % frames.length]")
    }
}
