@file:JsModule("react-bootstrap/Container")
@file:JsNonModule

package com.bytelegend.app.client.ui.bootstrap

import react.RClass

@JsName("default")
external val BootstrapContainer: RClass<BootstrapContainerProps>

external interface BootstrapContainerProps : ExtraClassAwareProps {
    var fluid: String
    var bsPrefix: String
}
