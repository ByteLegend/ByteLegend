@file:JsModule("react-bootstrap/Col")
@file:JsNonModule

package com.bytelegend.app.client.ui.bootstrap

import react.RClass

@JsName("default")
external val BootstrapCol: RClass<BootstrapColProps>

external interface BootstrapColProps : ExtraClassAwareProps {
    var lg: Int
    var md: Int
    var noGutters: Boolean
    var sm: Int
    var xl: Int
    var xs: Int
    var bsPrefix: String
}
