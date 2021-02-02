@file:JsModule("react-bootstrap/Container")
@file:JsNonModule

package common.ui.bootstrap

import react.RClass

@JsName("default")
external val BootstrapContainer: RClass<BootstrapContainerProps>

external interface BootstrapContainerProps : ExtraClassAwareProps {
    var fluid: String
    var bsPrefix: String
}
