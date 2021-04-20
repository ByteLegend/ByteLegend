@file:JsModule("react-bootstrap/ListGroup")
@file:JsNonModule

package com.bytelegend.app.client.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapListGroup: RClass<BootstrapListGroupProps>

external interface BootstrapListGroupProps : RProps {
    var horizontal: String
    var style: dynamic
}
