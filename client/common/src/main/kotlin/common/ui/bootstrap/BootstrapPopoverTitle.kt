@file:JsModule("react-bootstrap/PopoverTitle")
@file:JsNonModule

package common.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapPopoverTitle: RClass<BootstrapPopoverTitleProps>

external interface BootstrapPopoverTitleProps : RProps {
    var id: String
    var `as`: String
}
