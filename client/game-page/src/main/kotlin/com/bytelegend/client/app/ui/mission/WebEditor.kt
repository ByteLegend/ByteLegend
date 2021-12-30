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

@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.client.app.ui.mission

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.missionRepaintEvent
import com.bytelegend.app.client.ui.bootstrap.BootstrapAlert
import com.bytelegend.app.shared.entities.PullRequestAnswer
import com.bytelegend.app.shared.entities.mission.ChallengeSpec
import com.bytelegend.app.shared.entities.mission.ChallengeType
import com.bytelegend.app.shared.protocol.ChallengeUpdateEventData
import com.bytelegend.app.shared.protocol.LogStreamEventData
import com.bytelegend.app.shared.protocol.logStreamEvent
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.minimap.mapToNativeJsArray
import com.bytelegend.client.app.ui.minimap.nativeJsArrayOf
import com.bytelegend.client.app.ui.unsafeSpan
import kotlinext.js.jsObject
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.currentTimeMillis
import kotlinx.html.id
import kotlinx.html.js.onLoadFunction
import org.w3c.dom.HTMLIFrameElement
import react.RBuilder
import react.RComponent
import react.State
import react.dom.div
import react.dom.iframe
import react.setState

private const val IFRAME_WEBEDITOR_INIT_COMPLETED = "webeditor.init.completed"

interface WebEditorState : State {
    var showSubmitAnswerButton: Boolean
}

interface WebEditorProps : GameProps {
    var missionId: String

    // Supported challenge type:
    // 1. Star: hide activity bar, show tutorial view, not open any markdown by default.
    // 2. PullRequest/HeroNoticeboard: show all views
    // 3. Question: hide activity bar, show tutorial view, open the question readme by default (it can be a url or string).
    var challengeSpec: ChallengeSpec
    var whitelist: List<String>?
}

class WebEditor : RComponent<WebEditorProps, WebEditorState>() {
    private val webEditorIframeId = "webeditor-${com.bytelegend.client.app.obj.uuid()}"

    private val missionRepaintEventListener: EventListener<ChallengeUpdateEventData> = this::onChallengeAnswersUpdate
    private val liveLogStreamEventListener: EventListener<LogStreamEventData> = this::onLiveLogStream
    private val webEditorInitCompletedEventListener: EventListener<Nothing> = {
        setState { showSubmitAnswerButton = true }
    }
    private val isPullRequestChallenge
        get() = props.challengeSpec.type == ChallengeType.PullRequest || props.challengeSpec.type == ChallengeType.HeroNoticeboard

    // Add a timestamp to webeditor url to avoid disk cache, but don't change in every render
    // Otherwise, if `render()` method is called twice, the page may flickr.
    private var timestamp: Long = currentTimeMillis()

    override fun WebEditorState.init() {
        showSubmitAnswerButton = false
    }

    override fun RBuilder.render() {
        div("webeditor-wrapper") {
            if (props.game.heroPlayer.isAnonymous) {
                BootstrapAlert {
                    attrs.show = true
                    attrs.variant = "warning"
                    unsafeSpan(props.game.i("ToSubmitAnswerClickHereToLogin"))
                }
            }
            iframe {
                attrs.id = webEditorIframeId
                attrs.src = determineWebEditorUrl()
                attrs.attributes["frameBorder"] = "0"
                attrs.onLoadFunction = {
                    postMessageToWebEditorIframe(jsObject<dynamic> {
                        bytelegendInitData = determineWebEditorInitData()
                    })
                }
            }

            if (state.showSubmitAnswerButton && isPullRequestChallenge && !props.game.heroPlayer.isAnonymous) {
                child(SubmitAnswerButton::class) {
                    attrs.game = props.game
                    attrs.challengeId = props.challengeSpec.id
                    attrs.onClick = {
                        val arg = nativeJsArrayOf()
                        postMessageToWebEditorIframe(jsObject<dynamic> {
                            forwardCommand = "bytelegend.submitAnswer"
                            forwardCommandArgs = arg
                        })
                    }
                }
            }
        }
    }

    private fun getLatestOpenPullRequest(): PullRequestAnswer? {
        return props.game.activeScene.challengeAnswers.getPullRequestChallengeAnswersByChallengeId(props.challengeSpec.id).firstOrNull { it.open && !it.stale }
    }

    private fun determineWebEditorUrl(): String {
        val baseUrl = if (window.location.hostname == "localhost") "http://localhost:5000" else "https://webeditor.bytelegend.com"

        return if (isPullRequestChallenge) {
            val latestPullRequestAnswer = getLatestOpenPullRequest()
            val targetBranch =
                if (latestPullRequestAnswer != null && latestPullRequestAnswer.baseRepoFullName == latestPullRequestAnswer.headRepoFullName)
                    latestPullRequestAnswer.branch
                else "main"
            "$baseUrl/${props.challengeSpec.spec.substringAfter("github.com/")}/blob/$targetBranch/README.md?v=$timestamp"
        } else {
            "$baseUrl?v=$timestamp"
        }
    }

