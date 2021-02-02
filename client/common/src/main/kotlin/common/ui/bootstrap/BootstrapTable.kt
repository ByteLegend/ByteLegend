@file:JsModule("react-bootstrap/Table")
@file:JsNonModule

// https://react-bootstrap.github.io/components/table/#table-props
package common.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapTable: RClass<BootstrapTableProps>

external interface BootstrapTableProps : RProps {
    var bordered: Boolean
    var borderless: Boolean
    var hover: Boolean
    var size: String
    var striped: Boolean
    var variant: String
    var bsPrefix: String
    var responsive: Any
}
