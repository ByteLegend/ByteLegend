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
import com.bytelegend.client.utils.JSArrayBackedList
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.browser.window
import kotlinx.html.classes
import react.RBuilder
import react.RState
import react.dom.div
import react.dom.jsStyle
import react.setState

const val BANNER_UPDATE_EVENT = "banner.update.event"

// Default modal z-index seems to be ~1020
const val BANNER_Z_INDEX = 2000

class DefaultBannerController(
    private val eventBus: EventBus
) : BannerController {
    override fun showBanner(banner: Banner) {
        eventBus.emit(BANNER_UPDATE_EVENT, banner)
    }
}

interface GameBannersState : RState {
    var banners: MutableList<Banner>
}

class BannerUIComponent : GameUIComponent<GameProps, GameBannersState>() {
    override fun GameBannersState.init() {
        banners = JSArrayBackedList()
    }

    private val bannerUpdateEventListener: EventListener<Banner> = {
        setState {
            banners = banners.apply { add(it) }
        }
    }

    @Suppress("UnsafeCastFromDynamic")
    override fun RBuilder.render() {
        if (state.banners.isNotEmpty()) {
            div {
                attrs.classes = jsObjectBackedSetOf("fixed-top")
                attrs.jsStyle {
                    zIndex = BANNER_Z_INDEX
                    margin = "auto"
                    maxWidth = "${gameContainerWidth / 2}px"
                }

                for (index in state.banners.size - 1 downTo 0) {
                    val banner = state.banners[index]
                    BootstrapAlert {
                        attrs.variant = banner.variant
                        attrs.dismissible = true
                        attrs.onClose = {
                            setState {
                                banners = banners.apply { remove(banner) }
                            }
                        }
                        attrs.className = "top-banner"
                        unsafeSpan(banner.contentHtml)
                    }

                    if (banner.autoDismissMs != 0) {
                        window.setTimeout(
                            {
                                setState {
                                    banners = banners.apply { remove(banner) }
                                }
                            },
                            banner.autoDismissMs
                        )
                    }
                }
            }
        }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        props.game.eventBus.on(BANNER_UPDATE_EVENT, bannerUpdateEventListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.game.eventBus.remove(BANNER_UPDATE_EVENT, bannerUpdateEventListener)
    }
}
