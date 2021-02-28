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
    val fadeInLayer = document.createElement("div").unsafeCast<HTMLDivElement>()
    fadeInLayer.style.zIndex = Layer.FadeInFadeOut.zIndex().toString()
    fadeInLayer.style.width = "${gameContainerSize.width}px"
    fadeInLayer.style.height = "${gameContainerSize.height}px"
    fadeInLayer.style.position = "absolute"
    fadeInLayer.style.top = "0"
    fadeInLayer.style.left = "0"
    fadeInLayer.style.backgroundColor = "black"
    fadeInLayer.id = id

    document.body?.appendChild(fadeInLayer)
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
