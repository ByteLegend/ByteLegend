@file:JsModule("react-bootstrap/Card")
@file:JsNonModule

package com.bytelegend.app.client.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapCard: RClass<BootstrapCardProps>

external interface BootstrapCardProps : RProps {
    var `as`: Any
    var bg: String
    var body: Boolean
    var border: String
    var text: String
    var bsPrefix: String
}
