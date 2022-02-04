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

import com.bytelegend.app.shared.annotations.DynamoDbIgnore
import com.bytelegend.app.shared.annotations.JsonIgnore
import com.bytelegend.app.shared.util.currentTimeIso8601

/**
 * Represents an abstract answer to a challenge.
 * It can be an answer from frontend,
 * or answer from GitHub webhook event.
 *
 * An answer is immutable after created.
 */
@Suppress("UNCHECKED_CAST")
open class ChallengeAnswer(
    /**
     * How many stars the player can get from this answer?
     */
    val star: Int,
    val accomplished: Boolean,
    val answer: String,
    val data: Map<String, String>,
    val time: String = currentTimeIso8601()
) {
    @get:JsonIgnore
    @get:DynamoDbIgnore
    val answerData: ChallengeAnswerData by lazy {
        fromMap(data)
    }

    @DynamoDbIgnore
    fun <T : ChallengeAnswerData> answerDataAs(): T = answerData as T

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        other as ChallengeAnswer

        if (star != other.star) return false
        if (accomplished != other.accomplished) return false
        if (answer != other.answer) return false
        if (data != other.data) return false
        if (time != other.time) return false

        return true
    }

    override fun hashCode(): Int {
        var result = star
        result = 31 * result + accomplished.hashCode()
        result = 31 * result + answer.hashCode()
        result = 31 * result + data.hashCode()
        result = 31 * result + time.hashCode()
        return result
    }

    override fun toString(): String {
        return "ChallengeAnswer(star=$star, accomplished=$accomplished, answer='$answer', data=$data, time='$time')"
    }
}

interface ChallengeAnswerData {
    // pull_request/check_run
    val type: String
}

fun fromMap(map: Map<String, String>): ChallengeAnswerData {
    return when {
        map.isEmpty() -> EmptyAnswerData
        map["type"] == PULL_REQUEST_TYPE -> PullRequestAnswerData(
            map.getValue("action"),
            map.getValue("number"),
            map.getValue("subjectId"),
            map.getValue("headRepoFullName"),
            map.getValue("branch"),
            map.getValue("sha"),
        )
        map["type"] == CHECK_RUN_TYPE -> CheckRunAnswerData(
            map.getValue("action"),
            map.getValue("id"),
            map.getValue("sha"),
            map["conclusion"]
        )
        else -> throw IllegalArgumentException("Unsupported conversion: $map")
    }
}

const val PULL_REQUEST_TYPE = "pull_request"

// https://docs.github.com/en/actions/reference/events-that-trigger-workflows#pull_request
enum class PullRequestEventAction(val saveInDb: Boolean = false) {
    ASSIGNED,
    UNASSIGNED,
    LABELED,
    UNLABELED,
    OPENED(true),
    EDITED,
    CLOSED(true),
    REOPENED(true),
    SYNCHRONIZE(true),
    READY_FOR_REVIEW,
    LOCKED,
    UNLOCKED,
    REVIEW_REQUESTED,
    REVIEW_REQUEST_REMOVED
}

// https://docs.github.com/en/developers/webhooks-and-events/webhooks/webhook-events-and-payloads#check_run
enum class CheckRunEventAction {
    CREATED,
    COMPLETED,
    REREQUESTED,
    REQUESTED_ACTION
}

enum class CheckRunConclusion {
    ACTION_REQUIRED, CANCELLED, FAILURE, NEUTRAL, SUCCESS, SKIPPED, STALE, TIMED_OUT
}

// https://docs.github.com/en/rest/reference/checks#update-a-check-run
enum class CheckRunStatus {
    QUEUED, IN_PROGRESS, COMPLETED
}

const val CHECK_RUN_TYPE = "check_run"

object EmptyAnswerData : ChallengeAnswerData {
    @get: JsonIgnore
    override val type: String
        get() = throw UnsupportedOperationException()
}

data class PullRequestAnswerData(
    val action: String,
    val number: String,
    val subjectId: String,
    // Where does this PR come from?
    val headRepoFullName: String,
    // Which branch is the PR branch?
    val branch: String,
    // the head sha upon the pr event
    val sha: String,
) : ChallengeAnswerData {
    override val type: String = "pull_request"
}

data class CheckRunAnswerData(
    val action: String,
    val id: String,
    val sha: String,
    val conclusion: String?
) : ChallengeAnswerData {
    override val type: String = "check_run"

    @JsonIgnore
    fun isCompleted() = action.equals(CheckRunEventAction.COMPLETED.name, true)
    @JsonIgnore
    fun isCreated() = action.equals(CheckRunEventAction.CREATED.name, true)
}

class SceneInitData(
    val online: Int,
    val players: List<BasePlayer>,
    val states: MapChallengeStates
)
