package com.bytelegend.client.app.ui

import com.bytelegend.app.client.ui.bootstrap.BootstrapSwitchButton
import com.bytelegend.client.app.engine.util.jsObjectBackedSetOf
import kotlinx.html.classes
import react.RBuilder
import react.RState
import react.dom.div
import react.dom.jsStyle
import react.dom.span
import react.setState

class AudioSwitchWidget : GameUIComponent<GameProps, RState>() {
    override fun RBuilder.render() {
        div {
            span {
                // move the music symbol slightly down
                attrs.jsStyle {
                    position = "relative"
                    top = "4px"
                }
                attrs.classes = jsObjectBackedSetOf("white-text-white-shadow-1")
                +"\uD83C\uDFB5 "
            }
            attrs.classes = jsObjectBackedSetOf("map-title-widget")
            attrs.jsStyle {
                display = "inline-block"
            }
            BootstrapSwitchButton {
                attrs.size = "xs"
                attrs.checked = gameControl.audioEnabled
                attrs.onChange = {
                    gameControl.audioEnabled = it
                    setState { }
                }
            }
        }
    }
}
