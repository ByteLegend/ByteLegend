@file:JsModule("react-bootstrap/Toast")
@file:JsNonModule

package common.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapToast: RClass<BootstrapToastProps>

// https://react-bootstrap.github.io/components/toasts/
external interface BootstrapToastProps : RProps {
    var animation: Boolean
    var autohide: Boolean
    var delay: Int
    var onClose: Any
    var show: Boolean
    var transition: Any
    var bsPrefix: String
}
