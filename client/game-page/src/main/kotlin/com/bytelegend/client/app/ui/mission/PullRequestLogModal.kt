@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.bytelegend.client.app.ui.mission

import BootstrapNavItem
import BootstrapNavLink
import com.bytelegend.app.client.ui.bootstrap.BootstrapNav
import com.bytelegend.app.client.ui.bootstrap.BootstrapSpinner
import com.bytelegend.app.shared.entities.PullRequestAnswer
import com.bytelegend.app.shared.entities.PullRequestCheckRun
import com.bytelegend.client.app.engine.GAME_CLOCK_1HZ_EVENT
import com.bytelegend.client.app.external.SyntaxHighlighter
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import react.RBuilder
import react.RState
import react.setState

interface PullRequestLogModalProps : GameProps {
    var answer: PullRequestAnswer
}

interface PullRequestLogModalState : RState {
    var activeTabIndex: Int
}

class PullRequestLogModal : GameUIComponent<PullRequestLogModalProps, PullRequestLogModalState>() {
    override fun PullRequestLogModalState.init() {
        activeTabIndex = 0
    }

    override fun RBuilder.render() {
        if (props.answer.checkRuns.size > 2) {
            BootstrapNav {
                attrs.variant = "tabs"
                props.answer.checkRuns.forEachIndexed { index: Int, checkRun: PullRequestCheckRun ->
                    BootstrapNavItem {
                        BootstrapNavLink {
                            attrs.active = index == state.activeTabIndex
                            attrs.eventKey = "tab-$index"
                            attrs.onSelect = {
                                setState {
                                    activeTabIndex = index
                                }
                            }
                            +i("Action ${checkRun.id}")
                        }
                    }
                }
            }
        }

        val liveLog = activeScene.logs.getLiveLogsByAnswer(props.answer, props.answer.checkRuns[state.activeTabIndex].id)
        val downloadedLog = activeScene.logs.downloadLogByAnswerAsync(props.answer, props.answer.checkRuns[state.activeTabIndex].id)

        console.log("live: ${JSON.stringify(liveLog)}")

        if (props.answer.accomplished) {
            if (downloadedLog.isCompleted) {
                SyntaxHighlighter {
                    +downloadedLog.getCompleted()
                }
            } else {
                // if the log is being downloaded, let's show the live log for now.
                if (liveLog.isNotEmpty()) {
                    SyntaxHighlighter {
                        liveLog.joinToString("\n")
                    }
                }
                BootstrapSpinner {
                    attrs.animation = "border"
                }
            }
        } else {
            SyntaxHighlighter {
                +liveLog.joinToString("\n")
            }
        }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        props.game.eventBus.on(GAME_CLOCK_1HZ_EVENT, gameUiUpdateEventListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.game.eventBus.remove(GAME_CLOCK_1HZ_EVENT, gameUiUpdateEventListener)
    }
}
