package com.bytelegend.app.shared.entities

import com.bytelegend.app.shared.annotations.DynamoDbIgnore
import com.bytelegend.app.shared.annotations.JsonIgnore

open class States {
    @get: DynamoDbIgnore
    @get: JsonIgnore
    var playerId: String? = null

    @get: DynamoDbIgnore
    @get: JsonIgnore
    var map: String? = null
    var states: MutableMap<String, String> = HashMap()
}