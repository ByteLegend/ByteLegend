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
import com.bytelegend.app.client.ui.bootstrap.BootstrapButton
import com.bytelegend.app.shared.entities.PullRequestAnswer
import com.bytelegend.app.shared.entities.mission.ChallengeSpec
import com.bytelegend.app.shared.protocol.ChallengeUpdateEventData
import com.bytelegend.app.shared.protocol.LogStreamEventData
import com.bytelegend.app.shared.protocol.logStreamEvent
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.minimap.mapToNativeJsArray
import com.bytelegend.client.app.ui.minimap.nativeJsArrayOf
import kotlinext.js.jsObject
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.id
import kotlinx.html.js.onLoadFunction
import org.w3c.dom.HTMLIFrameElement
import react.RBuilder
import react.RComponent
import react.State
import react.dom.b
import react.dom.div
import react.dom.iframe
import react.dom.p
import react.setState

private const val IFRAME_WEBEDITOR_INIT_COMPLETED = "webeditor.init.completed"

interface WebEditorState : State {
    var open: Boolean
    var showSubmitAnswerButton: Boolean
}

interface WebEditorProps : GameProps {
    var missionId: String
    var challengeSpec: ChallengeSpec
    var whitelist: List<String>
}

class WebEditor : RComponent<WebEditorProps, WebEditorState>() {
    private val webEditorIframeId = "webeditor-${com.bytelegend.client.app.obj.uuid()}"

    private val missionRepaintEventListener: EventListener<ChallengeUpdateEventData> = this::onChallengeAnswersUpdate
    private val liveLogStreamEventListener: EventListener<LogStreamEventData> = this::onLiveLogStream
    private val webEditorInitCompletedEventListener: EventListener<Nothing> = {
        setState { showSubmitAnswerButton = true }
    }

    override fun WebEditorState.init() {
        open = false
        showSubmitAnswerButton = false
    }

    override fun RBuilder.render() {
        if (!state.open) {
            if (props.game.heroPlayer.isAnonymous) {
                BootstrapButton {
                    attrs.className = "open-close-webeditor-btn"
                    attrs.disabled = true
                    +"↓ ${props.game.i("OpenWebEditor")}"
                }
                p("text-align-center") {
                    b {
                        +props.game.i("YouMustSignInToUseWebEditor")
                    }
                }
            } else {
                BootstrapButton {
                    attrs.className = "open-close-webeditor-btn"
                    +"↓ ${props.game.i("OpenWebEditor")}"
                    attrs.onClick = {
                        setState {
                            open = true
                        }
                    }
                }
            }
        } else {
            BootstrapButton {
                attrs.className = "open-close-webeditor-btn"
                +"↑ ${props.game.i("CloseWebEditor")}"
                attrs.onClick = {
                    setState {
                        open = false
                        showSubmitAnswerButton = false
                    }
                }
            }

            div("webeditor-wrapper") {
                iframe {
                    attrs.id = webEditorIframeId
                    attrs.src = determineWebEditorUrl()
                    attrs.attributes["frameBorder"] = "0"
                    attrs.onLoadFunction = {
                        postMessageToWebEditorIframe(jsObject<dynamic> {
                            bytelegendInitData = getWebEditorInitData()
                        })
                    }
                }

                if (state.showSubmitAnswerButton) {
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
    }

    private fun getLatestOpenPullRequest(): PullRequestAnswer? {
        return props.game.activeScene.challengeAnswers.getPullRequestChallengeAnswersByChallengeId(props.challengeSpec.id).firstOrNull { it.open }
    }

    /**
     * If there're any open PR, locate to {baseUrl}/{owner}/{repo}/blob/{branch}/{whitelistFilePath}
     * Else: {baseUrl}/{owner}/{repo}, this will open default README preview
     */
    private fun determineWebEditorUrl(): String {
        val baseUrl = if (window.location.hostname == "localhost") "http://localhost:5000" else "https://webeditor.bytelegend.com"

        val latestPullRequestAnswer = getLatestOpenPullRequest()
        val targetBranch =
            if (latestPullRequestAnswer != null && latestPullRequestAnswer.baseRepoFullName == latestPullRequestAnswer.headRepoFullName)
                latestPullRequestAnswer.branch
            else "main"
        return if (props.whitelist.isEmpty()) {
            "$baseUrl/${props.challengeSpec.spec.substringAfter("github.com/")}/tree/$targetBranch"
        } else {
            "$baseUrl/${props.challengeSpec.spec.substringAfter("github.com/")}/blob/$targetBranch/${props.whitelist.first()}"
        }
    }

    @Suppress("HttpUrlsUsage")
    private fun getWebEditorInitData(): dynamic = jsObject {
        missionId = props.missionId
        challengeId = props.challengeSpec.id
        apiServer = if (window.location.hostname == "localhost") "http://${window.location.host}" else "https://bytelegend.com"
        whitelist = props.whitelist.toTypedArray()
        answers = getAnswers()
        repoFullName = props.challengeSpec.spec.substringAfter("github.com/")
        i18nTexts = props.game.i18nTextsForWebEditor
        locale = props.game.locale.javascriptLocale
        liveLogs = getLiveLogs()
    }

    private fun getLiveLogs(): dynamic {
        return props.game.activeScene.challengeAnswers.getPullRequestChallengeAnswersByChallengeId(props.challengeSpec.id)
            .filter { it.isRunning }
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
        return state.open != nextState.open || state.showSubmitAnswerButton != nextState.showSubmitAnswerButton
    }

    override fun componentDidMount() {
        props.game.eventBus.on(logStreamEvent(props.game.activeScene.map.id), liveLogStreamEventListener)
        props.game.eventBus.on(missionRepaintEvent(props.missionId), missionRepaintEventListener)
        props.game.eventBus.on(IFRAME_WEBEDITOR_INIT_COMPLETED, webEditorInitCompletedEventListener)
    }

    override fun componentWillUnmount() {
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
