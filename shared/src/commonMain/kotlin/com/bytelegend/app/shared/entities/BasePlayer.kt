package com.bytelegend.app.shared.entities

import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.annotations.DynamoDbIgnore
import com.bytelegend.app.shared.annotations.DynamoDbSecondaryPartitionKey
import com.bytelegend.app.shared.annotations.JsonIgnore
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
     * positive number: the id of the server which the player connects to.
     *
     */
    @get: DynamoDbSecondaryPartitionKey(indexNames = ["serverIndex"])
    var server: Int = 0

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
