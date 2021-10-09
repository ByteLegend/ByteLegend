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
package com.bytelegend.utils

import com.bytelegend.app.shared.i18n.LocalizedText
import com.bytelegend.app.shared.i18n.LocalizedTextFormat
import org.commonmark.ext.task.list.items.TaskListItemsExtension
import org.commonmark.node.Code
import org.commonmark.node.Link
import org.commonmark.node.Node
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.AttributeProvider
import org.commonmark.renderer.html.HtmlRenderer

interface MarkdownRenderer {
    fun render(localizedText: LocalizedText): LocalizedText
}

object CommonmarkMarkdownRenderer : MarkdownRenderer {
    override fun render(localizedText: LocalizedText): LocalizedText {
        if (localizedText.format == LocalizedTextFormat.MARKDOWN) {
            val extensions = listOf(TaskListItemsExtension.create())
            val parser: Parser = Parser.builder()
                .extensions(extensions)
                .build()
            val renderer: HtmlRenderer = HtmlRenderer.builder()
                .attributeProviderFactory { NoTranslateClassAttributeProvider }
                .attributeProviderFactory { TargetBlankLinkProvider }
                .extensions(extensions)
                .build()

            return LocalizedText(
                localizedText.id,
                localizedText.data.mapValues { renderer.render(parser.parse(it.value)) },
                LocalizedTextFormat.HTML
            )
        } else {
            return localizedText
        }
    }
}

object NoTranslateClassAttributeProvider : AttributeProvider {
    override fun setAttributes(node: Node, tagName: String, attributes: MutableMap<String, String>) {
        if (node is Code) {
            attributes["class"] = "notranslate"
        }
    }
}

object TargetBlankLinkProvider : AttributeProvider {
    override fun setAttributes(node: Node, tagName: String, attributes: MutableMap<String, String>) {
        if (node is Link) {
            attributes["target"] = "_blank"
        }
    }
}
