package com.bytelegend.client.app.ui

import react.RBuilder
import react.RState

/**
 * A layer containing various interactable UI components, like popovers, modals, toasts, etc.
 */

class InteractableWidgetsLayer : GameUIComponent<GameProps, RState>() {
    override fun RBuilder.render() {
        children()
    }
}
