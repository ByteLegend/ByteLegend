package com.bytelegend.app.shared.entities

import BsonId
import BsonIgnore
import JsonIgnore
import kotlinx.serialization.Serializable

@Serializable
class Player {
    /**
     * The auto-generated database id
     */
    @JsonIgnore
    @BsonId
    var _id: String? = null

    /**
     * ID for human reading, e.g.
     *
     * anno#1a2b3c
     * gh#blindpirate
     * gt#blindpirate
     * wc#1234567
     */
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
    var map: String? = null

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
    var server: Int = 0

    val online: Boolean
        @JsonIgnore
        @BsonIgnore
        get() = server != 0

    /**
     * The preferred locale.
     */
    var locale: String? = null

    var avatarUrl: String? = null

    /**
     * The main email.
     */
    @JsonIgnore
    var email: String? = null

    /**
     * All possible emails.
     */
    @JsonIgnore
    val emails: List<String> = emptyList()

    /**
     * The character id for display.
     */
    var characterId: Int? = null
    var missions: Map<String, String> = emptyMap()
    var states: Map<String, String> = emptyMap()

    /**
     * The region name where the record is updated recently, e.g. BEIJING
     */
    @JsonIgnore
    var lastUpdatedIn: String? = null

    /**
     * Last updated timestamp, in epoch milliseconds.
     */
    @JsonIgnore
    var lastUpdatedMs: Long? = null

    @JsonIgnore
    var lastLoginEpochSecond: Long? = null

    @get: JsonIgnore
    val isAnonymous: Boolean
        @BsonIgnore
        get() = id!!.startsWith("anon#")
}