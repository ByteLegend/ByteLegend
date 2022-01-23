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
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelBlock
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.math.imageBlockOnCanvas
import com.bytelegend.app.shared.math.outOfCanvas
import com.bytelegend.app.shared.objects.CoordinateAware
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.app.client.utils.jsObjectBackedSetOf

fun PixelBlock.outOfCanvas(gameScene: GameScene): Boolean {
    return x > gameScene.canvasState.getCanvasPixelSize().width ||
        y > gameScene.canvasState.getCanvasPixelSize().height ||
        x + width < 0 ||
        y + height < 0
}

fun CoordinateAware.getSpriteBlockOnCanvas(gameScene: GameScene) = PixelBlock(
    pixelCoordinate.x - gameScene.canvasState.getCanvasCoordinateInMap().x,
    pixelCoordinate.y - gameScene.canvasState.getCanvasCoordinateInMap().y,
    gameScene.map.tileSize.width,
    gameScene.map.tileSize.height
)

/**
 * A sprite with static location (but might have animation frames,
 * like running river, waterfall, etc.)
 */
abstract class AbstractStaticLocationSprite(
    override val gridCoordinate: GridCoordinate,
    override val pixelCoordinate: PixelCoordinate
) : CoordinateAware, Sprite {
    abstract val pixelSize: PixelSize
    override val roles: Set<String> = jsObjectBackedSetOf(GameObjectRole.Sprite.toString())
    override fun outOfCanvas() = getImageBlockOnCanvas(gameScene).outOfCanvas(gameScene.canvasState.getCanvasPixelSize())
    open fun getImageBlockOnCanvas(gameScene: GameScene) =
        imageBlockOnCanvas(pixelCoordinate, gameScene.canvasState.getCanvasCoordinateInMap(), pixelSize)
}
