@file:Suppress("UnsafeCastFromDynamic", "ConvertToStringTemplate")

package com.bytelegend.client.app.web

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.ExpensiveResource
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.JSObjectBackedMap
import com.bytelegend.app.shared.entities.SceneInitData
import com.bytelegend.app.shared.protocol.ADD_ITEM
import com.bytelegend.app.shared.protocol.ENTER_SCENE
import com.bytelegend.app.shared.protocol.GET_SCENE_INIT_DATA
import com.bytelegend.app.shared.protocol.GameServerProtocol
import com.bytelegend.app.shared.protocol.LEAVE_SCENE
import com.bytelegend.app.shared.protocol.MOVE_TO
import com.bytelegend.app.shared.protocol.ONLINE_COUNTER_UPDATE_EVENT
import com.bytelegend.app.shared.protocol.PUT_STATE
import com.bytelegend.app.shared.protocol.PublishMessage
import com.bytelegend.app.shared.protocol.REMOVE_ITEM
import com.bytelegend.app.shared.protocol.REMOVE_STATE
import com.bytelegend.app.shared.protocol.ReplyMessage
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
import com.bytelegend.client.app.engine.GAME_UI_UPDATE_EVENT
import com.bytelegend.client.app.obj.uuid
import kotlinext.js.js
import kotlinx.browser.window
import kotlinx.coroutines.Deferred
import org.kodein.di.DI
import org.kodein.di.instance
import org.w3c.dom.WebSocket
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

const val DEFAULT_AUTO_RECONNECT_ATTEMPTS = 3

// Start reconnecting upon network issues
const val GAME_SERVER_RECONNECTING_START = "game.server.reconnecting.start"

// Successfully reconnected
const val GAME_SERVER_RECONNECTING_SUCCESS = "game.server.reconnecting.success"

// Failed after {DEFAULT_AUTO_RECONNECT_ATTEMPTS} attempts
const val GAME_SERVER_RECONNECTING_FAIL = "game.server.reconnecting.fail"

typealias ReplyHandler = (ReplyMessage<Any>) -> Unit

const val DEFAULT_REPLY_TIMEOUT_SECONDS = 10

/**
 * Web socket is a global resource.
 * Player data of each scene is a per-scene resource.
 */
class WebSocketClient(
    private val di: DI
) : ExpensiveResource<WebSocketClient>, GameServerProtocol {
    override val id: String = "websocket-client"
    override val weight: Int = 1

    private val eventBus: EventBus by di.instance()
    private val gameRuntime: GameRuntime by di.instance()
    lateinit var self: Deferred<WebSocketClient>
    var connected = false

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
            connected = false
            console.warn("Server closed WS connection unexpectedly: ${JSON.stringify(it)}")
            gameRuntime.bannerController.showWarningBanner(gameRuntime.i("SeemsToBeDisconnectedFromServer"))
            eventBus.emit(GAME_UI_UPDATE_EVENT, null)
        }
    }

    override suspend fun getSceneInitData(mapId: String): SceneInitData {
        return toSceneInitData(send(GET_SCENE_INIT_DATA, mapId))
    }

    override suspend fun moveTo(x: Int, y: Int) {
        send<Unit>(MOVE_TO, x.toString(), y.toString())
    }

    override suspend fun putState(key: String, value: String) {
        send<Unit>(PUT_STATE, key, value)
    }

    override suspend fun removeState(key: String) {
        send<Unit>(REMOVE_STATE, key)
    }

    override suspend fun addItem(item: String) {
        send<Unit>(ADD_ITEM, item)
    }

    override suspend fun removeItem(item: String) {
        send<Unit>(REMOVE_ITEM, item)
    }

    override suspend fun leaveScene(srcMapId: String, destMapId: String) {
        send<Unit>(LEAVE_SCENE, srcMapId, destMapId)
    }

    override suspend fun enterScene(srcMapId: String, destMapId: String) {
        send<Unit>(ENTER_SCENE, srcMapId, destMapId)
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
    private val mapId: String,
    private val client: WebSocketClient
//    private val eventBus: EventBus,
//    private val resourceLoader: ResourceLoader
) : ExpensiveResource<SceneInitData> {
    override val id: String = "$mapId-players"
    override val weight: Int = 1

    override suspend fun load(): SceneInitData {
        return client.self.await().getSceneInitData(mapId)
//        val players = PlayerContainer(mapId, eventBus, client, resourceLoader, data.players).apply { init() }
//        val missions = DefaultMissionContainer(data.missions)
//        val states = StateContainer(data.states)
//        return Triple(players, missions, states)
    }
}
