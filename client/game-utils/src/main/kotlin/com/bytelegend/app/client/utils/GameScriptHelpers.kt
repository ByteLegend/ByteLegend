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
package com.bytelegend.app.client.utils

import com.bytelegend.app.client.api.AnimationFrame
import com.bytelegend.app.client.api.Character
import com.bytelegend.app.client.api.DynamicSprite
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.FramePlayingAnimation
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.HERO_ID
import com.bytelegend.app.client.api.StaticFrame
import com.bytelegend.app.client.api.closeMissionModalEvent
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.client.api.missionItemUsedEvent
import com.bytelegend.app.client.api.missionRepaintEvent
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.objects.GameMapCurve
import com.bytelegend.app.shared.objects.GridCoordinateAware
import com.bytelegend.app.shared.protocol.ChallengeUpdateEventData
import kotlinx.browser.window

/**
 * Configure the animation for a sprite, based on the latest item usage state.
 * For example, if a key is not used, the sprite should be a "closed chest",
 * otherwise, if the chest is already an "open chest", do nothing,
 * otherwise, display the animation and show the "open chest" frame eventually.
 */
fun GameScene.refreshAnimationForItem(
    sprite: DynamicSprite,
    itemId: String,
    /* the frame to display if it's finished, e.g. an open chest. Nothe that we only support static frame for finished state. */
    finishedStateFrameIndex: Int,
    /* the animation to display if it's unfinished, e.g. a closed chest */
    unfinishedStateAnimationFrameRange: AnimationFrameRange,
    /* the animation to display if it's changed from unfinished to finished, e.g. the chest opening animation. null means no animation */
    finishStateChangeAnimationFrameRange: AnimationFrameRange? = null,
    animationFrameDurationMs: Int = 500,
    removeBlocker: Boolean = true
) {
    if (!gameRuntime.heroPlayer.usedItems.contains(itemId)) {
        // Not finished, show the unfinished state
        sprite.setAnimation(animationFrameDurationMs, unfinishedStateAnimationFrameRange)
    } else if (!sprite.animation.isStatic || sprite.animation.getNextFrameIndex() != finishedStateFrameIndex) {
        if (removeBlocker) {
            removeMissionBlocker(sprite)
        }

        if (finishStateChangeAnimationFrameRange == null) {
            sprite.animation = StaticFrame(finishedStateFrameIndex)
        } else {
            val stateChangeAnimation = sprite.mapDynamicSprite.animationWithFixedInterval(
                animationFrameDurationMs, finishStateChangeAnimationFrameRange.startIndexInclusive, finishStateChangeAnimationFrameRange.endIndexExclusive, false
            )
            sprite.animation = stateChangeAnimation
            window.setTimeout({
                sprite.animation = StaticFrame(finishedStateFrameIndex)
            }, animationFrameDurationMs * (stateChangeAnimation.frames.size))
        }
    }
}

fun GameScene.refreshTeslaCoilAnimation(mission: DynamicSprite) {
    val mapId = map.id
    val frameIntervalMs = 200
    val teslaCoilRunningAnimationRange = AnimationFrameRange(2, 11)
    if (gameRuntime.heroPlayer.usedItems.contains("gold-sword:$mapId:${mission.id}")) {
        refreshAnimationForItem(mission, "gold-sword:$mapId:${mission.id}", 11, teslaCoilRunningAnimationRange, animationFrameDurationMs = frameIntervalMs, removeBlocker = true)
    } else if (gameRuntime.heroPlayer.usedItems.contains("silver-sword:$mapId:${mission.id}")) {
        refreshAnimationForItem(mission, "silver-sword:$mapId:${mission.id}", 1, teslaCoilRunningAnimationRange, animationFrameDurationMs = frameIntervalMs, removeBlocker = false)
    } else if (gameRuntime.heroPlayer.usedItems.contains("iron-sword:$mapId:${mission.id}")) {
        refreshAnimationForItem(mission, "iron-sword:$mapId:${mission.id}", 0, teslaCoilRunningAnimationRange, animationFrameDurationMs = frameIntervalMs, removeBlocker = false)
    } else {
        mission.setAnimation(frameIntervalMs, teslaCoilRunningAnimationRange)
    }
}

// Remove mission from the map so player can walk through it
// For example, the mission is a blocker stone, people can walk through after finishing mission
// the mission is an evil, after finishing the mission it becomes a dead body
// See `GameMission.init`
fun GameScene.removeMissionBlocker(mission: DynamicSprite) {
    val dynamicSprite = mission.mapDynamicSprite
    val missionX = mission.unsafeCast<GridCoordinateAware>().gridCoordinate.x
    val missionY = mission.unsafeCast<GridCoordinateAware>().gridCoordinate.y

    for (y in 0 until dynamicSprite.size.height) {
        for (x in 0 until dynamicSprite.size.width) {
            blockers[missionY + y][missionX + x]++
            objects.removeFromCoordinate(mission, GridCoordinate(missionX + x, missionY + y))
        }
    }
}

fun GameScene.onMissionItemUsed(missionId: String, callback: (String) -> Unit) {
    gameRuntime.eventBus.on(missionItemUsedEvent(missionId), callback)
}

fun GameScene.configureChestOpenByKey(missionId: String) {
    refreshAnimationForItem(
        objects.getById(missionId),
        "key:${map.id}:$missionId",
        3,
        AnimationFrameRange(0, 0),
        AnimationFrameRange(),
        500,
        false
    )
    onMissionItemUsed(missionId) { _: String ->
        refreshAnimationForItem(
            objects.getById(missionId),
            "key:${map.id}:$missionId",
            3,
            AnimationFrameRange(0, 0),
            AnimationFrameRange(),
            500,
            false
        )
    }
}

