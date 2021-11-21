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
package com.bytelegend.client.app.ui.mission

import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalHeader
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalTitle
import com.bytelegend.app.client.ui.bootstrap.BootstrapSpinner
import com.bytelegend.app.shared.entities.CheckRunConclusion
import com.bytelegend.app.shared.entities.PullRequestAnswer
import com.bytelegend.app.shared.entities.PullRequestCheckRun
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.icon
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.browser.window
import kotlinx.html.classes
import kotlinx.html.js.onClickFunction
import kotlinx.html.role
import react.RBuilder
import react.RComponent
import react.State
import react.dom.a
import react.dom.div
import react.dom.jsStyle
import react.dom.span

interface MissionTitlePullRequestAnswerButtonButtonProps : GameProps {
    var pullRequestAnswer: PullRequestAnswer
}

class MissionTitlePullRequestAnswerButton : RComponent<MissionTitlePullRequestAnswerButtonButtonProps, State>() {
    private fun RBuilder.pendingSpinner() {
        BootstrapSpinner {
            attrs.animation = "grow"
            attrs.size = "sm"
            attrs.`as` = "span"
            attrs.variant = "warning"
        }
    }

    override fun RBuilder.render() {
        div {
            attrs.classes = jsObjectBackedSetOf("pull-request-answer-button")
            val latestConclusion = props.pullRequestAnswer.latestCheckRun?.conclusion
            when {
                props.pullRequestAnswer.accomplished || latestConclusion == CheckRunConclusion.SUCCESS.name ->
                    icon(12, jsObjectBackedSetOf("icon-status-success", "green-tick-icon"))
                latestConclusion == null -> pendingSpinner()
                else -> icon(12, jsObjectBackedSetOf("icon-status-failure", "red-cross-icon"))
            }

            span {
                attrs.jsStyle {
                    marginLeft = "10px"
                }
                +"Pull request #${props.pullRequestAnswer.number}"
            }

            attrs.onClickFunction = {
                if (props.pullRequestAnswer.checkRuns.isEmpty()) {
                    // No check runs somehow, let's just open the PR
                    window.open(props.pullRequestAnswer.htmlUrl, "_blank")
                } else {
                    props.game.modalController.show {
                        attrs.size = "xl"
                        BootstrapModalHeader {
                            attrs.closeButton = true
                            BootstrapModalTitle {
                                openPullRequestButton()
                                props.pullRequestAnswer.checkRuns.forEach {
                                    openGitHubActionButton(it)
                                }
                            }
                        }
                        BootstrapModalBody {
                            child(PullRequestLogModal::class) {
                                attrs.game = props.game
                                attrs.answer = props.pullRequestAnswer
                            }
                        }
                    }
                }
            }
        }
    }

    private fun RBuilder.openGitHubActionButton(checkRun: PullRequestCheckRun) {
        a {
            attrs.href = checkRun.htmlUrl
            attrs.classes = jsObjectBackedSetOf("btn", "btn-sm", "btn-outline-info")
            attrs.target = "_blank"
            attrs.role = "button"
            +"Check ${checkRun.id} ↗"
        }
    }

    private fun RBuilder.openPullRequestButton() {
        a {
            attrs.href = props.pullRequestAnswer.htmlUrl
            attrs.classes = jsObjectBackedSetOf("btn", "btn-sm", "btn-outline-info")
            attrs.target = "_blank"
            attrs.role = "button"
            +"Pull request #${props.pullRequestAnswer.number} ↗"
        }
    }
}
