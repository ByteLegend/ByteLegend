package com.bytelegend.app.client.ui.bootstrap

import react.RClass
import react.RProps

val BootstrapListGroupItem: RClass<BootstrapListGroupItemProps> = BootstrapListGroup.asDynamic().Item

external interface BootstrapListGroupItemProps : RProps {
    var className: String
    var href: String
    var variant: String
    var onClick: Any
    var active: Boolean
    var action: Boolean
}
