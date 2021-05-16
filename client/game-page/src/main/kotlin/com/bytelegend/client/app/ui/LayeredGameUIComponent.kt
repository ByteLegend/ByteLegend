package com.bytelegend.client.app.ui

import kotlinx.html.id
import react.RBuilder
import react.RState

interface GameContainerProps : GameProps

class GameContainer : GameUIComponent<GameContainerProps, RState>() {
    override fun RBuilder.render() {
        containerFillingDiv(Layer.GameContainer.zIndex()) {
            attrs.id = "game-container-layer"
            children()
        }
    }
}
