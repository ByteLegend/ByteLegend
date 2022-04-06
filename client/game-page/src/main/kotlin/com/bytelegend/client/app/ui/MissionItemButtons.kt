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

package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.GameMission
import com.bytelegend.app.client.api.missionItemUsedEvent
import com.bytelegend.app.client.api.missionItemsButtonRepaintEvent
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.missionHeroDistance
import com.bytelegend.client.app.engine.GAME_ANIMATION_EVENT
import com.bytelegend.client.app.engine.GAME_UI_UPDATE_EVENT
import com.bytelegend.client.app.engine.Item
import kotlinx.js.jso
import kotlinx.browser.document
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLDivElement
import react.Component
import react.Fragment
import react.State
import react.create
import react.dom.html.ReactHTML.img
import react.react

private const val MISSION_TITLE_BUTTONS_LAYER = "mission-title-buttons-layer"

interface MissionItemButtonsState : State {
    var items: List<Item>
}

class MissionItemButtons(props: GameProps) : GameUIComponent<GameProps, MissionItemButtonsState>(props) {
    init {
        state = jso { items = emptyList() }
        refreshItems(Unit)
    }

    private val onAnimation: EventListener<Nothing> = this::onAnimation
    private val refreshItems: EventListener<Unit> = this::refreshItems

    private val divCoordinate: PixelCoordinate
        get() = canvasCoordinateInGameContainer - canvasCoordinateInMap

    override fun render() = Fragment.create {
        absoluteDiv(
            left = divCoordinate.x,
            top = divCoordinate.y,
            width = mapPixelSize.width,
            height = mapPixelSize.height,
            zIndex = Layer.MissionItemButton.zIndex(),
            className = MISSION_TITLE_BUTTONS_LAYER
        ) {
            it.id = MISSION_TITLE_BUTTONS_LAYER
            state.items.forEach { item ->
                if (item.mission?.map == activeScene.map.id) {
                    child(MissionItemButton::class.react, jso {
                        this.game = props.game
                        this.item = item
                        this.mission = activeScene.objects.getById(item.mission.id)
                    })
                }
            }
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onAnimation(n: Nothing) {
        document.getElementById(MISSION_TITLE_BUTTONS_LAYER)?.apply {
            val divStyle = unsafeCast<HTMLDivElement>().style
            divStyle.top = "${divCoordinate.y}px"
            divStyle.left = "${divCoordinate.x}px"
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun refreshItems(u: Unit) {
        GlobalScope.launch {
            val items = props.game.itemAchievementManager.getItems()
            val currentMap = props.game.activeScene.map.id
            setState {
                this.items = items.values.filter { it.mission?.map == currentMap }
            }
        }
    }

    override fun componentDidMount() {
        props.game.eventBus.on(GAME_UI_UPDATE_EVENT, refreshItems)
        props.game.eventBus.on(GAME_ANIMATION_EVENT, onAnimation)
    }

    override fun componentWillUnmount() {
        props.game.eventBus.remove(GAME_UI_UPDATE_EVENT, refreshItems)
        props.game.eventBus.remove(GAME_ANIMATION_EVENT, onAnimation)
    }
}

interface MissionItemButtonProps : GameProps {
    var item: Item
    var mission: GameMission
}

interface MissionItemButtonState : State {
    var loading: Boolean
}

class MissionItemButton(props: MissionItemButtonProps) : Component<MissionItemButtonProps, MissionItemButtonState>(props) {
    init {
        state = jso {
            loading = false
        }
    }

    private val onRepaint: EventListener<Nothing> = this::onRepaint
    private val distanceToHero: Int
        get() = props.mission.gridCoordinate.missionHeroDistance(props.game.heroPlayer.x, props.game.heroPlayer.y)

    override fun componentDidUpdate(prevProps: MissionItemButtonProps, prevState: MissionItemButtonState, snapshot: Any) {
        if (props.mission.id != prevProps.mission.id) {
            // this may happen when previously there were two buttons, but now there's only one
            props.game.eventBus.remove(missionItemsButtonRepaintEvent(prevProps.mission.id), onRepaint)
            props.game.eventBus.on(missionItemsButtonRepaintEvent(props.mission.id), onRepaint)
        }
    }

    override fun componentDidMount() {
        props.game.eventBus.on(missionItemsButtonRepaintEvent(props.mission.id), onRepaint)
    }

    override fun componentWillUnmount() {
        props.game.eventBus.remove(missionItemsButtonRepaintEvent(props.mission.id), onRepaint)
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onRepaint(n: Nothing) {
        setState {}
    }

    override fun render() = Fragment.create {
        val disabled = distanceToHero > 2
        var className = "mission-item-button"
        className += if (disabled) {
            " mission-item-button-disabled"
        } else {
            " mission-item-button-animation"
        }
        absoluteDiv(
            left = props.mission.pixelCoordinate.x - 8,
            top = props.mission.pixelCoordinate.y - 8,
            width = 48,
            height = 48,
            className = className,
            zIndex = Layer.MissionItemButton.zIndex(),
        ) { div ->
            img {
                src = props.game.resolve(props.item.metadata.iconUrl)
            }
            if (state.loading) {
                loadingSpinner()
            }
            div.onClick = {
                val itemName = props.game.i(props.item.metadata.nameTextId)
                if (disabled) {
                    val title = props.game.i("CantUseItem")
                    val body = props.game.i("YouMustBeAdjacentToUseTheItem", itemName)
                    props.game.toastController.addToast(title, body, 5000)
                } else {
                    if (!props.game.hero!!.isMoving()) {
                        props.game.hero!!.direction = props.game.hero!!.directionTo(props.mission)
                    }
                    setState { loading = true }
                    GlobalScope.launch {
                        val result = props.game.itemAchievementManager.useItem(props.item)
                        if (result == null) {
                            // make button disappear
                            props.game.eventBus.emit(GAME_UI_UPDATE_EVENT, null)
                            props.game.eventBus.emit(missionItemUsedEvent(props.mission.id), props.item.id)
                        } else {
                            val title = props.game.i("UseItemFailed")
                            val body = props.game.i("UseItemFailedWith", itemName, result.message ?: "")
                            props.game.toastController.addToast(title, body, 5000)
                        }

                        props.game.eventBus.emit(GAME_UI_UPDATE_EVENT, null)
                        setState { loading = false }
                    }
                }
            }
        }
    }
}
