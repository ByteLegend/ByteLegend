@file:Suppress("UNCHECKED_CAST")

package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.ExpensiveResource
import com.bytelegend.app.client.api.JSObjectBackedMap
import com.bytelegend.app.client.api.ResourceLoader
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

const val RESOURCE_LOADING_SUCCESS_EVENT = "resource.loading.success"
const val RESOURCE_LOADING_FAILURE_EVENT = "resource.loading.failure"

class ResourceLoadingSuccessEvent<T>(
    val id: String,
    val data: T
)

class ResourceLoadingFailureEvent(
    val id: String,
    val message: String
)

typealias ResourceLoadingSuccessEventListener<T> = (ResourceLoadingSuccessEvent<T>) -> Unit
typealias ResourceLoadingFailureEventListener = (ResourceLoadingFailureEvent) -> Unit

/**
 * Coordinates loading expensive resources, such as images, map jsons, audios, etc. It does:
 *
 * 1. When any resource loading succeeds, emit a "resource.loading.success.$resourceId" event with the resource.
 * 2. When any resource loading fails, emit a "resource.loading.success.$resourceId" event with message so it can be displayed on UI.
 */
class DefaultResourceLoader(override val di: DI) : ResourceLoader, DIAware {
    private val eventBus: EventBus by di.instance()
    private val allLoadedResources: MutableMap<String, Any> = JSObjectBackedMap()
    private val loadingResourcesInSession: MutableMap<String, ExpensiveResource<out Any>> = JSObjectBackedMap()
    private val loadedResourcesInSession: MutableMap<String, ExpensiveResource<out Any>> = JSObjectBackedMap()
    private val loadFailedResourcesInSession: MutableMap<String, ExpensiveResource<out Any>> = JSObjectBackedMap()

    override fun <T> add(
        resource: ExpensiveResource<out T>,
        blockingScene: Boolean,
        onFailure: (Throwable) -> Unit,
        onSuccess: (T) -> Unit,
    ) {
        if (allLoadedResources.containsKey(resource.id)) {
            console.warn("${resource.id} already loaded, did you duplicate the resource id?")
        }
        if (blockingScene) {
            addSceneBlockingResource(resource, onSuccess, onFailure)
        } else {
            startLoading(resource, onSuccess, onFailure)
        }
    }

    override fun resetSession() {
        loadFailedResourcesInSession.clear()
        loadingResourcesInSession.clear()
        loadedResourcesInSession.clear()
    }

    private fun <T> startLoading(
        resource: ExpensiveResource<out T>,
        onSuccess: (T) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        GlobalScope.launch {
            try {
                val resourceData = resource.load()
                allLoadedResources[resource.id] = resourceData.unsafeCast<Any>()
                onSuccess(resourceData)
                // load LoadingPage if necessary, otherwise it complains
                // "Can't perform a React state update on an unmounted component"
                eventBus.emit(GAME_UI_UPDATE_EVENT, null)
                eventBus.emit(RESOURCE_LOADING_SUCCESS_EVENT, ResourceLoadingSuccessEvent(resource.id, resourceData))
            } catch (e: Throwable) {
                onFailure(e)
                eventBus.emit(GAME_UI_UPDATE_EVENT, null)
                eventBus.emit(
                    RESOURCE_LOADING_FAILURE_EVENT,
                    ResourceLoadingFailureEvent(resource.id, "Loading resource $resource failed: ${e.message}")
                )
                throw e
            }
        }
    }

    private fun <T> addSceneBlockingResource(
        resource: ExpensiveResource<out T>,
        onSuccess: (T) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        loadingResourcesInSession[resource.id] = resource.unsafeCast<ExpensiveResource<out Any>>()
        startLoading(
            resource,
            { resourceData ->
                // Execute user callback first
                onSuccess(resourceData)
                loadingResourcesInSession.remove(resource.id)
                loadedResourcesInSession[resource.id] = resource.unsafeCast<ExpensiveResource<out Any>>()
            },
            { t ->
                onFailure(t)
                loadingResourcesInSession.remove(resource.id)
                loadFailedResourcesInSession[resource.id] = resource.unsafeCast<ExpensiveResource<out Any>>()
            }
        )
    }

    override fun isResourceLoading(id: String): Boolean = loadingResourcesInSession.containsKey(id)

    @Suppress("UnsafeCastFromDynamic")
    override fun <T> getLoadedResource(id: String): T {
        return getLoadedResourceOrNull(id) ?: throw NoSuchElementException("Can't find $id in loaded resources. Loaded resources are: ${allLoadedResources.keys.joinToString(",")}")
    }

    override fun <T> getLoadedResourceOrNull(id: String): T? = allLoadedResources[id] as T?

    override fun currentProgress(): Int {
        val loadingSum = loadingResourcesInSession.values.sumBy { it.weight }
        val sum = loadFailedResourcesInSession.values.sumBy { it.weight } +
            loadingSum +
            loadedResourcesInSession.values.sumBy { it.weight }
        return if (sum == 0) {
            100
        } else {
            (100.0 - 100.0 * loadingSum / sum).toInt()
        }
    }
}
