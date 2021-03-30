@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.client.app.script.effect

import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.client.app.ui.Layer
import kotlinext.js.jsObject
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.HTMLCanvasElement
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

fun <T : Element> Document.createAndAppend(tagName: String, configure: T.() -> Unit): T {
    val tag: T = createElement(tagName).asDynamic()
    tag.configure()
    body?.appendChild(tag)
    return tag
}

suspend fun starIncrementEffect(
    increment: Int,
    left: Int,
    top: Int,
    width: Int,
    height: Int
): Unit = suspendCoroutine { continuation ->
    val divId = "star-increment"
    val div = document.createAndAppend<HTMLDivElement>("div") {
        id = divId
        className = "map-title-text"
        style.zIndex = Layer.ScriptWidget.zIndex().toString()
        style.position = "absolute"
        style.width = "${width}px"
        style.height = "${height}px"
        appendChild(document.createTextNode("+$increment⭐"))
    }

    window.asDynamic().gsap.fromTo(
        "#$divId",
        jsObject {
            autoAlpha = 1
            x = left
            y = top
        },
        jsObject {
            duration = 2
            autoAlpha = 0
            x = left
            y = top - 32
            onComplete = {
                document.body?.removeChild(div)
                continuation.resume(Unit)
            }
        }
    )
}

suspend fun starFlyEffect(
    gameContainerSize: PixelSize,
    from: PixelCoordinate,
    to: PixelCoordinate,
    durationSecond: Int
): Unit = suspendCoroutine { continuation ->
    val canvas = document.createAndAppend<HTMLCanvasElement>("canvas") {
        id = "starfly-canvas"
        style.zIndex = Layer.ScriptWidget.zIndex().toString()
        style.position = "absolute"
        width = gameContainerSize.width
        height = gameContainerSize.height
    }
    val starDiv = document.createAndAppend<HTMLDivElement>("div") {
        id = "starfly-star"
        style.zIndex = (Layer.ScriptWidget.zIndex() + 1).toString()
        style.top = "0px"
        style.left = "0px"
        style.backgroundColor = "transparent"
        style.position = "absolute"

        appendChild(document.createTextNode("⭐"))
    }

    window.asDynamic().starFly(
        canvas,
        starDiv,
        from.x,
        from.y,
        to.x,
        to.y,
        durationSecond,
        // continue animation after star flying is completed
        { continuation.resume(Unit) }
    ) {
        // but wait for all particles to finish then clean up
        document.body?.removeChild(starDiv)
        document.body?.removeChild(canvas)
    }
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
