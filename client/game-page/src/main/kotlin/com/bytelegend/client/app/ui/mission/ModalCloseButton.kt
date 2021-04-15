package com.bytelegend.client.app.ui.mission

import kotlinx.html.classes
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div

interface ModalCloseButtonProps : RProps {
    var onClickFunction: () -> Unit
}

class ModalCloseButton : RComponent<ModalCloseButtonProps, RState>() {
    override fun RBuilder.render() {
        div {
            attrs.classes = setOf("modal-close-button")
            attrs.onClickFunction = {
                props.onClickFunction()
            }
        }
    }
}
