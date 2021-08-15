// https://react-bootstrap.github.io/components/alerts/#alert-props
package com.bytelegend.app.client.ui.bootstrap

import react.RClass
import react.RProps

val BootstrapAlertLink: RClass<BootstrapAlertLinkProps> = BootstrapAlert.asDynamic().Link

external interface BootstrapAlertLinkProps : RProps {
    var href: String
    var className: String
    var onClick: Any
}
