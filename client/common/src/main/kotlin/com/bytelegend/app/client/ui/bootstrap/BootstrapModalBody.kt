@file:JsModule("react-bootstrap/ModalBody")
@file:JsNonModule

package com.bytelegend.app.client.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapModalBody: RClass<BootstrapModalBodyProps>

// https://react-bootstrap.github.io/components/badge/
external interface BootstrapModalBodyProps : RProps {
    var `as`: Any
    var bsPrefix: String
}
