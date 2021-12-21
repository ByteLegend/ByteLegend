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

package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.Banner
import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.misc.playAudio
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.protocol.ACHIEVEMENT_UPDATE_EVENT
import com.bytelegend.app.shared.protocol.AchievementUpdateEventData
import com.bytelegend.app.shared.protocol.ITEMS_STATES_UPDATE_EVENT
import com.bytelegend.app.shared.protocol.ItemsStatesUpdateEventData
import com.bytelegend.app.shared.protocol.ONLINE_COUNTER_UPDATE_EVENT
import com.bytelegend.client.app.script.ASYNC_ANIMATION_CHANNEL
import com.bytelegend.client.app.script.DefaultGameDirector
import com.bytelegend.client.app.script.effect.itemPopupEffect
import com.bytelegend.client.app.ui.determineRightSideBarTopLeftCornerCoordinateInGameContainer
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.instance

/**
 * Some events are global and not bound to a specific map, e.g.
 * item, states, achievement, coin.
 * They're handled in this singleton.
 */
class GlobalEventHandler(
    di: DI,
    private val game: Game
) {
    private val gameControl: GameControl by di.instance()
    private val eventBus: EventBus by di.instance<EventBus>()

    fun start() {
        eventBus.on(ITEMS_STATES_UPDATE_EVENT, this::onItemsStatesUpdateEvent)
        eventBus.on(ACHIEVEMENT_UPDATE_EVENT, this::onAchievementUpdateEvent)
        eventBus.on(ONLINE_COUNTER_UPDATE_EVENT) { number: Int ->
            game.onlineNumber = number
        }
    }

    private fun onItemsStatesUpdateEvent(eventData: ItemsStatesUpdateEventData) {
        if (!eventData.onFinishSpec.items.isEmpty()) {
            val set = jsObjectBackedSetOf().apply { addAll(game.heroPlayer.items) }
            eventData.onFinishSpec.items.add.forEach {
                set.add(it)
            }
            eventData.onFinishSpec.items.remove.forEach {
                set.remove(it)
            }
            game.heroPlayer.items.clear()
            game.heroPlayer.items.addAll(set)

            onItemsUpdate(eventData)
        }
        if (!eventData.onFinishSpec.states.isEmpty()) {
            eventData.onFinishSpec.states.put.forEach {
                game.heroPlayer.states[it.key] = it.value
            }
            eventData.onFinishSpec.states.remove.forEach {
                game.heroPlayer.states.remove(it)
            }
        }

        eventBus.emit(GAME_UI_UPDATE_EVENT, null)
    }

    @Suppress("EXPERIMENTAL_API_USAGE")
    private fun onAchievementUpdateEvent(eventData: AchievementUpdateEventData) {
        val startCoordinate = determineAnimationStartPointInGameContainer(eventData.map, eventData.missionId)
        val activeScene = game.activeScene.unsafeCast<DefaultGameScene>()

        val set = game.heroPlayer.achievements.toMutableSet()
        set.add(eventData.achievementId)
        game.heroPlayer.achievements.clear()
        game.heroPlayer.achievements.addAll(set)

        if (!gameControl.isWindowVisible) {
            activeScene.scripts(ASYNC_ANIMATION_CHANNEL, false) {
                this.unsafeCast<DefaultGameDirector>().suspendAnimation {
                    achievementPopup(eventData.achievementId, startCoordinate)
                }
            }
        } else {
            GlobalScope.launch {
                achievementPopup(eventData.achievementId, startCoordinate)
            }
        }
    }

    private fun determineAnimationStartPointInGameContainer(map: String, missionId: String): PixelCoordinate {
        val canvasState = game.activeScene.canvasState
        val gameContainerCenter = PixelCoordinate(canvasState.gameContainerSize.width / 2, canvasState.gameContainerSize.height / 2)
        val scene = game.sceneContainer.getSceneByIdOrNull(map)
        if (scene?.isActive != true) {
            return gameContainerCenter
        }

        val mission = scene.objects.getByIdOrNull<DefaultGameMission>(missionId)?.gameMapMission ?: return gameContainerCenter
        val missionCoordinateInGameContainer: PixelCoordinate = canvasState.calculateCoordinateInGameContainer(mission.gridCoordinate)
        return if (missionCoordinateInGameContainer.isOutOfGameContainer(canvasState.gameContainerSize))
            gameContainerCenter
        else
            missionCoordinateInGameContainer
    }

    @Suppress("EXPERIMENTAL_API_USAGE")
    private fun onItemsUpdate(eventData: ItemsStatesUpdateEventData) {
        val startCoordinate = determineAnimationStartPointInGameContainer(eventData.map, eventData.missionId)
        val activeScene = game.activeScene.unsafeCast<DefaultGameScene>()

        if (!gameControl.isWindowVisible) {
            activeScene.scripts(ASYNC_ANIMATION_CHANNEL, false) {
                eventData.onFinishSpec.items.add.forEach { item ->
                    this.unsafeCast<DefaultGameDirector>().suspendAnimation {
                        itemPopup(item, startCoordinate)
                    }
                }
            }
        } else {
            GlobalScope.launch {
                eventData.onFinishSpec.items.add.forEach { item ->
                    itemPopup(item, startCoordinate)
                }
            }
        }
    }

    private fun PixelCoordinate.isOutOfGameContainer(gameContainerSize: PixelSize) = x < 0 || y < 0 || x >= gameContainerSize.width || y >= gameContainerSize.height

    private suspend fun itemPopup(item: String, startPointInGameContainer: PixelCoordinate) {
        val text = game.i(item)
        val canvasState = game.activeScene.canvasState
        game.bannerController.showBanner(Banner(game.i("GetItem", text), "success", 5))
        playAudio("popup")
        itemPopupEffect(
            item,
            game.getIconUrl(item),
            canvasState.gameContainerSize,
            startPointInGameContainer,
            canvasState.determineRightSideBarTopLeftCornerCoordinateInGameContainer() + PixelCoordinate(
                0,
                200
            ), /* items box offset */
            5.0
        )
    }

    private suspend fun achievementPopup(achievementId: String, startPointInGameContainer: PixelCoordinate) {
        val text = game.i(achievementId)
        val canvasState = game.activeScene.canvasState
        game.bannerController.showBanner(Banner(game.i("GetAchievement", text), "success", 5))
        playAudio("achievement")
        itemPopupEffect(
            achievementId,
            game.getIconUrl(achievementId),
            canvasState.gameContainerSize,
            startPointInGameContainer,
            canvasState.determineRightSideBarTopLeftCornerCoordinateInGameContainer() + PixelCoordinate(
                0,
                250
            ), /* items box offset */
            5.0
        )
    }
}
