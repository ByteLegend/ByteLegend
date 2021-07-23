@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.bytelegend.client.app.ui.mission

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.PullRequestLogContainer
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.entities.PullRequestAnswer
import com.bytelegend.app.shared.entities.PullRequestCheckRun
import com.bytelegend.app.shared.protocol.LogStreamEventData
import com.bytelegend.app.shared.protocol.logStreamEvent
import com.bytelegend.client.app.engine.GameMission
import com.bytelegend.client.app.web.checkStatusCode
import com.bytelegend.client.utils.JSArrayBackedList
import com.bytelegend.client.utils.JSObjectBackedMap
import kotlinx.browser.window
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.await
import org.w3c.fetch.Response

class DefaultPullRequestLogContainer(
    private val gameScene: GameScene
) : PullRequestLogContainer {
    private val liveLogs: MutableMap<String, LiveLog> = JSObjectBackedMap()
    private val downloadedLogs: MutableMap<String, Deferred<String>> = JSObjectBackedMap()
    private val eventBus = gameScene.gameRuntime.eventBus

    init {
        eventBus.on(logStreamEvent(gameScene.map.id), this::onLogStreamEvent)
    }

    private fun liveLogId(checkRunId: String) = "$checkRunId-live-log"
    private fun downloadedLogId(checkRunId: String) = "$checkRunId-downloaded-log"

    private fun onLogStreamEvent(logStreamEventData: LogStreamEventData) {
        val id = liveLogId(logStreamEventData.checkRunId)
        var currentLiveLog = liveLogs[id]
        if (currentLiveLog == null) {
            val point = gameScene.objects.getById<GameMission>(logStreamEventData.missionId).gameMapMission.gridCoordinate
            currentLiveLog = LiveLog(id, point)
            liveLogs[id] = currentLiveLog
        }
        if (logStreamEventData.last == true) {
            currentLiveLog.effect.close()
        } else {
            currentLiveLog.addLines(logStreamEventData.lines)
        }
    }

    override fun getLiveLogsByAnswer(answer: PullRequestAnswer, checkRunId: String): List<String> {
        return liveLogs[liveLogId(checkRunId)]?.lines ?: emptyList()
    }

    override fun downloadLogByAnswerAsync(answer: PullRequestAnswer, checkRun: PullRequestCheckRun): Deferred<String> {
        val id = downloadedLogId(checkRun.id)
        var downloadedLog = downloadedLogs[id]
        if (downloadedLog == null) {
            downloadedLog = GlobalScope.async { download(answer.repoFullName, checkRun.sha, checkRun.id) }
            downloadedLogs[id] = downloadedLog
        }
        return downloadedLog
    }

    private suspend fun download(repo: String, sha: String, checkRunId: String): String {
        return window.fetch("/game/api/log?repo=$repo&sha=$sha&checkRunId=$checkRunId")
            .await()
            .apply(Response::checkStatusCode)
            .text()
            .await()
    }

    inner class LiveLog(
        val id: String,
        missionPoint: GridCoordinate
    ) {
        val lines: MutableList<String> = JSArrayBackedList()
        val effect: LiveLogStreamEffect = LiveLogStreamEffect(id, gameScene, missionPoint)

        fun addLines(lines: List<String>) {
            this.lines.addAll(lines)
            effect.addLines(lines)
        }
    }
}
