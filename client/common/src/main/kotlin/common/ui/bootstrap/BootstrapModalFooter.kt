@file:JsModule("react-bootstrap/ModalFooter")
@file:JsNonModule

package common.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapModalFooter: RClass<BootstrapModalFooterProps>

// https://react-bootstrap.github.io/components/badge/
external interface BootstrapModalFooterProps : RProps {
    var `as`: Any
    var bsPrefix: String
}
