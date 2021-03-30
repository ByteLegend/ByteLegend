package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.MissionContainer
import com.bytelegend.app.client.api.ModalController
import com.bytelegend.app.client.api.getAudioElementOrNull
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.entities.Mission
import com.bytelegend.app.shared.entities.MissionType
import com.bytelegend.app.shared.objects.GameMapMissionDefinition
import com.bytelegend.app.shared.protocol.MISSION_UPDATE_EVENT
import com.bytelegend.app.shared.protocol.MissionUpdateEventData
import com.bytelegend.app.shared.protocol.STAR_UPDATE_EVENT
import com.bytelegend.app.shared.protocol.StarUpdateEventData
import com.bytelegend.client.app.script.DefaultGameDirector
import com.bytelegend.client.app.script.effect.starFlyEffect
import com.bytelegend.client.app.ui.STAR_INCREMENT_EVENT
import com.bytelegend.client.app.ui.determineStarCountWidgetCoordinateInGameContainerLeftTop
import com.bytelegend.client.app.ui.menu.determineMenuCoordinateInGameContainer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.instance

class DefaultMissionContainer(
    di: DI,
    private val missions: Map<String, Mission>
) : MissionContainer {
    private val eventBus: EventBus by di.instance()
    private val game: GameRuntime by di.instance()
    private val modalController: ModalController by lazy {
        game.modalController
    }
    var gameScene: GameScene? = null

    private val starUpdateEventListener: EventListener<StarUpdateEventData> = this::onStarUpdate
    private val missionUpdateEventListener: EventListener<MissionUpdateEventData> = this::onMissionUpdate

    override fun missionAccomplished(missionId: String): Boolean {
        return missions[missionId]?.accomplished == true
    }

    private fun onStarUpdate(eventData: StarUpdateEventData) {
        val currentMap: String = gameScene?.map?.id ?: return
        if (currentMap == eventData.map) {
            // star/mission change happens on current map
            // respond to the event
            val missionDefinition = gameScene!!.objects.getById<GameMapMissionDefinition>(eventData.missionId)
            val canvasState = gameScene!!.canvasState
            val endCoordinateInGameContainer: PixelCoordinate = canvasState.determineStarCountWidgetCoordinateInGameContainerLeftTop()
            val startCoordinateInGameContainer: PixelCoordinate = if (missionDefinition.missionDefinition.type == MissionType.Star) {
                // See MenuItem, from the GitHub menu icon
                canvasState.determineMenuCoordinateInGameContainer()
            } else {
                missionDefinition.point * gameScene!!.map.tileSize -
                    canvasState.getCanvasCoordinateInMap() + canvasState.getCanvasCoordinateInGameContainer()
            }

            if (modalController.visible) {
                // if modal is visible, add to script list
                gameScene?.scripts {
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
        } else if (gameScene!!.gameRuntime.sceneContainer.getSceneByIdOrNull(eventData.map) == null &&
            gameScene!!.isActive
        ) {
            // the corresponding scene is not loaded, let activeScene respond
            // only add star
            if (modalController.visible) {
                // if modal is visible, add to script list
                gameScene?.scripts {
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
        getAudioElementOrNull("starfly")?.apply {
            loop = false
            play()
        }
        starFlyEffect(
            gameContainerSize,
            startCoordinateInGameContainer,
            endCoordinateInGameContainer,
            3
        )
        game.eventBus.emit(STAR_INCREMENT_EVENT, eventData)
    }

    private suspend fun starIncrement(eventData: StarUpdateEventData) {
        getAudioElementOrNull("starfly")?.apply {
            loop = false
            play()
        }
        game.eventBus.emit(STAR_INCREMENT_EVENT, eventData)
    }

    private fun onMissionUpdate(eventData: MissionUpdateEventData) {
    }

    fun init(gameScene: GameScene) {
        this.gameScene = gameScene
        eventBus.on(STAR_UPDATE_EVENT, starUpdateEventListener)
        eventBus.on(MISSION_UPDATE_EVENT, missionUpdateEventListener)
    }

    fun close() {
        eventBus.remove(STAR_UPDATE_EVENT, starUpdateEventListener)
    }
}
