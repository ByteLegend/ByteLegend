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
package com.bytelegend.client.app.engine.resource

import com.bytelegend.app.client.api.ExpensiveResource
import com.bytelegend.app.client.api.ImageResourceData
import com.bytelegend.app.client.misc.getOrCreateAudioElement
import com.bytelegend.app.client.misc.getOrCreateImageElement
import com.bytelegend.app.client.utils.JSObjectBackedMap
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.client.app.engine.ItemOrAchievementMetadata
import com.bytelegend.client.app.web.checkStatusCode
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
    private val i18nContainer: MutableMap<String, String>
) : AjaxResource<Map<String, String>>(id, url) {
    override suspend fun decode(response: Response): Map<String, String> {
        return response.text().await().let {
            val result = JSObjectBackedMap<String>(JSON.parse(it))
            i18nContainer.putAll(result)
            result
        }
    }
}

class ItemAchievementMetadataResource(id: String, url: String) : AjaxResource<Map<String, ItemOrAchievementMetadata>>(id, url) {
    override suspend fun decode(response: Response): Map<String, ItemOrAchievementMetadata> {
        return response.json().await().unsafeCast<Array<dynamic>>().let {
            val result = JSObjectBackedMap<ItemOrAchievementMetadata>()
            it.forEach {
                result[it.iconId] = it
            }
            result
        }
    }
}

class AudioResource(
    override val id: String,
    val url: String
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
    val url: String
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
    val url: String
) : ExpensiveResource<T> {
    abstract suspend fun decode(response: Response): T

    override suspend fun load(): T {
        return decode(
            window.fetch(url)
                .await()
                .apply {
                    checkStatusCode()
                }
        )
    }

    override fun toString() = url
}

class TextAjaxResource(
    override val id: String,
    url: String
) : AjaxResource<String>(id, url) {
    override suspend fun decode(response: Response) = response.text().await()
}
