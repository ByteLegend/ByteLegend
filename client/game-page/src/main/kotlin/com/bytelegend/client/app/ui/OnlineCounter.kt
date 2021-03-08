package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.shared.protocol.ONLINE_COUNTER_UPDATE_EVENT
import kotlinx.html.classes
import kotlinx.html.id
import react.RBuilder
import react.RState
import react.dom.span
import react.setState

interface OnlineCounterProps : GameProps

interface OnlineCounterState : RState {
    var count: Int
}

/**
 * Display current FPS. Update upon "window.animate" event.
 */
class OnlineCounter : GameUIComponent<OnlineCounterProps, OnlineCounterState>() {
    private val onlineCounterUpdateEventListener: EventListener<Int> = {
        setState {
            count = it
        }
    }

    override fun OnlineCounterState.init() {
        count = 0
    }

    @Suppress("UnsafeCastFromDynamic")
    override fun RBuilder.render() {
        span {
            attrs.id = "online-counter"
            attrs.classes = setOf("map-title-widget")

            +i("OnlineCount")
            +state.count.toString()
        }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        props.game.eventBus.on(ONLINE_COUNTER_UPDATE_EVENT, onlineCounterUpdateEventListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.game.eventBus.remove(ONLINE_COUNTER_UPDATE_EVENT, onlineCounterUpdateEventListener)
    }
}
