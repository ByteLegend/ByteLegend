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
@file:Suppress("UnsafeCastFromDynamic", "UNUSED_PARAMETER")

package com.bytelegend.client.app.ui.mission

import BootstrapNavItem
import BootstrapNavLink
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.missionRepaintEvent
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapNav
import com.bytelegend.app.shared.entities.ChallengeTabData
import com.bytelegend.app.shared.entities.DiscussionsTabData
import com.bytelegend.app.shared.entities.HeroNoticeboardTabData
import com.bytelegend.app.shared.entities.MissionModalData
import com.bytelegend.app.shared.entities.MissionTabData
import com.bytelegend.app.shared.entities.MissionTabType
import com.bytelegend.app.shared.protocol.ChallengeUpdateEventData
import com.bytelegend.client.app.engine.DefaultGameScene
import com.bytelegend.client.app.engine.MISSION_DATA_LOAD_FINISH
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.heronoticeboard.JavaIslandHeroNoticeboard
import com.bytelegend.client.app.ui.loadingSpinner
import com.bytelegend.client.app.ui.setState
import com.bytelegend.client.app.ui.unsafeDiv
import csstype.ClassName
import kotlinx.js.jso
import kotlinx.browser.window
import react.ChildrenBuilder
import react.Fragment
import react.State
import react.create
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.span
import react.react

interface MissionModalProps : GameProps {
    var missionId: String
}

interface MissionModalState : State {
    var activeTabIndex: Int
}

class MissionModal : GameUIComponent<MissionModalProps, MissionModalState>() {
    private val missionDataLoadFinishListener: EventListener<String> = this::onMissionDataLoadFinish
    private val onMissionRepaintListener: EventListener<ChallengeUpdateEventData> = this::onMissionRepaint

    @Suppress("UNUSED_PARAMETER")
    private fun onMissionRepaint(eventData: ChallengeUpdateEventData) {
        // refresh the star count at tab index
        setState {}
    }

    init {
        state = jso { activeTabIndex = 0 }
    }

    override fun render() = Fragment.create {
        child(ModalCloseButton::class.react, jso {
            onClickFunction = {
                game.modalController.hide(props.missionId)
            }
        })
        BootstrapModalBody {
            className = "mission-modal-body"
            val missions = game.activeScene.unsafeCast<DefaultGameScene>().missions
            if (missions.isMissionModalDataLoading(props.missionId)) {
                loadingSpinner()
            } else {
                val mission = missions.getMissionModalDataById(props.missionId)
                if (mission.tabs.isEmpty()) {
                    return@BootstrapModalBody
                }
                BootstrapNav {
                    variant = "tabs"
                    var challengeCounter = 0
                    val moreThanOneChallenge = mission.tabs.count { it.title == "MissionChallenge" } > 1
                    mission.tabs.forEachIndexed { index: Int, tab: MissionTabData<*> ->
                        BootstrapNavItem {
                            BootstrapNavLink {
                                active = (index == state.activeTabIndex)
                                eventKey = "tab-$index"
                                span {
                                    +i(tab.title)
                                    if (tab.title == "MissionChallenge") {
                                        challengeCounter++
                                        if (moreThanOneChallenge) {
                                            +"$challengeCounter "
                                        }
                                        child(TitleStarCounter::class.react, jso {
                                            val challengeSpec = tab.unsafeCast<ChallengeTabData>().data
                                            total = challengeSpec.star
                                            current = game.activeScene.challengeAnswers.challengeStar(challengeSpec.id)
                                            starSize = 16
                                        })
                                    }
                                }
                                onSelect = {
                                    if (tab.type == MissionTabType.Discussions) {
                                        window.open(tab.unsafeCast<DiscussionsTabData>().data.url, "_blank")
                                    } else {
                                        setState {
                                            activeTabIndex = index
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                val activeTab = mission.tabs[state.activeTabIndex]
                div {
                    when (activeTab.type) {
                        MissionTabType.QuestionChallenge -> renderQuestionChallenge(mission, activeTab.asDynamic())
                        MissionTabType.StarChallenge -> renderStarChallenge(mission, activeTab.asDynamic())
                        MissionTabType.PullRequestChallenge -> renderPullRequestChallenge(mission, activeTab.asDynamic())
                        MissionTabType.HeroNoticeboardChallenge -> heroNoticeboardChallenge(mission, activeTab.asDynamic())
                        MissionTabType.TextContentChallenge -> textContentChallenge(activeTab.asDynamic())
                        else -> throw IllegalArgumentException(activeTab.title)
                    }
                }
            }
        }
    }

    private fun ChildrenBuilder.heroNoticeboardChallenge(missionModalData: MissionModalData, tab: HeroNoticeboardTabData) {
        child(JavaIslandHeroNoticeboard::class.react, jso {
            game = props.game
            this.missionModalData = missionModalData
            initTiles = tab.data.tiles
            totalPage = tab.data.page
            challengeSpec = tab.challengeSpec
            whitelist = tab.whitelist
        })
    }

    private fun ChildrenBuilder.textContentChallenge(tab: ChallengeTabData) {
        unsafeDiv(i(tab.data.spec)) {
            className = ClassName("mission-tab-content")
        }
    }

    private fun ChildrenBuilder.renderPullRequestChallenge(missionModalData: MissionModalData, tab: ChallengeTabData) {
        child(PullRequestChallengeTab::class.react, jso {
            game = props.game
            this.missionModalData = missionModalData
            challengeSpec = tab.data
            whitelist = tab.whitelist
        })
    }

    private fun ChildrenBuilder.renderStarChallenge(missionModalData: MissionModalData, tab: ChallengeTabData) {
        child(StarChallengeTab::class.react, jso {
            game = props.game
            this.missionModalData = missionModalData
            contentHtml = props.game.i(tab.data.readme)
            challengeSpec = tab.data
        })
    }

    private fun ChildrenBuilder.renderQuestionChallenge(missionModalData: MissionModalData, tabData: ChallengeTabData) {
        child(QuestionChallengeTab::class.react, jso {
            game = props.game
            this.missionModalData = missionModalData
            challengeSpec = tabData.data
        })
    }

    private fun onMissionDataLoadFinish(missionId: String) {
        if (missionId == props.missionId) {
            setState { }
        }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        props.game.eventBus.on(MISSION_DATA_LOAD_FINISH, missionDataLoadFinishListener)
        props.game.eventBus.on(missionRepaintEvent(props.missionId), onMissionRepaintListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.game.eventBus.remove(missionRepaintEvent(props.missionId), onMissionRepaintListener)
    }
}
