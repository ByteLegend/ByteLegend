@file:JsModule("react-bootstrap/Tabs")
@file:JsNonModule

// https://react-bootstrap.github.io/components/tabs/#tabs-props

package com.bytelegend.app.client.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapTabs: RClass<BootstrapTabsProps>

external interface BootstrapTabsProps : RProps {
    var id: String
    var activeKey: Any
    var defaultActiveKey: Any
    var transition: Any
}
