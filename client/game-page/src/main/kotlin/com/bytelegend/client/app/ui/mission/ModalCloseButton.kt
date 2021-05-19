package com.bytelegend.client.app.ui.mission

import com.bytelegend.client.app.engine.util.jsObjectBackedSetOf
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
            attrs.classes = jsObjectBackedSetOf("modal-close-button")
            attrs.onClickFunction = {
                props.onClickFunction()
            }
        }
    }
}
