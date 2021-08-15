@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.app.client.misc

import kotlinx.browser.document
import org.w3c.dom.HTMLAudioElement
import org.w3c.dom.HTMLImageElement
import org.w3c.dom.HTMLSpanElement

fun getImageElement(imageId: String): HTMLImageElement {
    val elementId = "img-container-$imageId"
    return (
        document.getElementById(elementId)
            ?: throw NoSuchElementException("Img element $elementId not found")
        ) as HTMLImageElement
}

/**
 * TODO make this a suspend function
 * Note that this has potential risk that when two `play()` calls are too close,
 * there might be only one
 */
fun playAudio(audioId: String) = getAudioElementOrNull(audioId)?.apply {
    loop = false
    currentTime = 0.0
    play()
}

fun getAudioElementOrNull(audioId: String): HTMLAudioElement? {
    val elementId = "audio-container-$audioId"
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

fun getOrCreateSpanElement(spanId: String): HTMLSpanElement {
    return getOrCreateHtmlElement("span", spanId)
}

fun getOrCreateImageElement(imageId: String): HTMLImageElement {
    return getOrCreateHtmlElement("img", imageId)
}

fun getOrCreateAudioElement(audioId: String): HTMLAudioElement {
    return getOrCreateHtmlElement("audio", audioId)
}
