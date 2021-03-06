@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.bytelegend.client.app.ui.mission

import BootstrapNavItem
import BootstrapNavLink
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.ui.bootstrap.BootstrapNav
import com.bytelegend.app.client.ui.bootstrap.BootstrapSpinner
import com.bytelegend.app.shared.entities.PullRequestAnswer
import com.bytelegend.app.shared.entities.PullRequestCheckRun
import com.bytelegend.client.app.engine.GAME_CLOCK_1HZ_EVENT
import com.bytelegend.client.app.external.codeBlock
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.unsafeSpan
import react.RBuilder
import react.RComponent
import react.RState
import react.dom.p
import react.setState

interface PullRequestLogModalProps : GameProps {
    var answer: PullRequestAnswer
}

interface PullRequestLogModalState : RState {
    var activeTabIndex: Int
}

class PullRequestLogModal : RComponent<PullRequestLogModalProps, PullRequestLogModalState>() {
    private val refreshTimerEventListener: EventListener<String> = {
        setState { }
    }

    override fun PullRequestLogModalState.init() {
        activeTabIndex = 0
    }

    override fun RBuilder.render() {
        if (props.answer.checkRuns.size > 1) {
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
                            +"Check ${checkRun.id}"
                        }
                    }
                }
            }
        }
        renderTab()
    }

    private fun RBuilder.renderTab() {
        val liveLog = props.game.activeScene.logs.getLiveLogsByAnswer(props.answer, props.answer.checkRuns[state.activeTabIndex].id)
        val downloadedLog = props.game.activeScene.logs.downloadLogByAnswerAsync(props.answer, props.answer.checkRuns[state.activeTabIndex])

        if (props.answer.checkRuns[state.activeTabIndex].conclusion != null) {
            if (downloadedLog.isCompleted) {
                val exception = downloadedLog.getCompletionExceptionOrNull()
                if (exception != null) {
                    p {
                        unsafeSpan("Unexpected failure, please report at <a target='_blank' href='https://github.com/ByteLegend/ByteLegend/issues'>https://github.com/ByteLegend/ByteLegend/issues</span>")
                    }
                    p {
                        +exception.stackTraceToString()
                    }
                } else {
                    codeBlock {
                        attrs.lines = listOf(downloadedLog.getCompleted())
                        attrs.language = "log"
                    }
                }
            } else {
                // if the log is being downloaded, let's show the live log for now.
                if (liveLog.isNotEmpty()) {
                    codeBlock {
                        attrs.lines = liveLog
                        attrs.language = "log"
                    }
                }
                BootstrapSpinner {
                    attrs.animation = "border"
                }
            }
        } else {
            codeBlock {
                attrs.lines = liveLog
                attrs.language = "log"
            }
        }
    }

    override fun componentDidMount() {
        props.game.eventBus.on(GAME_CLOCK_1HZ_EVENT, refreshTimerEventListener)
    }

    override fun componentWillUnmount() {
        props.game.eventBus.remove(GAME_CLOCK_1HZ_EVENT, refreshTimerEventListener)
    }
}
