package com.bytelegend.app.serverapi.data

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonIgnore
import java.time.Duration
import java.time.Instant
import java.util.HashMap

val DEFAULT_SESSION_AGE = Duration.ofDays(14)
val DEFAULT_SESSION_RENEW = Duration.ofDays(7)

class Session(
    @BsonId
    var id: String,
    var playerId: String,
) {
    var version = 0

    var createdAt: Instant = Instant.now()
    var data: MutableMap<String, String> = HashMap()

    val isRenewable: Boolean
        @BsonIgnore
        get() = createdAt.plus(DEFAULT_SESSION_RENEW) < Instant.now()

    val isExpired: Boolean
        @BsonIgnore
        get() = expireAt < Instant.now()

    val expireAt: Instant
        @BsonIgnore
        get() = createdAt.plus(DEFAULT_SESSION_AGE)

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Session

        if (id != other.id) return false

        return true
    }
}
