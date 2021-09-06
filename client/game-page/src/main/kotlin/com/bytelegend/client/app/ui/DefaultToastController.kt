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
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.ToastController
import com.bytelegend.app.client.ui.bootstrap.BootstrapToast
import com.bytelegend.app.client.ui.bootstrap.BootstrapToastBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapToastHeader
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.browser.window
import kotlinx.html.classes
import react.RBuilder
import react.State
import react.dom.strong
import react.setState

val TOASTS_UPDATE_EVENT = "toasts.update.event"

// TODO: make it adaptive to game container size
val MAX_TOASTS = 3

class Toast(
    val headerHtml: String,
    val bodyHtml: String,
    // How long it will be auto-hidden, 0 means no autohide
    val autoHideMs: Int
) {
    var show: Boolean = true
}

class DefaultToastController(
    val eventBus: EventBus
) : ToastController {
    private val toasts: ArrayDeque<Toast> = ArrayDeque()
    override fun addToast(headerHtml: String, bodyHtml: String, autoHideMs: Int) {
        val toast = Toast(headerHtml, bodyHtml, autoHideMs)
        toasts.add(toast)
        if (toasts.size > MAX_TOASTS) {
            toasts.removeFirst()
        }
        if (autoHideMs > 0) {
            window.setTimeout(
                {
                    toasts.remove(toast)
                },
                autoHideMs
            )
        }

        eventBus.emit(TOASTS_UPDATE_EVENT, toasts.toList())
    }
}

interface ToastUIComponentState : State {
    var toasts: List<Toast>
}

class ToastUIComponent : GameUIComponent<GameProps, ToastUIComponentState>() {
    private val toastsUpdateEventListener: EventListener<List<Toast>> = {
        setState { toasts = it }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        props.game.eventBus.on(TOASTS_UPDATE_EVENT, toastsUpdateEventListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.game.eventBus.remove(TOASTS_UPDATE_EVENT, toastsUpdateEventListener)
    }

    override fun RBuilder.render() {
        if (state.toasts == undefined || state.toasts.none { it.show }) {
            return
        }

        absoluteDiv(
            zIndex = Layer.BannerToast.zIndex(),
            classes = jsObjectBackedSetOf("toast-container"),
            extraStyleBuilder = {
                left = uiContainerCoordinateInGameContainer.x
                /* minimap height + gap */
                bottom = "${gameMap.size.width * 2 + (gameContainerHeight - canvasPixelSize.height)}px"
            }
        ) {
            state.toasts.forEach { t ->
                BootstrapToast {
                    if (t.autoHideMs > 0) {
                        attrs.autohide = true
                        attrs.delay = t.autoHideMs
                    }
                    attrs.show = t.show
                    attrs.onClose = {
                        t.show = false
                        setState {}
                    }
                    BootstrapToastHeader {
                        strong {
                            attrs.classes = jsObjectBackedSetOf("mr-auto")
                            unsafeSpan(t.headerHtml)
                        }
                    }
                    BootstrapToastBody {
                        unsafeSpan(t.bodyHtml)
                    }
                }
            }
        }
    }
}
