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
package com.bytelegend.app.servershared.dal

import com.fasterxml.jackson.annotation.JsonIgnore
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbIgnore
import software.amazon.awssdk.services.dynamodb.model.AttributeValue

// This is an adapter layer for application and database layer
// For Player, the sk is "P"
// For mission, the sk is "M#mapId#missionId"
// For headSha -> pull request, the sk is "H#headSha"
// +--------------+------------------------------+------------+------------+----+----+-----------------+------------+
// | PartitionKey | SortKey                      | server     | map        | x  | y  | answers         | states     |
// +--------------+------------------------------+------------+------------+----+----+-----------------+------------+
// | gh#alice     | P                            | gh#alice@1 | JavaIsland | 20 | 80 |                 |            |
// +--------------+------------------------------+------------+------------+----+----+-----------------+------------+
// | gh#alice     |   PC#mapId#mission#challenge |            |            |    |    | [{star:0, ...}] |            |
// +--------------+------------------------------+------------+------------+----+----+-----------------+------------+
// | gh#alice     |   H#5843f14c34f5d0e05        | pullRequests: listOf(repo: github.com/ByteLegend/ByteLegend number:1234 state:closed merged: true)
// +--------------+------------------------------+------------+------------+----+----+-----------------+------------+
// | gh#alice     | CodeChangeHistory#2021-10-01 | records: List<CoinChangeHistoryRecord>                           |
// +--------------+------------------------------+------------+------------+----+----+-----------------+------------+
// | gh#alice     | PullRequestProblem#github.com/ByteLegendQuest/remember-brave-people/1#1a2b3c   | problems: List<PullRequestProblem>                           |
// +--------------+------------------------------+------------+------------+----+----+-----------------+------------+
// | gh#alice     | AccountBinding               | qq: 12345  |
// +--------------+------------------------------+------------+------------+----+----+-----------------+------------+
// | qq#12345     | AccountBinding               | gh: alice  |
// +--------------+------------------------------+------------+------------+----+----+-----------------+------------+
// | gh#alice     | InvitedPeople                | people: [bob,...]       |  reward: 500                            |
// +--------------+------------------------------+------------+------------+----+----+-----------------+------------+
// | gh#alice     | ChallengeAnswers#map#mission#challenge    | answers: {"pull/12345": [{star:0}], "pull/23456"}   |
// +--------------+------------------------------+------------+------------+----+----+-----------------+------------+
const val PLAYER_SORT_KEY = "P"
const val HEAD_SHA_SORT_KEY = "H"
const val PLAYER_COIN_CHANGE_HISTORY_KEY = "CoinChangeHistory"
const val PLAYER_REPUTATION_CHANGE_HISTORY_KEY = "ReputationChangeHistory"
const val PULL_REQUEST_PROBLEM = "PullRequestProblem"
const val ACCOUNT_BINDING_SORT_KEY = "AccountBinding"
const val INVITED_PEOPLE_SORT_KEY = "InvitedPeople"
const val CHALLENGE_ANSWERS_SORT_KEY = "ChallengeAnswers"
const val MAP_CHALLENGE_STATES_SORT_KEY = "MapChallengeStates"

interface StoredInPlayerTable {
    val pk: String
    val sk: String

    @get:DynamoDbIgnore
    @get: JsonIgnore
    val primaryKeyMap
        get() = mapOf(
            "pk" to AttributeValue.builder().s(pk).build(),
            "sk" to AttributeValue.builder().s(sk).build()
        )

    @get:DynamoDbIgnore
    @get: JsonIgnore
    val primaryKey: Key
        get() = Key.builder().partitionValue(pk)
            .sortValue(sk)
            .build()
}

abstract class AbstractStoredInPlayerTableBuilder {
    protected var pk: String = ""
    fun pk(value: String) = apply { pk = value }
    protected var sk: String = ""
    fun sk(value: String) = apply { sk = value }

    protected var version: Int? = null
    fun version(value: Int?) = apply { version = value }
}
