package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.AbstractStaticLocationSprite
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.Sprite
import com.bytelegend.app.shared.PixelBlock
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.RawAnimationLayer
import com.bytelegend.app.shared.RawGameMapTileLayer
import com.bytelegend.app.shared.RawStaticImageLayer
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLImageElement

fun RawGameMapTileLayer.toSprite(
    gameScene: GameScene,
    coordinate: PixelCoordinate,
    tileset: HTMLImageElement
): Sprite =
    if (this is RawStaticImageLayer) {
        StaticImageBlockSprite(gameScene, coordinate, tileset, this)
    } else {
        AnimationSprite(gameScene, coordinate, tileset, this as RawAnimationLayer)
    }

class StaticImageBlockSprite(
    override val gameScene: GameScene,
    coordinate: PixelCoordinate,
    private val tileset: HTMLImageElement,
    private val imageLayer: RawStaticImageLayer
) : AbstractStaticLocationSprite(coordinate, gameScene.map.tileSize) {
    override val id: String = "${coordinate.x}-${coordinate.y}-${imageLayer.layer}"
    override val layer: Int
        get() = imageLayer.layer

    override fun draw(canvas: CanvasRenderingContext2D) {
        getImageBlockOnCanvas(gameScene).run {
            canvas.drawImage(tileset, PixelBlock(imageLayer.coordinate, gameScene.map.tileSize), this)
        }
    }
}

class AnimationSprite(
    override val gameScene: GameScene,
    coordinate: PixelCoordinate,
    private val tileset: HTMLImageElement,
    private val animationLayer: RawAnimationLayer
) : AbstractStaticLocationSprite(coordinate, gameScene.map.tileSize) {
    override val id: String = "${coordinate.x}-${coordinate.y}-${animationLayer.layer}"
    override val layer: Int = animationLayer.layer

    override fun draw(canvas: CanvasRenderingContext2D) {
        getImageBlockOnCanvas(gameScene).run {
            canvas.drawImage(
                tileset,
                PixelBlock(animationLayer.getCurrentFrame(gameScene.gameRuntime.currentTimeMillis).coordinate, gameScene.map.tileSize),
                this
            )
        }
    }
}
