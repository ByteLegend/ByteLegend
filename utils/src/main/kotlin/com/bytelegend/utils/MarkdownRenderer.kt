package com.bytelegend.utils

import com.bytelegend.app.shared.i18n.LocalizedText
import com.bytelegend.app.shared.i18n.LocalizedTextFormat
import org.commonmark.node.Code
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
            val parser: Parser = Parser.builder().build()
            val renderer: HtmlRenderer = HtmlRenderer.builder()
                .attributeProviderFactory { NoTranslateClassAttributeProvider }
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
