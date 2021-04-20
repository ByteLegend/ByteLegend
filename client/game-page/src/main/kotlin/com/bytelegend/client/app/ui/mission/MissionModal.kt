package com.bytelegend.client.app.ui.mission

import BootstrapNavItem
import BootstrapNavLink
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapNav
import com.bytelegend.app.client.ui.bootstrap.BootstrapSpinner
import com.bytelegend.app.shared.entities.MissionTab
import com.bytelegend.app.shared.entities.MissionTabType
import com.bytelegend.client.app.engine.DefaultGameScene
import com.bytelegend.client.app.engine.MISSION_DATA_LOAD_FINISH
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import react.RBuilder
import react.RElementBuilder
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
                    mission.tabs.forEachIndexed { index: Int, tab: MissionTab ->
                        BootstrapNavItem {
                            BootstrapNavLink {
                                attrs.active = (index == state.activeTabIndex)
                                attrs.eventKey = "tab-$index"
                                +i(tab.title)
                                attrs.onSelect = {
                                    setState {
                                        activeTabIndex = index
                                    }
                                }
                            }
                        }
                    }
                }
                val activeTab = mission.tabs[state.activeTabIndex]
                when (activeTab.type) {
                    MissionTabType.QuestionChallenge -> child(QuestionChallengeTab::class) {
                    }
                    MissionTabType.StarChallenge -> child(StarChallengeTab::class) {
                        attrs.game = game
                    }
                    MissionTabType.PRChallenge -> child(PRChallengeTab::class) {
                    }
                    MissionTabType.NoticeboardChallenge -> child(RememberBravePeopleChallengeTab::class) {
                    }
                    MissionTabType.Tutorial -> child(TutorialTab::class) {
                    }
                    MissionTabType.Discussion -> child(DiscussionTab::class) {
                    }
                }
            }
        }
    }

    private fun renderDiscussion(tab: MissionTab) {
        TODO("Not yet implemented")
    }

    private fun renderTutorial(tab: MissionTab) {
        TODO("Not yet implemented")
    }

    private fun renderRememberBravePeopleChallenge(tab: MissionTab) {
        TODO("Not yet implemented")
    }

    private fun renderPRChallenge(tab: MissionTab) {
    }

    private fun RElementBuilder<*>.renderStarChallenge(tab: MissionTab) {
    }

    private fun renderQuestionChallenge(tab: MissionTab) {
        TODO("Not yet implemented")
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
