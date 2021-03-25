package com.bytelegend.client.app.ui

import kotlinx.html.id
import react.RBuilder
import react.RState

interface StarCountWidgetProps : GameProps
interface StarCountWidgetState : RState

class StarCountWidget : GameUIComponent<StarCountWidgetProps, StarCountWidgetState>() {
    override fun RBuilder.render() {
        if (!game.heroPlayer.isAnonymous) {
            absoluteDiv(
                right = uiContainerCoordinateInGameContainer.x,
                top = uiContainerCoordinateInGameContainer.y + AVATAR_HEIGHT,
                zIndex = Layer.UserAvatarWidget.zIndex(),
                classes = setOf("map-title-text")
            ) {
                attrs.id = "star-count"
                +"${game.heroPlayer.star}⭐"
            }
        }
    }
}
