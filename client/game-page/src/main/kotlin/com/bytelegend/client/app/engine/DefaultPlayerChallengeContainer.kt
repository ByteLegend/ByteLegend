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
@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.GameCanvasState
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.PlayerChallengeContainer
import com.bytelegend.app.client.api.missionRepaintEvent
import com.bytelegend.app.client.misc.playAudio
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.entities.PlayerChallenge
import com.bytelegend.app.shared.entities.PullRequestAnswer
import com.bytelegend.app.shared.entities.toPullRequestAnswers
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.app.shared.protocol.ChallengeUpdateEventData
import com.bytelegend.app.shared.protocol.STAR_UPDATE_EVENT
import com.bytelegend.app.shared.protocol.StarUpdateEventData
import com.bytelegend.app.shared.protocol.challengeUpdateEvent
import com.bytelegend.client.app.script.ASYNC_ANIMATION_CHANNEL
import com.bytelegend.client.app.script.DefaultGameDirector
import com.bytelegend.client.app.script.STAR_BYTELEGEND_MISSION_ID
import com.bytelegend.client.app.script.effect.showConfetti
import com.bytelegend.client.app.script.effect.starFlyEffect
import com.bytelegend.client.app.ui.STAR_INCREMENT_EVENT
import com.bytelegend.client.app.ui.determineRightSideBarTopLeftCornerCoordinateInGameContainer
import com.bytelegend.client.app.ui.menu.determineMenuCoordinateInGameContainer
import com.bytelegend.client.utils.JSArrayBackedList
import com.bytelegend.client.utils.JSObjectBackedMap
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.instance

