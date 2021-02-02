package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.client.app.engine.GAME_UI_UPDATE_EVENT
import common.ui.bootstrap.BootstrapModal
import common.ui.bootstrap.BootstrapModalProps
import org.kodein.di.DI
import org.kodein.di.instance
import react.Component
import react.RBuilder
import react.RElementBuilder
import react.RState

interface ModalController {
    val show: Boolean
    fun showModal(sender: Component<*, *>, modal: RElementBuilder<BootstrapModalProps>.() -> Unit)
    fun hideModal(sender: Component<*, *>)
}

class DefaultModalController(
    di: DI
) : ModalController {
    private val initModalAction: RElementBuilder<BootstrapModalProps>.() -> Unit = {}
    private val eventBus: EventBus by di.instance()
    var currentModal: RElementBuilder<BootstrapModalProps>.() -> Unit = initModalAction
        private set
    override val show: Boolean
        get() = currentModal != initModalAction

    override fun showModal(sender: Component<*, *>, modal: RElementBuilder<BootstrapModalProps>.() -> Unit) {
        currentModal = modal
        // Update the modal component's state
        eventBus.emit(GAME_UI_UPDATE_EVENT, null)
    }

    override fun hideModal(sender: Component<*, *>) {
        currentModal = initModalAction
        eventBus.emit(GAME_UI_UPDATE_EVENT, null)
    }
}

class GameModal : GameUIComponent<GameProps, RState>() {
    override fun RBuilder.render() {
        if (game.modalController.show) {
            BootstrapModal {
                attrs.show = true
                attrs.size = "lg"
                attrs.onHide = {
                    game.modalController.hideModal(this@GameModal)
                }
                attrs.centered = true
                (game.modalController as DefaultModalController).currentModal(this)
            }
        }
    }
}
