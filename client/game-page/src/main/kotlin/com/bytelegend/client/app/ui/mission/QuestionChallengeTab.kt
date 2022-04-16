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

import BootstrapFormRow
import com.bytelegend.app.client.api.Banner
import com.bytelegend.app.client.api.Character
import com.bytelegend.app.client.api.HERO_ID
import com.bytelegend.app.client.ui.bootstrap.BootstrapAccordion
import com.bytelegend.app.client.ui.bootstrap.BootstrapAccordionCollapse
import com.bytelegend.app.client.ui.bootstrap.BootstrapAccordionToggle
import com.bytelegend.app.client.ui.bootstrap.BootstrapAlert
import com.bytelegend.app.client.ui.bootstrap.BootstrapButton
import com.bytelegend.app.client.ui.bootstrap.BootstrapCard
import com.bytelegend.app.client.ui.bootstrap.BootstrapCardHeader
import com.bytelegend.app.client.ui.bootstrap.BootstrapCol
import com.bytelegend.app.client.ui.bootstrap.BootstrapRow
import com.bytelegend.app.shared.entities.ChallengeAnswer
import com.bytelegend.app.shared.entities.MissionModalData
import com.bytelegend.app.shared.entities.mission.ChallengeSpec
import com.bytelegend.app.shared.protocol.challengeUpdateEvent
import com.bytelegend.client.app.engine.DefaultGameMission
import com.bytelegend.client.app.engine.GAME_UI_UPDATE_EVENT
import com.bytelegend.client.app.engine.util.format
import com.bytelegend.client.app.external.TextareaAutosize
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.setState
import com.bytelegend.client.app.ui.unsafeDiv
import com.bytelegend.client.app.ui.unsafeH4
import com.bytelegend.client.app.web.submitChallengeAnswer
import csstype.ClassName
import kotlinx.js.jso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLTextAreaElement
import react.ChildrenBuilder
import react.Fragment
import react.State
import react.create
import react.dom.html.ReactHTML.br
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h4
import react.dom.html.ReactHTML.pre
import react.dom.html.ReactHTML.span
import react.react

interface QuestionChallengeTabProps : GameProps {
    var missionModalData: MissionModalData
    var challengeSpec: ChallengeSpec
}

interface QuestionChallengeTabState : State {
    var loading: Boolean
}

class QuestionChallengeTab : GameUIComponent<QuestionChallengeTabProps, QuestionChallengeTabState>() {
    lateinit var textarea: HTMLTextAreaElement

    init {
        state = jso { loading = false }
    }

    // If player is not adjacent to the mission, disable the input box and submit button
    private fun isDisabled(): Boolean {
        if (props.missionModalData.missionId != "install-java") {
            return false
        }
        val heroInScene = activeScene.objects.getByIdOrNull<Character>(HERO_ID) ?: return true
        return heroInScene.gridCoordinate.manhattanDistanceTo(activeScene.objects.getById<DefaultGameMission>(props.missionModalData.missionId).gridCoordinate) > 2
    }

    override fun render() = Fragment.create {
        div {
            className = ClassName("mission-tab-content")
            if (!game.heroPlayer.isAnonymous && isDisabled()) {
                BootstrapAlert {
                    show = true
                    variant = "warning"
                    +props.game.i("YouMustBeAdjacentToTheMission")
                }
            }
            renderTldr()

            h4 {
                +i("YourAnswer")
            }
            BootstrapFormRow {
                BootstrapCol {
                    xs = 10
                    TextareaAutosize {
                        disabled = state.loading || props.game.heroPlayer.isAnonymous || isDisabled()
                        minRows = 2
                        maxRows = 5
                        ref = { it: dynamic ->
                            textarea = it
                        }
                    }
                }
                BootstrapCol {
                    xs = 2

                    if (state.loading) {
                        BootstrapButton {
                            disabled = true
                            span {
                                className = ClassName("spinner-border spinner-border-sm")
                            }
                            +("Checking...")
                        }
                    } else {
                        BootstrapButton {
                            +i("Submit")
                            disabled = props.game.heroPlayer.isAnonymous || isDisabled()
                            className = "modal-submit-answer-button"
                            onClick = {
                                GlobalScope.launch {
                                    onClickSubmitAnswer()
                                }
                            }
                        }
                    }
                }
            }
            br { }

            val answers: List<ChallengeAnswer> =
                game.activeScene.challengeAnswers.getChallengeAnswersByMissionId(props.missionModalData.missionId)
                    .flatMap { answersOfChallenge ->
                        answersOfChallenge.answers.values.map { it.last().unsafeCast<ChallengeAnswer>() }
                    }

            renderQuestionAnswers(answers)

            child(WebEditor::class.react, jso {
                game = props.game
                missionModalData = props.missionModalData
                challengeSpec = props.challengeSpec
            })
        }
    }

    private fun ChildrenBuilder.renderTldr() {
        if (props.challengeSpec.tldr.isNotBlank()) {
            unsafeH4(i("TLDR"))
            unsafeDiv(i(props.challengeSpec.tldr))
        } else if (props.challengeSpec.readme.startsWith("https://")) {
            unsafeH4(i("TLDR"))
            unsafeDiv(i("AnswerTheQuestionInReadme"))
        }
    }

    private suspend fun onClickSubmitAnswer() {
        if (game.heroPlayer.isAnonymous || textarea.value.isBlank() || isDisabled()) {
            return
        }
        setState {
            loading = true
        }

        val challengeUpdateEventData = submitChallengeAnswer(props.missionModalData.missionId, props.challengeSpec.id, textarea.value)
        game.eventBus.emit(challengeUpdateEvent(activeScene.map.id), challengeUpdateEventData)
        if (challengeUpdateEventData.change.accomplished) {
            game.bannerController.showBanner(
                Banner(
                    game.i("AnswerCorrect"),
                    "success",
                    5
                )
            )
        }
        game.eventBus.emit(GAME_UI_UPDATE_EVENT, null)

        setState {
            loading = false
        }
    }

    private fun ChildrenBuilder.renderQuestionAnswers(answers: List<ChallengeAnswer>) {
        if (answers.isEmpty()) {
            return
        }
        h4 {
            +i("AnswerHistory")
        }
        BootstrapAccordion {
            answers.forEachIndexed { index, missionAnswer ->
                BootstrapCard {
                    BootstrapAccordionToggle {
                        className =
                            if (missionAnswer.accomplished) "list-group-item-success mission-player-answer"
                            else "list-group-item-danger mission-player-answer"
                        `as` = BootstrapCardHeader
                        eventKey = index.toString()

                        BootstrapRow {
                            BootstrapCol {
                                md = "auto"
                                if (missionAnswer.accomplished) {
                                    div { className = ClassName("mission-success-tick-icon inline-icon-16") }
                                } else {
                                    div { className = ClassName("mission-fail-cross-icon inline-icon-16") }
                                }
                            }
                            BootstrapCol {
                                md = "auto"
                                +props.game.format(missionAnswer.time)
                            }
                            BootstrapCol {
                                md = "auto"
                                className = "mission-player-answer-inline"
                                +missionAnswer.answer
                            }
                        }
                    }
                    BootstrapAccordionCollapse {
                        eventKey = index.toString()
                        com.bytelegend.app.client.ui.bootstrap.BootstrapCardBody {
                            pre {
                                className = ClassName("pre-scrollable bg-light")
                                +missionAnswer.answer
                            }
                        }
                    }
                }
            }
        }
        br { }
    }
}
