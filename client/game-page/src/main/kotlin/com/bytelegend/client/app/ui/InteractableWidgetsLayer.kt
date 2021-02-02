package com.bytelegend.client.app.ui

import react.RBuilder
import react.RState

/**
 * A layer containing various interactable UI components, like popovers, modals, toasts, etc.
 */
interface InteractableWidgetsLayerProps : GameProps

class InteractableWidgetsLayer : LayeredGameUIComponent<InteractableWidgetsLayerProps, RState>() {
    override fun RBuilder.render() {
        children()
    }
}
