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
@file:Suppress("UNCHECKED_CAST", "UnsafeCastFromDynamic", "EXPERIMENTAL_API_USAGE")

package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.ExpensiveResource
import com.bytelegend.app.client.api.ResourceLoader
import com.bytelegend.app.client.utils.JSObjectBackedMap
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

const val RESOURCE_LOADING_FAILURE_EVENT = "resource.loading.failure"

class ResourceLoadingSuccessEvent<T>(
    val id: String,
    val data: T
)

class ResourceLoadingFailureEvent(
    val id: String,
    val message: String
)

typealias ResourceLoadingFailureEventListener = (ResourceLoadingFailureEvent) -> Unit

/**
 * Coordinates loading expensive resources, such as images, map jsons, audios, etc. It does:
 *
 * 1. When any resource loading fails, emit a "resource.loading.success.$resourceId" event with message so it can be displayed on UI.
 * 2. When a scene-blocking resource is added, emit a "scene.loading.start" event.
 * 3. When all scene-blocking resources are finished and GameScene is switched successfully, emit a "scene.loading.end" event.
 *
 */
class DefaultResourceLoader(override val di: DI) : ResourceLoader, DIAware {
    private val eventBus: EventBus by di.instance()
    private val allLoadedResources: MutableMap<String, Any> = JSObjectBackedMap()
    private val allLoadingResources: MutableMap<String, Deferred<Any>> = JSObjectBackedMap()
    private val loadingSceneBlockingResources: MutableMap<String, Deferred<Any>> = JSObjectBackedMap()

    // For counting progress
    private var loadFailedSceneBlockingResourcesCount = 0
    private var loadSucceededSceneBlockingResourcesCount = 0

    private var isSceneLoading = false

    override fun <T> loadAsync(resource: ExpensiveResource<out T>, blockingScene: Boolean): Deferred<T> {
        val loaded = allLoadedResources[resource.id]
        if (loaded != null) {
            return CompletableDeferred<T>(null).apply { complete(loaded.asDynamic()) }
        }
        val loading = allLoadingResources[resource.id]
        if (loading != null) {
            return loading.asDynamic()
        }
        val resourceDataDeferred = GlobalScope.async {
            doLoad(resource, blockingScene)
        }
        allLoadingResources[resource.id] = resourceDataDeferred.asDynamic()
        if (blockingScene) {
            loadingSceneBlockingResources[resource.id] = resourceDataDeferred.asDynamic()
        }
        return resourceDataDeferred
    }

    fun sceneSwitchReady() {
        logger.debug("Scene switch finished! Check loading status.")
        isSceneLoading = false
        checkSceneLoadingEnded()
    }

    private fun checkSceneLoadingEnded() {
        if (!isSceneLoading && currentProgress() == 100) {
            logger.debug("Scene switched and all resources loaded.")
            eventBus.emit(SCENE_LOADING_END_EVENT, null)
            loadingSceneBlockingResources.clear()
            loadFailedSceneBlockingResourcesCount = 0
            loadSucceededSceneBlockingResourcesCount = 0
        }
    }

    @Suppress("DeferredResultUnused")
    private suspend fun <T> doLoad(resource: ExpensiveResource<out T>, blockingScene: Boolean): T {
        try {
            logger.debug("Start loading ${resource.id}, blocking: $blockingScene")
            if (!isSceneLoading && blockingScene) {
                // edge-trigger
                logger.debug("Loading ${resource.id} triggers scene loading event as it's the first one")
                isSceneLoading = true
                eventBus.emit(SCENE_LOADING_START_EVENT, null)
            }
            val resourceData = resource.load()
            logger.debug("Loaded ${resource.id} successfully!")
            allLoadingResources.remove(resource.id)
            allLoadedResources[resource.id] = resourceData.unsafeCast<Any>()
            if (blockingScene) {
                loadingSceneBlockingResources.remove(resource.id)
                loadSucceededSceneBlockingResourcesCount++
                checkSceneLoadingEnded()
            }
            eventBus.emit(GAME_UI_UPDATE_EVENT, null)
            return resourceData
        } catch (e: Throwable) {
            allLoadingResources.remove(resource.id)
            if (blockingScene) {
                loadingSceneBlockingResources.remove(resource.id)
                loadFailedSceneBlockingResourcesCount++
            }
            eventBus.emit(
                RESOURCE_LOADING_FAILURE_EVENT,
                ResourceLoadingFailureEvent(resource.id, "Loading resource ${resource.id} failed: ${e.message}")
            )
            throw e
        }
    }

    override fun isResourceLoading(id: String): Boolean = allLoadingResources.containsKey(id)

    @Suppress("UnsafeCastFromDynamic")
    override fun <T> getLoadedResource(id: String): T {
        return getLoadedResourceOrNull(id) ?: throw NoSuchElementException("Can't find $id in loaded resources. Loaded resources are: ${allLoadedResources.keys.joinToString(",")}")
    }

    override fun <T> getLoadedResourceOrNull(id: String): T? = allLoadedResources[id] as T?

    override fun currentProgress(): Int {
        val loadingSum = loadingSceneBlockingResources.size
        val sum = loadingSum + loadSucceededSceneBlockingResourcesCount + loadFailedSceneBlockingResourcesCount
        return if (sum == 0) {
            100
        } else {
            (100.0 - 100.0 * loadingSum / sum).toInt()
        }
    }
}
