package com.bytelegend.app.shared.enums

enum class ServerLocation(
    val awsRegionId: String,
    /**
     * We generate a unique id for each server atomically based on database,
     * to avoid cross-region id conflict, an offset is assigned to the id generator.
     *
     * The gap is calculated by: if we have 100 servers which restarts 10 times per day,
     * then we run out of 10_000_000 after 3 years.
     */
    val serverIdOffset: Int
) {
    BEIJING("cn-north-1", 0),
    SEOUL("ap-northeast-2", 1_000_000);

    fun displayNameId() = "${name.toLowerCase().capitalize()}ServerDisplayNameId"
}