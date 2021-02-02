@file:JsModule("react-bootstrap/Row")
@file:JsNonModule

package common.ui.bootstrap

import react.RClass

@JsName("default")
external val BootstrapRow: RClass<BootstrapRowProps>

external interface BootstrapRowProps : ExtraClassAwareProps {
    var lg: Int
    var md: Int
    var noGutters: Boolean
    var sm: Int
    var xl: Int
    var xs: Int
    var bsPrefix: String
}
