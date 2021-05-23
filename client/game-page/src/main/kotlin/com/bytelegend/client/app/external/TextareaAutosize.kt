@file:JsModule("react-textarea-autosize")
@file:JsNonModule

package com.bytelegend.client.app.external

import react.RClass
import react.RProps

@JsName("default")
external val TextareaAutosize: RClass<TextareaAutosizeProps>

external interface TextareaAutosizeProps : RProps {
    var className: String
    var maxRows: Int
    var minRows: Int
    var onHeightChange: Any
    var cacheMeasurements: Boolean
}
