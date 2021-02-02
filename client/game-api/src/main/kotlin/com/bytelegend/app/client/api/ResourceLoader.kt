package com.bytelegend.app.client.api

import com.bytelegend.app.shared.GameMapDefinition
import com.bytelegend.app.shared.PixelSize
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonTransformingSerializer
import org.w3c.dom.HTMLImageElement
import org.w3c.fetch.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

interface ExpensiveResource<T> {
    val id: String
    val url: String
    val weight: Int

    suspend fun load(): T
}

class I18nTextResource(
    override val id: String,
    override val url: String,
    override val weight: Int
) : AjaxResource<Map<String, String>>(id, url, weight) {
    override suspend fun decode(response: Response): Map<String, String> {
        return response.text().await().let {
            JSObjectBackedMap(JSON.parse(it))
        }
    }
}

object GameMapListSerializer : JsonTransformingSerializer<List<GameMapDefinition>>(ListSerializer(GameMapDefinition.serializer()))

const val GAME_MAP_HIERARCHY_ID = "game-map-hierarchy"

class GameMapHierarchyResource(
    override val url: String,
    override val weight: Int
) : AjaxResource<List<GameMapDefinition>>(GAME_MAP_HIERARCHY_ID, url, weight) {
    override suspend fun decode(response: Response): List<GameMapDefinition> {
        return response.text().await().let {
            Json.decodeFromString(GameMapListSerializer, it)
        }
    }
}

data class ImageResourceData(
    val imageId: String,
    val size: PixelSize,
    val htmlElement: HTMLImageElement
)

fun getImageElement(imageId: String): HTMLImageElement {
    val elementId = "image-container-$imageId"
    return (document.getElementById(elementId) ?: throw NoSuchElementException("Img element $elementId not found")) as HTMLImageElement
}

fun getOrCreateImageElement(imageId: String): HTMLImageElement {
    val elementId = "image-container-$imageId"
    return (
        document.getElementById(elementId) ?: document.createElement("img").apply {
            this.id = elementId
            document.body?.appendChild(this)
        }
        ) as HTMLImageElement
}

// class GameScriptResource(
//    override val id: String,
//    override val url: String,
//    override val weight: Int
// ) : AjaxResource<String>(id, url, weight) {
//    override suspend fun decode(response: Response): String {
//        return response.text()
//            .await()
//            .apply { eval(this) }
//    }
// }

class ImageResource(
    override val id: String,
    override val url: String,
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
    override val url: String,
    override val weight: Int
) : ExpensiveResource<T> {
    abstract suspend fun decode(response: Response): T

    override suspend fun load(): T {
        return decode(
            window.fetch(url)
                .await()
                .apply {
                    if (status < 200 || status > 400) {
                        throw Exception("Got response status code $status")
                    }
                }
        )
    }

    override fun toString() = url
}

class TextAjaxResource(
    override val id: String,
    override val url: String,
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
    fun <T> add(
        resource: ExpensiveResource<out T>,
        blockingScene: Boolean = true,
        onFailure: (Throwable) -> Unit = {},
        onSuccess: (T) -> Unit = {}
    )

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

    fun isLoading() = currentProgress() < 100
}
