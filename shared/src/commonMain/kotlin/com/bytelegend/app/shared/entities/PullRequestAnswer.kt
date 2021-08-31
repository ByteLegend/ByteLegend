/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 * 
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

fun List<PlayerChallengeAnswer>.toPullRequestAnswers(): List<PullRequestAnswer> {
    val groupByAnswers = filter { it.answer.startsWith("https://") }.groupBy { it.answer }
    return groupByAnswers.mapNotNull { it.value.toPullRequestAnswer() }.sortedBy { it.lastUpdatedAt }.reversed()
}

private fun List<PlayerChallengeAnswer>.toPullRequestAnswer(): PullRequestAnswer? {
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
            val actionHtmlUrl = "https://github.com/$repo/runs/$id"
            PullRequestCheckRun(id, sha, actionHtmlUrl, conclusion)
        }
    val maxCreatedAt = maxOf { it.createdAt }

    return PullRequestAnswer(htmlUrl, number, repo, accomplished, maxCreatedAt, checkRuns)
}
