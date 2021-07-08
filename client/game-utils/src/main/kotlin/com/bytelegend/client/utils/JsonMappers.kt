@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.client.utils

import com.bytelegend.app.shared.GameInitData
import com.bytelegend.app.shared.GameMapDefinition
import com.bytelegend.app.shared.entities.BasePlayer
import com.bytelegend.app.shared.entities.ChallengeTabData
import com.bytelegend.app.shared.entities.DiscussionsTabData
import com.bytelegend.app.shared.entities.MissionModalData
import com.bytelegend.app.shared.entities.MissionTabData
import com.bytelegend.app.shared.entities.MissionTabType.Discussions
import com.bytelegend.app.shared.entities.MissionTabType.NoticeboardChallenge
import com.bytelegend.app.shared.entities.MissionTabType.PullRequestChallenge
import com.bytelegend.app.shared.entities.MissionTabType.QuestionChallenge
import com.bytelegend.app.shared.entities.MissionTabType.StarChallenge
import com.bytelegend.app.shared.entities.MissionTabType.Tutorials
import com.bytelegend.app.shared.entities.MissionTabType.valueOf
import com.bytelegend.app.shared.entities.Player
import com.bytelegend.app.shared.entities.PlayerMission
import com.bytelegend.app.shared.entities.PlayerMissionAnswer
import com.bytelegend.app.shared.entities.SceneInitData
import com.bytelegend.app.shared.entities.TutorialsTabData
import com.bytelegend.app.shared.entities.mission.ChallengeSpec
import com.bytelegend.app.shared.entities.mission.ChallengeType
import com.bytelegend.app.shared.entities.mission.DiscussionsSpec
import com.bytelegend.app.shared.entities.mission.OnFinishItemsChange
import com.bytelegend.app.shared.entities.mission.OnFinishSpec
import com.bytelegend.app.shared.entities.mission.OnFinishStatesChange
import com.bytelegend.app.shared.entities.mission.Pagination
import com.bytelegend.app.shared.entities.mission.Tutorial
import com.bytelegend.app.shared.enums.ServerLocation
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.app.shared.i18n.LocalizedText
import com.bytelegend.app.shared.i18n.LocalizedTextFormat
import com.bytelegend.app.shared.protocol.ITEMS_STATES_UPDATE_EVENT
import com.bytelegend.app.shared.protocol.ItemsStatesUpdateEventData
import com.bytelegend.app.shared.protocol.KICK_OFF_EVENT
import com.bytelegend.app.shared.protocol.KickOffEventData
import com.bytelegend.app.shared.protocol.LOG_STREAM_EVENT_PREFIX
import com.bytelegend.app.shared.protocol.LogStreamEventData
import com.bytelegend.app.shared.protocol.MISSION_UPDATE_EVENT_PREFIX
import com.bytelegend.app.shared.protocol.MissionUpdateEventData
import com.bytelegend.app.shared.protocol.ONLINE_COUNTER_UPDATE_EVENT
import com.bytelegend.app.shared.protocol.STAR_UPDATE_EVENT
import com.bytelegend.app.shared.protocol.StarUpdateEventData

@Suppress("UnsafeCastFromDynamic")
fun parseServerEvent(eventMessage: dynamic): Any {
    val event: String = eventMessage.event
    return when {
        event.startsWith("protocol.player") -> toBasePlayer(eventMessage.payload)
        event == ONLINE_COUNTER_UPDATE_EVENT -> eventMessage.payload
        event == STAR_UPDATE_EVENT -> toStarUpdateEventData(eventMessage.payload)
        event == ITEMS_STATES_UPDATE_EVENT -> toItemsStatesUpdateEventData(eventMessage.payload)
        event == KICK_OFF_EVENT -> toKickOffEventData(eventMessage.payload)
        event.startsWith(LOG_STREAM_EVENT_PREFIX) -> toLogStreamEventData(eventMessage.payload)
        event.startsWith(MISSION_UPDATE_EVENT_PREFIX) -> toMissionUpdateEventData(eventMessage.payload)
        else -> throw IllegalStateException("Unsupported event: $event")
    }
}

fun toLogStreamEventData(jsonObject: dynamic) = LogStreamEventData(
    jsonObject.last,
    jsonObject.mapId,
    jsonObject.missionId,
    jsonObject.missionAnswer,
    jsonObject.checkRunId,
    JSArrayBackedList(delegate = jsonObject.lines)
)

fun toKickOffEventData(jsonObject: dynamic) = KickOffEventData(
    jsonObject.playerId,
    jsonObject.reason
)

fun toItemsStatesUpdateEventData(jsonObject: dynamic) = ItemsStatesUpdateEventData(
    jsonObject.playerId,
    jsonObject.missionId,
    toOnFinishSpec(jsonObject.onFinishSpec)
)

fun toOnFinishSpec(jsonObject: dynamic) = OnFinishSpec(
    toOnFinishItemsChange(jsonObject.items),
    toOnFinishStatesChange(jsonObject.states)
)

