package com.bytelegend.app.shared.entities

import com.bytelegend.app.shared.annotations.DynamoDbIgnore
import com.bytelegend.app.shared.annotations.DynamoDbSecondaryPartitionKey
import com.bytelegend.app.shared.annotations.JsonIgnore
import com.bytelegend.app.shared.annotations.ReadOnly
import kotlinx.serialization.Serializable

val ANONYMOUS_DUMMY_MAP = "ANONYMOUS_DUMMY_MAP"

@Serializable
open class Player {
    /**
     * ID for human reading, e.g.
     *
     * anno#1a2b3c
     * gh#blindpirate
     * gt#blindpirate
     * wc#1234567
     */
    @get: DynamoDbIgnore
    var id: String? = null

    /**
     * The username in 3rd-party system, e.g. GitHub account.
     */
    var username: String? = null

    /**
     * The name for display
     */
    var nickname: String? = null

    /**
     * The map where player is currently on.
     */
    var map: String = ANONYMOUS_DUMMY_MAP

    /**
     * The grid coordinate of player on the map.
     * Note that this is not realtime, e.g. only the location which the player "is supposed to be",
     * e.g. player click mouse on a location.
     */
    var x: Int? = null
    var y: Int? = null

    /**
     * Whether the player is currently keeping a connection to server.
     *
     * 0: the player is offline.
     * positive number: the id of the server which the player connects to.
     *
     */
    @get: DynamoDbSecondaryPartitionKey(indexNames = ["serverIndex"])
    @JsonIgnore
    var server: Int = 0

    // TODO @ReadOnly
    var star: Int = 0
    var coin: Int = 0
    var reputation: Int = 0

    /**
     * Note: items and states are updated asynchronously.
     * This means that `save()` should avoid saving these two fields.
     */
    @get: ReadOnly
    // it's stored as dynamodb set
    var items: MutableList<String> = ArrayList()

    /**
     * This is actually a set, but enhanced ddb client can't map to java.util.LinkedHashSet by default.
     * Caller should take care of deduplication.
     */
    @get: ReadOnly
    var states: MutableMap<String, String> = HashMap()

    val online: Boolean
        @JsonIgnore
        @DynamoDbIgnore
        get() = server != 0

    /**
     * The preferred locale.
     */
    var locale: String? = null

    var avatarUrl: String? = null

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

    /**
     * The character id for display.
     */
    var characterId: Int? = null

    val isAnonymous: Boolean
        @JsonIgnore
        @DynamoDbIgnore
        get() = id!!.startsWith("anon#")

    @JsonIgnore
    @DynamoDbIgnore
    fun getPartialEntity() = Player().apply {
        this.id = this@Player.id
        this.username = this@Player.username
        this.nickname = this@Player.nickname
        this.map = this@Player.map
        this.x = this@Player.x
        this.y = this@Player.y
        this.characterId = this@Player.characterId
    }
}

fun ghLoginToPlayerId(ghLogin: String) = "gh#$ghLogin"