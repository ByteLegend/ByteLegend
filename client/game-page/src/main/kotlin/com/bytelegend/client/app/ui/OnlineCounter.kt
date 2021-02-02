package com.bytelegend.client.app.ui

import kotlinx.html.ButtonType
import kotlinx.html.classes
import kotlinx.html.id
import react.RBuilder
import react.RState
import react.dom.button
import react.dom.span

interface OnlineCounterProps : GameProps

interface OnlineCounterState : RState {
    var count: Int
}

/**
 * Display current FPS. Update upon "window.animate" event.
 */
class OnlineCounter : GameUIComponent<OnlineCounterProps, OnlineCounterState>() {
    override fun OnlineCounterState.init() {
        count = 0
    }

    @Suppress("UnsafeCastFromDynamic")
    override fun RBuilder.render() {
        button {
            attrs.id = "online-counter"
            attrs.type = ButtonType.button
            attrs.classes = setOf("btn", "btn-primary", "map-title-widget")

            +i("OnlineCount")
            span {
                attrs.classes = setOf("badge", "badge-light")
                +state.count.toString()
            }
        }
    }
}
