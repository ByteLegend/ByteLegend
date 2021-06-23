package com.bytelegend.app.shared.protocol

import com.bytelegend.app.shared.entities.PlayerMission
import com.bytelegend.app.shared.entities.PlayerMissionAnswer
import com.bytelegend.app.shared.entities.mission.OnFinishSpec

interface ServerAware {
    val server: Int
}

interface UnicastEventData {
    val playerId: String
}

data class StarUpdateEventData(
    override val playerId: String,
    val map: String,
    val missionId: String,
    val change: Int,
    val newValue: Int,
    override val server: Int = 0
) : UnicastEventData, ServerAware

data class MissionUpdateEventData(
    override val playerId: String,
    val map: String,
    val change: PlayerMissionAnswer,
    val newValue: PlayerMission,
    override val server: Int = 0
) : UnicastEventData, ServerAware

data class ItemsStatesUpdateEventData(
    override val playerId: String,
    val missionId: String,
    val onFinishSpec: OnFinishSpec,
    override val server: Int = 0
) : UnicastEventData, ServerAware

data class KickOffEventData(
    override val playerId: String,
    val reason: String
) : UnicastEventData