fun toOnFinishItemsChange(jsonObject: dynamic) = OnFinishItemsChange(
    JSArrayBackedList(delegate = jsonObject.add),
    JSArrayBackedList(delegate = jsonObject.remove)
)

fun toOnFinishStatesChange(jsonObject: dynamic) = OnFinishStatesChange(
    JSObjectBackedMap(delegate = jsonObject.put),
    JSArrayBackedList(delegate = jsonObject.remove)
)

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
    configureBasePlayer(jsonObject)
    star = jsonObject.star
    coin = jsonObject.coin
    reputation = jsonObject.reputation
    locale = jsonObject.locale
    avatarUrl = jsonObject.avatarUrl
    items = JSArrayBackedList(delegate = jsonObject.items)
    states = JSObjectBackedMap(jsonObject.states)
}

private fun BasePlayer.configureBasePlayer(jsonObject: dynamic) {
    this.id = jsonObject.id
    this.username = jsonObject.username
    this.nickname = jsonObject.nickname
    this.map = jsonObject.map
    this.x = jsonObject.x
    this.y = jsonObject.y
    this.characterId = jsonObject.characterId
    this.server = jsonObject.server
}

fun toBasePlayer(jsonObject: dynamic) = BasePlayer().apply { configureBasePlayer(jsonObject) }

fun <T> toTypedList(jsonArray: dynamic, mapper: (dynamic) -> T): MutableList<T> {
    val ret = JSArrayBackedList<T>()
    jsonArray.unsafeCast<Array<dynamic>>().forEach {
        ret.add(mapper(it))
    }
    return ret
}

fun <T> toTypedMap(jsonObject: dynamic, valueMapper: (dynamic) -> T): Map<String, T> {
    return JSObjectBackedMap<dynamic>(jsonObject).apply {
        entries.forEach {
            it.setValue(valueMapper(it.value))
        }
    }
}

fun toSceneInitData(heroId: String, jsonObject: dynamic) = SceneInitData(
    jsonObject.online as Int,
    toTypedList(jsonObject.players, ::toBasePlayer).filter { it.id != heroId },
    toTypedMap(jsonObject.missions, ::toMission)
)

fun toMissionModalData(jsonObject: dynamic) = MissionModalData(
    toTypedList(jsonObject.tabs, ::toMissionTabData)
)

fun toPlayerMission(jsonObject: dynamic) = PlayerMission(
    jsonObject.playerId,
    jsonObject.missionId,
    jsonObject.map,
    toTypedList(jsonObject.answers, ::toMissionAnswer).asDynamic()
)

fun toMissionTabData(jsonObject: dynamic): MissionTabData<*> {
    return when (valueOf(jsonObject.type)) {
        QuestionChallenge,
        StarChallenge,
        PullRequestChallenge,
        NoticeboardChallenge -> ChallengeTabData(
            toChallengeSpec(jsonObject.data)
        )
        Tutorials -> TutorialsTabData(
            toPagination(jsonObject.data, ::toTutorial),
            toTypedList(jsonObject.locales) { Locale.of(it) }
        )
        Discussions -> DiscussionsTabData(
            toDiscussionsSpec(jsonObject.data)
        )
        else -> throw IllegalArgumentException()
    }
}

fun toChallengeSpec(jsonObject: dynamic): ChallengeSpec {
    return ChallengeSpec(
        ChallengeType.valueOf(jsonObject.type),
        jsonObject.star,
        jsonObject.spec,
        jsonObject.tldr,
        jsonObject.readme
    )
}

fun toDiscussionsSpec(jsonObject: dynamic): DiscussionsSpec {
    return DiscussionsSpec(
        jsonObject.url
    )
}

fun <T> toPagination(jsonObject: dynamic, mapper: (dynamic) -> T): Pagination<T> {
    return Pagination(
        toTypedList(jsonObject.items, mapper),
        jsonObject.totalPages,
        jsonObject.pageNumber,
        jsonObject.pageSize,
    )
}

fun toTutorial(jsonObject: dynamic): Tutorial {
    return Tutorial(
        jsonObject.id,
        jsonObject.title,
        jsonObject.type,
        jsonObject.href,
        toTypedList(jsonObject.languages) { Locale.of(it) }
    )
}

fun toMission(jsonObject: dynamic) = PlayerMission(
    playerId = jsonObject.playerId,
    missionId = jsonObject.missionId,
    map = jsonObject.map,
    answers = toTypedList(jsonObject.answers, ::toMissionAnswer)
)

fun toMissionAnswer(jsonObject: dynamic) = PlayerMissionAnswer(
    star = jsonObject.star,
    answer = jsonObject.answer,
    accomplished = jsonObject.accomplished.toString() == "true",
    createdAt = jsonObject.createdAt,
    data = JSObjectBackedMap(jsonObject.data)
)

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
