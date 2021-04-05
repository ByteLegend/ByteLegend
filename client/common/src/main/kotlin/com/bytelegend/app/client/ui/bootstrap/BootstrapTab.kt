@file:JsModule("react-bootstrap/Tab")
@file:JsNonModule

// https://react-bootstrap.github.io/components/tabs/#tabs-props

package com.bytelegend.app.client.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapTab: RClass<BootstrapTabProps>

external interface BootstrapTabProps : RProps {
    var key: String
    var disabled: Boolean
    var eventKey: String
    var tabClassName: String
    var title: Any
}
