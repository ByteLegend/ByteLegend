package com.bytelegend.client.app.ui

// import com.bytelegend.app.shared.Player
import react.RBuilder
import react.RState

interface SpriteNameWidgetProps : GameProps
interface SpriteNameWidgetState : RState

class SpriteNameWidget : GameUIComponent<SpriteNameWidgetProps, SpriteNameWidgetState>() {
    override fun RBuilder.render() {
//        if (game.heroSprite != null && !game.heroSprite!!.outOfCanvas()) {
//            renderOne(currentUser, game.heroSprite!!)
//        }
//
//        game.otherPlayerSprites.entries.forEach {
//            renderOne(game.otherPlayers[it.key]!!, it.value)
//        }
    }

//    fun RBuilder.renderOne(player: Player, sprite: CharacterSprite) {
//        val imageBlockOnCanvas = sprite.getImageBlockOnCanvas()
//        absoluteDiv(
//            left = imageBlockOnCanvas.x + canvasCoordinateInGameContainer.x,
//            top = imageBlockOnCanvas.y + canvasCoordinateInGameContainer.y - 10,
//            zIndex = props.layer.zIndex,
//            classes = setOf("nickname-span")
//        ) {
//            +player.nickname
//        }
//    }
}
