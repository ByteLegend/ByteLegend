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
import com.bytelegend.app.client.api.GameMission
import com.bytelegend.app.client.api.missionRepaintEvent
import com.bytelegend.app.client.misc.uuid
import com.bytelegend.app.client.ui.bootstrap.BootstrapAlert
import com.bytelegend.app.shared.entities.MissionModalData
import com.bytelegend.app.shared.entities.PullRequestAnswer
import com.bytelegend.app.shared.entities.mission.ChallengeSpec
import com.bytelegend.app.shared.entities.mission.ChallengeType
import com.bytelegend.app.shared.protocol.ChallengeUpdateEventData
import com.bytelegend.app.shared.protocol.LogStreamEventData
import com.bytelegend.app.shared.protocol.logStreamEvent
import com.bytelegend.app.shared.util.currentTimeMillis
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.jsStyle
import com.bytelegend.client.app.ui.minimap.mapToNativeJsArray
import com.bytelegend.client.app.ui.minimap.nativeJsArrayOf
import com.bytelegend.client.app.ui.setState
import com.bytelegend.client.app.ui.unsafeSpan
import csstype.ClassName
import kotlinx.js.jso
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLIFrameElement
import org.w3c.dom.url.URLSearchParams
import react.Component
import react.Fragment
import react.State
import react.create
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.iframe
import react.react

private const val WEBEDITOR_IFRAME_INIT_COMPLETED = "webeditor.init.completed"
private const val WEBEDITOR_IFRAME_READY_FOR_INIT_DATA = "webeditor.ready.for.init.data"

interface WebEditorState : State {
    var showSubmitAnswerButton: Boolean
}

interface WebEditorProps : GameProps {
    var missionModalData: MissionModalData

    // Supported challenge type:
    // 1. Star: hide activity bar, show tutorial view, not open any markdown by default.
    // 2. PullRequest/HeroNoticeboard: show all views
    // 3. Question: hide activity bar, show tutorial view, open the question readme by default (it can be a url or string).
    var challengeSpec: ChallengeSpec
    var whitelist: List<String>?
}

class WebEditor : Component<WebEditorProps, WebEditorState>() {
    private val webEditorIframeId = "webeditor-${uuid()}"
    private val useLocalWebEditor: Boolean by lazy {
        URLSearchParams(window.location.search).get("useLocalWebEditor")?.toBoolean() ?: false
    }
    private val useLocalGitHubApi: Boolean by lazy {
        URLSearchParams(window.location.search).get("useLocalGitHubApi")?.toBoolean() ?: false
    }

    private val missionRepaintEventListener: EventListener<ChallengeUpdateEventData> = this::onChallengeAnswersUpdate
    private val liveLogStreamEventListener: EventListener<LogStreamEventData> = this::onLiveLogStream
    private val openTutorialsEventListener: EventListener<Nothing> = this::onOpenTutorials
    private val webEditorReadyForInitDataEventListener: EventListener<Nothing> = {
        postMessageToWebEditorIframe(jso<dynamic> {
            bytelegendInitData = determineWebEditorInitData()
        })
    }
    private val webEditorInitCompletedEventListener: EventListener<Nothing> = {
        setState { showSubmitAnswerButton = true }
    }
    private val isPullRequestChallenge
        get() = props.challengeSpec.type == ChallengeType.PullRequest || props.challengeSpec.type == ChallengeType.HeroNoticeboard

    // Add a timestamp to webeditor url to avoid disk cache, but don't change in every render
    // Otherwise, if `render()` method is called twice, the page may flickr.
    private var timestamp: Long = currentTimeMillis()

    init {
        state = jso { showSubmitAnswerButton = false }
    }

    override fun render() = Fragment.create {
        div {
            className = ClassName("webeditor-wrapper")
            if (props.game.heroPlayer.isAnonymous) {
                BootstrapAlert {
                    show = true
                    variant = "warning"
                    unsafeSpan(props.game.i("ToSubmitAnswerClickHereToLogin"))
                }
            }
            iframe {
                id = webEditorIframeId
                src = determineWebEditorUrl()
                jsStyle {
                    border = "none"
                }
            }

            if (state.showSubmitAnswerButton && isPullRequestChallenge && !props.game.heroPlayer.isAnonymous) {
                child(WebEditorButtonGroup::class.react, jso {
                    game = props.game
                    challengeId = props.challengeSpec.id
                    missionModalData = props.missionModalData
                    onClickSubmitAnswerButton = {
                        val arg = nativeJsArrayOf()
                        postMessageToWebEditorIframe(jso<dynamic> {
                            forwardCommand = "bytelegend.submitAnswer"
                            forwardCommandArgs = arg
                        })
                    }
                })
            }
        }
    }

