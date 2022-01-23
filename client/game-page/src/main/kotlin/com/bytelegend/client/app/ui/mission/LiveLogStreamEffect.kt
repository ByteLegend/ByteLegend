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
package com.bytelegend.client.app.ui.mission

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.Sprite
import com.bytelegend.app.client.api.Timestamp
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.LOG_STREAM_EFFECT_LAYER
import com.bytelegend.app.shared.PixelBlock
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.obj.outOfCanvas
import com.bytelegend.app.client.utils.JSArrayBackedList
import com.bytelegend.app.client.utils.jsObjectBackedSetOf
import org.w3c.dom.CENTER
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.CanvasTextAlign
import kotlin.math.pow
import kotlin.random.Random

private const val LOG_STREAM_EFFECT_WIDTH = 500
private const val LOG_STREAM_EFFECT_HEIGHT = 500
private const val GRAVITATIONAL_ACCELERATION = 0.1

class LiveLogStreamEffect(
    override val id: String,
    override val gameScene: GameScene,
    missionPoint: GridCoordinate,
) : Sprite {
    override val layer: Int = LOG_STREAM_EFFECT_LAYER
    override val roles: Set<String> = jsObjectBackedSetOf(GameObjectRole.Sprite.toString())
    private val endPointOnMap = missionPoint * gameScene.map.tileSize
    private val linesDroppings: MutableList<LogStreamLinesDropping> = JSArrayBackedList()

    init {
        gameScene.objects.add(this)
    }

    fun close() {
        gameScene.objects.remove<Sprite>(id)
    }

    override fun outOfCanvas(): Boolean {
        val blockOnCanvas = PixelBlock(
            endPointOnMap.x - LOG_STREAM_EFFECT_WIDTH / 2 - gameScene.canvasState.getCanvasCoordinateInMap().x,
            endPointOnMap.y - LOG_STREAM_EFFECT_HEIGHT - gameScene.canvasState.getCanvasCoordinateInMap().y,
            LOG_STREAM_EFFECT_WIDTH,
            LOG_STREAM_EFFECT_HEIGHT
        )
        return blockOnCanvas.outOfCanvas(gameScene)
    }

    override fun draw(canvas: CanvasRenderingContext2D) {
        linesDroppings.forEach {
            it.draw(canvas)
        }
    }

    fun addLines(newLines: List<String>) {
        linesDroppings.add(
            LogStreamLinesDropping(randomStartPoint(), endPointOnMap, newLines)
        )
    }

    private fun randomStartPoint(): PixelCoordinate {
        val x = Random.nextInt(endPointOnMap.x - LOG_STREAM_EFFECT_WIDTH / 2, endPointOnMap.x + LOG_STREAM_EFFECT_WIDTH / 2)
        val y = endPointOnMap.y - LOG_STREAM_EFFECT_HEIGHT
        return PixelCoordinate(x, y)
    }

    inner class LogStreamLinesDropping(
        private val startPointOnMap: PixelCoordinate,
        private val endPointOnMap: PixelCoordinate,
        private val lines: List<String>
    ) {
        private val controlPoint = PixelCoordinate(endPointOnMap.x, startPointOnMap.y)
        private val startTime = Timestamp.now()

        // 0.0 - 1.0
        private var ratio: Double = 0.0

        fun draw(canvas: CanvasRenderingContext2D) {
            if (ratio > 1.0) {
                linesDroppings.remove(this)
            } else {
                // h = GRAVITATIONAL_ACCELERATION * t^2
                val elapsedTimeSecond = (Timestamp.now() - startTime).toDouble() / 1000
                ratio = elapsedTimeSecond.pow(2) * GRAVITATIONAL_ACCELERATION
            }

            val x = (1 - ratio).pow(2) * startPointOnMap.x + 2 * (1 - ratio) * ratio * controlPoint.x + ratio.pow(2) * endPointOnMap.x -
                gameScene.canvasState.getCanvasCoordinateInMap().x
            val y = (1 - ratio).pow(2) * startPointOnMap.y + 2 * (1 - ratio) * ratio * controlPoint.y + ratio.pow(2) * endPointOnMap.y -
                gameScene.canvasState.getCanvasCoordinateInMap().y

            canvas.save()
            canvas.fillStyle = "white"
            canvas.strokeStyle = "black"
            canvas.font = "bold 20px sans-serif"
            canvas.textAlign = CanvasTextAlign.CENTER
            val lineHeightPx = 20
            lines.forEachIndexed { index, line ->
                canvas.fillText(line, x, y + index * lineHeightPx)
            }
            canvas.restore()
        }
    }
}
