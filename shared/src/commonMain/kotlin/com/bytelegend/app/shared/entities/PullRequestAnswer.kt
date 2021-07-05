package com.bytelegend.app.shared.entities

/**
 * Also see `PullRequestContext`
 */
class PullRequestAnswer(
    val htmlUrl: String,
    val number: String,
    val repoFullName: String,
    val accomplished: Boolean,
    val lastUpdatedAt: Long,
    // order by start time desc
    val checkRuns: List<PullRequestCheckRun>
) {
    val latestCheckRun
        get() = checkRuns.firstOrNull()
}

class PullRequestCheckRun(
    val id: String,
    val sha: String,
    val htmlUrl: String,
    // null means it's still running
    val conclusion: CheckRunConclusion?
)

enum class CheckRunConclusion {
    ACTION_REQUIRED, CANCELLED, FAILURE, NEUTRAL, SUCCESS, SKIPPED, STALE, TIMED_OUT
}

fun List<PlayerMissionAnswer>.toPullRequestAnswers(): List<PullRequestAnswer> {
    val groupByAnswers = filter { it.answer.startsWith("https://") }.groupBy { it.answer }
    return groupByAnswers.mapNotNull { it.value.toPullRequestAnswer() }.sortedBy { it.lastUpdatedAt }.reversed()
}

private fun List<PlayerMissionAnswer>.toPullRequestAnswer(): PullRequestAnswer? {
    if (this.isEmpty()) {
        return null
    }
    val htmlUrl = get(0).answer
    val repo = htmlUrl.substringAfter("https://github.com/").substringBefore("/pull")
    val number = htmlUrl.substringAfter("pull/")
    val accomplished = any { it.accomplished }
    val sortedCheckRunByStartedTimeDesc = filter {
        it.data["event"] == "check_run"
    }.sortedBy { it.createdAt }.reversed()

    val idToConclusion = sortedCheckRunByStartedTimeDesc.filter {
        it.data["action"] == "completed"
    }.associate {
        it.data["id"] to it.data["conclusion"]?.let { CheckRunConclusion.valueOf(it.uppercase()) }
    }
    val checkRuns = sortedCheckRunByStartedTimeDesc
        .filter { it.data["action"] == "created" }
        .map {
            val id = it.data["id"]!!
            val sha = it.data["sha"]!!
            val conclusion = idToConclusion[id]
            val actionHtmlUrl = "https://github.com/$repo/actions/runs/$id"
            PullRequestCheckRun(id, sha, actionHtmlUrl, conclusion)
        }
    val maxCreatedAt = maxOf { it.createdAt }

    return PullRequestAnswer(htmlUrl, number, repo, accomplished, maxCreatedAt, checkRuns)
}
