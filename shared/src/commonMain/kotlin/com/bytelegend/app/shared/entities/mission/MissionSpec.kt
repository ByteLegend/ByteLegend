package com.bytelegend.app.shared.entities.mission

import com.bytelegend.app.shared.GridCoordinate

/**
 * MissionSpec is immutable data structure to store the mission definitions.
 * It's usually stored in GitHub repositories and maintained by community.
 */
data class MissionSpec(
    /**
     * The id of the mission
     */
    val id: String,
    /**
     * The i18n id of the mission
     */
    val title: String,
    val point: GridCoordinate,
    val challenge: ChallengeSpec?,
    val tutorials: TutorialsSpec?,
    val discussions: DiscussionsSpec?,
    val roadmap: RoadmapSpec?
)
