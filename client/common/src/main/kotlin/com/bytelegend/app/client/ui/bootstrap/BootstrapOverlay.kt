@file:JsModule("react-bootstrap/Overlay")
@file:JsNonModule

package com.bytelegend.app.client.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapOverlay: RClass<BootstrapOverlayProps>

external interface BootstrapOverlayProps : RProps {
    var placement: String
    var show: Boolean
    var target: Any
}
