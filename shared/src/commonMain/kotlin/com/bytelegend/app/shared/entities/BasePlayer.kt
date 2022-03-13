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

import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.annotations.DynamoDbIgnore
import com.bytelegend.app.shared.annotations.DynamoDbSecondaryPartitionKey
import com.bytelegend.app.shared.annotations.JsonIgnore
import com.bytelegend.app.shared.annotations.ReadOnly
import com.bytelegend.app.shared.objects.GridCoordinateAware
import kotlinx.serialization.Serializable

/**
 * IMPORTANT NOTE:
 *
 * This should be kept sync with player table "server" index projection.
 */
@Serializable
open class BasePlayer : GridCoordinateAware {
    /**
     * ID for human reading, e.g.
     *
     * anno#1a2b3c
     * gh#blindpirate
     * gt#blindpirate
     * wc#1234567
     */
    @get: DynamoDbIgnore
    var id: String = ""

    /**
     * The username in 3rd-party system, e.g. GitHub account.
     */
    var username: String = ""

    /**
     * The name for display
     */
    var nickname: String = ""

    /**
     * The map where player is currently on.
     */
    var map: String = ANONYMOUS_DUMMY_MAP

    /**
     * The grid coordinate of player on the map.
     * Note that this is not realtime, e.g. only the location which the player "is supposed to be",
     * e.g. player click mouse on a location.
     */
    var x: Int = -1
    var y: Int = -1

    /**
     * Whether the player is currently keeping a connection to server.
     *
     * 0: the player is offline.
     * -1: the player hasn't signed up yet, but we need to store some items/achievements.
     *     In this case we create a "placeholder" player without names/emails/nicknames.
     * positive number: the id of the server which the player connects to.
     *
     */
    @get: DynamoDbSecondaryPartitionKey(indexNames = ["serverIndex"])
    var server: Int = 0

    /**
     * Star marks the progress of player challenge. Players get stars
     * by accomplishing challenges.
     */
    @get: ReadOnly
    var star: Int = 0

    var avatarUrl: String? = null

    /**
     * The character id for display.
     */
    var characterId: Int = -1

    val isAnonymous: Boolean
        @JsonIgnore
        @DynamoDbIgnore
        get() = id.startsWith("anon#")

    val isOnymous: Boolean
        @JsonIgnore
        get() = !id.startsWith("anon#")

    override val gridCoordinate: GridCoordinate
        @JsonIgnore
        get() = GridCoordinate(x, y)
}
