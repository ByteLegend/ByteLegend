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
data class PullRequestAnswer(
    val htmlUrl: String,
    val number: String,
    val repoFullName: String,
    val accomplished: Boolean,
    val lastUpdatedTime: String,
    val checkRuns: List<PullRequestCheckRun>
) {
    val latestCheckRun
        get() = checkRuns.firstOrNull()
}

data class PullRequestCheckRun(
    val id: String,
    val sha: String,
    val htmlUrl: String,
    // null means it's still running
    val conclusion: CheckRunConclusion?
)

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
    val repo = htmlUrl.substringAfter("https://github.com/").substringBefore("/pull/")
    val number = htmlUrl.substringAfter("pull/")
    val accomplished = any { it.accomplished }
    val sortedCheckRunByStartedTimeDesc: List<CheckRunAnswerData> = sortedByDescending { it.time }
        .map { it.answerData }.filterIsInstance<CheckRunAnswerData>()

    val idToConclusion = sortedCheckRunByStartedTimeDesc
        .filter(CheckRunAnswerData::isCompleted).associate {
            it.id to it.conclusion?.let { CheckRunConclusion.valueOf(it.uppercase()) }
        }
    val checkRuns = sortedCheckRunByStartedTimeDesc
        .filter { it.isCreated() }
        .map {
            val id = it.id
            val sha = it.sha
            val conclusion = idToConclusion[id]
            val actionHtmlUrl = "https://github.com/$repo/runs/$id"
            PullRequestCheckRun(id, sha, actionHtmlUrl, conclusion)
        }
    return PullRequestAnswer(htmlUrl, number, repo, accomplished, maxOf { it.time }, checkRuns)
}
