package com.bytelegend.app.shared.entities

import kotlinx.serialization.Serializable

@Serializable
abstract class Replicable(
    val _id: String,
    /**
     * The region name where the record is updated recently, e.g. BEIJING
     */
    val lastUpdatedIn: String,

    /**
     * Last updated timestamp, in epoch milliseconds.
     */
    val lastUpdatedMs: Long,
) {
}