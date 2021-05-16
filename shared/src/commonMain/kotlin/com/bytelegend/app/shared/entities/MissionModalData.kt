package com.bytelegend.app.shared.entities

import com.bytelegend.app.shared.entities.mission.ChallengeSpec
import com.bytelegend.app.shared.entities.mission.ChallengeType
import com.bytelegend.app.shared.entities.mission.DiscussionsSpec
import com.bytelegend.app.shared.entities.mission.Pagination
import com.bytelegend.app.shared.entities.mission.Tutorial
import com.bytelegend.app.shared.i18n.Locale

data class MissionModalData(
    val tabs: List<MissionTabData<*>>
)

interface MissionTabData<T> {
    val type: MissionTabType

    /**
     * The i18n-title id.
     */
    val title: String
    val data: Any
}

class TutorialsTabData(
    override val data: Pagination<Tutorial>,
    val locales: List<Locale>
) : MissionTabData<Pagination<Tutorial>> {
    override val type: MissionTabType = MissionTabType.Tutorials
    override val title: String = "MissionTutorials"
}

class ChallengeTabData(
    override val data: ChallengeSpec
) : MissionTabData<ChallengeSpec> {
    @Suppress("REDUNDANT_ELSE_IN_WHEN")
    override val type: MissionTabType = when (data.type) {
        ChallengeType.Noticeboard -> MissionTabType.NoticeboardChallenge
        ChallengeType.Star -> MissionTabType.StarChallenge
        ChallengeType.PullRequest -> MissionTabType.PullRequestChallenge
        ChallengeType.Question -> MissionTabType.QuestionChallenge
        else -> throw IllegalArgumentException(data.type.toString())
    }
    override val title: String = "MissionChallenge"
}

class DiscussionsTabData(
    override val data: DiscussionsSpec
) : MissionTabData<DiscussionsSpec> {
    override val type: MissionTabType = MissionTabType.Discussions
    override val title: String = "MissionDiscussion"
}

enum class MissionTabType {
    /**
     * A tab displaying the current question challenge.
     */
    QuestionChallenge,

    StarChallenge,

    PullRequestChallenge,

    NoticeboardChallenge,

    /**
     * A tab displaying the tutorials
     */
    Tutorials,

    Discussions
}