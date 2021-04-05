@file:JsModule("react-bootstrap/InputGroup")
@file:JsNonModule

package com.bytelegend.app.client.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapInputGroup: RClass<BootstrapInputGroupProps>

// https://react-bootstrap.github.io/components/input-group/#input-group-props
external interface BootstrapInputGroupProps : RProps {
    var className: String
    var size: String
    var bsPrefix: String
}
