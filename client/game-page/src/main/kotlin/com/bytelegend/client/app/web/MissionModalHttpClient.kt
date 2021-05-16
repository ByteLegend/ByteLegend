package com.bytelegend.client.app.web

import com.bytelegend.app.shared.entities.MissionModalData
import com.bytelegend.app.shared.entities.mission.Pagination
import com.bytelegend.app.shared.entities.mission.Tutorial
import com.bytelegend.app.shared.i18n.Locale
import kotlinx.browser.window
import kotlinx.coroutines.await
import org.w3c.fetch.Response

fun Response.checkStatusCode() {
    if (status < 200 || status > 400) {
        throw Exception("Got response status code $status")
    }
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
