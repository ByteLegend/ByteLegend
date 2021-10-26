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

import BootstrapDropdownItem
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.missionRepaintEvent
import com.bytelegend.app.client.ui.bootstrap.BootstrapButton
import com.bytelegend.app.client.ui.bootstrap.BootstrapSplitButton
import com.bytelegend.app.shared.entities.PullRequestAnswer
import com.bytelegend.app.shared.protocol.ChallengeUpdateEventData
import com.bytelegend.client.app.engine.DefaultGameMission
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.Layer
import kotlinext.js.jsObject
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.State
import react.createElement
import react.dom.div
import react.dom.jsStyle
import react.react
import react.setState

interface MissionTitleAnswersState : State

interface MissionTitleAnswerProps : GameProps {
    var mission: DefaultGameMission
}

class MissionTitleAnswers : RComponent<MissionTitleAnswerProps, MissionTitleAnswersState>() {
    private val challengeUpdateEventListener: EventListener<ChallengeUpdateEventData> = {
        setState { }
    }

    override fun RBuilder.render() {
        div {
            val z = Layer.MissionTitlePullRequestAnswerButton.zIndex()
            attrs.jsStyle {
                zIndex = z
            }
            attrs.onClickFunction = { it: dynamic ->
                it.stopPropagation()
            }

            val pullRequestAnswers = props.game.activeScene.challengeAnswers.getPullRequestChallengeAnswersByMissionId(props.mission.id)
            if (pullRequestAnswers.isNotEmpty()) {
                renderPullRequestAnswers(pullRequestAnswers)
            }
        }
    }

    private fun RBuilder.renderPullRequestAnswers(pullRequestAnswers: List<PullRequestAnswer>) {
        if (pullRequestAnswers.size == 1) {
            BootstrapButton {
                attrs.variant = "light"
                attrs.size = "sm"
                child(MissionTitlePullRequestAnswerButton::class) {
                    attrs.game = props.game
                    attrs.pullRequestAnswer = pullRequestAnswers[0]
                }
            }
        } else {
            BootstrapSplitButton {
                attrs.drop = "right"
                attrs.variant = "light"
                attrs.size = "sm"
                attrs.title = createElement(MissionTitlePullRequestAnswerButton::class.react, jsObject {
                    this.game = props.game
                    pullRequestAnswer = pullRequestAnswers[0]
                })

                pullRequestAnswers.forEachIndexed { index, pullRequestAnswer ->
                    if (index != 0) {
                        BootstrapDropdownItem {
                            val z = Layer.MissionTitlePullRequestAnswerButton.zIndex() + 2
                            attrs.style = jsObject {
                                zIndex = z
                            }
                            child(MissionTitlePullRequestAnswerButton::class) {
                                attrs.game = props.game
                                attrs.pullRequestAnswer = pullRequestAnswer
                            }
                        }
                    }
                }
            }
        }
    }

    override fun componentDidMount() {
        props.game.eventBus.on(missionRepaintEvent(props.mission.id), challengeUpdateEventListener)
    }

    override fun componentWillUnmount() {
        props.game.eventBus.remove(missionRepaintEvent(props.mission.id), challengeUpdateEventListener)
    }
}
