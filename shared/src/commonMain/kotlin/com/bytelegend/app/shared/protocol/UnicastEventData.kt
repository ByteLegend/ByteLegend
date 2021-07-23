package com.bytelegend.app.shared.protocol

import com.bytelegend.app.shared.annotations.JsonIgnore
import com.bytelegend.app.shared.entities.PlayerChallenge
import com.bytelegend.app.shared.entities.PlayerChallengeAnswer
import com.bytelegend.app.shared.entities.mission.OnFinishSpec

interface UnicastEventData {
    val playerId: String
}

data class StarUpdateEventData(
    override val playerId: String,
    val map: String,
    val missionId: String,
    val change: Int,
    val newValue: Int,
) : UnicastEventData

data class ChallengeUpdateEventData(
    val change: PlayerChallengeAnswer,
    val newValue: PlayerChallenge,
) : UnicastEventData {
    override val playerId: String
        @JsonIgnore
        get() = newValue.playerId
}

data class ItemsStatesUpdateEventData(
    override val playerId: String,
    val missionId: String,
    val onFinishSpec: OnFinishSpec,
) : UnicastEventData

data class KickOffEventData(
    override val playerId: String,
    val reason: String
) : UnicastEventData

data class LogStreamEventData(
    val last: Boolean?,
    val mapId: String,
    val missionId: String,
    val challengeId: String,
    val challengeAnswer: String,
    val checkRunId: String,
    val lines: List<String>
)
