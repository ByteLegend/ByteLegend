package com.bytelegend.app.shared.entities.mission

data class ChallengeSpec(
    val type: ChallengeType,
    val star: Int,

    val spec: String,
    // the text id at challenge tab
    val readme: String = "",
)

enum class ChallengeType {
    Star, PullRequest, Question
}
