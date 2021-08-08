@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.Banner
import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.GameCanvasState
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.PlayerChallengeContainer
import com.bytelegend.app.client.misc.playAudio
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.entities.PlayerChallenge
import com.bytelegend.app.shared.entities.PullRequestAnswer
import com.bytelegend.app.shared.entities.toPullRequestAnswers
import com.bytelegend.app.shared.objects.GameMapMission
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.app.shared.protocol.ChallengeUpdateEventData
import com.bytelegend.app.shared.protocol.ItemsStatesUpdateEventData
import com.bytelegend.app.shared.protocol.STAR_UPDATE_EVENT
import com.bytelegend.app.shared.protocol.StarUpdateEventData
import com.bytelegend.app.shared.protocol.challengeUpdateEvent
import com.bytelegend.client.app.script.ASYNC_ANIMATION_CHANNEL
import com.bytelegend.client.app.script.DefaultGameDirector
import com.bytelegend.client.app.script.STAR_BYTELEGEND_MISSION_ID
import com.bytelegend.client.app.script.effect.itemPopupEffect
import com.bytelegend.client.app.script.effect.showConfetti
import com.bytelegend.client.app.script.effect.starFlyEffect
import com.bytelegend.client.app.ui.NumberIncrementEvent
import com.bytelegend.client.app.ui.STAR_INCREMENT_EVENT
import com.bytelegend.client.app.ui.determineRightSideBarTopLeftCornerCoordinateInGameContainer
import com.bytelegend.client.app.ui.menu.determineMenuCoordinateInGameContainer
import com.bytelegend.client.utils.JSArrayBackedList
import com.bytelegend.client.utils.JSObjectBackedMap
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.instance

const val MISSION_REPAINT_EVENT = "mission.repaint"

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
        val oldMission = playerChallenges[challengeId]
        if (oldMission == null) {
            playerChallenges[challengeId] = challenge
            challengeIdToPullRequestAnswers[challengeId] = challenge.answers.toPullRequestAnswers()
        } else {
            // When the answer events from server are misordered, it might be:
            // [answer1, answer2, answer3] comes first and [answer1, answer2] comes later
            // In this case, we need to make sure no answers missing
            val set = oldMission.answers.toMutableSet().apply { addAll(challenge.answers) }
            challenge.answers.clear()
            challenge.answers.addAll(JSArrayBackedList(set))
            playerChallenges[challengeId] = challenge
            challengeIdToPullRequestAnswers[challengeId] = challenge.answers.toPullRequestAnswers()
        }
    }

    private fun isCanvasInvisible(): Boolean {
        return !gameControl.isWindowVisible
    }

    fun onItemsUpdate(eventData: ItemsStatesUpdateEventData) {
        val mission = gameScene.objects.getByIdOrNull<GameMission>(eventData.missionId)?.gameMapMission ?: return

        if (isCanvasInvisible()) {
            gameScene.scripts(ASYNC_ANIMATION_CHANNEL, false) {
                eventData.onFinishSpec.items.add.forEach { item ->
                    this.unsafeCast<DefaultGameDirector>().suspendAnimation {
                        itemPopup(item, mission)
                    }
                }
            }
        } else {
            GlobalScope.launch {
                eventData.onFinishSpec.items.add.forEach { item ->
                    itemPopup(item, mission)
                }
            }
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

            if (isCanvasInvisible()) {
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
            if (isCanvasInvisible()) {
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

    private suspend fun itemPopup(item: String, mission: GameMapMission) {
        game.bannerController.showBanner(
            Banner(
                game.i("GetItem", "Coffee"),
                "success",
                true,
                5000
            )
        )
        playAudio("popup")
        val canvasState = gameScene.canvasState
        itemPopupEffect(
            item,
            canvasState.gameContainerSize,
            canvasState.calculateCoordinateInGameContainer(mission.gridCoordinate),
            canvasState.determineRightSideBarTopLeftCornerCoordinateInGameContainer() + PixelCoordinate(
                0,
                200
            ), /* items box offset */
            3.0
        )
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
        game.eventBus.emit(STAR_INCREMENT_EVENT, NumberIncrementEvent(eventData.change, eventData.newValue))
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
        eventBus.emit(MISSION_REPAINT_EVENT, eventData)
    }

    fun init(gameScene: GameScene) {
        this.gameScene = gameScene.unsafeCast<DefaultGameScene>()
        this.gameScene.objects.getByRole<GameMission>(GameObjectRole.Mission).forEach {
            missionIdToChallenges[it.gameMapMission.id] = it.gameMapMission.challenges
        }
        eventBus.on(STAR_UPDATE_EVENT, starUpdateEventListener)
        eventBus.on(challengeUpdateEvent(gameScene.map.id), challengeUpdateEventListener)
    }

    fun close() {
        eventBus.remove(challengeUpdateEvent(gameScene.map.id), challengeUpdateEventListener)
        eventBus.remove(STAR_UPDATE_EVENT, starUpdateEventListener)
    }
}

fun GameCanvasState.calculateCoordinateInGameContainer(mapCoordinate: GridCoordinate): PixelCoordinate {
    return mapCoordinate * tileSize - getCanvasCoordinateInMap() + getCanvasCoordinateInGameContainer()
}
