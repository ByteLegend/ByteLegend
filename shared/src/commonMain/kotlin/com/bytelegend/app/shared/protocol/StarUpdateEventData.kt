package com.bytelegend.app.shared.protocol

import com.bytelegend.app.shared.entities.MissionAnswer
import com.bytelegend.app.shared.entities.PlayerMission
import com.bytelegend.app.shared.entities.mission.OnFinishSpec

interface UnicastEventData {
    val playerId: String
}

data class StarUpdateEventData(
    override val playerId: String,
    val map: String,
    val missionId: String,
    val change: Int,
    val newValue: Int
) : UnicastEventData

data class MissionUpdateEventData(
    override val playerId: String,
    val map: String,
    val change: MissionAnswer,
    val newValue: PlayerMission
) : UnicastEventData

data class ItemsStatesUpdateEventData(
    override val playerId: String,
    val missionId: String,
    val onFinishSpec: OnFinishSpec
) : UnicastEventData
