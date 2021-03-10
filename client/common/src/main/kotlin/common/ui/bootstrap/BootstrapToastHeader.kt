@file:JsModule("react-bootstrap/ToastHeader")
@file:JsNonModule

package common.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapToastHeader: RClass<BootstrapToastProps>

// https://react-bootstrap.github.io/components/toasts/
external interface BootstrapToastHeaderProps : RProps {
    var closeButton: Boolean
    var closeLabel: String
    var bsPrefix: String
}
