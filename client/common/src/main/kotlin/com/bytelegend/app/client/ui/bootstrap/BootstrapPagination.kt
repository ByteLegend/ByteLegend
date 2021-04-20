@file:JsModule("react-bootstrap/Pagination")
@file:JsNonModule

package com.bytelegend.app.client.ui.bootstrap

import react.RClass
import react.RProps

@JsName("default")
external val BootstrapPagination: RClass<BootstrapPaginationProps>

external interface BootstrapPaginationProps : RProps {
    var size: String
    var bsPrefix: String
}
