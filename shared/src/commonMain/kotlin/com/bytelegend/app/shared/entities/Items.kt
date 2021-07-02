package com.bytelegend.app.shared.entities

import com.bytelegend.app.shared.annotations.DynamoDbIgnore

open class Items {
    @get: DynamoDbIgnore
    var playerId: String? = null
    var items: MutableList<Item> = mutableListOf()
}

data class Item(
    val id: String,
    val name: String
)
