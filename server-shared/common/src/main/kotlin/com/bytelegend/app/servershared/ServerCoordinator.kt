package com.bytelegend.app.servershared

import com.bytelegend.app.shared.enums.ServerLocation

interface ServerCoordinator {
    val currentLocation: ServerLocation
    /**
     * Id of current server. It's guaranteed to be unique across all regions,
     * by underlying database atomic operations.
     */
    val currentId: Int

    /**
     * All online servers' ids.
     */
    suspend fun getOnlineServerIds(): Set<Int>
}
