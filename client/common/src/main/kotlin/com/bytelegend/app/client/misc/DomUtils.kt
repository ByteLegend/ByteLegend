@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.app.client.misc

import kotlinx.browser.document
import org.w3c.dom.HTMLAudioElement
import org.w3c.dom.HTMLImageElement

fun getImageElement(imageId: String): HTMLImageElement {
    val elementId = "img-container-$imageId"
    return (
        document.getElementById(elementId)
            ?: throw NoSuchElementException("Img element $elementId not found")
        ) as HTMLImageElement
}

fun getAudioElementOrNull(imageId: String): HTMLAudioElement? {
    val elementId = "audio-container-$imageId"
    return document.getElementById(elementId).asDynamic()
}

private fun <T> getOrCreateHtmlElement(tagName: String, id: String): T {
    val elementId = "$tagName-container-$id"
    return (
        document.getElementById(elementId) ?: document.createElement(tagName).apply {
            this.id = elementId
            document.body?.appendChild(this)
        }
        ).asDynamic()
}

fun getOrCreateImageElement(imageId: String): HTMLImageElement {
    return getOrCreateHtmlElement("img", imageId)
}

fun getOrCreateAudioElement(audioId: String): HTMLAudioElement {
    return getOrCreateHtmlElement("audio", audioId)
}
