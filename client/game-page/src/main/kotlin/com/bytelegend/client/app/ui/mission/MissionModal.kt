@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.client.app.ui.mission

import BootstrapNavItem
import BootstrapNavLink
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapNav
import com.bytelegend.app.client.ui.bootstrap.BootstrapSpinner
import com.bytelegend.app.shared.entities.ChallengeTabData
import com.bytelegend.app.shared.entities.DiscussionsTabData
import com.bytelegend.app.shared.entities.MissionTabData
import com.bytelegend.app.shared.entities.MissionTabType
import com.bytelegend.app.shared.entities.TutorialsTabData
import com.bytelegend.client.app.engine.DefaultGameScene
import com.bytelegend.client.app.engine.MISSION_DATA_LOAD_FINISH
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import kotlinx.browser.window
import react.RBuilder
import react.RState
import react.setState

interface MissionModalProps : GameProps {
    var missionId: String
    var onClose: UnitFunction
}

interface MissionModalState : RState {
    var activeTabIndex: Int
}

class MissionModal : GameUIComponent<MissionModalProps, MissionModalState>() {
    private val missionDataLoadFinishListener: EventListener<String> = this::onMissionDataLoadFinish
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
                    mission.tabs.forEachIndexed { index: Int, tab: MissionTabData<*> ->
                        BootstrapNavItem {
                            BootstrapNavLink {
                                attrs.active = (index == state.activeTabIndex)
                                attrs.eventKey = "tab-$index"
                                +i(tab.title)
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
                when (activeTab.type) {
                    MissionTabType.QuestionChallenge -> renderQuestionChallenge(activeTab.asDynamic())
                    MissionTabType.StarChallenge -> renderStarChallenge(activeTab.asDynamic())
                    MissionTabType.PullRequestChallenge -> renderPullRequestChallenge(activeTab.asDynamic())
                    MissionTabType.NoticeboardChallenge -> renderRememberBravePeopleChallenge(activeTab.asDynamic())
                    MissionTabType.Tutorials -> renderTutorials(activeTab.asDynamic())
                    else -> throw IllegalArgumentException(activeTab.title)
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

    private fun RBuilder.renderRememberBravePeopleChallenge(tab: ChallengeTabData) {
        child(RememberBravePeopleChallengeTab::class) {
            attrs.game = game
        }
    }

    private fun RBuilder.renderPullRequestChallenge(tab: ChallengeTabData) {
        child(PullRequestChallengeTab::class) {
            attrs.game = game
        }
    }

    private fun RBuilder.renderStarChallenge(tab: ChallengeTabData) {
        child(StarChallengeTab::class) {
            attrs.game = game
        }
    }

    private fun RBuilder.renderQuestionChallenge(tab: ChallengeTabData) {
        child(QuestionChallengeTab::class) {
            attrs.game = game
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
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.game.eventBus.remove(MISSION_DATA_LOAD_FINISH, missionDataLoadFinishListener)
    }
}
