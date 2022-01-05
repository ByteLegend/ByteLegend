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
package com.bytelegend.app.shared

const val ANIMATION_TILE_WIDTH = 96
const val ANIMATION_TILE_HEIGHT = 128
const val ANIMATION_SET_HORIZONTAL_NUMBER = 8
const val PLAYER_ANIMATION_SET_VERTICAL_NUMBER = 8
const val NPC_ANIMATION_SET_VERTICAL_NUMBER = 9999

val DOWN_STILL = GridCoordinate(1, 0)
val DOWN_MOVE_1 = GridCoordinate(0, 0)
val DOWN_MOVE_2 = GridCoordinate(2, 0)
val DOWN_MOVE = listOf(DOWN_MOVE_1, DOWN_MOVE_2)
val UP_STILL = GridCoordinate(1, 3)
val UP_MOVE_1 = GridCoordinate(0, 3)
val UP_MOVE_2 = GridCoordinate(2, 3)
val UP_MOVE = listOf(UP_MOVE_1, UP_MOVE_2)
val LEFT_STILL = GridCoordinate(1, 1)
val LEFT_MOVE_1 = GridCoordinate(0, 1)
val LEFT_MOVE_2 = GridCoordinate(2, 1)
val LEFT_MOVE = listOf(LEFT_MOVE_1, LEFT_MOVE_2)
val RIGHT_STILL = GridCoordinate(1, 2)
val RIGHT_MOVE_1 = GridCoordinate(0, 2)
val RIGHT_MOVE_2 = GridCoordinate(2, 2)
val RIGHT_MOVE = listOf(RIGHT_MOVE_1, RIGHT_MOVE_2)

fun playerAnimationSetResourceId(characterId: Int) = "animation-set-${animationSetId(characterId)}"

fun playerAnimationSetId(animationSetId: Int) = "animation-set-$animationSetId"

fun animationSetId(characterId: Int) = characterId / (ANIMATION_SET_HORIZONTAL_NUMBER * PLAYER_ANIMATION_SET_VERTICAL_NUMBER)

/**
 * The pixel coordinate of animation tile in animation set
 */
fun playerAnimationSetCoordinate(index: Int) = animationSetCoordinate(index, PLAYER_ANIMATION_SET_VERTICAL_NUMBER)

private fun animationSetCoordinate(characterId: Int, animationSetVerticalNumber: Int): PixelCoordinate {
    val offset = characterId % (ANIMATION_SET_HORIZONTAL_NUMBER * animationSetVerticalNumber)
    val x = offset % ANIMATION_SET_HORIZONTAL_NUMBER
    val y = offset / ANIMATION_SET_HORIZONTAL_NUMBER

    return PixelCoordinate(x * ANIMATION_TILE_WIDTH, y * ANIMATION_TILE_HEIGHT)
}
