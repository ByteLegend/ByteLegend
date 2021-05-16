@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.app.client.api

import com.bytelegend.app.shared.PixelSize
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.await
import org.w3c.dom.HTMLAudioElement
import org.w3c.dom.HTMLImageElement
import org.w3c.fetch.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

interface ExpensiveResource<T> {
    val id: String
    val weight: Int

    /**
     * Load a resource. Implementations must finish all work
     * before returning,
     *
     * For example, we may need to load i18n texts and put them
     * into a container. This means that putting texts into container
     * must be done in this method, not `load(XXX).then(put)`.
     *
     */
    suspend fun load(): T
}

class I18nTextResource(
    override val id: String,
    url: String,
    override val weight: Int,
    private val i18nContainer: MutableMap<String, String>
) : AjaxResource<Map<String, String>>(id, url, weight) {
    override suspend fun decode(response: Response): Map<String, String> {
        return response.text().await().let {
            val result = JSObjectBackedMap<String>(JSON.parse(it))
            i18nContainer.putAll(result)
            result
        }
    }
}

data class ImageResourceData(
    val imageId: String,
    val size: PixelSize,
    val htmlElement: HTMLImageElement
)

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
        element.oncanplay = {
            continuation.resume(it.target.unsafeCast<HTMLAudioElement>())
        }
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

interface ResourceLoader {
    /**
     * Add a resource to be loaded in a session.
     *
     * The scene has to wait for all "blockingScene" resources to be loaded.
     *
     */
    suspend fun <T> load(
        resource: ExpensiveResource<out T>,
        blockingScene: Boolean = true
    ): T

    fun <T> loadAsync(
        resource: ExpensiveResource<out T>,
        blockingScene: Boolean = true
    ): Deferred<T> = GlobalScope.async { load(resource, blockingScene) }

    /**
     * Reset the session (esp. the loading progress).
     */
    fun resetSession()

    /**
     * Whether a specific resource is loading or not.
     */
    fun isResourceLoading(id: String): Boolean

    /**
     * Get a loaded resource, which can be image, audio, etc.
     * Throws NoSuchElementException on absence.
     */
    fun <T> getLoadedResource(id: String): T

    fun <T> getLoadedResourceOrNull(id: String): T?

    /**
     * 100 * (loaded resource weight sum) / (all resource weight sum)
     */
    fun currentProgress(): Int
}
