@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.client.app.script.effect

import com.bytelegend.app.shared.PixelSize
import com.bytelegend.client.app.ui.Layer
import kotlinext.js.jsObject
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLDivElement
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.js.Date

suspend fun fadeInEffect(gameContainerSize: PixelSize): Unit = suspendCoroutine { continuation ->
    val id = "fadeIn-${Date().getTime().toLong()}"
    val fadeInLayer = createFullscreenDiv(id, Layer.FadeInFadeOut.zIndex(), gameContainerSize) {
        style.backgroundColor = "black"
    }
    window.asDynamic().gsap.fromTo(
        "#$id",
        jsObject {
            autoAlpha = 1
        },
        jsObject {
            duration = 1
            autoAlpha = 0
            onComplete = {
                document.body?.removeChild(fadeInLayer)
                continuation.resume(Unit)
            }
        }
    )
}

fun disconnectionEffect(gameContainerSize: PixelSize) {
    val id = "disconnect-${Date().getTime().toLong()}"
    createFullscreenDiv(id, Layer.FadeInFadeOut.zIndex(), gameContainerSize) {
        style.backgroundColor = "black"
    }
    window.asDynamic().gsap.fromTo(
        "#$id",
        jsObject {
            x = gameContainerSize.width / 2
            y = gameContainerSize.height / 2
            width = 0
            height = 0
        },
        jsObject {
            x = 0
            y = 0
            width = gameContainerSize.width
            height = gameContainerSize.height
        }
    )
}

private fun createFullscreenDiv(id: String, zIndex: Int, gameContainerSize: PixelSize, configure: HTMLDivElement.() -> Unit): HTMLDivElement {
    val div = document.createElement("div").unsafeCast<HTMLDivElement>()
    div.style.zIndex = zIndex.toString()
    div.style.width = "${gameContainerSize.width}px"
    div.style.height = "${gameContainerSize.height}px"
    div.style.position = "absolute"
    div.style.top = "0"
    div.style.left = "0"
    div.id = id
    div.configure()

    document.body?.appendChild(div)
    return div
}
