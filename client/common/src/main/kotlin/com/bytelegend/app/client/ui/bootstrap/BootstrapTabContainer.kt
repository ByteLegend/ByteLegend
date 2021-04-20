@file:JsModule("react-bootstrap/TabContainer")
@file:JsNonModule

// https://react-bootstrap.github.io/components/tabs/?#tab-container-props

package com.bytelegend.app.client.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapTabContainer: RClass<BootstrapTabContainerProps>

external interface BootstrapTabContainerProps : RProps {
    var defaultActiveKey: Any
    var activeKey: Any
    var generateChildId: Any
    var id: String
    var mountOnEnter: Boolean
    var onSelect: Any
    var transition: Any
    var unmountOnExit: Boolean
}
