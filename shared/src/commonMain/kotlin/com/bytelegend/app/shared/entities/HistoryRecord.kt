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

import com.bytelegend.app.shared.annotations.DynamoDbPartitionKey
import com.bytelegend.app.shared.annotations.DynamoDbSortKey

open class HistoryRecord(
    @get:DynamoDbPartitionKey
    val playerId: String,
    /**
     * IMPORTANT NOTE: this is not the "historyRecordId" used in various services.
     *
     * The sort key. Format: "{Type}#{historyRecordId}".
     *
     * Example:
     *
     * - CoinChange#1a2b3c4d
     * - CoinChange#2022-10-12
     * - ReputationChange#1a2b3c4d
     * - CoinChange#UnlockMissionTutorials#mapId#missionId
     */
    @get:DynamoDbSortKey
    val id: String,

    /**
     * ISO8601 format, e.g. 2020-02-13T18:51:09.840Z
     */
    val createdAt: String,

    /**
     * Changed value. Can be negative.
     */
    val change: Int,

    /**
     * The change reason.
     */
    val reasonId: String,

    /**
     * The change args.
     */
    val reasonArgs: List<String> = emptyList()
)
