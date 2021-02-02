@file:JsModule("react-bootstrap/ModalHeader")
@file:JsNonModule

package common.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapModalHeader: RClass<BootstrapModalHeaderProps>

// https://react-bootstrap.github.io/components/badge/
external interface BootstrapModalHeaderProps : RProps {
    var closeButton: Boolean
    var closeLabel: Boolean
    var onHide: Any
    var bsPrefix: String
}
