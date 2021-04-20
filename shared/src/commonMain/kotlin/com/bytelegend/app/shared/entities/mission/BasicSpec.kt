package com.bytelegend.app.shared.entities.mission

import com.bytelegend.app.shared.GridCoordinate

interface ChallengeSpec {
    val type: ChallengeType
    val star: Int

    // the html displayed at challenge tab
    val readme: String
    val spec: String
}

/**
 * Represent the data to display at tutorial tab
 */
interface TutorialsSpec {
}

data class DiscussionsSpec(
    val url: String
)

interface TutorialSpec {
    val id: String
    val title: String
    val locales: List<String>
    val labels: List<String>
}

interface Pagination<T> {
    val items: List<T>
    val totalPages: Int
    val currentPage: Int
    val pageSize: Int
}

enum class ChallengeType {
    Star, PR, Question
}

data class RoadmapSpec(
    val map: String,
    val sprite: String,
    /**
     * Top-left corner of the mission
     */
    val point: GridCoordinate,
    val checkpoint: Boolean,
    val children: List<String>,
    val next: String?
)