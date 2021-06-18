package com.bytelegend.client.app.engine.resource

import com.bytelegend.app.client.api.ExpensiveResource
import com.bytelegend.app.client.api.ImageResourceData
import com.bytelegend.app.client.misc.getOrCreateAudioElement
import com.bytelegend.app.client.misc.getOrCreateImageElement
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.client.app.engine.logger
import com.bytelegend.client.app.engine.util.JSObjectBackedMap
import kotlinx.browser.window
import kotlinx.coroutines.await
import org.w3c.dom.HTMLAudioElement
import org.w3c.dom.HTMLImageElement
import org.w3c.dom.events.Event
import org.w3c.fetch.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class I18nTextResource(
    override val id: String,
    url: String,
    override val weight: Int,
    private val i18nContainer: MutableMap<String, String>
) : AjaxResource<Map<String, String>>(id, url, weight) {
    override suspend fun decode(response: Response): Map<String, String> {
        return response.text().await().let {
            val result = JSObjectBackedMap<String>(JSON.parse(it))
            if (id.startsWith("common")) {
                logger.debug("$id i18n resource size: ${result.size}, ${result.get("BeijingServerDisplayNameId")}")
            }
            i18nContainer.putAll(result)
            result
        }
    }
}

class AudioResource(
    override val id: String,
    val url: String,
    override val weight: Int
) : ExpensiveResource<HTMLAudioElement> {
    override suspend fun load(): HTMLAudioElement = suspendCoroutine { continuation ->
        val element = getOrCreateAudioElement(id)
        element.src = url
        element.preload = "auto"
        element.loop = true
        val oncanplay = { it: Event ->
            continuation.resume(it.target.unsafeCast<HTMLAudioElement>())
            element.oncanplay = null
        }
        element.oncanplay = oncanplay
        element.onerror = { _, _, _, _, _ ->
            continuation.resumeWithException(Exception("Can't load $url"))
        }
    }
}

class ImageResource(
    override val id: String,
    val url: String,
    override val weight: Int
) : ExpensiveResource<ImageResourceData> {
    override suspend fun load(): ImageResourceData = suspendCoroutine { continuation ->
        val element = getOrCreateImageElement(id)
        element.src = url
        element.style.display = "none"
        element.onload = {
            val target = it.target as HTMLImageElement
            continuation.resume(
                ImageResourceData(id, PixelSize(target.naturalWidth, target.naturalHeight), target)
            )
        }
        element.onerror = { _, _, _, _, _ ->
            continuation.resumeWithException(Exception("Can't load $url"))
        }
    }

    override fun toString() = "Image $id"
}

abstract class AjaxResource<T>(
    override val id: String,
    val url: String,
    override val weight: Int
) : ExpensiveResource<T> {
    abstract suspend fun decode(response: Response): T

    override suspend fun load(): T {
        return decode(
            window.fetch(url)
                .await()
                .apply {
                    if (status < 200 || status > 400) {
                        throw Exception("Got response status code $status when requesting $url")
                    }
                }
        )
    }

    override fun toString() = url
}

class TextAjaxResource(
    override val id: String,
    url: String,
    override val weight: Int
) : AjaxResource<String>(id, url, weight) {
    override suspend fun decode(response: Response) = response.text().await()
}