// TODO this should not in `game-api` module
/**
 * We have to use instance method due to the defect of current module loading mechanism
 */
class GameScriptHelpers(val gameScene: GameScene) {
    private fun addCloseCallbackToMission(mission: DynamicSprite, callback: EventListener<Unit>) {
        gameScene.gameRuntime.eventBus.on(closeMissionModalEvent(mission.id), callback)
    }

    fun configureAnimation(sprite: DynamicSprite, animationFrameNumber: Int, animationFrameDurationMs: Int = 500) {
        if (gameScene.challengeAnswers.missionAccomplished(sprite.id)) {
            sprite.animation = StaticFrame(animationFrameNumber)
        } else {
            sprite.animation = sprite.mapDynamicSprite.animationWithFixedInterval(animationFrameDurationMs, animationFrameNumber)
        }
    }

    fun configureChest(missionId: String) {
        val mission = gameScene.objects.getById<DynamicSprite>(missionId)

        if (gameScene.challengeAnswers.missionAccomplished(missionId)) {
            mission.animation = StaticFrame(3)
        } else {
            mission.animation = StaticFrame(0)
        }
        addCloseCallbackToMission(mission) {
            if (mission.animation.isStatic &&
                mission.animation.unsafeCast<StaticFrame>().frameIndex != 3 &&
                gameScene.challengeAnswers.missionAccomplished(missionId)
            ) {
                // the player finishes the challenge. Play the animation
                mission.animation = FramePlayingAnimation(
                    frames = arrayOf(
                        AnimationFrame(0, 300),
                        AnimationFrame(1, 300),
                        AnimationFrame(2, 300),
                        AnimationFrame(3, 300)
                    ),
                    repetitive = false
                )
                window.setTimeout({
                    mission.animation = StaticFrame(3)
                }, 1200)
            }
        }
    }

    fun addMissionRepaintCallback(mission: DynamicSprite, callback: EventListener<ChallengeUpdateEventData>) {
        gameScene.gameRuntime.eventBus.on(missionRepaintEvent(mission.id), callback)

        mission.onCloseFunction = {
            gameScene.gameRuntime.eventBus.remove(missionRepaintEvent(mission.id), callback)
        }
    }

    fun distanceOf(character1Id: String, character2Id: String): Int {
        return getCharacter(character1Id).gridCoordinate.manhattanDistanceTo(
            getCharacter(character2Id).gridCoordinate
        )
    }

    /**
     * Search a path along the `GameMapCurve` points, for vehicles, like boats, planes, etc.
     */
    fun searchVehiclePath(curveName: String): List<GridCoordinate> {
        val list = mutableListOf<GridCoordinate>()
        val curvePoints = gameScene.objects.getById<GameMapCurve>(curveName).points
        for (i in 0 until curvePoints.size - 1) {
            val start = curvePoints[i] / gameScene.map.tileSize
            val end = curvePoints[i + 1] / gameScene.map.tileSize
            val path = gameScene.searchPath(start, end) { false }
            // Not adding the last point because it's next path's start point
            // If we don't do this will get: [(1,1), (1,2), (1,3), (1,3), ...]
            for (j in 0 until path.size - 1) {
                list.add(path[j])
            }
        }
        return list
    }

    fun getCharacter(characterId: String) = gameScene.objects.getById<Character>(characterId)

    /**
     * Standard NPC speech behaviour when clicked, including:
     *
     * 1. Display toast "you are not login" for anonymous user.
     * 2. Make hero move to the npc, make them face to face, then call `onInteraction`
     * 3. If hero can't reach NPC, call `onUnreachable`
     */
    fun standardNpcSpeech(
        npcId: String,
        onInteraction: UnitFunction
    ) = standardNpcSpeech(npcId, onInteraction) {}

    fun standardNpcSpeech(
        npcId: String,
        onInteraction: UnitFunction,
        onUnreachable: UnitFunction
    ) = click@{
        if (gameScene.gameRuntime.hero == null) {
            gameScene.gameRuntime.toastController.addToast(
                gameScene.gameRuntime.i("YouAreNotLoggedIn"),
                gameScene.gameRuntime.i("ClickHereToLogin"),
                5000
            )
            return@click
        }
        // hero is not in the current scene
        val hero = gameScene.objects.getByIdOrNull<Character>(HERO_ID) ?: return@click
        val npc = gameScene.objects.getById<Character>(npcId)

        if (distanceOf(HERO_ID, npcId) > 1) {
            // This is a bit tricky: if searching path from hero to NPC
            // it will be unreachable because NPC is a blocker
            // so we remove blocker first then restore
            val tmp = gameScene.blockers[npc.gridCoordinate.y][npc.gridCoordinate.x]
            try {
                gameScene.blockers[npc.gridCoordinate.y][npc.gridCoordinate.x] = 0
                val movePath = hero.searchPath(npc.gridCoordinate)
                if (movePath.isEmpty()) {
                    onUnreachable()
                } else {
                    hero.moveAlong(movePath.subList(0, movePath.size - 1)) {
                        faceToFaceThenInteract(hero, npc, onInteraction)
                    }
                }
            } finally {
                gameScene.blockers[npc.gridCoordinate.y][npc.gridCoordinate.x] = tmp
            }
        } else {
            faceToFaceThenInteract(hero, npc, onInteraction)
        }
    }

    private fun faceToFaceThenInteract(hero: Character, npc: Character, onInteraction: UnitFunction) {
        npc.direction = npc.directionTo(hero)
        hero.direction = hero.directionTo(npc)
        onInteraction()
    }
}
