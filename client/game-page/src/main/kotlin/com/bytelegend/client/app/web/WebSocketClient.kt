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
@file:Suppress("UnsafeCastFromDynamic", "ConvertToStringTemplate")

package com.bytelegend.client.app.web

import com.bytelegend.app.client.api.Banner
import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.ExpensiveResource
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.misc.uuid
import com.bytelegend.app.client.utils.JSObjectBackedMap
import com.bytelegend.client.utils.parseServerEvent
import com.bytelegend.client.utils.toSceneInitData
import com.bytelegend.app.shared.entities.SceneInitData
import com.bytelegend.app.shared.protocol.DEFAULT_REPLY_TIMEOUT_SECONDS
import com.bytelegend.app.shared.protocol.ENTER_SCENE
import com.bytelegend.app.shared.protocol.GET_SCENE_INIT_DATA
import com.bytelegend.app.shared.protocol.GameServerProtocol
import com.bytelegend.app.shared.protocol.KICK_OFF_EVENT
import com.bytelegend.app.shared.protocol.KickOffEventData
import com.bytelegend.app.shared.protocol.MOVE_TO
import com.bytelegend.app.shared.protocol.ONLINE_COUNTER_UPDATE_EVENT
import com.bytelegend.app.shared.protocol.PUT_STATE_EVENT
import com.bytelegend.app.shared.protocol.PublishMessage
import com.bytelegend.app.shared.protocol.ReplyMessage
import com.bytelegend.app.shared.protocol.SPEAK
import com.bytelegend.app.shared.protocol.SendMessage
import com.bytelegend.app.shared.protocol.SubscribeUnsubscribeMessage
import com.bytelegend.app.shared.protocol.WebSocketMessage
import com.bytelegend.app.shared.protocol.WebSocketMessageType
import com.bytelegend.app.shared.protocol.WebSocketMessageType.PUBLISH
import com.bytelegend.app.shared.protocol.WebSocketMessageType.REPLY
import com.bytelegend.app.shared.protocol.WebSocketMessageType.REPLY_ERROR
import com.bytelegend.app.shared.protocol.WebSocketMessageType.SEND
import com.bytelegend.app.shared.protocol.WebSocketMessageType.SUBSCRIBE
import com.bytelegend.app.shared.protocol.WebSocketMessageType.UNSUBSCRIBE
import com.bytelegend.app.shared.util.currentTimeMillis
import com.bytelegend.client.app.engine.GAME_UI_UPDATE_EVENT
import com.bytelegend.client.app.obj.isFirefox
import kotlinext.js.js
import kotlinx.browser.window
import kotlinx.coroutines.Deferred
import org.kodein.di.DI
import org.kodein.di.instance
import org.w3c.dom.WebSocket
import org.w3c.dom.events.Event
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

typealias ReplyHandler = (ReplyMessage<Any>) -> Unit

/**
 * On firefox, when you click login button, before the page redirects to login page,
 * the WS will be disconnected and "You're offline" banner is displayed, which
 * confuses users. In this case, we delay the banner for 5s.
 */
val LOGIN_LINK_CLICKED_EVENT = "login.link.clicked"

/**
 * Web socket is a global resource.
 * Player data of each scene is a per-scene resource.
 */
