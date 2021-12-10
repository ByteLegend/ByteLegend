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

import com.bytelegend.app.shared.util.currentTimeMillis
import com.bytelegend.app.shared.util.toIso8601

const val MAX_CHECK_RUN_MS: Long = 10L * 60 * 1000
const val STALE_PULL_REQUEST_MS: Long = 7L * 24 * 3600 * 1000

/**
 * Also see `PullRequestContext`
 */
data class PullRequestAnswer(
    val htmlUrl: String,
    val number: String,
    val baseRepoFullName: String,
    val headRepoFullName: String,
    val branch: String,
    val open: Boolean,
    val accomplished: Boolean,
    val lastUpdatedTime: String,
    /**
     * Ordered by created time desc
     */
    val checkRuns: List<PullRequestCheckRun>
) {
    val latestCheckRun = checkRuns.firstOrNull()
    val running = latestCheckRun.let {
        it != null && it.conclusion == null && !it.isStale
    }
    val stale = lastUpdatedTime < (currentTimeMillis() - STALE_PULL_REQUEST_MS).toIso8601()
}

data class PullRequestCheckRun(
    val id: String,
    val sha: String,
    val htmlUrl: String,
    val time: String,
    // null means it's still running
    val conclusion: String?
) {
    /**
     * If a check run has no conclusion and started 10min ago, we see it as "stale".
     */
    val isStale: Boolean
        get() {
            val tenMinutesAgo = currentTimeMillis() - MAX_CHECK_RUN_MS
            return time < tenMinutesAgo.toIso8601()
        }
}

fun ChallengeAnswers.toPullRequestAnswers(): List<PullRequestAnswer> {
    return answers.values.mapNotNull { it.toPullRequestAnswer() }.sortedByDescending { it.lastUpdatedTime }
}

fun List<ChallengeAnswer>.toPullRequestAnswer(): PullRequestAnswer? {
    if (this.isEmpty()) {
        return null
    }
    if (any { it.answerData == EmptyAnswerData }) {
        // star/question answer
        return null
    }
    val htmlUrl = get(0).answer
    val baseRepoFullName = htmlUrl.substringAfter("https://github.com/").substringBeforeLast("/pull/")
    val number = htmlUrl.substringAfter("pull/")
    val firstPrData = firstOrNull { it.answerData.type == PULL_REQUEST_TYPE }?.answerDataAs<PullRequestAnswerData>()
    val headRepoFullName = firstPrData?.headRepoFullName ?: baseRepoFullName
    val branch = firstPrData?.branch ?: "main"
    val lastOpenCloseAction = filter { it.answerData.type == PULL_REQUEST_TYPE }
        .map { it.answerDataAs<PullRequestAnswerData>() }
        .lastOrNull { it.action.equals(PullRequestEventAction.CLOSED.name, true) || it.action.endsWith("opened") }
    val open = lastOpenCloseAction == null || lastOpenCloseAction.action.endsWith("opened")

    val accomplished = any { it.accomplished }
    val sortedCheckRunByStartedTimeDesc: List<ChallengeAnswer> = sortedByDescending { it.time }.filter { it.answerData is CheckRunAnswerData }

    val idToConclusion = sortedCheckRunByStartedTimeDesc
        .filter { it.answerDataAs<CheckRunAnswerData>().isCompleted() }
        .associate {
            val data = it.answerDataAs<CheckRunAnswerData>()
            data.id to data.conclusion?.let { CheckRunConclusion.valueOf(it.uppercase()) }
        }
    val checkRuns = sortedCheckRunByStartedTimeDesc
        .filter { it.answerDataAs<CheckRunAnswerData>().isCreated() }
        .map {
            val data = it.answerDataAs<CheckRunAnswerData>()
            val id = data.id
            val conclusion = idToConclusion[id]
            val actionHtmlUrl = "https://github.com/$baseRepoFullName/runs/$id"
            PullRequestCheckRun(id, data.sha, actionHtmlUrl, it.time, conclusion?.name)
        }
    return PullRequestAnswer(htmlUrl, number, baseRepoFullName, headRepoFullName, branch, open, accomplished, maxOf { it.time }, checkRuns)
}
