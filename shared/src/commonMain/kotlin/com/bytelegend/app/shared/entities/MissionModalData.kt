package com.bytelegend.app.shared.entities

data class MissionModalData(
    val tabs: List<MissionTab>
)

data class MissionTab(
    val type: MissionTabType,
    /**
     * The i18n-title id.
     */
    val title: String,
    var data: Any
)

enum class MissionTabType {
    /**
     * A tab displaying the current question challenge.
     */
    QuestionChallenge,

    StarChallenge,

    PRChallenge,

    RememberBravePeopleChallenge,

    /**
     * A tab displaying the tutorials
     */
    Tutorial,

    Discussion
}