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
import com.bytelegend.app.shared.PixelCoordinate
import org.w3c.dom.CanvasRenderingContext2D

val OUTER_GLOW_ANIMATION_FPS = 2

interface Effect {
    fun draw(origin: PixelCoordinate, gameScene: GameScene, canvas: CanvasRenderingContext2D)
}

object NoEffect : Effect {
    override fun draw(origin: PixelCoordinate, gameScene: GameScene, canvas: CanvasRenderingContext2D) {
    }
}

class RectangleOuterGlowEffect(
    private val x: Int,
    private val y: Int,
    private val w: Int,
    private val h: Int,
    private val blur: Int,
    private val color: String
) : Effect {
    override fun draw(origin: PixelCoordinate, gameScene: GameScene, canvas: CanvasRenderingContext2D) {
        val frameIndex = (gameScene.gameRuntime.elapsedTimeSinceStart * OUTER_GLOW_ANIMATION_FPS / 1000).toInt() % 2
        if (frameIndex == 0) {
            canvas.save()
            canvas.setShadow(color, 0, 0, blur)
            canvas.fillRect(origin.x.toDouble() + x, origin.y.toDouble() + y, w.toDouble(), h.toDouble())
            canvas.restore()
        }
    }
}
