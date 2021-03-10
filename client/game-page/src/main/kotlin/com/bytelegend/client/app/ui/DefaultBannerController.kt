package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.BannerController
import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.EventListener
import common.ui.bootstrap.BootstrapAlert
import react.RBuilder
import react.RState
import react.setState

val BANNER_UPDATE_EVENT = "banner.update.event"

class DefaultBannerController(
    private val eventBus: EventBus
) : BannerController {
    override fun showWarningBanner(content: String) {
        eventBus.emit(BANNER_UPDATE_EVENT, Banner(content, "warning"))
    }
}

class Banner(
    val content: String,
    /**
     * success/warning, etc.
     */
    val variant: String
)

interface GameBannersState : RState {
    var banner: Banner?
}

class BannerUIComponent : GameUIComponent<GameProps, GameBannersState>() {
    private val bannerUpdateEventListener: EventListener<Banner> = {
        setState {
            banner = it
        }
    }

    @Suppress("UnsafeCastFromDynamic")
    override fun RBuilder.render() {
        if (state.banner != undefined) {
            BootstrapAlert {
                attrs.variant = state.banner!!.variant
                attrs.className = "fixed-top"
                attrs.dismissible = true
                attrs.onClose = {
                    setState {
                        banner = undefined
                    }
                }
                attrs.style = kotlinext.js.js {
                    zIndex = 2000
                    margin = "auto"
                    maxWidth = "${gameContainerWidth / 2}px"
                }
                +state.banner!!.content
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
