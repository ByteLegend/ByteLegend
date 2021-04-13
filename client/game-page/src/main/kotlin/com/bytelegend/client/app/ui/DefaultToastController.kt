package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.ToastController
import com.bytelegend.app.client.ui.bootstrap.BootstrapToast
import com.bytelegend.app.client.ui.bootstrap.BootstrapToastBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapToastHeader
import kotlinx.browser.window
import kotlinx.html.classes
import react.RBuilder
import react.RState
import react.dom.span
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

interface ToastUIComponentRState : RState {
    var toasts: List<Toast>
}

class ToastUIComponent : GameUIComponent<GameProps, ToastUIComponentRState>() {
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
            classes = setOf("toast-container"),
            extraStyleBuilder = {
                left = canvasCoordinateInGameContainer.x
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
                            attrs.classes = setOf("mr-auto")
                            consumer.onTagContentUnsafe {
                                +t.headerHtml
                            }
                        }
                    }
                    BootstrapToastBody {
                        span {
                            consumer.onTagContentUnsafe {
                                +t.bodyHtml
                            }
                        }
                    }
                }
            }
        }
    }
}
