package com.bytelegend.app.shared.protocol

import com.bytelegend.app.shared.entities.MissionModalData
import com.bytelegend.app.shared.entities.SceneInitData


/**
 * Upon switching to a new scene, get all online players and missions/states on a map.
 */
const val GET_SCENE_INIT_DATA = "protocol.get.scene.init.data"
const val GET_MISSION_MODAL_DATA = "protocol.get.mission.modal.data"

/**
 * Player move to another point
 */
const val MOVE_TO = "protocol.move.to"


/***************** Events broadcast from server to client-side EventBus ***********************/
fun playerEnterSceneEvent(mapId: String) = "protocol.player.enter.${mapId}"
fun playerLeaveSceneEvent(mapId: String) = "protocol.player.leave.${mapId}"
fun playerMoveOnSceneEvent(mapId: String) = "protocol.player.move.${mapId}"
/**
 * Periodically get online player number
 */
const val ONLINE_COUNTER_UPDATE_EVENT = "protocol.online.counter"

/***************** Point-to-point events from server to client-side EventBus ***********************/
const val STAR_UPDATE_EVENT = "protocol.star.update"
const val MISSION_UPDATE_EVENT = "protocol.mission.udpate"

interface GameServerProtocol {
    /**
     * Upon a scene's initialization, client requests all
     * online players' data on a map, not including the requesting player.
     */
    suspend fun getSceneInitData(mapId: String): SceneInitData

    suspend fun moveTo(x: Int, y: Int)

    suspend fun getMissionModalData(missionId: String): MissionModalData
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
class ReplyMessage<T>(
    override val type: WebSocketMessageType,
    val replyAddress: String,
    val payload: T
) : WebSocketMessage


data class PublishMessage<T>(
    val event: String,
    val payload: T
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