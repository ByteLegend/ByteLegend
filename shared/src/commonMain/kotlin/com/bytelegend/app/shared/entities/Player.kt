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
import com.bytelegend.app.shared.annotations.ReadOnly

const val ANONYMOUS_DUMMY_MAP = "ANONYMOUS_DUMMY_MAP"
const val PLACEHOLDER_PLAYER_MARKER = -1
const val OFFLINE_PLAYER_MARKER = 0

open class Player : BasePlayer() {

    /**
     * The basic currency in the game. Can be obtained via many ways.
     */
    @get: ReadOnly
    var coin: Int = 0

    /**
     * Reputation, can only be obtained by helping others or the community.
     * For example, helping others solve problems or contributing to the code.
     */
    @get: ReadOnly
    var reputation: Int = 0

    /**
     * Note: items and states are updated asynchronously.
     * This means that `save()` should avoid saving these ReadOnly-marked fields.
     */
    @get: ReadOnly
    // it's stored as dynamodb string set
    var items: MutableList<String> = ArrayList()

    @get: ReadOnly
    // it's stored as dynamodb string set
    var usedItems: MutableList<String> = ArrayList()

    @get: ReadOnly
    // it's stored as dynamodb string set
    var achievements: MutableList<String> = ArrayList()

    /**
     * This is actually a set, but enhanced ddb client can't map to java.util.LinkedHashSet by default.
     * Caller should take care of deduplication.
     */
    @get: ReadOnly
    var states: MutableMap<String, String> = HashMap()

    val online: Boolean
        @JsonIgnore
        @DynamoDbIgnore
        get() = server > 0

    /**
     * The preferred locale.
     */
    var locale: String? = null

    /**
     * The main email.
     */
    val email: String?
        @JsonIgnore
        @DynamoDbIgnore
        get() = emails.firstOrNull()

    /**
     * All possible emails.
     */
    @JsonIgnore
    var emails: MutableList<String> = ArrayList()

    @JsonIgnore
    var githubToken: String? = null

    var createdAt: String? = null
    /**
     * The time of last connection to server via WebSocket.
     */
    var lastLogInAt: String? = null

    fun toPartialEntity() = BasePlayer().apply {
        this.id = this@Player.id
        this.username = this@Player.username
        this.nickname = this@Player.nickname
        this.map = this@Player.map
        this.x = this@Player.x
        this.y = this@Player.y
        this.characterId = this@Player.characterId
        this.server = this@Player.server
        this.star = this@Player.star
        this.avatarUrl = this@Player.avatarUrl
    }
}

fun String.ghLoginToPlayerId() = "gh#$this"
fun String.playerIdToGhLogin() = this.substringAfter("gh#")
