package com.bytelegend.app.shared.protocol

import com.bytelegend.app.shared.entities.PlayerMission
import com.bytelegend.app.shared.entities.PlayerMissionAnswer
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

data class MissionUpdateEventData(
    override val playerId: String,
    val map: String,
    val change: PlayerMissionAnswer,
    val newValue: PlayerMission,
) : UnicastEventData

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
    val missionAnswer: String,
    val checkRunId: String,
    val lines: List<String>
)
