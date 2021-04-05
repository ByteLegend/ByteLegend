@file:JsModule("react-bootstrap/Popover")
@file:JsNonModule

package com.bytelegend.app.client.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapPopover: RClass<BootstrapPopoverProps>

external interface BootstrapPopoverProps : RProps {
    var id: String
    var placement: String
    var style: Any
}
