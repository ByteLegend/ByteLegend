@file:JsModule("react-bootstrap/Nav")
@file:JsNonModule

// https://react-bootstrap.github.io/components/navs/#nav-props

package com.bytelegend.app.client.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapNav: RClass<BootstrapNavProps>

external interface BootstrapNavProps : RProps {
    var activeKey: String
    var `as`: Any
    var variant: String
    var className: String
    var defaultActiveKey: String
}
