package com.bytelegend.app.client.api

import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.shared.Direction

/**
 * We have to use instance method due to the defect of current module loading mechanism
 */
class GameScriptHelpers(val gameScene: GameScene) {
    fun distanceOf(character1Id: String, character2Id: String): Int {
        return getCharacter(character1Id).gridCoordinate.manhattanDistanceTo(
            getCharacter(character2Id).gridCoordinate
        )
    }

    fun getCharacter(characterId: String) = gameScene.objects.getById<Character>(characterId)

    fun faceDirectionOf(character1: Character, character2: Character): Direction {
        val npcCoordinate = character1.gridCoordinate
        val heroCoordinate = character2.gridCoordinate
        return when {
            npcCoordinate.x == heroCoordinate.x && npcCoordinate.y + 1 == heroCoordinate.y -> Direction.DOWN
            npcCoordinate.x == heroCoordinate.x && npcCoordinate.y - 1 == heroCoordinate.y -> Direction.UP
            npcCoordinate.y == heroCoordinate.y && npcCoordinate.x + 1 == heroCoordinate.x -> Direction.RIGHT
            npcCoordinate.y == heroCoordinate.y && npcCoordinate.x - 1 == heroCoordinate.x -> Direction.LEFT
            else -> throw IllegalStateException("Can't determine direction: $npcCoordinate, $heroCoordinate")
        }
    }

    /**
     * Standard NPC speech behaviour when clicked, including:
     *
     * 1. Ignore anonymous user.
     * 2. Say "I can't hear you" when hero is far from NPC.
     * 3. Make NPC and hero face to face.
     */
    fun standardNpcSpeech(npcId: String, onClick: UnitFunction) = click@{
        if (gameScene.gameRuntime.hero == null) {
            return@click
        }
        if (distanceOf(HERO_ID, npcId) > 1) {
            gameScene.scripts {
                speech {
                    objectId = npcId
                    contentHtmlId = "ICantHearYou"
                }
            }
            return@click
        }
        val npc = gameScene.objects.getById<Character>(npcId)
        val hero = gameScene.objects.getById<Character>(HERO_ID)
        gameScene.objects.getById<Character>(npcId).direction = faceDirectionOf(npc, hero)
        gameScene.gameRuntime.hero!!.direction = faceDirectionOf(hero, npc)

        onClick()
    }
}
