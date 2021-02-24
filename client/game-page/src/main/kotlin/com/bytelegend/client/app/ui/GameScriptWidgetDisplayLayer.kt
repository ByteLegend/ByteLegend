package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.client.app.script.DefaultGameDirector
import react.RBuilder
import react.RState
import react.setState
import kotlin.reflect.KClass

interface GameScriptWidgetDisplayLayerProps : GameProps

class GameScriptWidgetDisplayLayer : GameUIComponent<GameScriptWidgetDisplayLayerProps, RState>() {
    override fun RBuilder.render() {
        activeScene.director.unsafeCast<DefaultGameDirector>().currentWidgets.values.forEach {
            child(it.klass.asDynamic(), it.handler)
        }
    }
}
