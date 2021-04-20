@file:JsModule("react-bootstrap/PageItem")
@file:JsNonModule

package com.bytelegend.app.client.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapPaginationItem: RClass<BootstrapPaginationItemProps>

external interface BootstrapPaginationItemProps : RProps {
    var active: Boolean
    var activeLabel: String
    var disabled: Boolean
    var href: String
    var onClick: Any
}
