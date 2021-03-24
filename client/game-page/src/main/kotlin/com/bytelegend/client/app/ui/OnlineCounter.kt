package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.shared.protocol.ONLINE_COUNTER_UPDATE_EVENT
import com.bytelegend.client.app.page.GAME_INIT_DATA
import kotlinext.js.jsObject
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.classes
import kotlinx.html.id
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLSpanElement
import react.RBuilder
import react.RState
import react.dom.div
import react.dom.jsStyle
import react.dom.span
import react.setState

const val ANIMATION_DIV_ID = "counter-animation-div"

interface OnlineCounterProps : GameProps

interface OnlineCounterState : RState {
    var count: String
    var offsetY: Int
}

class OnlineCounter : GameUIComponent<OnlineCounterProps, OnlineCounterState>() {
    lateinit var span: HTMLSpanElement
    private val onlineCounterUpdateEventListener: EventListener<Int> = {
        // It's possible the event during the animation is lost, but we think it's fine
        if (it.toString() != state.count) {
            animate(it.toString())
        }
    }

    private fun animating() = document.getElementById(ANIMATION_DIV_ID) != null

    /**
     * Display animation for the updated value
     */
    private fun animate(targetValue: String) {
        if (animating() || !gameControl.online) {
            return
        }
        val rect = span.getBoundingClientRect()
        val newDiv = document.createElement("div").unsafeCast<HTMLDivElement>().apply {
            this.id = ANIMATION_DIV_ID
            this.className = "map-title-text"
            this.style.position = "absolute"
            this.style.overflowY = "hidden"
            this.style.left = "${rect.left}px"
            this.style.top = "${rect.top}px"
            this.style.width = "${rect.width + 20}px" // in case of number digit ++: 9 -> 10
            this.style.height = "${rect.height}px"
            document.body?.appendChild(this)
        }

        val initialSpanOffsetY = -20
        val initialSpanTop = rect.top + initialSpanOffsetY
        val newSpan = document.createElement("span").unsafeCast<HTMLSpanElement>().apply {
            appendChild(document.createTextNode(targetValue))
            this.style.position = "absolute"
        }
        newDiv.appendChild(newSpan)
        window.asDynamic().gsap.fromTo(
            newSpan,
            jsObject { y = initialSpanOffsetY },
            jsObject {
                y = -2
                duration = 1
                ease = "elastic.out(1,0.3)"
                onUpdate = {
                    val movingDistanceSinceStart = newSpan.getBoundingClientRect().top - initialSpanTop
                    setState {
                        offsetY = movingDistanceSinceStart.toInt()
                    }
                }
                onComplete = {
                    document.body?.removeChild(newDiv)
                    setState {
                        count = targetValue
                        offsetY = 0
                    }
                }
            },
        )
    }

    override fun OnlineCounterState.init() {
        count = GAME_INIT_DATA.onlineCount.toString()
        offsetY = 0
    }

    @Suppress("UnsafeCastFromDynamic")
    override fun RBuilder.render() {
        span {
            if (gameControl.online) {
                attrs.id = "online-counter"
                attrs.classes = setOf("map-title-widget")

                +i("OnlineCount")
                span {
                    +state.count
                    attrs.jsStyle {
                        position = "relative"
                        top = "${state.offsetY}px"
                    }
                    ref {
                        if (it != null) {
                            span = (it as HTMLSpanElement)
                        }
                    }
                }
            } else {
                attrs.id = "online-counter-offline"
                attrs.classes = setOf("map-title-widget")

                +i("OfflineMode")
            }
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
