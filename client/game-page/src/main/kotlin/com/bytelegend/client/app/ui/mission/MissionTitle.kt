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

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.missionRepaintEvent
import com.bytelegend.app.shared.protocol.ChallengeUpdateEventData
import com.bytelegend.client.app.engine.DefaultGameMission
import com.bytelegend.client.app.ui.Layer
import com.bytelegend.client.app.ui.absoluteDiv
import com.bytelegend.client.app.ui.setState
import kotlinx.js.jso
import react.Fragment
import react.ReactNode
import react.create
import react.dom.html.ReactHTML.div
import react.react

interface MissionTitleProps : BouncingTitleProps {
    var totalStar: Int
    var mission: DefaultGameMission
}

interface MissionTitleState : BouncingTitleState {
    var currentStar: Int
}

class MissionTitle(props: MissionTitleProps) : AbstractBouncingTitleWidget<MissionTitleProps, MissionTitleState>(props) {
    private val onMissionRepaintListener: EventListener<ChallengeUpdateEventData> = this::onMissionRepaint

    @Suppress("UNUSED_PARAMETER")
    private fun onMissionRepaint(eventData: ChallengeUpdateEventData) {
        setState {
            currentStar = props.gameScene.challengeAnswers.missionStar(props.mission.id)
        }
    }

    init {
        state = jso {
            hovered = false
            currentStar = props.gameScene.challengeAnswers.missionStar(props.mission.id)
        }
    }

    override fun render(): ReactNode {
        return Fragment.create {
            renderTitle {
                absoluteDiv(
                    zIndex = Layer.BouncingTitle.zIndex() + 3,
                    className = "title-star-answer-box"
                ) {
                    div {
                        child(TitleStarCounter::class.react, jso {
                            total = props.totalStar
                            current = state.currentStar
                            starSize = 24
                        })
                    }
                }
            }
        }
    }

    override fun shouldComponentUpdate(nextProps: MissionTitleProps, nextState: MissionTitleState): Boolean {
        return super.shouldComponentUpdate(nextProps, nextState) || state.currentStar != nextState.currentStar
    }

    override fun UNSAFE_componentWillReceiveProps(nextProps: MissionTitleProps) {
        setState {
            currentStar = props.gameScene.challengeAnswers.missionStar(props.mission.id)
        }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        props.gameScene.gameRuntime.eventBus.on(missionRepaintEvent(props.mission.id), onMissionRepaintListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.gameScene.gameRuntime.eventBus.remove(missionRepaintEvent(props.mission.id), onMissionRepaintListener)
    }
}
