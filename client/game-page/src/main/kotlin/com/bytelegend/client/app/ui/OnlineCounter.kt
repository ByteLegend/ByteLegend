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

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.shared.protocol.ONLINE_COUNTER_UPDATE_EVENT
import csstype.ClassName
import kotlinx.js.jso
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLSpanElement
import react.Fragment
import react.RefObject
import react.State
import react.create
import react.createRef
import react.dom.html.ReactHTML.span

const val ANIMATION_DIV_ID = "counter-animation-div"

interface OnlineCounterProps : GameProps {
    var initCount: Int
}

interface OnlineCounterState : State {
    var count: Int
    var offsetY: Int
}

class OnlineCounter(props: OnlineCounterProps) : GameUIComponent<OnlineCounterProps, OnlineCounterState>(props) {
    private val spanRef: RefObject<HTMLSpanElement> = createRef()
    private val span: HTMLSpanElement by lazy {
        spanRef.current!!
    }
    private val onlineCounterUpdateEventListener: EventListener<Int> = {
        // It's possible the event during the animation is lost, but we think it's fine
        if (it != state.count) {
            animate(it)
        }
    }

    init {
        state = jso {
            offsetY = 0
            count = props.initCount
        }
    }

    private fun animating() = document.getElementById(ANIMATION_DIV_ID) != null

    /**
     * Display animation for the updated value
     */
    private fun animate(targetValue: Int) {
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
            appendChild(document.createTextNode(targetValue.toString()))
            this.style.position = "absolute"
        }
        newDiv.appendChild(newSpan)
        window.asDynamic().gsap.fromTo(
            newSpan,
            jso { y = initialSpanOffsetY },
            jso {
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

    @Suppress("UnsafeCastFromDynamic")
    override fun render() = Fragment.create {
        span {
            if (gameControl.online) {
                id = "online-counter"
                className = ClassName("map-title-widget")

                +i("OnlineCount")
                span {
                    +state.count.toString()
                    jsStyle {
                        position = "relative"
                        top = "${state.offsetY}px"
                    }
                    ref = spanRef
                }
            } else {
                id = "online-counter-offline"
                className = ClassName("map-title-widget")

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
