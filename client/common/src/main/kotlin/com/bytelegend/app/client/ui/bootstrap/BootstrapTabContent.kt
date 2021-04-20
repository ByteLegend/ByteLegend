@file:JsModule("react-bootstrap/TabContent")
@file:JsNonModule

// https://react-bootstrap.github.io/components/tabs/?#tab-content-props

package com.bytelegend.app.client.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapTabContent: RClass<BootstrapTabContentProps>

external interface BootstrapTabContentProps : RProps {
    var `as`: Any
    var bsPrefix: String
}
