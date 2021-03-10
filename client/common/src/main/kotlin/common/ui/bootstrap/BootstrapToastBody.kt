@file:JsModule("react-bootstrap/ToastBody")
@file:JsNonModule

package common.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapToastBody: RClass<BootstrapToastBodyProps>

// https://react-bootstrap.github.io/components/toasts/
external interface BootstrapToastBodyProps : RProps {
    var `as`: Any
    var bsPrefix: String
}
