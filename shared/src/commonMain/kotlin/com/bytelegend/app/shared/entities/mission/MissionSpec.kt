package com.bytelegend.app.shared.entities.mission

/**
 * MissionSpec is immutable data structure to store the mission definitions.
 * It's usually stored in GitHub repositories so that it can be contributed by community.
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
    /**
     * The challenge in this mission
     */
    val challenge: ChallengeSpec?,
    /**
     * The tutorials in this mission
     */
    val tutorials: TutorialsSpec?,
    /**
     * The discussions in this mission
     */
    val discussions: DiscussionsSpec?,
    /**
     * Some information of this mission is not maintained in YAML,
     * but in map JSON. See {@link GameMapMission}.
     */
    val mapMissionSpec: MapMissionSpec?,
    /**
     * Special actions to be performed when the mission is accomplished
     */
    val onFinish: OnFinishSpec?
)





