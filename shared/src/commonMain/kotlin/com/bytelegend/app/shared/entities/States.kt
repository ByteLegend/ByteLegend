package com.bytelegend.app.shared.entities

import com.bytelegend.app.shared.annotations.DynamoDbIgnore

open class States {
    @get: DynamoDbIgnore
    var playerId: String? = null

    @get: DynamoDbIgnore
    var map: String? = null
    var states: MutableMap<String, String> = HashMap()
}