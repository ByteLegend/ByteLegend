package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.GameScript
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.client.app.engine.Game
import kotlinx.css.div
import react.RBuilder
import react.RState
import react.dom.div
import react.dom.jsStyle

class DialogScript(
    val gameScene: GameScene,
    val characterCoordinate: PixelCoordinate,
    val text: String
) : GameScript {
    override fun play(builder: RBuilder, gameRuntime: GameRuntime) {
        val game = gameRuntime.unsafeCast<Game>()
        if (gameScene != gameRuntime.activeScene) {
            return
        }
        builder.absoluteDiv(
            characterCoordinate.x - gameScene.canvasState.getCanvasCoordinateInMap().x + gameScene.canvasState.getUICoordinateInGameContainer().x,
            characterCoordinate.y - gameScene.canvasState.getCanvasCoordinateInMap().y + gameScene.canvasState.getUICoordinateInGameContainer().y,
            400,
            300,
            Layer.GameScript.zIndex()
        ) {
            attrs.jsStyle {
                backgroundImage = """url("${game.resolve("/img/ui/dialog-box.png")}")"""
            }

            +text
        }
    }
}

interface GameScriptDisplayLayerProps : GameProps {
    var script: GameScript
}

class GameScriptDisplayLayer : GameUIComponent<GameScriptDisplayLayerProps, RState>() {
    override fun RBuilder.render() {
        props.script.play(this, game)
    }
}
