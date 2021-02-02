@file:JsModule("react-bootstrap/Alert")
@file:JsNonModule

// https://react-bootstrap.github.io/components/alerts/#alert-props
package common.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapAlert: RClass<BootstrapAlertProps>

external interface BootstrapAlertProps : RProps {
    var show: Boolean
    var variant: String
}
