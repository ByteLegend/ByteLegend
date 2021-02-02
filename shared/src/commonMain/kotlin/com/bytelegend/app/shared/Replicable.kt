package com.bytelegend.app.shared

abstract class Replicable {
    /**
     * The region name where the record is updated recently, e.g. cn-north-1
     */
    var lastUpdatedIn: String? = null

    /**
     * Last updated timestamp, in epoch milliseconds.
     */
    var lastUpdatedTime: Long? = null
}