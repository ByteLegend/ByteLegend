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

import com.bytelegend.app.client.api.Animation
import com.bytelegend.app.client.api.DynamicSprite
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.Static
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.client.utils.jsObjectBackedSetOf
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelBlock
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.objects.GameMapDynamicSprite
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.engine.atTileBorder
import org.w3c.dom.CanvasRenderingContext2D

/**
 * A DynamicSprite is added to the map and controlled by game script,
 * which is much more flexible.
 */
class DefaultDynamicSprite(
    override val id: String,
    override val gameScene: GameScene,
    override val gridCoordinate: GridCoordinate,
    override var mapDynamicSprite: GameMapDynamicSprite,
) : DynamicSprite, AbstractStaticLocationSprite(
    gridCoordinate,
    gridCoordinate * gameScene.map.tileSize
) {
    override val roles: Set<String> = jsObjectBackedSetOf(
        GameObjectRole.Sprite,
        GameObjectRole.CoordinateAware,
        GameObjectRole.Clickable,
        GameObjectRole.UnableToBeSetAsDestination.toString()
    )
    override var animation: Animation = Static
    override var onClickFunction: UnitFunction? = null
    override var onCloseFunction: UnitFunction? = null
    override val layer: Int = mapDynamicSprite.layer
    override val pixelSize: PixelSize = mapDynamicSprite.size * gameScene.map.tileSize

    override fun onClick() {
        // if the player is current moving and not at the border of the tile, not trigger
        // the click event on game object because the object handler may change moving state
        // of the player.
        val hero = gameScene.gameRuntime.hero
        if (hero == null || atTileBorder(gameScene.map.tileSize, hero.pixelCoordinate)) {
            onClickFunction?.invoke()
        }
    }

    override fun draw(canvas: CanvasRenderingContext2D) {
        canvas.draw(this)
    }

    fun close() {
        onCloseFunction?.invoke()
        gameScene.objects.remove<GameObject>(this.id)
    }
}

fun CanvasRenderingContext2D.draw(dynamicSprite: DynamicSprite) {
    val coordinateInCanvas = dynamicSprite.pixelCoordinate - dynamicSprite.gameScene.canvasState.getCanvasCoordinateInMap()
    val mapDynamicSprite = dynamicSprite.mapDynamicSprite
    val gameScene = dynamicSprite.gameScene
    val nextFrame = dynamicSprite.animation.getNextFrameIndex()
    if (nextFrame == -1) {
        return
    }

    for (y in 0 until mapDynamicSprite.size.height) {
        for (x in 0 until mapDynamicSprite.size.width) {
            val frame = mapDynamicSprite.frames[y][x][nextFrame]
            drawImage(
                gameScene.tileset.htmlElement,
                PixelBlock(frame * gameScene.map.tileSize, gameScene.map.tileSize),
                PixelBlock(coordinateInCanvas + gameScene.map.tileSize * GridCoordinate(x, y), gameScene.map.tileSize),
            )
        }
    }
}