    private fun getLatestOpenPullRequest(): PullRequestAnswer? {
        return props.game.activeScene.challengeAnswers.getPullRequestChallengeAnswersByChallengeId(props.challengeSpec.id).firstOrNull { it.open && !it.stale }
    }

    private fun determineWebEditorUrl(): String {
        val baseUrl = if (useLocalWebEditor && window.location.hostname == "localhost") "http://localhost:5000" else "https://webeditor.bytelegend.com"

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
        val githubApiBaseUrl = when {
            useLocalGitHubApi -> "${window.location.protocol}//${window.location.host}/ghapi"
            props.game.heroPlayer.isAnonymous -> "https://bytelegend.com/ghapi"
            else -> "https://ghapi.bytelegend.com"
        }
        val tutorialsPrice = props.game.activeScene.objects.getById<GameMission>(props.missionModalData.missionId).gameMapMission.tutorialsPrice

        val ret = jso<dynamic> {
            missionId = props.missionModalData.missionId
            challengeId = props.challengeSpec.id
            this.apiServer = apiServer
            this.githubApiBaseUrl = githubApiBaseUrl
            this.whitelist = whitelist
            this.tutorialsPrice = tutorialsPrice
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
                jso<dynamic> {
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
        props.game.eventBus.on(WEBEDITOR_OPEN_TUTORIALS_EVENT, openTutorialsEventListener)
        props.game.eventBus.on(logStreamEvent(props.game.activeScene.map.id), liveLogStreamEventListener)
        props.game.eventBus.on(missionRepaintEvent(props.missionModalData.missionId), missionRepaintEventListener)
        props.game.eventBus.on(WEBEDITOR_IFRAME_INIT_COMPLETED, webEditorInitCompletedEventListener)
        props.game.eventBus.on(WEBEDITOR_IFRAME_READY_FOR_INIT_DATA, webEditorReadyForInitDataEventListener)
    }

    override fun componentWillUnmount() {
        props.game.eventBus.remove(WEBEDITOR_OPEN_TUTORIALS_EVENT, openTutorialsEventListener)
        props.game.eventBus.remove(WEBEDITOR_IFRAME_INIT_COMPLETED, webEditorInitCompletedEventListener)
        props.game.eventBus.remove(missionRepaintEvent(props.missionModalData.missionId), missionRepaintEventListener)
        props.game.eventBus.remove(logStreamEvent(props.game.activeScene.map.id), liveLogStreamEventListener)
        props.game.eventBus.remove(WEBEDITOR_IFRAME_READY_FOR_INIT_DATA, webEditorReadyForInitDataEventListener)
    }

    private fun onOpenTutorials(n: Nothing) {
        postMessageToWebEditorIframe(jso<dynamic> {
            forwardCommand = "bytelegend.views.tutorials.focus"
            forwardCommandArgs = nativeJsArrayOf()
        })
    }

    private fun onLiveLogStream(logStreamEventData: LogStreamEventData) {
        if (logStreamEventData.challengeId == props.challengeSpec.id) {
            val args = nativeJsArrayOf(logStreamEventData.checkRunId, logStreamEventData.lines.toTypedArray())
            postMessageToWebEditorIframe(jso<dynamic> {
                forwardCommand = "bytelegend.appendLog"
                forwardCommandArgs = args
            })
        }
    }

    private fun onChallengeAnswersUpdate(eventData: ChallengeUpdateEventData) {
        if (eventData.newValue.challengeId == props.challengeSpec.id) {
            val prAnswers = props.game.activeScene.challengeAnswers.getPullRequestChallengeAnswersByChallengeId(props.challengeSpec.id)
            if (!prAnswers.anyCheckRunning()) {
                props.game.eventBus.emit(ANSWER_BUTTON_CONTROL_EVENT, jso<dynamic> {
                    spinning = false
                    textId = "SubmitAnswer"
                })
            }

            val args = nativeJsArrayOf(prAnswers.toJsArray())
            postMessageToWebEditorIframe(jso<dynamic> {
                forwardCommand = "bytelegend.updateAnswers"
                forwardCommandArgs = args
            })
        }
    }
}
