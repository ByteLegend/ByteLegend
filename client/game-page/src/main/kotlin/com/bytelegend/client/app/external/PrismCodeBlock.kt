package com.bytelegend.client.app.external

import com.bytelegend.client.app.engine.util.jsObjectBackedSetOf
import kotlinx.browser.window
import kotlinx.html.classes
import org.w3c.dom.HTMLElement
import react.RBuilder
import react.RComponent
import react.RElementBuilder
import react.RProps
import react.RState
import react.dom.code
import react.dom.jsStyle
import react.dom.pre

interface CodeBlockProps : RProps {
    var language: String
    var plugins: Set<String>
}

fun RBuilder.codeBlock(block: RElementBuilder<CodeBlockProps>.() -> Unit = {}) {
    child(PrismCodeBlock::class) {
        attrs.plugins = jsObjectBackedSetOf("line-numbers")
        block()
    }
}

class PrismCodeBlock : RComponent<CodeBlockProps, RState>() {
    lateinit var element: HTMLElement
    override fun RBuilder.render() {
        pre {
            if (props.plugins != undefined) {
                attrs.classes = props.plugins
            }
            code {
                attrs.jsStyle {
                    whiteSpace = "pre-wrap"
                }
                ref {
                    if (it != null) {
                        element = (it as HTMLElement)
                    }
                }
                attrs.classes = jsObjectBackedSetOf("language-${props.language}")

                children()
            }
        }
    }

    override fun componentDidMount() {
        window.asDynamic().Prism.highlightElement(element)
    }

    override fun componentDidUpdate(prevProps: CodeBlockProps, prevState: RState, snapshot: Any) {
        window.asDynamic().Prism.highlightElement(element)
    }
}
