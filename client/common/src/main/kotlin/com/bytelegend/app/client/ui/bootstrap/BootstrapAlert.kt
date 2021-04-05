@file:JsModule("react-bootstrap/Alert")
@file:JsNonModule

// https://react-bootstrap.github.io/components/alerts/#alert-props
package com.bytelegend.app.client.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapAlert: RClass<BootstrapAlertProps>

external interface BootstrapAlertProps : RProps {
    var show: Boolean
    var variant: String
    var dismissible: Any
    var transition: Any
    var onClose: Any
    var className: String
    var style: Any
}
