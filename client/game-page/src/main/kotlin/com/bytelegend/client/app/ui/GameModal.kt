/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.ModalController
import com.bytelegend.app.client.api.closeMissionModalEvent
import com.bytelegend.app.client.ui.bootstrap.BootstrapModal
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalHeader
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalProps
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalTitle
import com.bytelegend.client.app.engine.GAME_SCRIPT_NEXT
import com.bytelegend.client.app.engine.GAME_UI_UPDATE_EVENT
import com.bytelegend.client.app.script.ASYNC_ANIMATION_CHANNEL
import org.kodein.di.DI
import org.kodein.di.instance
import react.ChildrenBuilder
import react.ReactNode
import react.State
import react.create

interface ModalControllerInternal : ModalController {
    fun hide(missionId: String? = null)
    fun show(modal: ChildrenBuilder.(BootstrapModalProps) -> Unit)
}

class DefaultModalController(
    di: DI
) : ModalControllerInternal {
    private val initModalAction: ChildrenBuilder.(BootstrapModalProps) -> Unit = {}
    private val eventBus: EventBus by di.instance()
    private val gameRuntime: GameRuntime by di.instance()
    var currentModal: ChildrenBuilder.(BootstrapModalProps) -> Unit = initModalAction
        private set
    override val visible: Boolean
        get() = currentModal != initModalAction

    override fun show(modal: ChildrenBuilder.(BootstrapModalProps) -> Unit) {
        currentModal = modal
        // Update the modal component's state
        eventBus.emit(GAME_UI_UPDATE_EVENT, null)
    }

    override fun showModal(content: String, title: String?) {
        show {
            if (title != null) {
                BootstrapModalHeader {
                    closeButton = true
                    BootstrapModalTitle {
                        unsafeSpan(title)
                    }
                }
            }

            BootstrapModalBody {
                unsafeDiv(content)
            }
        }
    }

    override fun hide(missionId: String?) {
        currentModal = initModalAction
        if (missionId != null) {
            gameRuntime.eventBus.emit(closeMissionModalEvent(missionId), null)
        }

        eventBus.emit(GAME_SCRIPT_NEXT, ASYNC_ANIMATION_CHANNEL)
        eventBus.emit(GAME_UI_UPDATE_EVENT, null)
    }
}

class GameModal : GameUIComponent<GameProps, State>() {
    override fun render(): ReactNode? {
        if (game.modalController.visible) {
            return BootstrapModal.create {
                show = true
                size = "lg"
                animation = false
                onHide = {
                    game.modalController.hide()
                }
                centered = true
                (game.modalController as DefaultModalController).currentModal.invoke(this, this)
            }
        }
        return null
    }
}
