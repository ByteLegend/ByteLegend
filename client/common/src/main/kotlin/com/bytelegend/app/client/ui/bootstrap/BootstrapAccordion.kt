@file:JsModule("react-bootstrap/Accordion")
@file:JsNonModule

package com.bytelegend.app.client.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapAccordion: RClass<BootstrapAccordionProps>

external interface BootstrapAccordionProps : RProps {
    var `as`: Any
    var activeKey: String
    var defaultActiveKey: String
    var onSelect: Any
    var bsPrefix: String
}
