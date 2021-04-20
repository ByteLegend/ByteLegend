@file:JsModule("react-bootstrap/TabPane")
@file:JsNonModule

// https://react-bootstrap.github.io/components/tabs/?#tab-pane-props
package com.bytelegend.app.client.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapTabPane: RClass<BootstrapTabPaneProps>

external interface BootstrapTabPaneProps : RProps {
    var active: Boolean
    var `as`: Any
    var eventKey: Any
    var id: String
    var mountOnEnter: Boolean
    var onEnter: Any
    var onEntered: Any
    var onEntering: Any
    var onExit: Any
    var onExited: Any
    var onExiting: Any
    var transition: Any
    var unmountOnExit: Boolean
    var bsPrefix: String
}
