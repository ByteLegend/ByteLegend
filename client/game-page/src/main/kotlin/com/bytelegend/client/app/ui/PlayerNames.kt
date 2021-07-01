package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.shared.entities.BasePlayer
import com.bytelegend.client.app.engine.DefaultGameScene
import com.bytelegend.client.app.engine.GAME_CLOCK_50HZ_EVENT
import com.bytelegend.client.app.engine.util.jsObjectBackedSetOf
import com.bytelegend.client.app.obj.CharacterSprite
import com.bytelegend.client.app.obj.getSpriteBlockOnCanvas
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.setState

interface PlayerNamesProps : GameProps
interface PlayerNamesState : RState

class PlayerNames : GameUIComponent<PlayerNamesProps, PlayerNamesState>() {
    private val on50HzClockListener: EventListener<Nothing> = {
        setState { }
    }

    override fun RBuilder.render() {
        if (!game.heroPlayer.isAnonymous && !game._hero!!.outOfCanvas() && game.heroPlayer.map == activeScene.map.id) {
            renderOne(game.heroPlayer, game._hero!!, true)
        }

        game.activeScene.unsafeCast<DefaultGameScene>()
            .players
            .getDrawableCharacters()
            .forEach {
                renderOne(it.player, it, false)
            }
    }

    private fun RBuilder.renderOne(player: BasePlayer, sprite: CharacterSprite, isHero: Boolean) {
        val imageBlockOnCanvas = sprite.getSpriteBlockOnCanvas(activeScene)
        val x = imageBlockOnCanvas.x + canvasCoordinateInGameContainer.x + activeScene.map.tileSize.width / 2
        val y = imageBlockOnCanvas.y + canvasCoordinateInGameContainer.y - 10
        val name = player.nickname
        child(PlayerNameSpan::class) {
            attrs.x = x
            attrs.y = y
            attrs.name = name
            attrs.isHero = isHero
        }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        props.game.eventBus.on(GAME_CLOCK_50HZ_EVENT, on50HzClockListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.game.eventBus.remove(GAME_CLOCK_50HZ_EVENT, on50HzClockListener)
    }
}

interface PlayerNameSpanProps : RProps {
    // coordinate in game container
    var x: Int
    var y: Int
    var name: String
    var isHero: Boolean
}

class PlayerNameSpan : RComponent<PlayerNameSpanProps, RState>() {
    override fun RBuilder.render() {
        absoluteDiv(
            left = props.x,
            top = props.y,
            zIndex = Layer.PlayerNames.zIndex(),
            classes = if (props.isHero)
                jsObjectBackedSetOf("yellow-text-black-shadow", "nickname-span")
            else
                jsObjectBackedSetOf("white-text-black-shadow", "nickname-span")
        ) {
            +props.name
        }
    }

    override fun shouldComponentUpdate(nextProps: PlayerNameSpanProps, nextState: RState): Boolean {
        return props.x != nextProps.x || props.y != nextProps.y || props.name != nextProps.name
    }
}
