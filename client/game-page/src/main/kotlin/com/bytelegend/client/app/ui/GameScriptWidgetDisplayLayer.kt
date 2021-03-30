package com.bytelegend.client.app.ui

import com.bytelegend.client.app.script.DefaultGameDirector
import react.RBuilder
import react.RState

interface GameScriptWidgetDisplayLayerProps : GameProps

class GameScriptWidgetDisplayLayer : GameUIComponent<GameScriptWidgetDisplayLayerProps, RState>() {
    @Suppress("UnsafeCastFromDynamic")
    override fun RBuilder.render() {
        game.director.unsafeCast<DefaultGameDirector>().currentWidgets.values.forEach {
            child(it.klass.asDynamic(), it.handler)
        }
    }
}
