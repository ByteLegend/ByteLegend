package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.ModalController
import com.bytelegend.client.app.engine.GAME_UI_UPDATE_EVENT
import common.ui.bootstrap.BootstrapModal
import common.ui.bootstrap.BootstrapModalProps
import org.kodein.di.DI
import org.kodein.di.instance
import react.RBuilder
import react.RElementBuilder
import react.RState

interface ModalControllerInternal : ModalController {
    val visible: Boolean
    fun hide()
}

class DefaultModalController(
    di: DI
) : ModalControllerInternal {
    private val initModalAction: RElementBuilder<BootstrapModalProps>.() -> Unit = {}
    private val eventBus: EventBus by di.instance()
    var currentModal: RElementBuilder<BootstrapModalProps>.() -> Unit = initModalAction
        private set
    override val visible: Boolean
        get() = currentModal != initModalAction

    override fun show(modal: RElementBuilder<BootstrapModalProps>.() -> Unit) {
        currentModal = modal
        // Update the modal component's state
        eventBus.emit(GAME_UI_UPDATE_EVENT, null)
    }

    override fun hide() {
        currentModal = initModalAction
        eventBus.emit(GAME_UI_UPDATE_EVENT, null)
    }
}

class GameModal : GameUIComponent<GameProps, RState>() {
    override fun RBuilder.render() {
        if (game.modalController.visible) {
            BootstrapModal {
                attrs.show = true
                attrs.size = "lg"
                attrs.onHide = {
                    game.modalController.hide()
                }
                attrs.centered = true
                (game.modalController as DefaultModalController).currentModal(this)
            }
        }
    }
}