    @Suppress("HttpUrlsUsage")
    private fun determineWebEditorInitData(): dynamic {
        val whitelist = props.whitelist?.toTypedArray() ?: emptyArray()
        val apiServer = if (window.location.hostname == "localhost") "http://${window.location.host}" else "https://bytelegend.com"
        val githubApiBaseUrl = if (props.game.heroPlayer.isAnonymous) "${window.location.protocol}/${window.location.host}/ghapi" else "https://ghapi.bytelegend.com"
        val ret = jsObject<dynamic> {
            missionId = props.missionId
            challengeId = props.challengeSpec.id
            this.apiServer = apiServer
            this.githubApiBaseUrl = githubApiBaseUrl
            this.whitelist = whitelist
            answers = getAnswers()
            locale = props.game.locale.javascriptLocale
            localeName = props.game.locale.displayName
            i18nTexts = props.game.i18nTextsForWebEditor
            gfw = props.game.gfw
        }
        if (isPullRequestChallenge) {
            ret.repoFullName = props.challengeSpec.spec.substringAfter("github.com/")
            ret.liveLogs = getLiveLogs()
        } else {
            ret.repoFullName = "ByteLegend/ByteLegend"
            ret.showActivityBar = false
            ret.initFocusView = "tutorial"
            if (props.challengeSpec.readme.startsWith("https://")) {
                ret.initReadme = props.challengeSpec.readme
            } else {
                val readme = props.game.i(props.challengeSpec.readme)
                val replaced = if (props.game.gfw) readme.replace("https://raw.githubusercontent.com/", "$apiServer/ghraw/") else readme
                ret.initReadme = replaced
            }
        }
        return ret
    }

    private fun getLiveLogs(): dynamic {
        return props.game.activeScene.challengeAnswers.getPullRequestChallengeAnswersByChallengeId(props.challengeSpec.id)
            .filter { it.running }
            .map {
                val checkRunId = it.latestCheckRun!!.id
                val checkRunLogs = props.game.activeScene.logs.getLiveLogsByAnswer(it, checkRunId).toTypedArray()
                jsObject<dynamic> {
                    id = checkRunId
                    logs = checkRunLogs
                }
            }.toTypedArray()
    }

    // See MyAnswerTreeDataProvider
    private fun getAnswers(): dynamic {
        return props.game.activeScene.challengeAnswers.getPullRequestChallengeAnswersByChallengeId(props.challengeSpec.id).toJsArray()
    }

    private fun List<PullRequestAnswer>.toJsArray() = mapToNativeJsArray {
        JSON.parse(JSON.stringify(it))
    }

    private fun postMessageToWebEditorIframe(message: Any) {
        document.getElementById(webEditorIframeId)?.unsafeCast<HTMLIFrameElement>()?.contentWindow?.postMessage(message, "*")
    }

    /**
     * Upon GameUIRepaint event or MissionRepaint event, we don't want webeditor to refresh,
     * because when user finish the job, we want the webeditor to stay PR branch, unless user close and reopen webeditor.
     */
    override fun shouldComponentUpdate(nextProps: WebEditorProps, nextState: WebEditorState): Boolean {
        return state.showSubmitAnswerButton != nextState.showSubmitAnswerButton || props.challengeSpec.id != nextProps.challengeSpec.id
    }

    override fun componentDidMount() {
        props.game.eventBus.on(logStreamEvent(props.game.activeScene.map.id), liveLogStreamEventListener)
        props.game.eventBus.on(missionRepaintEvent(props.missionId), missionRepaintEventListener)
        props.game.eventBus.on(IFRAME_WEBEDITOR_INIT_COMPLETED, webEditorInitCompletedEventListener)
    }

    override fun componentWillUnmount() {
        timestamp = currentTimeMillis()
        props.game.eventBus.remove(IFRAME_WEBEDITOR_INIT_COMPLETED, webEditorInitCompletedEventListener)
        props.game.eventBus.remove(missionRepaintEvent(props.missionId), missionRepaintEventListener)
        props.game.eventBus.remove(logStreamEvent(props.game.activeScene.map.id), liveLogStreamEventListener)
    }

    private fun onLiveLogStream(logStreamEventData: LogStreamEventData) {
        if (logStreamEventData.challengeId == props.challengeSpec.id) {
            val args = nativeJsArrayOf(logStreamEventData.checkRunId, logStreamEventData.lines.toTypedArray())
            postMessageToWebEditorIframe(jsObject<dynamic> {
                forwardCommand = "bytelegend.appendLog"
                forwardCommandArgs = args
            })
        }
    }

    private fun onChallengeAnswersUpdate(eventData: ChallengeUpdateEventData) {
        if (eventData.newValue.challengeId == props.challengeSpec.id) {
            val prAnswers = props.game.activeScene.challengeAnswers.getPullRequestChallengeAnswersByChallengeId(props.challengeSpec.id)
            if (!prAnswers.anyCheckRunning()) {
                props.game.eventBus.emit(ANSWER_BUTTON_CONTROL_EVENT, jsObject<dynamic> {
                    spinning = false
                    textId = "SubmitAnswer"
                })
            }

            val args = nativeJsArrayOf(prAnswers.toJsArray())
            postMessageToWebEditorIframe(jsObject<dynamic> {
                forwardCommand = "bytelegend.updateAnswers"
                forwardCommandArgs = args
            })
        }
    }
}
