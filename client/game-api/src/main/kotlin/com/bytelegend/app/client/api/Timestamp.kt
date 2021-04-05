package com.bytelegend.app.client.api

import kotlin.js.Date

class Timestamp(
    private val epochTimeMs: Long
) {
    constructor() : this(Date().getTime().toLong())

    operator fun minus(other: Timestamp): Long = epochTimeMs - other.epochTimeMs
    operator fun compareTo(other: Timestamp) = epochTimeMs.compareTo(other.epochTimeMs)

    fun elapsedTimeMs() = Date().getTime().toLong() - this.epochTimeMs

    companion object {
        fun now() = Timestamp()
    }
}
