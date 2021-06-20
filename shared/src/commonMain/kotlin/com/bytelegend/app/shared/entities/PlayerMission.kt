package com.bytelegend.app.shared.entities

import com.bytelegend.app.shared.annotations.DynamoDbIgnore
import com.bytelegend.app.shared.util.currentTimeMillis
import kotlin.jvm.JvmStatic

open class PlayerMission(
    @get: DynamoDbIgnore
    val playerId: String,

    @get: DynamoDbIgnore
    val missionId: String,

    @get: DynamoDbIgnore
    val map: String,

    @get: DynamoDbIgnore
    open val answers: MutableList<PlayerMissionAnswer>
) {

    @get: DynamoDbIgnore
    val accomplished: Boolean
        get() = answers.any { it.accomplished }

    @get: DynamoDbIgnore
    val star: Int
        get() = answers.maxOfOrNull { it.star } ?: 0
}

/**
 * Represents an abstract answer to a mission.
 * It can be an answer from frontend,
 * or answer from GitHub webhook event.
 *
 * An answer is immutable after created.
 */
open class PlayerMissionAnswer(
    /**
     * How many stars the player can get from this answer?
     */
    val star: Int,
    val accomplished: Boolean,
    val answer: String,
    val data: Map<String, String> = emptyMap(),
    // Epoch ms
    val createdAt: Long = currentTimeMillis()
)

class SceneInitData(
    val online: Int,
    val players: List<BasePlayer>,
    val missions: Map<String, PlayerMission>
)

