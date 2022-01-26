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

fun uuid(): String {
    // https://stackoverflow.com/questions/105034/how-to-create-a-guid-uuid
    return js(
        """
        'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
            var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(16);
        })
    """
    )
}
