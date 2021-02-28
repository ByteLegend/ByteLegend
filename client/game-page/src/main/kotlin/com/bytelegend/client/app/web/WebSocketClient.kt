package com.bytelegend.client.app.web

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.ExpensiveResource
import kotlin.coroutines.suspendCoroutine

class WebSocketClient(
    private val eventBus: EventBus
) : ExpensiveResource<Nothing> {
    override val id: String = "websocket-client"
    override val weight: Int = 1

    private val serverEventBus: dynamic by lazy {
        js("new EventBus('/game')")
    }

    override suspend fun load(): Nothing = suspendCoroutine {
        serverEventBus.onopen = {
        }
//        serverEventBus.onerror = { err: Any ->
//            try {
//                console.error(err);
//            } catch (e: Throwable) {
//                // dev tools are disabled so we cannot use console on IE
//            }
//        }
//        serverEventBus.onopen = {
//        }
    }
}
