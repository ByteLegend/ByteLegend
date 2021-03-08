package com.bytelegend.app.shared

enum class ServerLocation(
    val displayNameId: String,
    /**
     * We generate a unique id for each server atomically based on database,
     * to avoid cross-region id conflict, an offset is assigned to the id generator.
     *
     * The gap is calculated by: if we have 100 servers which restarts 10 times per day,
     * then we run out of 10_000_000 after 3 years.
     */
    val serverIdOffset: Int
) {
    BEIJING("BeijingServerDisplayNameId", 0),
    SEOUL("SeoulServerDisplayNameId", 1_000_000)
}