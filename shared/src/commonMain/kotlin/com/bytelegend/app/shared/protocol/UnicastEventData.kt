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
package com.bytelegend.app.shared.protocol

import com.bytelegend.app.shared.annotations.JsonIgnore
import com.bytelegend.app.shared.entities.ChallengeAnswer
import com.bytelegend.app.shared.entities.ChallengeAnswers
import com.bytelegend.app.shared.entities.mission.OnFinishSpec

interface UnicastEventData {
    val playerId: String
}

interface NumberChange {
    val change: Int
    val newValue: Int
}

data class StarUpdateEventData(
    override val playerId: String,
    val map: String,
    val missionId: String,
    override val change: Int,
    override val newValue: Int,
) : UnicastEventData, NumberChange

class CoinUpdateEventData(
    override val playerId: String,
    override val change: Int,
    override val newValue: Int,
    val reasonId: String,
    val reasonArgs: Array<String>
) : UnicastEventData, NumberChange

class ReputationUpdateEventData(
    override val playerId: String,
    override val change: Int,
    override val newValue: Int,
    val reasonId: String,
    val reasonArgs: Array<String>
) : UnicastEventData, NumberChange

data class ChallengeUpdateEventData(
    // Is the challenge accomplished before this change?
    val wasAccomplished: Boolean,
    val change: ChallengeAnswer,
    val newValue: ChallengeAnswers,
) : UnicastEventData {
    override val playerId: String
        @JsonIgnore
        get() = newValue.playerId
}

data class ItemsStatesUpdateEventData(
    override val playerId: String,
    val map: String,
    val missionId: String,
    val onFinishSpec: OnFinishSpec,
) : UnicastEventData

data class AchievementUpdateEventData(
    override val playerId: String,
    val map: String,
    val missionId: String,
    val achievementId: String
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
