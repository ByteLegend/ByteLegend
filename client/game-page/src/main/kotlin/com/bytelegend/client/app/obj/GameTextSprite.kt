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
import com.bytelegend.app.shared.objects.CoordinateAware
import com.bytelegend.app.shared.objects.GameMapText
import com.bytelegend.app.shared.objects.GameObjectRole
import org.w3c.dom.CanvasRenderingContext2D
import kotlin.math.PI

class GameTextSprite(
    override val gameScene: GameScene,
    private val obj: GameMapText
) : Sprite, CoordinateAware {
    override val id: String = obj.id
    override val layer: Int = obj.layer
    override val roles: Set<String> = jsObjectBackedSetOf(GameObjectRole.Sprite)

    override fun outOfCanvas() = obj.coordinate.outOfCanvas(gameScene)

    override fun draw(canvas: CanvasRenderingContext2D) {
        val coordinateInCanvas = obj.coordinate - gameScene.canvasState.getCanvasCoordinateInMap()
        canvas.save()
        canvas.translate(coordinateInCanvas.x.toDouble(), coordinateInCanvas.y.toDouble())
        // Firefox has known issue when rendering shadows.
        // It's INCREDIBLY SLOW when enabling shadow: 30 fps -> 1 fps in the profiling.
        if (!isFirefox()) {
            canvas.setShadow("rgba(0,0,0,0.8)", 10, 10, 4)
        }
        canvas.fillStyle = "white"
        canvas.rotate(obj.rotation.toDouble() * PI / 180)
        canvas.font = "bold ${obj.fontSize}px sans-serif"
        canvas.fillText(gameScene.gameRuntime.i(obj.id), 0.0, 0.0)
        canvas.restore()
    }

    override val pixelCoordinate: PixelCoordinate = obj.coordinate
    override val gridCoordinate: GridCoordinate = obj.coordinate / gameScene.map.tileSize
}
