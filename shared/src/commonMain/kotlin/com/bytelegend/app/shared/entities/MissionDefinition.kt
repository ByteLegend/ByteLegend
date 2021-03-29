package com.bytelegend.app.shared.entities

data class MissionDefinition(
    val id: String,
    val type: MissionType,
    val star: Int,
    /**
     * Type == Star, PR -> repo address
     * Type == Question -> correct answer regex
     */
    val data: String
)

data class BackendMissionDefinition(
    val id: String,
    val map: String,
    val type: MissionType,
    val star: Int,
    val data: String
) {
    constructor(map: String, definition: MissionDefinition) : this(
        definition.id,
        map,
        definition.type,
        definition.star,
        definition.data
    )
}

enum class MissionType {
    Star,
    PR,
    Question
}

