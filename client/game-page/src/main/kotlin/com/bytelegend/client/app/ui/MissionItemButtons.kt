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
import com.bytelegend.app.client.api.missionItemsButtonRepaintEvent
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.client.app.engine.GAME_ANIMATION_EVENT
import com.bytelegend.client.app.engine.Item
import kotlinext.js.jso
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
import kotlin.math.abs
import kotlin.math.max

private const val MISSION_TITLE_BUTTONS_LAYER = "mission-title-buttons-layer"

interface MissionItemButtonsState : State {
    var items: List<Item>
}

class MissionItemButtons(props: GameProps) : GameUIComponent<GameProps, MissionItemButtonsState>(props) {
    init {
        state = jso { items = emptyList() }
        GlobalScope.launch {
            val items = props.game.itemAchievementManager.getItems()
            val currentMap = props.game.activeScene.map.id
            setState {
                this.items = items.values.filter { it.mission?.map == currentMap }
            }
        }
    }

    private val onAnimation: EventListener<Nothing> = this::onAnimation

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
                child(MissionItemButton::class.react, jso {
                    this.game = props.game
                    this.item = item
                    this.mission = activeScene.objects.getById(item.mission!!.id)
                })
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

    override fun componentDidMount() {
        super.componentDidMount()
        props.game.eventBus.on(GAME_ANIMATION_EVENT, onAnimation)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.game.eventBus.remove(GAME_ANIMATION_EVENT, onAnimation)
    }
}

interface MissionItemButtonProps : GameProps {
    var item: Item
    var mission: GameMission
}

interface MissionItemButtonState : State {
    var disabled: Boolean
    var loading: Boolean
    var flickering: Boolean
}

class MissionItemButton(props: MissionItemButtonProps) : Component<MissionItemButtonProps, MissionItemButtonState>(props) {
    init {
        state = jso {
            disabled = distanceToHero > 2
            loading = false
            flickering = distanceToHero <= 2
        }
    }

    private val onRepaint: EventListener<Nothing> = this::onRepaint
    private val distanceToHero: Int
        get() = max(abs(props.game.heroPlayer.x - props.mission.gridCoordinate.x), abs(props.game.heroPlayer.y - props.mission.gridCoordinate.y))

    override fun componentDidMount() {
        props.game.eventBus.on(missionItemsButtonRepaintEvent(props.mission.id), onRepaint)
    }

    override fun componentWillUnmount() {
        props.game.eventBus.remove(missionItemsButtonRepaintEvent(props.mission.id), onRepaint)
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onRepaint(n: Nothing) {
        setState {
            disabled = distanceToHero > 2
            flickering = distanceToHero <= 2
        }
    }

    override fun render() = Fragment.create {
        var className = "mission-item-button"
        if (state.flickering) {
            className += " mission-item-button-animation"
        }
        if (state.disabled) {
            className += " mission-item-button-disabled"
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
                src = props.game.resolve(props.item.metadata.icon)
            }
            if (state.loading) {
                loadingSpinner()
            }
            div.onClick = {
                if (state.disabled) {
                    val title = props.game.i("CantUseItem")
                    val body = props.game.i("YouMustBeAdjacentToUseTheItem", props.game.i(props.item.metadata.nameTextId))
                    props.game.toastController.addToast(title, body, 5000)
                }
            }
        }
    }
}
