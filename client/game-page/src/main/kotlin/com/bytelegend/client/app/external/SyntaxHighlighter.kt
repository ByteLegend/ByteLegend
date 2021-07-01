@file:JsModule("react-syntax-highlighter")
@file:JsNonModule

package com.bytelegend.client.app.external

import react.RClass
import react.RProps

@JsName("default")
external val SyntaxHighlighter: RClass<SyntaxHighlighterProps>

external interface SyntaxHighlighterProps : RProps {
    var language: String
    var style: String
    var showLineNumbers: Boolean
}
