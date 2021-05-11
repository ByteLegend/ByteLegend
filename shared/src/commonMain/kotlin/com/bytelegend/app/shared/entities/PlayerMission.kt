package com.bytelegend.app.shared.entities

import com.bytelegend.app.shared.annotations.DynamoDbBean
import com.bytelegend.app.shared.annotations.DynamoDbIgnore

open class PlayerMission {
    /**
     * Mission id for human.
     */
    @get: DynamoDbIgnore
    var id: String? = null

    @get: DynamoDbIgnore
    var playerId: String? = null

    @get: DynamoDbIgnore
    var map: String? = null

    var answers: MutableList<MissionAnswer> = mutableListOf()

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
 * An answer can be updated after created, or immutable after created.
 */
@DynamoDbBean
class MissionAnswer {
    var star: Int = 0
    var answer: String? = null
    var accomplished: Boolean = false

    // Epoch ms
    var createdAt: Long = 0

    companion object {
        fun create(star: Int, answer: String, accomplished: Boolean, epochMs: Long) = MissionAnswer().apply {
            this.star = star
            this.answer = answer
            this.accomplished = accomplished
            this.createdAt = epochMs
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MissionAnswer

        if (star != other.star) return false
        if (answer != other.answer) return false
        if (accomplished != other.accomplished) return false

        return true
    }

    override fun hashCode(): Int {
        var result = star
        result = 31 * result + (answer?.hashCode() ?: 0)
        result = 31 * result + accomplished.hashCode()
        return result
    }
}

class SceneInitData(
    val players: List<Player>,
    val missions: Map<String, PlayerMission>
)