class WebSocketClient(
    private val di: DI
) : ExpensiveResource<WebSocketClient>, GameServerProtocol {
    override val id: String = "websocket-client"
    private val eventBus: EventBus by di.instance()
    private val gameRuntime: GameRuntime by di.instance()
    lateinit var self: Deferred<WebSocketClient>
    var connected = false
    var offlineReasonId = "SeemsToBeDisconnectedFromServer"
    var loginButtonClickedTimeMs: Long = 0L

    private val replyHandlers: MutableMap<String, ReplyHandler> = JSObjectBackedMap()

    private val client: WebSocket by lazy {
        val protocol = if (window.location.protocol.startsWith("https")) "wss" else "ws"
        val port = if (window.location.port == "") "" else ":${window.location.port}"
        val url = "$protocol://${window.location.hostname}$port/game/server"
        WebSocket(url)
    }

    private fun init() {
        replyHandlers.clear()
        client.onmessage = {
            if (it.type == "message") {
                onMessage(it.data.unsafeCast<String>())
            } else {
                console.warn("Discard WS message with type: ${it.type}")
            }
        }
        subscribe(ONLINE_COUNTER_UPDATE_EVENT)
        eventBus.on(KICK_OFF_EVENT, this::onKickOff)
        eventBus.on<Nothing>(LOGIN_LINK_CLICKED_EVENT) {
            loginButtonClickedTimeMs = currentTimeMillis()
        }
    }

    private fun onKickOff(event: KickOffEventData) {
        offlineReasonId = event.reason
        client.close()
        connected = false
    }

    /**
     * Subscribe a server event on client event bus.
     * After subscription, server will redirects corresponding events to client side.
     */
    fun subscribe(event: String) {
        if (connected) {
            client.send(SubscribeUnsubscribeMessage(SUBSCRIBE, event).toJson())
        } else {
            console.warn("Skip subscribe() as we're disconnected.")
        }
    }

    fun unsubscribe(event: String) {
        if (connected) {
            client.send(SubscribeUnsubscribeMessage(UNSUBSCRIBE, event).toJson())
        } else {
            console.warn("Skip subscribe() as we're disconnected.")
        }
    }

    @Suppress("UnsafeCastFromDynamic")
    private suspend fun <T> send(name: String, vararg args: String): T {
        if (!connected) {
            throw IllegalStateException("Can't send as we're disconnected.")
        }

        return suspendCoroutine { continuation ->
            val uuid = uuid()
            replyHandlers[uuid] = {
                when (it.type) {
                    REPLY -> continuation.resume(it.payload.asDynamic())
                    REPLY_ERROR -> continuation.resumeWithException(IllegalStateException(it.payload.unsafeCast<String>()))
                    else -> console.error("Unexpected reply type: ${it.type}")
                }
                replyHandlers.remove(uuid)
            }

            window.setTimeout(
                {
                    if (replyHandlers[uuid] != null) {
                        console.error("Timeout invoking $name with ${JSON.stringify(args)}")
                        replyHandlers.remove(uuid)
                    }
                },
                DEFAULT_REPLY_TIMEOUT_SECONDS * 1000
            )

            client.send(SendMessage(name, args.toList(), uuid).toJson())
        }
    }

    private fun onMessage(json: String) {
        val wsMessage: WebSocketMessage = fromJson(json)
        when {
            wsMessage == UnrecognizedMessage -> console.warn("Discard unrecognized message: " + json)
            wsMessage.type == REPLY || wsMessage.type == REPLY_ERROR -> onServerReply(wsMessage.asDynamic())
            wsMessage.type == PUBLISH -> onServerPublishEvent(wsMessage.asDynamic())
        }
    }

    private fun onServerPublishEvent(eventMessage: dynamic /* PublishMessage<Any> */) {
        eventBus.emit(eventMessage.event, parseServerEvent(eventMessage))
    }

    private fun onServerReply(replyMessage: ReplyMessage<Any>) {
        val handler = replyHandlers[replyMessage.replyAddress]
        if (handler == null) {
            console.warn("Received a reply but can't find handler: ${JSON.stringify(replyMessage)}")
        } else {
            handler(replyMessage)
        }
    }

    /**
     * This is invoked first to get global init data, e.g. player information, mission data on map, etc.
     */
    override suspend fun load(): WebSocketClient = suspendCoroutine { continuation ->
        client.onopen = {
            connected = true
            init()
            continuation.resume(this)
        }
        client.onerror = {
            if (!connected) {
                continuation.resumeWithException(IllegalStateException("Can't connect to game server"))
            }
            connected = false
            console.error(it)
        }
        client.onclose = {
            if (isFirefox() && loginButtonClickedTimeMs <= currentTimeMillis() && currentTimeMillis() <= loginButtonClickedTimeMs + 5000) {
                window.setTimeout({
                    onClose(it)
                }, 5000)
            } else {
                onClose(it)
            }
        }
    }

    private fun onClose(event: Event) {
        connected = false
        console.warn("WS connection is closed: $offlineReasonId ${JSON.stringify(event)}")
        gameRuntime.bannerController.showBanner(
            Banner(
                gameRuntime.i(offlineReasonId),
                "warning"
            )
        )
        eventBus.emit(GAME_UI_UPDATE_EVENT, null)
    }

    override suspend fun getSceneInitData(mapId: String): SceneInitData {
        return toSceneInitData(gameRuntime.heroPlayer.id, send(GET_SCENE_INIT_DATA, mapId))
    }

    override suspend fun moveTo(map: String, x: Int, y: Int) {
        send<Unit>(MOVE_TO, map, x.toString(), y.toString())
    }

    override suspend fun putState(key: String, value: String) {
        send<Unit>(PUT_STATE_EVENT, key, value)
    }

    override suspend fun switchScene(destMapId: String) {
        send<Unit>(ENTER_SCENE, destMapId)
    }

    override suspend fun speak(sentenceId: String) {
        send<Unit>(SPEAK, sentenceId)
    }
}

fun WebSocketMessage.toJson(): String {
    return when (type) {
        SEND -> {
            val message = this.unsafeCast<SendMessage>()
            JSON.stringify(
                js {
                    this.name = message.name
                    this.params = message.params.toTypedArray()
                    this.replyAddress = message.replyAddress
                    this.type = type.toString()
                }
            )
        }
        SUBSCRIBE, UNSUBSCRIBE -> {
            val message = this.unsafeCast<SubscribeUnsubscribeMessage>()
            JSON.stringify(
                js {
                    this.type = type.toString()
                    this.event = message.event
                }
            )
        }
        else -> throw IllegalArgumentException("Unrecognized type: $type")
    }
}

fun fromJson(json: String): WebSocketMessage {
    val jsonObj = JSON.parse<dynamic>(json)
    if (jsonObj?.type == null) {
        return UnrecognizedMessage
    }
    return when (val type = WebSocketMessageType.valueOf(jsonObj.type)) {
        REPLY, REPLY_ERROR -> ReplyMessage(type, jsonObj.replyAddress, jsonObj.payload)
        PUBLISH -> PublishMessage(jsonObj.event, jsonObj.payload)
        else -> throw IllegalArgumentException("Unrecognized type: $type")
    }
}

object UnrecognizedMessage : WebSocketMessage {
    override val type: WebSocketMessageType
        get() = throw IllegalStateException("Unrecognized message!")
}

class GameSceneInitResource(
    private val playerId: String,
    private val mapId: String,
    private val client: WebSocketClient
) : ExpensiveResource<SceneInitData> {
    override val id: String = "$mapId-players"

    override suspend fun load(): SceneInitData {
        return client.self.await().getSceneInitData(mapId)
    }
}