class DefaultPlayerChallengeContainer(
    di: DI,
    private val playerChallenges: MutableMap<String, PlayerChallenge>
) : PlayerChallengeContainer {
    private val eventBus: EventBus by di.instance()
    private val game: GameRuntime by di.instance()
    private val gameControl: GameControl by di.instance()
    private lateinit var gameScene: DefaultGameScene
    private val missionIdToChallenges: MutableMap<String, List<String>> = JSObjectBackedMap()
    private val challengeIdToPullRequestAnswers: MutableMap<String, List<PullRequestAnswer>> = JSObjectBackedMap()

    init {
        playerChallenges.forEach {
            challengeIdToPullRequestAnswers[it.key] = it.value.answers.toPullRequestAnswers()
        }
    }

    private val starUpdateEventListener: EventListener<StarUpdateEventData> = this::onStarUpdate
    private val challengeUpdateEventListener: EventListener<ChallengeUpdateEventData> = this::onPlayerChallengeUpdate

    fun init(gameScene: GameScene) {
        this.gameScene = gameScene.unsafeCast<DefaultGameScene>()
        this.gameScene.objects.getByRole<GameMission>(GameObjectRole.Mission).forEach {
            missionIdToChallenges[it.gameMapMission.id] = it.gameMapMission.challenges
        }
        eventBus.on(STAR_UPDATE_EVENT, starUpdateEventListener)
        eventBus.on(challengeUpdateEvent(gameScene.map.id), challengeUpdateEventListener)
    }

    override fun challengeAccomplished(challengeId: String): Boolean {
        return playerChallenges[challengeId]?.accomplished == true
    }

    override fun challengeStar(challengeId: String): Int {
        return playerChallenges[challengeId]?.star ?: 0
    }

    override fun missionStar(missionId: String): Int {
        val challengeIds: List<String> = missionIdToChallenges[missionId] ?: emptyList()
        return challengeIds.sumOf { challengeStar(it) }
    }

    override fun getPlayerChallengesByMissionId(missionId: String): List<PlayerChallenge> {
        val ret = JSArrayBackedList<PlayerChallenge>()
        missionIdToChallenges[missionId]?.forEach {
            val challenge = playerChallenges[it]
            if (challenge != null) {
                ret.add(challenge)
            }
        }
        return ret
    }

    override fun getPullRequestChallengeAnswersByMissionId(missionId: String): List<PullRequestAnswer> {
        val challengeId = missionIdToChallenges[missionId]?.firstOrNull() ?: return emptyList()
        return challengeIdToPullRequestAnswers[challengeId] ?: emptyList()
    }

    private fun putChallenge(challenge: PlayerChallenge) {
        val challengeId = challenge.challengeId
        val oldChallenge = playerChallenges[challengeId]
        if (oldChallenge == null) {
            playerChallenges[challengeId] = challenge
            challengeIdToPullRequestAnswers[challengeId] = challenge.answers.toPullRequestAnswers()
        } else {
            // When the answer events from server are misordered, it might be:
            // [answer1, answer2, answer3] comes first and [answer1, answer2] comes later
            // In this case, we need to make sure no answers missing
            val set = oldChallenge.answers.toMutableSet().apply { addAll(challenge.answers) }
            val newChallenge = PlayerChallenge(
                oldChallenge.playerId,
                oldChallenge.map,
                oldChallenge.missionId,
                oldChallenge.challengeId,
                JSArrayBackedList(set)
            )

            playerChallenges[challengeId] = newChallenge
            challengeIdToPullRequestAnswers[challengeId] = newChallenge.answers.toPullRequestAnswers()
        }
    }

    private fun onStarUpdate(eventData: StarUpdateEventData) {
        val currentMap: String = gameScene.map.id
        if (currentMap == eventData.map) {
            // star/mission change happens on current map
            // respond to the event
            val mission = gameScene.objects.getById<GameMission>(eventData.missionId).gameMapMission
            val canvasState = gameScene.canvasState
            val endCoordinateInGameContainer: PixelCoordinate =
                canvasState.determineRightSideBarTopLeftCornerCoordinateInGameContainer()
            val startCoordinateInGameContainer: PixelCoordinate =
                if (mission.id == STAR_BYTELEGEND_MISSION_ID)
                // See MenuItem, from the GitHub menu icon
                    canvasState.determineMenuCoordinateInGameContainer()
                else
                    canvasState.calculateCoordinateInGameContainer(mission.gridCoordinate)

            if (!gameControl.isWindowVisible) {
                gameScene.scripts(ASYNC_ANIMATION_CHANNEL, false) {
                    this.unsafeCast<DefaultGameDirector>().suspendAnimation {
                        starFlyThenIncrement(
                            canvasState.gameContainerSize,
                            startCoordinateInGameContainer,
                            endCoordinateInGameContainer,
                            eventData
                        )
                    }
                }
            } else {
                // fly then add the star
                GlobalScope.launch {
                    starFlyThenIncrement(
                        canvasState.gameContainerSize,
                        startCoordinateInGameContainer,
                        endCoordinateInGameContainer,
                        eventData
                    )
                }
            }
        } else if (gameScene.gameRuntime.sceneContainer.getSceneByIdOrNull(eventData.map) == null &&
            gameScene.isActive
        ) {
            // the corresponding scene is not loaded, let activeScene respond
            // only add star
            if (!gameControl.isWindowVisible) {
                // if modal is visible, add to script list
                gameScene.scripts(ASYNC_ANIMATION_CHANNEL, false) {
                    this.unsafeCast<DefaultGameDirector>().suspendAnimation {
                        starIncrement(eventData)
                    }
                }
            } else {
                // fly then add the star
                GlobalScope.launch {
                    starIncrement(eventData)
                }
            }
        }
    }

    private suspend fun starFlyThenIncrement(
        gameContainerSize: PixelSize,
        startCoordinateInGameContainer: PixelCoordinate,
        endCoordinateInGameContainer: PixelCoordinate,
        eventData: StarUpdateEventData
    ) {
        playAudio("starfly")
        starFlyEffect(
            gameContainerSize,
            startCoordinateInGameContainer,
            endCoordinateInGameContainer,
            3
        )
        game.eventBus.emit(STAR_INCREMENT_EVENT, eventData)
    }

    private fun starIncrement(eventData: StarUpdateEventData) {
        playAudio("starfly")
        game.eventBus.emit(STAR_INCREMENT_EVENT, eventData)
    }

    private fun onPlayerChallengeUpdate(eventData: ChallengeUpdateEventData) {
        if (logger.debugEnabled) {
            logger.debug("Received challenge update event: ${eventData.newValue.challengeId} ${eventData.change.answer} ${JSON.stringify(eventData.change.data)} ${eventData.change.createdAt}")
        }
        putChallenge(eventData.newValue)
        if (eventData.change.accomplished) {
            showConfetti(gameScene.canvasState, gameScene.objects.getPointById(eventData.newValue.missionId))
        }
        eventBus.emit(missionRepaintEvent(eventData.newValue.missionId), eventData)
    }

    fun close() {
        eventBus.remove(challengeUpdateEvent(gameScene.map.id), challengeUpdateEventListener)
        eventBus.remove(STAR_UPDATE_EVENT, starUpdateEventListener)
    }
}

fun GameCanvasState.calculateCoordinateInGameContainer(mapCoordinate: GridCoordinate): PixelCoordinate {
    return mapCoordinate * tileSize - getCanvasCoordinateInMap() + getCanvasCoordinateInGameContainer()
}
