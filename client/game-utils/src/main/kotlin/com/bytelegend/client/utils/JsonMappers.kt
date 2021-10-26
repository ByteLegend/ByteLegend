/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.client.utils

import com.bytelegend.app.shared.GameInitData
import com.bytelegend.app.shared.GameMapDefinition
import com.bytelegend.app.shared.InvitationInformation
import com.bytelegend.app.shared.entities.BasePlayer
import com.bytelegend.app.shared.entities.ChallengeAnswer
import com.bytelegend.app.shared.entities.ChallengeAnswers
import com.bytelegend.app.shared.entities.ChallengeTabData
import com.bytelegend.app.shared.entities.DiscussionsTabData
import com.bytelegend.app.shared.entities.HeroNoticeboardTabData
import com.bytelegend.app.shared.entities.LivestreamData
import com.bytelegend.app.shared.entities.MissionModalData
import com.bytelegend.app.shared.entities.MissionTabData
import com.bytelegend.app.shared.entities.MissionTabType.Discussions
import com.bytelegend.app.shared.entities.MissionTabType.HeroNoticeboardChallenge
import com.bytelegend.app.shared.entities.MissionTabType.PullRequestChallenge
import com.bytelegend.app.shared.entities.MissionTabType.QuestionChallenge
import com.bytelegend.app.shared.entities.MissionTabType.StarChallenge
import com.bytelegend.app.shared.entities.MissionTabType.TextContentChallenge
import com.bytelegend.app.shared.entities.MissionTabType.Tutorials
import com.bytelegend.app.shared.entities.MissionTabType.valueOf
import com.bytelegend.app.shared.entities.Player
import com.bytelegend.app.shared.entities.SceneInitData
import com.bytelegend.app.shared.entities.TutorialsTabData
import com.bytelegend.app.shared.entities.mission.ChallengeSpec
import com.bytelegend.app.shared.entities.mission.ChallengeType
import com.bytelegend.app.shared.entities.mission.DiscussionsSpec
import com.bytelegend.app.shared.entities.mission.HeroNoticeboardTile
import com.bytelegend.app.shared.entities.mission.HeroNoticeboardTilesData
import com.bytelegend.app.shared.entities.mission.OnFinishItemsChange
import com.bytelegend.app.shared.entities.mission.OnFinishSpec
import com.bytelegend.app.shared.entities.mission.OnFinishStatesChange
import com.bytelegend.app.shared.entities.mission.Pagination
import com.bytelegend.app.shared.entities.mission.Tutorial
import com.bytelegend.app.shared.enums.ServerLocation
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.app.shared.i18n.LocalizedText
import com.bytelegend.app.shared.i18n.LocalizedTextFormat
import com.bytelegend.app.shared.protocol.ACHIEVEMENT_UPDATE_EVENT
import com.bytelegend.app.shared.protocol.AchievementUpdateEventData
import com.bytelegend.app.shared.protocol.CHALLENGE_UPDATE_EVENT_PREFIX
import com.bytelegend.app.shared.protocol.COIN_UPDATE_EVENT
import com.bytelegend.app.shared.protocol.ChallengeUpdateEventData
import com.bytelegend.app.shared.protocol.CoinUpdateEventData
import com.bytelegend.app.shared.protocol.ITEMS_STATES_UPDATE_EVENT
import com.bytelegend.app.shared.protocol.ItemsStatesUpdateEventData
import com.bytelegend.app.shared.protocol.KICK_OFF_EVENT
import com.bytelegend.app.shared.protocol.KickOffEventData
import com.bytelegend.app.shared.protocol.LOG_STREAM_EVENT_PREFIX
import com.bytelegend.app.shared.protocol.LogStreamEventData
import com.bytelegend.app.shared.protocol.ONLINE_COUNTER_UPDATE_EVENT
import com.bytelegend.app.shared.protocol.PLAYER_SPEECH_EVENT_PREFIX
import com.bytelegend.app.shared.protocol.PlayerSpeechEventData
import com.bytelegend.app.shared.protocol.REPUTATION_UPDATE_EVENT
import com.bytelegend.app.shared.protocol.ReputationUpdateEventData
import com.bytelegend.app.shared.protocol.STAR_UPDATE_EVENT
import com.bytelegend.app.shared.protocol.StarUpdateEventData

@Suppress("UnsafeCastFromDynamic")
fun parseServerEvent(eventMessage: dynamic): Any {
    val event: String = eventMessage.event
    return when {
        event.startsWith(PLAYER_SPEECH_EVENT_PREFIX) -> toPlayerSpeechEventData(eventMessage.payload)
        event.startsWith("protocol.player") -> toBasePlayer(eventMessage.payload)
        event == ONLINE_COUNTER_UPDATE_EVENT -> eventMessage.payload
        event == STAR_UPDATE_EVENT -> toStarUpdateEventData(eventMessage.payload)
        event == COIN_UPDATE_EVENT -> toCoinUpdateEventData(eventMessage.payload)
        event == REPUTATION_UPDATE_EVENT -> toReputationUpdateEventData(eventMessage.payload)
        event == ITEMS_STATES_UPDATE_EVENT -> toItemsStatesUpdateEventData(eventMessage.payload)
        event == ACHIEVEMENT_UPDATE_EVENT -> toAchievementUpdateEventData(eventMessage.payload)
        event == KICK_OFF_EVENT -> toKickOffEventData(eventMessage.payload)
        event.startsWith(LOG_STREAM_EVENT_PREFIX) -> toLogStreamEventData(eventMessage.payload)
        event.startsWith(CHALLENGE_UPDATE_EVENT_PREFIX) -> toChallengeUpdateEventData(eventMessage.payload)
        else -> throw IllegalStateException("Unsupported event: $event")
    }
}

