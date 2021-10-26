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
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.client.api.missionRepaintEvent
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapNav
import com.bytelegend.app.client.ui.bootstrap.BootstrapSpinner
import com.bytelegend.app.shared.entities.ChallengeTabData
import com.bytelegend.app.shared.entities.DiscussionsTabData
import com.bytelegend.app.shared.entities.HeroNoticeboardTabData
import com.bytelegend.app.shared.entities.MissionTabData
import com.bytelegend.app.shared.entities.MissionTabType
import com.bytelegend.app.shared.entities.TutorialsTabData
import com.bytelegend.app.shared.protocol.ChallengeUpdateEventData
import com.bytelegend.client.app.engine.DefaultGameScene
import com.bytelegend.client.app.engine.MISSION_DATA_LOAD_FINISH
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.heronoticeboard.JavaIslandHeroNoticeboard
import com.bytelegend.client.app.ui.unsafeDiv
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.browser.window
import kotlinx.html.classes
import react.RBuilder
import react.State
import react.dom.div
import react.dom.span
import react.setState

interface MissionModalProps : GameProps {
    var missionId: String
    var onClose: UnitFunction
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

    override fun MissionModalState.init() {
        activeTabIndex = 0
    }

    override fun RBuilder.render() {
        child(ModalCloseButton::class) {
            attrs.onClickFunction = {
                game.modalController.hide()
                if (props.onClose != undefined) {
                    props.onClose()
                }
            }
        }
        BootstrapModalBody {
            attrs.className = "mission-modal-body"
            val missions = game.activeScene.unsafeCast<DefaultGameScene>().missions
            if (missions.isMissionModalDataLoading(props.missionId)) {
                BootstrapSpinner {
                    attrs.animation = "border"
                }
            } else {
                val mission = missions.getMissionModalDataById(props.missionId)
                BootstrapNav {
                    attrs.variant = "tabs"
                    var challengeCounter = 0
                    val moreThanOneChallenge = mission.tabs.count { it.title == "MissionChallenge" } > 1
                    mission.tabs.forEachIndexed { index: Int, tab: MissionTabData<*> ->
                        BootstrapNavItem {
                            BootstrapNavLink {
                                attrs.active = (index == state.activeTabIndex)
                                attrs.eventKey = "tab-$index"
                                span {
                                    +i(tab.title)
                                    if (tab.title == "MissionChallenge") {
                                        challengeCounter++
                                        if (moreThanOneChallenge) {
                                            +"$challengeCounter "
                                        }
                                        child(TitleStarCounter::class) {
                                            val challengeSpec = tab.unsafeCast<ChallengeTabData>().data
                                            attrs.total = challengeSpec.star
                                            attrs.current = game.activeScene.challengeAnswers.challengeStar(challengeSpec.id)
                                            attrs.starSize = 16
                                        }
                                    }
                                }
                                attrs.onSelect = {
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
                    attrs.classes = jsObjectBackedSetOf("mission-tab-content")
                    when (activeTab.type) {
                        MissionTabType.QuestionChallenge -> renderQuestionChallenge(activeTab.asDynamic())
                        MissionTabType.StarChallenge -> renderStarChallenge(activeTab.asDynamic())
                        MissionTabType.PullRequestChallenge -> renderPullRequestChallenge(activeTab.asDynamic())
                        MissionTabType.HeroNoticeboardChallenge -> heroNoticeboardChallenge(activeTab.asDynamic())
                        MissionTabType.TextContentChallenge -> textContentChallenge(activeTab.asDynamic())
                        MissionTabType.Tutorials -> renderTutorials(activeTab.asDynamic())
                        else -> throw IllegalArgumentException(activeTab.title)
                    }
                }
            }
        }
    }

    private fun RBuilder.renderTutorials(tab: TutorialsTabData) {
        child(TutorialTab::class) {
            attrs.missionId = props.missionId
            attrs.game = game
            attrs.initTutorials = tab.data
            attrs.initLocales = tab.locales
        }
    }

    private fun RBuilder.heroNoticeboardChallenge(tab: HeroNoticeboardTabData) {
        child(JavaIslandHeroNoticeboard::class) {
            attrs.game = game
            attrs.initTiles = tab.data.tiles
            attrs.totalPage = tab.data.page
        }
    }

    private fun RBuilder.textContentChallenge(tab: ChallengeTabData) {
        unsafeDiv(i(tab.data.spec))
    }

    private fun RBuilder.renderPullRequestChallenge(tab: ChallengeTabData) {
        child(PullRequestChallengeTab::class) {
            attrs.game = game
            attrs.missionId = props.missionId
            attrs.challengeSpec = tab.data
        }
    }

    private fun RBuilder.renderStarChallenge(tab: ChallengeTabData) {
        child(StarChallengeTab::class) {
            attrs.contentHtml = game.i(tab.data.readme)
            attrs.game = game
        }
    }

    private fun RBuilder.renderQuestionChallenge(tabData: ChallengeTabData) {
        child(QuestionChallengeTab::class) {
            attrs.game = game
            attrs.missionId = props.missionId
            attrs.challengeSpec = tabData.data
        }
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
