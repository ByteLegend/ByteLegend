package com.bytelegend.app.shared

const val ANIMATION_TILE_WIDTH = 96
const val ANIMATION_TILE_HEIGHT = 128
const val ANIMATION_SET_HORIZONTAL_NUMBER = 8
const val PLAYER_ANIMATION_SET_VERTICAL_NUMBER = 8
const val NPC_ANIMATION_SET_VERTICAL_NUMBER = 9999

fun playerAnimationSetResourceId(animationSetId: Int) = "animation-set-$animationSetId"

fun animationSetId(characterId: Int) = characterId / (ANIMATION_SET_HORIZONTAL_NUMBER * PLAYER_ANIMATION_SET_VERTICAL_NUMBER)

/**
 * The pixel coordinate of animation tile in animation set
 */
fun playerAnimationSetCoordinate(index: Int) = animationSetCoordinate(index, PLAYER_ANIMATION_SET_VERTICAL_NUMBER)

//fun npcAnimationSetCoordinate(index: Int) = animationSetCoordinate(index, NPC_ANIMATION_SET_VERTICAL_NUMBER)

private fun animationSetCoordinate(characterId: Int, animationSetVerticalNumber: Int): PixelCoordinate {
    val offset = characterId % (ANIMATION_SET_HORIZONTAL_NUMBER * animationSetVerticalNumber)
    val x = offset % ANIMATION_SET_HORIZONTAL_NUMBER
    val y = offset / ANIMATION_SET_HORIZONTAL_NUMBER

    return PixelCoordinate(x * ANIMATION_TILE_WIDTH, y * ANIMATION_TILE_HEIGHT)
}