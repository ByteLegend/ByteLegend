@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.bytelegend.app.client.api

import kotlinx.coroutines.Deferred

interface ExpensiveResource<T> {
    val id: String

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

interface ResourceLoader {
    /**
     * Load a resource asynchronously, return the Deferred result.
     * The scene has to wait for all "blockingScene" resources to be loaded.
     *
     * If calling on same resources multiple times, the same Deferred result will be returned.
     */
    fun <T> loadAsync(
        resource: ExpensiveResource<out T>,
        blockingScene: Boolean = true
    ): Deferred<T>

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
