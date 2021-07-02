package com.bytelegend.client.app.ui.mission

import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalHeader
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalTitle
import com.bytelegend.app.client.ui.bootstrap.BootstrapSpinner
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.entities.CheckRunConclusion
import com.bytelegend.app.shared.entities.PullRequestAnswer
import com.bytelegend.app.shared.entities.PullRequestCheckRun
import com.bytelegend.client.app.engine.util.jsObjectBackedSetOf
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.icon
import kotlinx.browser.window
import kotlinx.html.classes
import kotlinx.html.js.onClickFunction
import kotlinx.html.role
import react.RBuilder
import react.RComponent
import react.RState
import react.dom.a
import react.dom.div
import react.dom.jsStyle
import react.dom.span

const val GREEN_TICK_SVG_DATA =
    "data:image/svg+xml,%3Csvg class='icon' viewBox='0 0 1418 1024' xmlns='http://www.w3.org/2000/svg' width='276.953' height='200'%3E%3Cpath d='M491.192 1023.803L.136 539.637l111.888-110.274 379.168 373.892L1305.549.433l111.888 110.274-926.245 913.096z' fill='%2322863a'/%3E%3C/svg%3E"
const val RED_CORSS_SVG_DATA =
    "data:image/svg+xml,%3Csvg class='icon' viewBox='0 0 1024 1024' xmlns='http://www.w3.org/2000/svg' width='200' height='200'%3E%3Cpath d='M572.314 512L833.74 773.427c16.691 16.691 16.691 43.725 0 60.314s-43.725 16.691-60.314 0L512 572.314l-261.427 261.53c-16.691 16.69-43.725 16.69-60.314 0-16.691-16.692-16.691-43.726 0-60.314L451.686 512l-261.53-261.427c-16.69-16.691-16.69-43.725 0-60.314 16.692-16.691 43.726-16.691 60.314 0L512 451.686 773.427 190.26c16.691-16.691 43.725-16.691 60.314 0 16.691 16.691 16.691 43.725 0 60.314L572.314 512z' fill='%23d81e06'/%3E%3C/svg%3E"

interface MissionTitlePullRequestAnswerButtonButtonProps : GameProps {
    var pullRequestAnswer: PullRequestAnswer
}

class MissionTitlePullRequestAnswerButton : RComponent<MissionTitlePullRequestAnswerButtonButtonProps, RState>() {
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
            when (props.pullRequestAnswer.latestCheckRun?.conclusion) {
                null -> pendingSpinner()
                CheckRunConclusion.SUCCESS -> icon(GREEN_TICK_SVG_DATA, PixelSize(12, 12)) {
                    attrs.classes = jsObjectBackedSetOf("icon-status-success")
                }
                else -> icon(RED_CORSS_SVG_DATA, PixelSize(12, 12)) {
                    attrs.classes = jsObjectBackedSetOf("icon-status-failure")
                }
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
            +"Action ${checkRun.id} ↗"
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
