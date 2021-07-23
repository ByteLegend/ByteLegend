package com.bytelegend.app.shared.entities.mission

data class ChallengeSpec(
    val id: String,
    val type: ChallengeType,
    val star: Int,

    val spec: String,
    /**
     * A TL;DR description i18n id of this challenge
     */
    val tldr: String = "",
    /**
     * The detail description i18n id of this challenge
     */
    val readme: String = ""
)

enum class ChallengeType(val withGitHubRepo: Boolean) {
    None(false),
    Star(true),
    Noticeboard(true),
    PullRequest(true),
    Question(false)
}
