@file:JsModule("react-bootstrap/Tooltip")
@file:JsNonModule

package common.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapTooltip: RClass<BootstrapTooltipProps>

external interface BootstrapTooltipProps : RProps {
    var id: String
    var arrowProps: Any
    var placement: Any
    var popper: Any
    var show: Any
    var bsPrefix: Any
    var style: Any
}
