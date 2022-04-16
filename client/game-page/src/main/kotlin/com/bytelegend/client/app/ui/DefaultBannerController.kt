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

import com.bytelegend.app.client.api.Banner
import com.bytelegend.app.client.api.BannerController
import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.ui.bootstrap.BootstrapAlert
import com.bytelegend.client.app.engine.GAME_CLOCK_1S_EVENT
import com.bytelegend.app.client.utils.JSArrayBackedList
import csstype.ClassName
import kotlinx.js.jso
import react.Fragment
import react.State
import react.create
import react.dom.html.ReactHTML.b
import react.dom.html.ReactHTML.div

const val BANNER_UPDATE_EVENT = "banner.update.event"

// Default modal z-index seems to be ~1020, we want the banner/toast to appear above the modal
const val BANNER_TOAST_Z_INDEX = 2000

class DefaultBannerController(
    private val eventBus: EventBus
) : BannerController {
    override fun showBanner(banner: Banner) {
        eventBus.emit(BANNER_UPDATE_EVENT, banner)
    }
}

interface GameBannersState : State {
    var banners: MutableList<Banner>

    /**
     * How many seconds left for each banner.
     * For example, we have 3 banners: [NotAutoClosable, AutoClosableAfter3Seconds, AutoClosableAfter2Seconds]
     * This array would be: [0, 3, 2]
     * After 1 second, this array would be: [0, 2, 1]
     * After 1 more second, the array would be: [0,1], because the third banner is already auto closed.
     */
    var bannerLeftSeconds: MutableList<Int>
}

val Banner.isAutoClosable
    get() = autoCloseSeconds > 0

class BannerUIComponent : GameUIComponent<GameProps, GameBannersState>() {
    init {
        state = jso {
            banners = JSArrayBackedList()
            bannerLeftSeconds = JSArrayBackedList()
        }
    }

    private val bannerUpdateEventListener: EventListener<Banner> = {
        setState {
            banners = banners.apply { add(it) }
            bannerLeftSeconds = bannerLeftSeconds.apply { add(it.autoCloseSeconds) }
        }
    }
    private val checkRestSecondEverySecond: EventListener<Nothing> = {
        val newBanners = JSArrayBackedList<Banner>()
        val newLeftSeconds = JSArrayBackedList<Int>()

        for (i in 0 until state.banners.size) {
            val banner = state.banners[i]
            val secondsLeft = state.bannerLeftSeconds[i]
            if (banner.isAutoClosable) {
                if (secondsLeft > 1) {
                    newBanners.add(banner)
                    newLeftSeconds.add(secondsLeft - 1)
                }
            } else {
                newBanners.add(banner)
                newLeftSeconds.add(0)
            }
        }
        setState {
            banners = newBanners
            bannerLeftSeconds = newLeftSeconds
        }
    }

    @Suppress("UnsafeCastFromDynamic")
    override fun render() = Fragment.create {
        if (state.banners.isNotEmpty()) {
            div {
                className = ClassName("fixed-top")
                jsStyle {
                    zIndex = BANNER_TOAST_Z_INDEX
                    margin = "auto"
                    maxWidth = "${gameContainerWidth / 2}px"
                }

                for (index in state.banners.size - 1 downTo 0) {
                    val banner = state.banners[index]
                    val secondsLeft = state.bannerLeftSeconds[index]
                    BootstrapAlert {
                        variant = banner.variant
                        dismissible = true
                        onClose = {
                            setState {
                                banners = banners.apply { remove(banner) }
                            }
                        }
                        className = "top-banner"
                        unsafeSpan(banner.contentHtml)

                        if (banner.isAutoClosable) {
                            b {
                                className = ClassName("closed-in-span")
                                +" ${i("BannerClosedIn")} ${formatSecond(secondsLeft)}"
                            }
                        }
                    }
                }
            }
        }
    }

    private fun formatSecond(second: Int): String {
        return if (second <= 60) {
            "${second}s"
        } else {
            "${second / 60}m${(second % 60)}s"
        }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        props.game.eventBus.on(BANNER_UPDATE_EVENT, bannerUpdateEventListener)
        props.game.eventBus.on(GAME_CLOCK_1S_EVENT, checkRestSecondEverySecond)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.game.eventBus.remove(BANNER_UPDATE_EVENT, bannerUpdateEventListener)
        props.game.eventBus.remove(GAME_CLOCK_1S_EVENT, checkRestSecondEverySecond)
    }
}
