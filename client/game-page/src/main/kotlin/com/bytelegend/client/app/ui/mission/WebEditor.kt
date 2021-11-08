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
import com.bytelegend.app.client.ui.bootstrap.BootstrapButton
import com.bytelegend.app.shared.entities.PullRequestAnswer
import com.bytelegend.app.shared.entities.mission.ChallengeSpec
import com.bytelegend.app.shared.protocol.ChallengeUpdateEventData
import com.bytelegend.app.shared.protocol.challengeUpdateEvent
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import kotlinext.js.jsObject
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.id
import kotlinx.html.js.onLoadFunction
import org.w3c.dom.HTMLIFrameElement
import react.RBuilder
import react.State
import react.dom.b
import react.dom.div
import react.dom.iframe
import react.dom.p
import react.setState


interface WebEditorState : State {
    var open: Boolean
}

interface WebEditorProps : GameProps {
    var missionId: String
    var challengeSpec: ChallengeSpec
}

class WebEditor : GameUIComponent<WebEditorProps, WebEditorState>() {
    private val webEditorIframeId = "webeditor-${com.bytelegend.client.app.obj.uuid()}"

    private val challengeUpdateEventListener: EventListener<ChallengeUpdateEventData> = this::onChallengeAnswersUpdate

    override fun WebEditorState.init() {
        open = false
    }

    override fun RBuilder.render() {
        if (!state.open) {
            if (game.heroPlayer.isAnonymous) {
                BootstrapButton {
                    attrs.className = "open-close-webeditor-btn"
                    attrs.disabled = true
                    +"↓ ${game.i("OpenWebEditor")}"
                }
                p("text-align-center") {
                    b {
                        +i("YouMustSignInToUseWebEditor")
                    }
                }
            } else {
                BootstrapButton {
                    attrs.className = "open-close-webeditor-btn"
                    +"↓ ${game.i("OpenWebEditor")}"
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
                +"↑ ${game.i("CloseWebEditor")}"
                attrs.onClick = {
                    setState { open = false }
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

                child(SubmitAnswerButton::class) {
                    attrs.game = props.game
                    attrs.spinning = anyCheckRunning()
                    attrs.onClick = {
                    }
                }
            }
        }
    }

    private fun getLatestOpenPullRequest(): PullRequestAnswer? {
        return props.game.activeScene.challengeAnswers.getPullRequestChallengeAnswersByChallengeId(props.challengeSpec.id).firstOrNull { it.open }
    }

    private fun determineWebEditorUrl(): String {
        val baseUrl = if (window.location.host == "localhost") "http://localhost:5000" else "https://webeditor.bytelegend.com"
        val targetBranch = getLatestOpenPullRequest()?.branch ?: "main"
        return "$baseUrl/${props.challengeSpec.spec.substringAfter("github.com/")}/tree/$targetBranch"
    }

    private fun getWebEditorInitData(): dynamic = jsObject {
        apiServer = if (window.location.host == "localhost") "http://${window.location.hostname}" else "https://webeditor.bytelegend.com"
        answers = getAnswers()

    }

    private fun getAnswers() = props.game.activeScene.challengeAnswers.getPullRequestChallengeAnswersByChallengeId(props.challengeSpec.id)

    private fun getI18nTexts(): dynamic = jsObject {
    }

    private fun getAnswersForWebEditor(): dynamic {
        val pullRequestAnswers = getAnswers()
    }

    private fun postMessageToWebEditorIframe(message: Any) {
        val iframe = document.getElementById(webEditorIframeId)!!
        iframe.unsafeCast<HTMLIFrameElement>().contentWindow!!.postMessage(message, "*")
    }

    override fun componentDidMount() {
        super.componentDidMount()
        props.game.eventBus.on(challengeUpdateEvent(props.missionId), challengeUpdateEventListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.game.eventBus.remove(challengeUpdateEvent(props.missionId), challengeUpdateEventListener)
    }

    private fun anyCheckRunning(): Boolean {
        return props.game.activeScene.challengeAnswers
            .getPullRequestChallengeAnswersByChallengeId(props.challengeSpec.id)
            .any { it.latestCheckRun != null && it.latestCheckRun!!.conclusion == null }
    }

    private fun onChallengeAnswersUpdate(eventData: ChallengeUpdateEventData) {
        if (eventData.newValue.challengeId == props.challengeSpec.id) {

        }
    }
}