fun toPlayerSpeechEventData(jsonObject: dynamic) = PlayerSpeechEventData(
    jsonObject.playerId,
    jsonObject.sentenceId
)

fun toLogStreamEventData(jsonObject: dynamic) = LogStreamEventData(
    jsonObject.last,
    jsonObject.mapId,
    jsonObject.missionId,
    jsonObject.challengeId,
    jsonObject.challengeAnswer,
    jsonObject.checkRunId,
    JSArrayBackedList(delegate = jsonObject.lines)
)

fun toKickOffEventData(jsonObject: dynamic) = KickOffEventData(
    jsonObject.playerId,
    jsonObject.reason
)

fun toItemsStatesUpdateEventData(jsonObject: dynamic) = ItemsStatesUpdateEventData(
    jsonObject.playerId,
    jsonObject.map,
    jsonObject.missionId,
    toOnFinishSpec(jsonObject.onFinishSpec)
)

fun toAchievementUpdateEventData(jsonObject: dynamic) = AchievementUpdateEventData(
    jsonObject.playerId,
    jsonObject.map,
    jsonObject.missionId,
    jsonObject.achievementId
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

fun toCoinUpdateEventData(jsonObject: dynamic) = CoinUpdateEventData(
    jsonObject.playerId,
    jsonObject.change,
    jsonObject.newValue,
    jsonObject.reasonId,
    jsonObject.reasonArgs
)

fun toReputationUpdateEventData(jsonObject: dynamic) = ReputationUpdateEventData(
    jsonObject.playerId,
    jsonObject.change,
    jsonObject.newValue,
    jsonObject.reasonId,
    jsonObject.reasonArgs
)

fun toStarUpdateEventData(jsonObject: dynamic) = StarUpdateEventData(
    jsonObject.playerId,
    jsonObject.map,
    jsonObject.missionId,
    jsonObject.change,
    jsonObject.newValue
)

fun toChallengeUpdateEventData(jsonObject: dynamic) = ChallengeUpdateEventData(
    jsonObject.wasAccomplished,
    toChallengeAnswer(jsonObject.change),
    toChallengeAnswers(jsonObject.newValue)
)

fun <T> arrayToList(jsonArray: dynamic) = JSArrayBackedList<T>(delegate = jsonArray)

fun toPlayer(jsonObject: dynamic) = Player().apply {
    configureBasePlayer(jsonObject)
    star = jsonObject.star
    coin = jsonObject.coin
    reputation = jsonObject.reputation
    locale = jsonObject.locale
    avatarUrl = jsonObject.avatarUrl
    items = arrayToList(jsonObject.items)
    achievements = arrayToList(jsonObject.achievements)
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
    toTypedList(jsonObject.challengeAnswers, ::toChallengeAnswers)
)

fun toMissionModalData(jsonObject: dynamic) = MissionModalData(
    toTypedList(jsonObject.tabs, ::toMissionTabData)
)

fun toMissionTabData(jsonObject: dynamic): MissionTabData<*> {
    return when (valueOf(jsonObject.type)) {
        QuestionChallenge,
        StarChallenge,
        TextContentChallenge,
        PullRequestChallenge -> ChallengeTabData(
            toChallengeSpec(jsonObject.data)
        )
        HeroNoticeboardChallenge -> HeroNoticeboardTabData(
            toHeroNoticeboardTilesData(jsonObject.data)
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
        jsonObject.id,
        ChallengeType.valueOf(jsonObject.type),
        jsonObject.spec,
        jsonObject.star,
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

fun toChallengeAnswers(jsonObject: dynamic) = ChallengeAnswers(
    playerId = jsonObject.playerId,
    missionId = jsonObject.missionId,
    challengeId = jsonObject.challengeId,
    map = jsonObject.map,
    answers = toTypedMap(jsonObject.answers) {
        toTypedList(it, ::toChallengeAnswer)
    }
)

fun toChallengeAnswer(jsonObject: dynamic) = ChallengeAnswer(
    star = jsonObject.star,
    answer = jsonObject.answer,
    accomplished = jsonObject.accomplished.toString() == "true",
    time = jsonObject.time,
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
    jsonObject.joinQQGroupSecret
)

fun toLocalizedText(jsonObject: dynamic) = LocalizedText(
    jsonObject.id,
    toTypedMap(jsonObject.data) { it },
    LocalizedTextFormat.valueOf(jsonObject.format)
)

fun toLivestreams(jsonObject: dynamic) = toTypedList(jsonObject) {
    LivestreamData(
        it.id,
        it.title,
        it.url,
        it.startEpochMs,
        it.endEpochMs
    )
}

fun toHeroNoticeboardTilesData(jsonObject: dynamic) = HeroNoticeboardTilesData(
    jsonObject.page,
    toTypedList(jsonObject.tiles) {
        HeroNoticeboardTile(
            it.x,
            it.y,
            it.userid,
            it.color,
            it.createdAt,
            it.changedAt
        )
    }
)

fun toInvitationInformation(jsonObject: dynamic) = InvitationInformation(
    jsonObject.inviterId,
    jsonObject.invitationCode,
    jsonObject.rewardedCoin
)
