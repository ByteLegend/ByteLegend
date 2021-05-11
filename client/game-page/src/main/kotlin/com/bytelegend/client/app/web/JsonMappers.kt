@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.client.app.web

import com.bytelegend.app.client.api.JSArrayBackedList
import com.bytelegend.app.client.api.JSObjectBackedMap
import com.bytelegend.app.shared.GameInitData
import com.bytelegend.app.shared.GameMapDefinition
import com.bytelegend.app.shared.entities.MissionAnswer
import com.bytelegend.app.shared.entities.MissionModalData
import com.bytelegend.app.shared.entities.MissionTab
import com.bytelegend.app.shared.entities.MissionTabType
import com.bytelegend.app.shared.entities.Player
import com.bytelegend.app.shared.entities.PlayerMission
import com.bytelegend.app.shared.entities.SceneInitData
import com.bytelegend.app.shared.enums.ServerLocation
import com.bytelegend.app.shared.i18n.LocalizedText
import com.bytelegend.app.shared.i18n.LocalizedTextFormat
import com.bytelegend.app.shared.protocol.MISSION_UPDATE_EVENT
import com.bytelegend.app.shared.protocol.MissionUpdateEventData
import com.bytelegend.app.shared.protocol.ONLINE_COUNTER_UPDATE_EVENT
import com.bytelegend.app.shared.protocol.STAR_UPDATE_EVENT
import com.bytelegend.app.shared.protocol.StarUpdateEventData

// PublishMessage<Any>
@Suppress("UnsafeCastFromDynamic")
fun parseServerEvent(eventMessage: dynamic): Any {
    val event: String = eventMessage.event
    return when {
        event.startsWith("protocol.player") -> toPlayer(eventMessage.payload)
        event == ONLINE_COUNTER_UPDATE_EVENT -> eventMessage.payload
        event == STAR_UPDATE_EVENT -> toStarUpdateEventData(eventMessage.payload)
        event == MISSION_UPDATE_EVENT -> toMissionUpdateEventData(eventMessage.payload)
        else -> throw IllegalStateException("Unsupported event: $event")
    }
}

fun toStarUpdateEventData(jsonObject: dynamic) = StarUpdateEventData(
    jsonObject.playerId,
    jsonObject.map,
    jsonObject.missionId,
    jsonObject.change,
    jsonObject.newValue
)

fun toMissionUpdateEventData(jsonObject: dynamic) = MissionUpdateEventData(
    jsonObject.playerId,
    jsonObject.map,
    toMissionAnswer(jsonObject.change),
    toMission(jsonObject.newValue)
)

fun toPlayer(jsonObject: dynamic) = Player().apply {
    id = jsonObject.id
    username = jsonObject.username
    nickname = jsonObject.nickname
    map = jsonObject.map
    x = jsonObject.x
    y = jsonObject.y
    server = jsonObject.server
    star = jsonObject.star
    coin = jsonObject.coin
    reputation = jsonObject.reputation
    locale = jsonObject.locale
    characterId = jsonObject.characterId
    avatarUrl = jsonObject.avatarUrl
    items = JSArrayBackedList(delegate = jsonObject.items)
    states = JSObjectBackedMap(jsonObject.states)
}

fun <T> toTypedList(jsonArray: dynamic, mapper: (dynamic) -> T): List<T> {
    return jsonArray.unsafeCast<Array<dynamic>>().map(mapper)
}

fun <T> toTypedMap(jsonObject: dynamic, valueMapper: (dynamic) -> T): Map<String, T> {
    return JSObjectBackedMap<dynamic>(jsonObject).apply {
        entries.forEach {
            it.setValue(valueMapper(it.value))
        }
    }
}

fun toSceneInitData(jsonObject: dynamic) = SceneInitData(
    toTypedList(jsonObject.players, ::toPlayer),
    toTypedMap(jsonObject.missions, ::toMission)
)

fun toMissionModalData(jsonObject: dynamic) = MissionModalData(
    toTypedList(jsonObject.tabs, ::toMissionTab)
)

fun toMissionTab(jsonObject: dynamic): MissionTab {
    val type = MissionTabType.valueOf(jsonObject.type)
    return MissionTab(
        type,
        jsonObject.title,
        toMissionTabData(type, jsonObject.data)
    )
}

fun toMissionTabData(tabType: MissionTabType, jsonObject: dynamic): Any {
    return when (tabType) {
        MissionTabType.QuestionChallenge -> ""
        MissionTabType.StarChallenge -> ""
        MissionTabType.PRChallenge -> ""
        MissionTabType.NoticeboardChallenge -> ""
        MissionTabType.Tutorial -> ""
        MissionTabType.Discussion -> ""
        else -> throw IllegalStateException()
    }
}

fun toMission(jsonObject: dynamic) = PlayerMission().apply {
    id = jsonObject.id
    playerId = jsonObject.id
    map = jsonObject.map
    jsonObject.answers.unsafeCast<Array<dynamic>>().forEach { answers.add(it) }
}

fun toMissionAnswer(jsonObject: dynamic) = MissionAnswer().apply {
    star = jsonObject.star
    answer = jsonObject.answer
    accomplished = jsonObject.accomplished
    createdAt = jsonObject.createdAt
}

fun toGameMapDefinition(jsonObject: dynamic): GameMapDefinition = GameMapDefinition(
    jsonObject.id,
    jsonObject.frames,
    toTypedList(jsonObject.children, ::toGameMapDefinition)
)

fun toGameInitData(jsonObject: dynamic) = GameInitData(
    jsonObject.initMapId,
    jsonObject.onlineCount,
    ServerLocation.valueOf(jsonObject.serverLocation),
    jsonObject.rrbd,
    jsonObject.enjoyProgrammingText,
    toPlayer(jsonObject.player),
    toTypedList(jsonObject.maps, ::toGameMapDefinition),
    toTypedList(jsonObject.localizedTexts, ::toLocalizedText),
)

fun toLocalizedText(jsonObject: dynamic) = LocalizedText(
    jsonObject.id,
    toTypedMap(jsonObject.data) { it },
    LocalizedTextFormat.valueOf(jsonObject.format)
)
