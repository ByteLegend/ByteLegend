/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.Sprite
import com.bytelegend.app.client.utils.jsObjectBackedSetOf
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.RawAnimationLayer
import com.bytelegend.app.shared.RawGameMapTileLayer
import com.bytelegend.app.shared.RawStaticImageLayer
import com.bytelegend.app.shared.RawTileAnimationFrame
import com.bytelegend.app.shared.objects.CoordinateAware
import com.bytelegend.app.shared.objects.GameObjectRole
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLImageElement

fun RawGameMapTileLayer.toSprite(
    gameScene: GameScene,
    coordinate: GridCoordinate,
    tileset: HTMLImageElement
): BackgroundLayer =
    if (this is RawStaticImageLayer) {
        StaticImageBlockBackgroundLayer(gameScene, coordinate, tileset, this)
    } else {
        AnimationBlockBackgroundLayer(gameScene, coordinate, tileset, this as RawAnimationLayer)
    }

interface BackgroundLayer : Sprite {
    fun prerenderFrame(frameIndex: Int, canvas: CanvasRenderingContext2D)
}

class StaticImageBlockBackgroundLayer(
    override val gameScene: GameScene,
    override val gridCoordinate: GridCoordinate,
    private val tileset: HTMLImageElement,
    private val imageLayer: RawStaticImageLayer
) : BackgroundLayer, CoordinateAware {
    override val id: String = "${gridCoordinate.x}-${gridCoordinate.y}-${imageLayer.layer}"
    override val layer: Int = imageLayer.layer
    override val roles: Set<String> = jsObjectBackedSetOf(GameObjectRole.Sprite, GameObjectRole.CoordinateAware)
    override val pixelCoordinate: PixelCoordinate = gridCoordinate * gameScene.map.tileSize
    private val tileWidth = gameScene.map.tileSize.width
    private val tileHeight = gameScene.map.tileSize.width
    private val canvasState = gameScene.canvasState

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

class AnimationBlockBackgroundLayer(
    override val gameScene: GameScene,
    override val gridCoordinate: GridCoordinate,
    private val tileset: HTMLImageElement,
    private val animationLayer: RawAnimationLayer
) : BackgroundLayer, CoordinateAware {
    override val id: String = "${gridCoordinate.x}-${gridCoordinate.y}-${animationLayer.layer}"
    override val layer: Int = animationLayer.layer
    override val roles: Set<String> = jsObjectBackedSetOf(GameObjectRole.Sprite.toString(), GameObjectRole.CoordinateAware.toString())
    override val pixelCoordinate: PixelCoordinate = gridCoordinate * gameScene.map.tileSize
    private val tileWidth = gameScene.map.tileSize.width
    private val tileHeight = gameScene.map.tileSize.width
    private val canvasState = gameScene.canvasState
    private val frames = animationLayer.frames.toTypedArray()
    private val duration = animationLayer.frames[0].duration

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
        return ((gameScene.gameRuntime.elapsedTimeSinceStart / duration) % frames.size).toInt()
    }
}
