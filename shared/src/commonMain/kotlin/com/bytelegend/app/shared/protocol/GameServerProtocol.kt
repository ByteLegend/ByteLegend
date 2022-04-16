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
package com.bytelegend.app.shared.protocol

import com.bytelegend.app.shared.entities.SceneInitData

/**
 * Upon switching to a new scene, get all online players and missions/states on a map.
 */
const val GET_SCENE_INIT_DATA = "protocol.get.scene.init.data"

/**
 * Player move to another point
 */
const val MOVE_TO = "protocol.move.to"
const val ENTER_SCENE = "protocol.enter.scene"
const val SPEAK = "protocol.speak"

/***************** Events broadcast from server to client-side EventBus ***********************/
fun playerEnterSceneEvent(mapId: String) = "protocol.player.enter.$mapId"
fun playerLeaveSceneEvent(mapId: String) = "protocol.player.leave.$mapId"
fun playerMoveOnSceneEvent(mapId: String) = "protocol.player.move.$mapId"
fun playerSpeechEvent(mapId: String) = "protocol.player.speech.$mapId"

/**
 * Periodically get online player number
 */
const val ONLINE_COUNTER_UPDATE_EVENT = "protocol.online.counter"

/***************** Point-to-point events from server to client-side EventBus ***********************/
const val STAR_UPDATE_EVENT = "protocol.star.update"
const val COIN_UPDATE_EVENT = "protocol.coin.update"
const val MISSION_TUTORIALS_UNLOCKED_EVENT = "protocol.mission.tutorials.unlocked"
const val REPUTATION_UPDATE_EVENT = "protocol.reputation.update"
const val ITEM_UPDATE_EVENT = "protocol.item.update"
const val ACHIEVEMENT_UPDATE_EVENT = "protocol.achievement.update"
fun challengeUpdateEvent(mapId: String) = "protocol.challenge.update.$mapId"
val CHALLENGE_UPDATE_EVENT_PREFIX = challengeUpdateEvent("")
val PLAYER_SPEECH_EVENT_PREFIX = playerSpeechEvent("")
const val PUT_STATE_EVENT = "protocol.put.state"
const val PROTOCOL_PAY_AND_ENTER_SCENE = "protocol.pay.and.enter.scene"
const val KICK_OFF_EVENT = "protocol.kick.off"

fun logStreamEvent(mapId: String) = "protocol.log.stream.$mapId"
val LOG_STREAM_EVENT_PREFIX = logStreamEvent("")

interface GameServerProtocol {
    /**
     * Upon a scene's initialization, client requests all
     * online players' data on a map, not including the requesting player.
     */
    suspend fun getSceneInitData(mapId: String): SceneInitData

    suspend fun moveTo(map: String, x: Int, y: Int)

    // TODO verify frontend input
    suspend fun putState(key: String, value: String)
    suspend fun switchScene(destMapId: String)
    suspend fun speak(sentenceId: String)
}

const val DEFAULT_REPLY_TIMEOUT_SECONDS = 10

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
data class ReplyMessage<T>(
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
