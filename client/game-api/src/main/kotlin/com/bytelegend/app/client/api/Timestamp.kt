package com.bytelegend.app.client.api

import kotlin.js.Date

class Timestamp {
    operator fun minus(other: Timestamp): Long = epochTimeMs - other.epochTimeMs
    operator fun compareTo(other: Timestamp) = epochTimeMs.compareTo(other.epochTimeMs)
    val epochTimeMs = Date().getTime().toLong()

    companion object {
        fun now() = Timestamp()
    }
}
