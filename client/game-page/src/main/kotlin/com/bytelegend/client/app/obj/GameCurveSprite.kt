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
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.objects.GameMapCurve
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.app.client.utils.jsObjectBackedSetOf
import org.w3c.dom.CanvasRenderingContext2D

class GameCurveSprite(
    override val gameScene: GameScene,
    private val obj: GameMapCurve
) : Sprite {
    override val id: String = "${obj.id}-sprite"
    override val layer: Int = obj.layer
    override val roles: Set<String> = jsObjectBackedSetOf(
        GameObjectRole.Sprite.toString(),
        GameObjectRole.MapCurve.toString()
    )
    val points: List<PixelCoordinate> = obj.points

    override fun outOfCanvas() = obj.points.all { it.outOfCanvas(gameScene) }

    override fun draw(canvas: CanvasRenderingContext2D) {
        canvas.drawCurve(this, gameScene)
    }
}
