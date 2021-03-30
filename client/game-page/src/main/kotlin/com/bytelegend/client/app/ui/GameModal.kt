package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.ModalController
import com.bytelegend.client.app.engine.GAME_UI_UPDATE_EVENT
import common.ui.bootstrap.BootstrapModal
import common.ui.bootstrap.BootstrapModalBody
import common.ui.bootstrap.BootstrapModalHeader
import common.ui.bootstrap.BootstrapModalProps
import common.ui.bootstrap.BootstrapModalTitle
import org.kodein.di.DI
import org.kodein.di.instance
import react.RBuilder
import react.RElementBuilder
import react.RState
import react.dom.p
import react.dom.span

interface ModalControllerInternal : ModalController {
    fun hide()
    fun show(modal: RElementBuilder<BootstrapModalProps>.() -> Unit)
}

class DefaultModalController(
    di: DI
) : ModalControllerInternal {
    private val initModalAction: RElementBuilder<BootstrapModalProps>.() -> Unit = {}
    private val eventBus: EventBus by di.instance()
    private val gameRuntime: GameRuntime by di.instance()
    var currentModal: RElementBuilder<BootstrapModalProps>.() -> Unit = initModalAction
        private set
    override val visible: Boolean
        get() = currentModal != initModalAction

    override fun show(modal: RElementBuilder<BootstrapModalProps>.() -> Unit) {
        currentModal = modal
        // Update the modal component's state
        eventBus.emit(GAME_UI_UPDATE_EVENT, null)
    }

    override fun showModal(contentId: String, titleId: String?) {
        show {
            if (titleId != null) {
                BootstrapModalHeader {
                    attrs.closeButton = true
                    BootstrapModalTitle {
                        span {
                            consumer.onTagContentUnsafe {
                                +gameRuntime.i(titleId)
                            }
                        }
                    }
                }
            }

            BootstrapModalBody {
                p {
                    consumer.onTagContentUnsafe {
                        +gameRuntime.i(contentId)
                    }
                }
            }
        }
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
                attrs.animation = false
                attrs.onHide = {
                    game.modalController.hide()
                }
                attrs.centered = true
                (game.modalController as DefaultModalController).currentModal(this)
            }
        }
    }
}
