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
package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.client.app.engine.DefaultGameScene
import com.bytelegend.client.app.engine.GAME_CLOCK_20MS_EVENT
import com.bytelegend.client.app.obj.character.CharacterSprite
import com.bytelegend.client.app.obj.getSpriteBlockOnCanvas
import kotlinx.js.jso
import react.ChildrenBuilder
import react.Component
import react.Fragment
import react.Props
import react.State
import react.create
import react.react
import kotlin.math.max

interface PlayerNamesProps : GameProps
interface PlayerNamesState : State

class PlayerNames : GameUIComponent<PlayerNamesProps, PlayerNamesState>() {
    private val on50HzClockListener: EventListener<Nothing> = {
        setState { }
    }

    override fun render() = Fragment.create {
        if (!game.heroPlayer.isAnonymous && !game._hero!!.outOfCanvas() && game.heroPlayer.map == activeScene.map.id) {
            renderOne(game.heroPlayer.nickname, game._hero!!, true)
        }

        game.activeScene.unsafeCast<DefaultGameScene>()
            .players
            .getDrawableCharacters()
            .forEach {
                renderOne(it.player.nickname, it, false)
            }
    }

    private fun ChildrenBuilder.renderOne(playerNickName: String, sprite: CharacterSprite, isHero: Boolean) {
        val imageBlockOnCanvas = sprite.getSpriteBlockOnCanvas(activeScene)
        val x = imageBlockOnCanvas.x + canvasCoordinateInGameContainer.x + activeScene.map.tileSize.width / 2
        val y = imageBlockOnCanvas.y + canvasCoordinateInGameContainer.y - 10

        child(PlayerNameSpan::class.react, jso {
            this.x = x
            this.y = y
            this.opacity = determineOpacity(sprite)
            name = playerNickName
            this.isHero = isHero
        })
    }

    /**
     * The name widget may hide the mission (animation).
     * If mission.y + 1 == character.y, set the opacity to 0.5.
     */
    private fun determineOpacity(sprite: CharacterSprite): Double {
        val missionY = max(sprite.gridCoordinate.y - 1, 0)
        return if (activeScene.objects.getByCoordinate(GridCoordinate(sprite.gridCoordinate.x, missionY)).mission != null) {
            0.5
        } else {
            1.0
        }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        props.game.eventBus.on(GAME_CLOCK_20MS_EVENT, on50HzClockListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.game.eventBus.remove(GAME_CLOCK_20MS_EVENT, on50HzClockListener)
    }
}

interface PlayerNameSpanProps : Props {
    // coordinate in game container
    var x: Int
    var y: Int
    var name: String
    var isHero: Boolean
    var opacity: Double
}

class PlayerNameSpan : Component<PlayerNameSpanProps, State>() {
    override fun render() = Fragment.create {
        absoluteDiv(
            left = props.x,
            top = props.y,
            zIndex = Layer.PlayerNames.zIndex(),
            opacity = props.opacity,
            className = if (props.isHero)
                "yellow-text-black-shadow nickname-span"
            else
                "white-text-black-shadow nickname-span"
        ) {
            +props.name
        }
    }

    override fun shouldComponentUpdate(nextProps: PlayerNameSpanProps, nextState: State): Boolean {
        return props.x != nextProps.x || props.y != nextProps.y || props.name != nextProps.name || props.opacity != nextProps.opacity
    }
}
