package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.Banner
import com.bytelegend.app.client.api.BannerController
import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.ui.bootstrap.BootstrapAlert
import com.bytelegend.client.app.engine.util.JSArrayBackedList
import com.bytelegend.client.app.engine.util.jsObjectBackedSetOf
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
                        attrs.style = kotlinext.js.js {
                            // override .alert margin-bottom
                            marginBottom = "0"
                        }
                        unsafeSpan(banner.contentHtml)
                    }

                    if (banner.autoHide) {
                        window.setTimeout(
                            {
                                setState {
                                    banners = banners.apply { remove(banner) }
                                }
                            },
                            5000
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
