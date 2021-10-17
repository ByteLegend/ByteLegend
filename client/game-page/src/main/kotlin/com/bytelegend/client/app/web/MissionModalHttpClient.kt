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
package com.bytelegend.client.app.web

import com.bytelegend.app.shared.entities.MissionModalData
import com.bytelegend.app.shared.entities.mission.Pagination
import com.bytelegend.app.shared.entities.mission.Tutorial
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.app.shared.protocol.ChallengeUpdateEventData
import com.bytelegend.client.utils.toChallengeUpdateEventData
import com.bytelegend.client.utils.toMissionModalData
import com.bytelegend.client.utils.toPagination
import com.bytelegend.client.utils.toTutorial
import kotlinext.js.jsObject
import kotlinx.browser.window
import kotlinx.coroutines.await
import org.w3c.fetch.Response

class HttpRequestException(val statusCode: Int, override val message: String) : RuntimeException(message)

suspend fun Response.checkStatusCode(): Response {
    if (status < 200 || status >= 400) {
        throw HttpRequestException(status.toInt(), text().await())
    }
    return this
}

suspend fun get(uri: String): String {
    return window.fetch(uri).await().apply {
        checkStatusCode()
    }.text().await()
}

suspend fun post(uri: String, body: String): String {
    return window.fetch(
        uri,
        jsObject {
            method = "POST"
            this.headers = jsObject {
                this["Content-Type"] = "application/json"
                this["Accept"] = "application/json"
            }
            this.body = body
        }
    )
        .await()
        .apply {
            checkStatusCode()
        }
        .text()
        .await()
}

fun Map<String, String>.toDynamic(): dynamic {
    val ret = jsObject<dynamic>()
    forEach {
        ret[it.key] = it.value
    }
    return ret
}

suspend fun submitChallengeAnswer(
    missionId: String,
    challengeId: String,
    answer: String
): ChallengeUpdateEventData {
    val json = JSON.stringify(
        jsObject<dynamic> {
            this.answer = answer
        }
    )
    return toChallengeUpdateEventData(JSON.parse(post("/game/api/mission/$missionId/$challengeId/answer", json)))
}

suspend fun getMissionModalData(
    missionId: String
): MissionModalData {
    return toMissionModalData(JSON.parse(get("/game/api/mission/$missionId")))
}

suspend fun getMissionTutorial(
    missionId: String,
    pageNumber: Int,
    locales: List<Locale>
): Pagination<Tutorial> {
    return toPagination(JSON.parse(get("/game/api/tutorials?missionId=$missionId&locales=${locales.joinToString(",")}&pageNumber=$pageNumber&pageSize=20")), ::toTutorial)
}
