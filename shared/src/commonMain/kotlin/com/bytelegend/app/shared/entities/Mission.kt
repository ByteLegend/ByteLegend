package com.bytelegend.app.shared.entities

data class Mission(
    override val id: String,
    override val title: String,
    val type: MissionType,
    val star: Int,
    /**
     * Type == Star, PR -> repo address
     * Type == Question -> correct answer regex
     */
    val spec: String
) : Metadata

enum class MissionType {
    Star,
    PR,
    Question
}

