package com.bytelegend.client.app.ui.mission

import BootstrapDropdownItem
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.ui.bootstrap.BootstrapButton
import com.bytelegend.app.client.ui.bootstrap.BootstrapSplitButton
import com.bytelegend.app.shared.entities.PullRequestAnswer
import com.bytelegend.app.shared.protocol.ChallengeUpdateEventData
import com.bytelegend.client.app.engine.GameMission
import com.bytelegend.client.app.engine.MISSION_REPAINT_EVENT
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.Layer
import kotlinext.js.jsObject
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RState
import react.createElement
import react.dom.div
import react.dom.jsStyle
import react.rClass
import react.setState

interface MissionTitleAnswersState : RState

interface MissionTitleAnswerProps : GameProps {
    var mission: GameMission
}

class MissionTitleAnswers : RComponent<MissionTitleAnswerProps, MissionTitleAnswersState>() {
    private val challengeUpdateEventListener: EventListener<ChallengeUpdateEventData> = {
        if (it.newValue.challengeId == props.mission.id) {
            setState { }
        }
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

            val pullRequestAnswers = props.game.activeScene.playerChallenges.getPullRequestChallengeAnswersByMissionId(props.mission.id)
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
                attrs.title = createElement(MissionTitlePullRequestAnswerButton::class.rClass, jsObject<MissionTitlePullRequestAnswerButtonButtonProps> {
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
        props.game.eventBus.on(MISSION_REPAINT_EVENT, challengeUpdateEventListener)
    }

    override fun componentWillUnmount() {
        props.game.eventBus.remove(MISSION_REPAINT_EVENT, challengeUpdateEventListener)
    }
}
