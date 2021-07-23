package com.bytelegend.client.app.external

import com.bytelegend.client.app.obj.uuid
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.id
import org.w3c.dom.Element
import react.RBuilder
import react.RComponent
import react.RElementBuilder
import react.RProps
import react.RState
import react.dom.div
import kotlin.math.min

interface CodeBlockProps : RProps {
    var language: String
    var pluginClassName: String
    var lines: List<String>
}

fun RBuilder.codeBlock(block: RElementBuilder<CodeBlockProps>.() -> Unit = {}) {
    child(PrismCodeBlock::class) {
        attrs.pluginClassName = "line-numbers"
        block()
    }
}

class PrismCodeBlock : RComponent<CodeBlockProps, RState>() {
    private val codeContainerElementId = "code-container-${uuid()}"
    private val preElementId = "pre-${uuid()}"

    // how many lines displayed currently
    private var displayedLineNumber: Int = 0

    override fun RBuilder.render() {
        div {
            attrs.id = codeContainerElementId
        }
    }

    // We have to handle DOM manually to avoid
    //  DOMException: Failed to execute 'removeChild' on 'Node': The node to be removed is not a child of this node.
    // which happened when manipulating React-managed DOM
    override fun componentDidMount() {
        document.createElement("pre").apply {
            id = preElementId
            document.getElementById(codeContainerElementId)?.appendChild(this)
            className = props.pluginClassName

            appendAndHighlightLines(props.lines, 0)
            displayedLineNumber = props.lines.size
        }
    }

    private fun Element.appendAndHighlightLines(lines: List<String>, startIndex: Int) {
        for (index in startIndex until lines.size) {
            val line = lines[index]
            val codeNode = document.createElement("code").apply {
                id = "$codeContainerElementId-line-$index"
                className = "block-display language-${props.language}"
                innerHTML = "<div>$line</div>"
            }
            appendChild(codeNode)
            window.asDynamic().Prism.highlightElement(codeNode)
        }
    }

    override fun componentDidUpdate(prevProps: CodeBlockProps, prevState: RState, snapshot: Any) {
        val firstDirtyLineNumber: Int = determineFirstDirtyLineNumber(props.lines, prevProps.lines)

        // remove <code id = "xxxx-line-i"> and append new <code>
        document.getElementById(preElementId)?.apply {
            for (j in firstDirtyLineNumber until displayedLineNumber) {
                document.getElementById("$codeContainerElementId-line-$j")?.let {
                    removeChild(it)
                }
            }
            appendAndHighlightLines(props.lines, firstDirtyLineNumber)
        }
    }

    private fun determineFirstDirtyLineNumber(currentLines: List<String>, prevLines: List<String>): Int {
        if (currentLines === prevLines) {
            // lines are appended
            return displayedLineNumber + 1
        } else {
            val min = min(currentLines.size, prevLines.size)
            var firstDirtyLineNumber: Int = -1

            for (i in 0 until min) {
                if (currentLines[i] != prevLines[i]) {
                    firstDirtyLineNumber = i
                    break
                }
            }
            if (firstDirtyLineNumber == -1) {
                firstDirtyLineNumber = min
            }
            return firstDirtyLineNumber
        }
    }

    override fun shouldComponentUpdate(nextProps: CodeBlockProps, nextState: RState): Boolean {
        // TODO this can only handle appending case, but not modifying case
        if (nextProps.lines === props.lines) {
            return nextProps.lines.size != displayedLineNumber
        }
        // Don't use List.equals, they're unreliable
        if (props.lines.size != nextProps.lines.size) {
            return true
        }
        for (i in 0 until props.lines.size) {
            if (props.lines[i] != nextProps.lines[i]) {
                return true
            }
        }
        return false
    }

    override fun componentWillUnmount() {
        document.getElementById(codeContainerElementId)?.apply {
            firstElementChild?.let {
                this.removeChild(it)
            }
        }
    }
}
