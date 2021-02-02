@file:JsModule("react-bootstrap/PopoverContent")
@file:JsNonModule

package common.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapPopoverContent: RClass<BootstrapPopoverContentProps>

external interface BootstrapPopoverContentProps : RProps {
    var bsPrefix: String
}
