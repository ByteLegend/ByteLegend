@file:JsModule("bootstrap-switch-button-react")
@file:JsNonModule

// https://github.com/gitbrent/bootstrap-switch-button-react#readme
package com.bytelegend.app.client.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapSwitchButton: RClass<BootstrapSwitchButtonProps>

external interface BootstrapSwitchButtonProps : RProps {
    var checked: Boolean
    var onlabel: String
    var offlabel: String
    var size: String
    var onstyle: String
    var offstyle: String
    var style: String
    var width: String
    var height: String
    var onChange: (Boolean) -> Unit
}
