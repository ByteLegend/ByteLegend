@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.client.app.web

import com.bytelegend.app.client.api.JSArrayBackedList
import com.bytelegend.app.client.api.JSObjectBackedMap
import com.bytelegend.app.shared.GameInitData
import com.bytelegend.app.shared.GameMapDefinition
import com.bytelegend.app.shared.entities.ChallengeTabData
import com.bytelegend.app.shared.entities.DiscussionsTabData
import com.bytelegend.app.shared.entities.MissionAnswer
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
import com.bytelegend.app.shared.entities.SceneInitData
import com.bytelegend.app.shared.entities.TutorialsTabData
import com.bytelegend.app.shared.entities.mission.ChallengeSpec
import com.bytelegend.app.shared.entities.mission.ChallengeType
import com.bytelegend.app.shared.entities.mission.DiscussionsSpec
import com.bytelegend.app.shared.entities.mission.Pagination
import com.bytelegend.app.shared.entities.mission.Tutorial
import com.bytelegend.app.shared.entities.mission.TutorialType
import com.bytelegend.app.shared.enums.ServerLocation
import com.bytelegend.app.shared.i18n.Locale
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
    toTypedList(jsonObject.tabs, ::toMissionTabData)
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
        TutorialType(jsonObject.type.type, jsonObject.type.subtype),
        jsonObject.href,
        toTypedList(jsonObject.languages) { Locale.of(it) }
    )
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
