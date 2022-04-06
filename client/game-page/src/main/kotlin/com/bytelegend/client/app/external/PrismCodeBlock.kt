/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bytelegend.client.app.external

import com.bytelegend.app.client.misc.uuid
import com.bytelegend.client.app.engine.logger
import kotlinx.js.jso
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.Element
import react.ChildrenBuilder
import react.Component
import react.Fragment
import react.Props
import react.State
import react.create
import react.dom.html.ReactHTML.div
import react.react
import kotlin.math.min

interface CodeBlockProps : Props {
    var language: String
    var pluginClassName: String
    var lines: List<String>
}

fun ChildrenBuilder.codeBlock(withLineNumber: Boolean = true, block: CodeBlockProps.() -> Unit = {}) {
    child(PrismCodeBlock::class.react, jso {
        if (withLineNumber) {
            pluginClassName = "line-numbers"
        }
        block()
    })
}

class PrismCodeBlock : Component<CodeBlockProps, State>() {
    private val codeContainerElementId = "code-container-${uuid()}"
    private val preElementId = "pre-${uuid()}"

    // how many lines displayed currently
    private var displayedLineNumber: Int = 0

    override fun render() = Fragment.create {
        div {
            id = codeContainerElementId
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

    override fun componentDidUpdate(prevProps: CodeBlockProps, prevState: State, snapshot: Any) {
        val firstDirtyLineNumber: Int = determineFirstDirtyLineNumber(props.lines, prevProps.lines)

        if (logger.debugEnabled) {
            logger.debug("displayedLineNumber $displayedLineNumber")
            logger.debug("Append to code block, previous lines ${prevProps.lines.size}, current lines ${props.lines.size}, first dirty line: $firstDirtyLineNumber")
        }

        // remove <code id = "xxxx-line-i"> and append new <code>
        document.getElementById(preElementId)?.let { pre ->
            for (j in firstDirtyLineNumber until displayedLineNumber) {
                document.getElementById("$codeContainerElementId-line-$j")?.let {
                    pre.removeChild(it)
                }
            }
            pre.appendAndHighlightLines(props.lines, firstDirtyLineNumber)
            displayedLineNumber = props.lines.size
        }
    }

    /**
     * Determine the first "dirty" line comparing old and new lines.
     * Returned line number starts from 0.
     */
    private fun determineFirstDirtyLineNumber(currentLines: List<String>, prevLines: List<String>): Int {
        if (currentLines === prevLines) {
            // lines are appended
            return displayedLineNumber
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

    override fun shouldComponentUpdate(nextProps: CodeBlockProps, nextState: State): Boolean {
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
