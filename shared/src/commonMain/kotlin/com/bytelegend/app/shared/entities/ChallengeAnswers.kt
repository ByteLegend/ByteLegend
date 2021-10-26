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

package com.bytelegend.app.shared.entities

import com.bytelegend.app.shared.annotations.DynamoDbIgnore
import com.bytelegend.app.shared.annotations.JsonIgnore

open class ChallengeAnswers(
    @get: DynamoDbIgnore
    val playerId: String,

    @get: DynamoDbIgnore
    val map: String,

    @get: DynamoDbIgnore
    val missionId: String,

    @get: DynamoDbIgnore
    val challengeId: String,

    /**
     * Key: the answer. Value: some answer (e.g. PR) can have more than 1 answer.
     */
    @get: DynamoDbIgnore
    open val answers: Map<String, List<ChallengeAnswer>>
) {
    @get: DynamoDbIgnore
    @get: JsonIgnore
    val isPullRequest: Boolean by lazy {
        answers.values.any { it.any { it.data.isEmpty() } }
    }

    @get: DynamoDbIgnore
    @get: JsonIgnore
    val accomplished: Boolean by lazy {
        answers.values.flatten().any { it.accomplished }
    }

    @get: DynamoDbIgnore
    @get: JsonIgnore
    val star: Int by lazy {
        answers.values.flatten().maxOfOrNull { it.star } ?: 0
    }

    override fun toString(): String {
        return "ChallengeAnswers(playerId='$playerId', map='$map', missionId='$missionId', challengeId='$challengeId', answers=$answers)"
    }
}
