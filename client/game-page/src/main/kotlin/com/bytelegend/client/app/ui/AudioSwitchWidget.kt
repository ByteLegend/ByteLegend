package com.bytelegend.client.app.ui

import com.bytelegend.app.client.ui.bootstrap.BootstrapSwitchButton
import kotlinx.html.classes
import react.RBuilder
import react.RState
import react.dom.div
import react.dom.jsStyle
import react.dom.span
import react.setState

interface AudioSwitchWidgetProps : GameProps

class AudioSwitchWidget : GameUIComponent<AudioSwitchWidgetProps, RState>() {
    override fun RBuilder.render() {
        div {
            span {
                // move the music symbol slightly down
                attrs.jsStyle {
                    position = "relative"
                    top = "4px"
                }
                attrs.classes = setOf("white-text-white-shadow-1")
                +"\uD83C\uDFB5 "
            }
            attrs.classes = setOf("map-title-widget")
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
