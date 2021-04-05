@file:JsModule("react-bootstrap/ToggleButton")

@file:JsNonModule

package com.bytelegend.app.client.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapToggleButton: RClass<BootstrapToggleButtonProps>

external interface BootstrapToggleButtonProps : RProps {
    var checked: Boolean
    var disabled: Boolean
    var name: String
    var value: Any
    var inputRef: Any

    // checkbox | radio
    var type: String
    var onChange: Any
}
