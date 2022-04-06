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
@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.bytelegend.client.app.ui.mission

import BootstrapNavItem
import BootstrapNavLink
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.ui.bootstrap.BootstrapNav
import com.bytelegend.app.shared.entities.PullRequestAnswer
import com.bytelegend.app.shared.entities.PullRequestCheckRun
import com.bytelegend.client.app.engine.GAME_CLOCK_1S_EVENT
import com.bytelegend.client.app.external.codeBlock
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.loadingSpinner
import com.bytelegend.client.app.ui.setState
import com.bytelegend.client.app.ui.unsafeSpan
import kotlinx.js.jso
import react.ChildrenBuilder
import react.Component
import react.Fragment
import react.State
import react.create
import react.dom.html.ReactHTML.p

interface PullRequestLogModalProps : GameProps {
    var answer: PullRequestAnswer
}

interface PullRequestLogModalState : State {
    var activeTabIndex: Int
}

class PullRequestLogModal : Component<PullRequestLogModalProps, PullRequestLogModalState>() {
    private val refreshTimerEventListener: EventListener<String> = {
        setState { }
    }

    init {
        state = jso { activeTabIndex = 0 }
    }

    override fun render() = Fragment.create {
        if (props.answer.checkRuns.size > 1) {
            BootstrapNav {
                variant = "tabs"
                props.answer.checkRuns.forEachIndexed { index: Int, checkRun: PullRequestCheckRun ->
                    BootstrapNavItem {
                        BootstrapNavLink {
                            active = index == state.activeTabIndex
                            eventKey = "tab-$index"
                            onSelect = {
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

    private fun ChildrenBuilder.renderTab() {
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
                        lines = listOf(downloadedLog.getCompleted())
                        language = "log"
                    }
                }
            } else {
                // if the log is being downloaded, let's show the live log for now.
                if (liveLog.isNotEmpty()) {
                    codeBlock {
                        lines = liveLog
                        language = "log"
                    }
                }
                loadingSpinner()
            }
        } else {
            codeBlock {
                lines = liveLog
                language = "log"
            }
        }
    }

    override fun componentDidMount() {
        props.game.eventBus.on(GAME_CLOCK_1S_EVENT, refreshTimerEventListener)
    }

    override fun componentWillUnmount() {
        props.game.eventBus.remove(GAME_CLOCK_1S_EVENT, refreshTimerEventListener)
    }
}
