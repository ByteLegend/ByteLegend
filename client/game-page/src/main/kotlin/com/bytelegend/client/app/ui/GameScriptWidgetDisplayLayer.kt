package com.bytelegend.client.app.ui

import com.bytelegend.client.app.engine.DefaultGameScene
import react.RBuilder
import react.RState

interface GameScriptWidgetDisplayLayerProps : GameProps

class GameScriptWidgetDisplayLayer : GameUIComponent<GameScriptWidgetDisplayLayerProps, RState>() {
    @Suppress("UnsafeCastFromDynamic")
    override fun RBuilder.render() {
        game.activeScene.unsafeCast<DefaultGameScene>().mainChannelDirector.currentWidgets.entries.forEach {
            child(it.value.klass.asDynamic(), it.value.handler)
        }
    }
}
