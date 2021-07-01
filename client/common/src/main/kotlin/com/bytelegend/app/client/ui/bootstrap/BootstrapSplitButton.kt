@file:JsModule("react-bootstrap/SplitButton")
@file:JsNonModule

package com.bytelegend.app.client.ui.bootstrap

import react.RClass
import react.RProps

// https://react-bootstrap.github.io/components/dropdowns/#split-button-props
@JsName("default")
external val BootstrapSplitButton: RClass<BootstrapSplitButtonProps>

external interface BootstrapSplitButtonProps : RProps {
    var title: Any
    var variant: String
    var type: String
    var disabled: Boolean
    var href: String
    var id: String
    var menuAlign: String
    var menuRole: String
    var onClick: Any
    var size: String
    var drop: String
}
