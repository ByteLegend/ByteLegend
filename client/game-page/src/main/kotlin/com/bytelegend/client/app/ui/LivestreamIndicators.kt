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

@file:Suppress("EXPERIMENTAL_API_USAGE", "UNUSED_VARIABLE")

package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.Banner
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.shared.entities.LivestreamData
import com.bytelegend.app.shared.util.currentTimeMillis
import com.bytelegend.client.app.engine.GAME_CLOCK_60S_EVENT
import com.bytelegend.client.app.engine.gameContainerHeight
import com.bytelegend.client.app.engine.logger
import com.bytelegend.client.app.engine.uiContainerCoordinateInGameContainer
import com.bytelegend.client.app.engine.uiContainerSize
import com.bytelegend.client.app.web.getText
import com.bytelegend.app.client.utils.JSArrayBackedList
import com.bytelegend.app.client.utils.JSObjectBackedStringSet
import com.bytelegend.client.utils.toLivestreams
import csstype.ClassName
import kotlinx.js.jso
import kotlinx.browser.localStorage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import react.Component
import react.Fragment
import react.State
import react.create
import react.dom.html.AnchorTarget
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.div

private const val LIVESTREAM_DATA_URL = "/ghraw/ByteLegend/ByteLegend/master/livestream-data.json"

interface LivestreamIndicatorsState : State {
    var livestreams: List<LivestreamData>
}

/**
 * A special components which sends AJAX requests periodically and update.
 */
class LivestreamIndicators : Component<GameProps, LivestreamIndicatorsState>() {
    private val containerLeft: Int
        get() = props.game.uiContainerCoordinateInGameContainer.x
    private val containerBottom: Int
        get() = props.game.gameContainerHeight - props.game.uiContainerCoordinateInGameContainer.y - props.game.uiContainerSize.height + 240
    private val every1MinuteEventHandler: EventListener<Nothing> = {
        sendBannerIfNecessary()
        setState { }
    }

    private fun sendBannerIfNecessary() {
        val localStorageKey = "notifiedLivestreams"
        val notifiedLivestreamsInLocalStorage = JSObjectBackedStringSet()
        try {
            localStorage.getItem(localStorageKey)?.apply {
                JSON.parse<Array<String>>(this).forEach {
                    notifiedLivestreamsInLocalStorage.add(it)
                }
            }
        } catch (e: Exception) {
            logger.error(e.stackTraceToString())
        }

        state.livestreams.forEach {
            if (it.isPast()) {
                notifiedLivestreamsInLocalStorage.remove(it.id)
            } else if (it.isLive() && !notifiedLivestreamsInLocalStorage.contains(it.id)) {
                props.game.bannerController.showBanner(
                    Banner(
                        "${props.game.i("NowLivestreaming")}<a target='_blank' href='${it.url}'>${it.title}</a>",
                        "primary",
                        60
                    )
                )
                notifiedLivestreamsInLocalStorage.add(it.id)
            } else if (it.secondsToStart() < 1800 && !notifiedLivestreamsInLocalStorage.contains(it.id)) {
                props.game.bannerController.showBanner(
                    Banner(
                        "${props.game.i("IncomingLivestreaming")}<a target='_blank' href='${it.url}'>${it.title}</a>",
                        "primary",
                        it.secondsToStart()
                    )
                )
                notifiedLivestreamsInLocalStorage.add(it.id)
            }
        }
        localStorage.setItem(localStorageKey, JSON.stringify(notifiedLivestreamsInLocalStorage.toJSArray()))
    }

    init {
        GlobalScope.launch {
            try {
                val livestreamData = toLivestreams(JSON.parse(getText(LIVESTREAM_DATA_URL)))
                setState({
                    it.livestreams = livestreamData
                    it
                }, {
                    sendBannerIfNecessary()
                })
            } catch (e: Exception) {
                logger.error("Error accessing: $LIVESTREAM_DATA_URL" + e.stackTraceToString())
            }
        }
    }

    init {
        state = jso { livestreams = JSArrayBackedList() }
    }

    @Suppress("UNUSED_VARIABLE")
    private fun LivestreamData.isPast(): Boolean {
        val now = currentTimeMillis()
        val end = endEpochMs
        return js("now>=end")
    }

    @Suppress("UNUSED_VARIABLE")
    private fun LivestreamData.secondsToStart(): Int {
        val now = currentTimeMillis()
        val start = startEpochMs
        return js("Math.floor( (start-now) / 1000 )")
    }

    private fun LivestreamData.isLive(): Boolean {
        /*
        LivestreamIndicators.kt?c769:88 Uncaught TypeError: tmp$.lessThanOrEqual is not a function
    at LivestreamIndicators.isLive_0 (LivestreamIndicators.kt?c769:88)
    at eval (LivestreamIndicators.kt?c769:100)
    at absoluteDiv (Utils.kt?ecb7:103)
    at LivestreamIndicators.render_ss14n$ (LivestreamIndicators.kt?c769:92)
    at eval (kotlin-react.js?b73f:592)
    at buildElements (kotlin-react.js?b73f:452)
    at buildElements_0 (kotlin-react.js?b73f:469)
    at LivestreamIndicators.RComponent.render (kotlin-react.js?b73f:597)
    at finishClassComponent (react-dom.development.js?f6e0:17485)
    at updateClassComponent (react-dom.development.js?f6e0:17435)
         */
        val now = currentTimeMillis()
        val start = startEpochMs
        val end = endEpochMs
        return js("now>=start && now<=end")
    }

    override fun render() = Fragment.create {
        if (state.livestreams.isNotEmpty()) {
            absoluteDiv(
                left = containerLeft,
                bottom = containerBottom,
                zIndex = Layer.LivestreamIndicator.zIndex()
            ) {
                state.livestreams.filter { !it.isPast() }.forEach {
                    div {
                        className = ClassName("livestream-item")
                        if (it.isLive()) {
                            div {
                                className = ClassName("livestream-icon")
                            }
                            a {
                                target = AnchorTarget._blank
                                href = it.url
                                +(props.game.i("NowLivestreaming") + it.title)
                            }
                        } else {
                            div {
                                className = ClassName("incoming-livestream-icon")
                            }
                            a {
                                target = AnchorTarget._blank
                                href = it.url
                                +(props.game.i("IncomingLivestreaming") + it.title)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun componentDidMount() {
        props.game.eventBus.on(GAME_CLOCK_60S_EVENT, every1MinuteEventHandler)
    }

    override fun componentWillUnmount() {
        props.game.eventBus.remove(GAME_CLOCK_60S_EVENT, every1MinuteEventHandler)
    }
}
