package com.bytelegend.client.app.web

import com.bytelegend.app.shared.entities.MissionModalData
import com.bytelegend.app.shared.entities.mission.Pagination
import com.bytelegend.app.shared.entities.mission.Tutorial
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.app.shared.protocol.MissionUpdateEventData
import com.bytelegend.client.utils.JSObjectBackedMap
import com.bytelegend.client.utils.toMissionModalData
import com.bytelegend.client.utils.toMissionUpdateEventData
import com.bytelegend.client.utils.toPagination
import com.bytelegend.client.utils.toTutorial
import kotlinext.js.jsObject
import kotlinx.browser.window
import kotlinx.coroutines.await
import org.w3c.fetch.Response

fun Response.checkStatusCode() {
    if (status < 200 || status > 400) {
        throw Exception("Got response status code $status")
    }
}

suspend fun post(uri: String, body: String, headers: MutableMap<String, String> = JSObjectBackedMap()): String {
    return window.fetch(
        uri,
        jsObject {
            method = "POST"
            if (!headers.containsKey("Content-Type")) {
                headers["Content-Type"] = "application/json"
            }
            if (!headers.containsKey("Accept")) {
                headers["Accept"] = "application/json"
            }
            this.headers = headers.toDynamic()
            this.body = body
        }
    )
        .await()
        .apply(Response::checkStatusCode)
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

suspend fun submitMissionAnswer(
    missionId: String,
    answer: String
): MissionUpdateEventData {
    val json = JSON.stringify(
        jsObject<dynamic> {
            this.answer = answer
        }
    )
    return toMissionUpdateEventData(JSON.parse(post("/game/api/mission/$missionId/answer", json)))
}

suspend fun getMissionModalData(
    missionId: String
): MissionModalData {
    return window.fetch("/game/api/mission/$missionId")
        .await()
        .apply(Response::checkStatusCode)
        .text()
        .await()
        .let { toMissionModalData(JSON.parse(it)) }
}

suspend fun getMissionTutorial(
    missionId: String,
    pageNumber: Int,
    locales: List<Locale>
): Pagination<Tutorial> {
    return window.fetch("/game/api/tutorials?missionId=$missionId&locales=${locales.joinToString(",")}&pageNumber=$pageNumber&pageSize=20")
        .await()
        .apply(Response::checkStatusCode)
        .text()
        .await()
        .let { toPagination(JSON.parse(it), ::toTutorial) }
}
