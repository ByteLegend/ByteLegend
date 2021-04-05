@file:JsModule("react-bootstrap/Modal")
@file:JsNonModule

package com.bytelegend.app.client.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapModal: RClass<BootstrapModalProps>

// https://react-bootstrap.github.io/components/badge/
external interface BootstrapModalProps : RProps {
    var size: String
    var centered: Boolean
    var ariaLabelledby: Any
    var show: Boolean
    var onHide: Any
    var onExit: Any
    var onExited: Any
    var onExiting: Any
    var onShow: Any
    var animation: Boolean
}
