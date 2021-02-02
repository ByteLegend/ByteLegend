@file:JsModule("react-bootstrap/ToggleButtonGroup")

@file:JsNonModule

package common.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapToggleButtonGroup: RClass<BootstrapToggleButtonGroupProps>

external interface BootstrapToggleButtonGroupProps : RProps {
    var name: String

    // checkbox | radio
    var type: String

    var defaultValue: Any
}
