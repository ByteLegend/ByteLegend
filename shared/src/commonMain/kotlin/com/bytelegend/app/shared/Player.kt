package com.bytelegend.app.shared

import DynamoDbBean
import DynamoDbIgnore
import DynamoDbPartitionKey
import JsonIgnore
import com.bytelegend.app.shared.Replicable
import kotlinx.serialization.Serializable

@Serializable
@DynamoDbBean
class Player : Replicable() {
    @get: DynamoDbPartitionKey
    var id: String? = null
    var username: String? = null
    var nickname: String? = null
    var mapId: String? = null
    var submapId: String? = null
    var x: Int? = null
    var y: Int? = null
    var online: Int? = null
    var locale: String? = null
    var avatarUrl: String? = null
    var email: String? = null
    var characterId: Int? = null

    @get: DynamoDbIgnore
    var lastLoginEpochSecond: Long? = null

    @get: JsonIgnore
    val isAnonymous: Boolean
        get() = id!!.startsWith("anon#")
}