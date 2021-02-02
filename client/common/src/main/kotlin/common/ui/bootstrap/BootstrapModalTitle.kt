@file:JsModule("react-bootstrap/ModalTitle")
@file:JsNonModule

package common.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapModalTitle: RClass<BootstrapModalTitleProps>

// https://react-bootstrap.github.io/components/badge/
external interface BootstrapModalTitleProps : RProps {
    var `as`: Any
    var bsPrefix: String
}
