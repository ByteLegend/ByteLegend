package com.bytelegend.app.client.ui.bootstrap

import react.RClass
import react.RProps

val BootstrapAccordionToggle: RClass<BootstrapAccordionToggleProps> = BootstrapAccordion.asDynamic().Toggle
val BootstrapAccordionCollapse: RClass<BootstrapAccordionCollapseProps> = BootstrapAccordion.asDynamic().Collapse

external interface BootstrapAccordionToggleProps : RProps {
    var `as`: Any
    var className: String
    var variant: String
    var eventKey: String
    var onClick: Any
}

external interface BootstrapAccordionCollapseProps : RProps {
    var children: Any
    var className: String
    var eventKey: String
}
