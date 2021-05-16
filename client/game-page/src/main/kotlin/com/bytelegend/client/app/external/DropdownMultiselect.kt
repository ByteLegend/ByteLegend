@file:JsModule("react-select")
@file:JsNonModule

package com.bytelegend.client.app.external

import react.RClass
import react.RProps

@JsName("default")
external val ReactSelect: RClass<ReactSelectProps>

external interface ReactSelectProps : RProps {
    var className: String
    var options: Any
    var value: Any
    var onChange: Any
    var isMulti: Boolean
    var isSearchable: Boolean
    var isDisabled: Boolean
    var closeMenuOnSelect: Boolean

    // https://react-select.com/styles
    var styles: Any
    var onMenuOpen: Any
    var onMenuClose: Any
}
