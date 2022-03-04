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
package com.bytelegend.client.app.obj.character

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.ImageResourceData
import com.bytelegend.app.shared.DOWN_MOVE
import com.bytelegend.app.shared.DOWN_STILL
import com.bytelegend.app.shared.Direction
import com.bytelegend.app.shared.Direction.DOWN
import com.bytelegend.app.shared.Direction.LEFT
import com.bytelegend.app.shared.Direction.LEFT_DOWN
import com.bytelegend.app.shared.Direction.LEFT_UP
import com.bytelegend.app.shared.Direction.RIGHT
import com.bytelegend.app.shared.Direction.RIGHT_DOWN
import com.bytelegend.app.shared.Direction.RIGHT_UP
import com.bytelegend.app.shared.Direction.UP
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.LEFT_MOVE
import com.bytelegend.app.shared.LEFT_STILL
import com.bytelegend.app.shared.PixelBlock
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.RIGHT_MOVE
import com.bytelegend.app.shared.RIGHT_STILL
import com.bytelegend.app.shared.UP_MOVE
import com.bytelegend.app.shared.UP_STILL
import com.bytelegend.app.shared.objects.GameMapDynamicSprite
import com.bytelegend.app.shared.playerAnimationSetCoordinate
import com.bytelegend.app.shared.playerAnimationSetResourceId
import com.bytelegend.client.app.engine.Game
import org.w3c.dom.HTMLImageElement

interface AnimationSet {
    fun getFrame(still: Boolean, direction: Direction): Pair<HTMLImageElement, PixelBlock>
}

/**
 * AnimationSet stored in "player-collection" PNG, i.e. animation-set-X.png
 */
class TwelveTilesAnimationSet(
    private val gameScene: GameScene,
    characterId: Int
) : AnimationSet {
    private val htmlImageElement: HTMLImageElement =
        gameScene.gameRuntime.unsafeCast<Game>().resourceLoader.getLoadedResource<ImageResourceData>(playerAnimationSetResourceId(characterId)).htmlElement
    private val topLeftCorner: PixelCoordinate = playerAnimationSetCoordinate(characterId)
    override fun getFrame(still: Boolean, direction: Direction): Pair<HTMLImageElement, PixelBlock> {
        val coordinateIn12Set = if (still) {
            when (direction) {
                UP -> UP_STILL
                DOWN -> DOWN_STILL
                LEFT -> LEFT_STILL
                RIGHT -> RIGHT_STILL
                else -> throw IllegalStateException()
            }
        } else {
            when (direction) {
                UP -> animationFrameOf(gameScene, UP_MOVE)
                DOWN -> animationFrameOf(gameScene, DOWN_MOVE)
                LEFT -> animationFrameOf(gameScene, LEFT_MOVE)
                RIGHT -> animationFrameOf(gameScene, RIGHT_MOVE)
                else -> throw IllegalStateException()
            }
        }
        val frame = topLeftCorner + coordinateIn12Set * gameScene.map.tileSize
        return htmlImageElement to PixelBlock(frame, gameScene.map.tileSize)
    }
}

/**
 * AnimationSet stored in tileset. The frame information is stored in DynamicSprite
 */
class MapTilesetAnimationSet(
    private val gameScene: GameScene,
    dynamicSprite: GameMapDynamicSprite
) : AnimationSet {
    private val tileset = gameScene.tileset.htmlElement

    // TODO support multi-tile sprite
    private val frames: List<GridCoordinate> = dynamicSprite.frames[0][0]
    private val downStill = frames[1]
    private val downMove = listOf(frames[0], frames[2])
    private val upStill = frames[10]
    private val upMove = listOf(frames[9], frames[11])
    private val leftStill = frames[4]
    private val leftMove = listOf(frames[3], frames[5])
    private val rightStill = frames[7]
    private val rightMove = listOf(frames[6], frames[8])

    override fun getFrame(still: Boolean, direction: Direction): Pair<HTMLImageElement, PixelBlock> {
        val coordinateInTileset = if (still) {
            when (direction) {
                UP, LEFT_UP, RIGHT_UP -> upStill
                DOWN, LEFT_DOWN, RIGHT_DOWN -> downStill
                LEFT -> leftStill
                RIGHT -> rightStill
                else -> throw IllegalStateException()
            }
        } else {
            when (direction) {
                UP, LEFT_UP, RIGHT_UP -> animationFrameOf(gameScene, upMove)
                DOWN, LEFT_DOWN, RIGHT_DOWN -> animationFrameOf(gameScene, downMove)
                LEFT -> animationFrameOf(gameScene, leftMove)
                RIGHT -> animationFrameOf(gameScene, rightMove)
                else -> throw IllegalStateException()
            }
        }
        return tileset to PixelBlock(coordinateInTileset * gameScene.map.tileSize, gameScene.map.tileSize)
    }
}

private fun animationFrameOf(gameScene: GameScene, frames: List<GridCoordinate>): GridCoordinate {
    // ms/(1000/fps) % frameSize
    //
    // Given 2-frame animation
    // 2 fps:
    //   0~499   -> 0
    //   500~999 -> 1
    // 4 fps:
    //   0~249   -> 0
    //   250~499 -> 1
    //   500-749 -> 0
    //   750-999 -> 1
    val frameIndex = (gameScene.gameRuntime.elapsedTimeSinceStart * CHARACTER_ANIMATION_FPS / 1000).toInt() % frames.size
    return frames[frameIndex]
}
