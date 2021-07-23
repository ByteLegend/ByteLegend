package com.bytelegend.app.shared.entities

import com.bytelegend.app.shared.annotations.DynamoDbIgnore
import com.bytelegend.app.shared.annotations.JsonIgnore
import com.bytelegend.app.shared.util.currentTimeMillis

/**
 * A PlayerChallenge instance includes all answers a player makes to a challenge.
 */
open class PlayerChallenge(
    @get: DynamoDbIgnore
    val playerId: String,

    @get: DynamoDbIgnore
    val map: String,

    @get: DynamoDbIgnore
    val missionId: String,

    @get: DynamoDbIgnore
    val challengeId: String,

    @get: DynamoDbIgnore
    open val answers: MutableList<PlayerChallengeAnswer>
) {

    @get: DynamoDbIgnore
    @get: JsonIgnore
    val accomplished: Boolean
        get() = answers.any { it.accomplished }

    @get: DynamoDbIgnore
    @get: JsonIgnore
    val star: Int
        get() = answers.maxOfOrNull { it.star } ?: 0
}

/**
 * Represents an abstract answer to a challenge.
 * It can be an answer from frontend,
 * or answer from GitHub webhook event.
 *
 * An answer is immutable after created.
 */
open class PlayerChallengeAnswer(
    /**
     * How many stars the player can get from this answer?
     */
    val star: Int,
    val accomplished: Boolean,
    val answer: String,
    val data: Map<String, String> = emptyMap(),
    // Epoch ms
    val createdAt: Long = currentTimeMillis()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as PlayerChallengeAnswer

        if (star != other.star) return false
        if (accomplished != other.accomplished) return false
        if (answer != other.answer) return false
        if (data != other.data) return false
        if (createdAt != other.createdAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = star
        result = 31 * result + accomplished.hashCode()
        result = 31 * result + answer.hashCode()
        result = 31 * result + data.hashCode()
        result = 31 * result + createdAt.hashCode()
        return result
    }
}

class SceneInitData(
    val online: Int,
    val players: List<BasePlayer>,
    val playerChallenges: Map<String, PlayerChallenge>
)
