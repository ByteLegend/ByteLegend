package com.bytelegend.app.shared.protocol

import com.bytelegend.app.shared.entities.Player

fun playerEnterSceneEvent(mapId: String) = "protocol.player.enter.${mapId}"
fun playerLeaveSceneEvent(mapId: String) = "protocol.player.leave.${mapId}"
fun playerMoveOnSceneEvent(mapId: String) = "protocol.player.move.${mapId}"

/**
 * Upon switching to a new scene, get all online players.
 */
const val GET_ONLINE_PLAYERS = "protocol.get.online.players"

/**
 * Player move to another point
 */
const val MOVE_TO = "protocol.move.to"

/**
 * Periodically get online player number
 */
const val ONLINE_COUNTER_UPDATE_EVENT = "protocol.online.counter"

interface GameServerProtocol {
    /**
     * Upon a scene's initialization, client requests all
     * online players' data on a map, not including the requesting player.
     */
    suspend fun getOnlineNonAnonymousPlayers(mapId: String): List<Player>

    suspend fun moveTo(x: Int, y: Int)
}

interface WebSocketMessage {
    val type: WebSocketMessageType
}

data class SubscribeUnsubscribeMessage(
    override val type: WebSocketMessageType,
    /**
     * The event name to subscribe/unsubscribe
     */
    val event: String
) : WebSocketMessage

data class SendMessage(
    val name: String,
    val params: List<String>,
    val replyAddress: String
) : WebSocketMessage {
    override val type: WebSocketMessageType = WebSocketMessageType.SEND
}

/**
 * When type == REPLY, payload is the return value
 * when type == REPLY_ERROR, payload is the error message
 */
data class ReplyMessage(
    override val type: WebSocketMessageType,
    val replyAddress: String,
    val payload: Any
) : WebSocketMessage


data class PublishMessage(
    val event: String,
    val payload: Any
) : WebSocketMessage {
    override val type: WebSocketMessageType = WebSocketMessageType.PUBLISH
}

enum class WebSocketMessageType {
    /**
     * Game client subscribes an event, e.g. other player movement event.
     */
    SUBSCRIBE,
    UNSUBSCRIBE,

    /**
     * Game client invokes a sever-side method.
     */
    SEND,
    REPLY,
    REPLY_ERROR,

    /**
     * Server publishes an event subscribed by client previously.
     */
    PUBLISH
}