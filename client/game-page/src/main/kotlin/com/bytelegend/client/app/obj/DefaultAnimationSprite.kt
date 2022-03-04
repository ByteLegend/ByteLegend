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

import com.bytelegend.app.client.api.AnimationSprite
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.ImageResourceData
import com.bytelegend.app.client.misc.uuid
import com.bytelegend.app.client.utils.jsObjectBackedSetOf
import com.bytelegend.app.shared.objects.GameMapAnimation
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.app.shared.util.currentTimeMillis
import org.w3c.dom.CanvasRenderingContext2D

class DefaultAnimationSprite(
    override val gameScene: GameScene,
    override val image: ImageResourceData,
    override val gameMapAnimation: GameMapAnimation,
    private val frameDurationMs: Long,
    /**
     * How many times it should be looped. 0 means infinite loop
     */
    private val loop: Int = 1,
    private val initDelayMs: Long = 0,
    private val onDraw: AnimationSprite.(CanvasRenderingContext2D, Int) -> Unit,
    private val onClose: () -> Unit = {}
) : AnimationSprite {
    override val id: String = "${gameMapAnimation.id}-${uuid()}"
    override val layer: Int = gameMapAnimation.layer
    override val roles: Set<String> = jsObjectBackedSetOf(GameObjectRole.Sprite.toString(), GameObjectRole.Animation.toString())
    private var startTimeMs: Long = 0

    fun init() {
        gameScene.objects.add(this)
        startTimeMs = currentTimeMillis()
    }

    override fun outOfCanvas(): Boolean = false

    private fun close() {
        onClose()
        gameScene.objects.remove<GameObject>(this.id)
    }

    override fun draw(canvas: CanvasRenderingContext2D) {
        val elapsedTimeMs = currentTimeMillis() - startTimeMs
        if (elapsedTimeMs >= initDelayMs) {
            val frame = determineFrame(elapsedTimeMs - initDelayMs)
            if (frame == -1) {
                close()
            } else {
                canvas.save()
                onDraw(canvas, frame)
                canvas.restore()
            }
        }
    }

    /**
     * Returns current frame to draw. Returns -1 if the animation is over.
     */
    private fun determineFrame(elapsedTimeMs: Long): Int {
        if (loop == 0 || elapsedTimeMs < frameDurationMs * gameMapAnimation.frameCount * loop) {
            return ((elapsedTimeMs / frameDurationMs).toInt()) % gameMapAnimation.frameCount
        } else {
            return -1
        }
    }
}
