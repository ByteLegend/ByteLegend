@file:JsModule("react-bootstrap/Nav")
@file:JsNonModule

// https://react-bootstrap.github.io/components/navs/#nav-props

package common.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapNav: RClass<BootstrapNavProps>

external interface BootstrapNavProps : RProps {
    var activeKey: String
    var `as`: Any
    var variant: String
    var defaultActiveKey: String
}
