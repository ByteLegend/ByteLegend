package com.bytelegend.app.shared.entities

import JsonIgnore
import kotlinx.serialization.Serializable

@Serializable
class Player : Replicable() {
    var _id: String? = null
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
    var missions: Map<String, String> = emptyMap()
    var states: Map<String, String> = emptyMap()

    var lastLoginEpochSecond: Long? = null

    @get: JsonIgnore
    val isAnonymous: Boolean
        get() = _id!!.startsWith("anon#")
}