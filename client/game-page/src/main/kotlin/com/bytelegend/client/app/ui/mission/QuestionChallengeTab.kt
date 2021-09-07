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
import com.bytelegend.app.shared.entities.PlayerChallengeAnswer
import com.bytelegend.app.shared.entities.mission.ChallengeSpec
import com.bytelegend.app.shared.protocol.challengeUpdateEvent
import com.bytelegend.client.app.engine.GAME_UI_UPDATE_EVENT
import com.bytelegend.client.app.engine.GameMission
import com.bytelegend.client.app.engine.util.format
import com.bytelegend.client.app.external.TextareaAutosize
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.unsafeDiv
import com.bytelegend.client.app.ui.unsafeH4
import com.bytelegend.client.app.ui.unsafeSpan
import com.bytelegend.client.app.web.submitChallengeAnswer
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.html.classes
import org.w3c.dom.HTMLTextAreaElement
import react.RBuilder
import react.State
import react.dom.br
import react.dom.div
import react.dom.h4
import react.dom.p
import react.dom.pre
import react.dom.span
import react.setState

interface QuestionChallengeTabProps : GameProps {
    var missionId: String
    var challengeSpec: ChallengeSpec
}

interface QuestionChallengeTabState : State {
    var loading: Boolean
}

class QuestionChallengeTab : GameUIComponent<QuestionChallengeTabProps, QuestionChallengeTabState>() {
    lateinit var textarea: HTMLTextAreaElement

    override fun QuestionChallengeTabState.init() {
        loading = false
    }

    // If player is not adjacent to the mission, disable the input box and submit button
    private fun isDisabled(): Boolean {
        val heroInScene = activeScene.objects.getByIdOrNull<Character>(HERO_ID) ?: return true
        return heroInScene.gridCoordinate.manhattanDistanceTo(activeScene.objects.getById<GameMission>(props.missionId).gridCoordinate) > 2
    }

    override fun RBuilder.render() {
        if (game.heroPlayer.isAnonymous) {
            BootstrapAlert {
                attrs.show = true
                attrs.variant = "warning"
                unsafeSpan("${game.i("YouAreNotLoggedIn")}.${game.i("ClickHereToLogin")}")
            }
        } else if (props.missionId == "install-java-ide") {
            BootstrapAlert {
                attrs.show = true
                attrs.variant = "danger"
                +game.i("ThisIsNotFinishedYet")
            }
        } else {
            BootstrapAlert {
                attrs.show = true
                attrs.variant = "warning"
                +game.i("YouMustBeAdjacentToTheMission")
            }
        }
        unsafeH4(i("TLDR"))
        unsafeDiv(i(props.challengeSpec.tldr))
        h4 {
            +i("YourAnswer")
        }
        BootstrapFormRow {
            BootstrapCol {
                attrs.xs = 10
                TextareaAutosize {
                    attrs.disabled = state.loading || game.heroPlayer.isAnonymous || isDisabled()
                    attrs.minRows = 2
                    attrs.maxRows = 5
                    attrs.ref = { it: dynamic ->
                        textarea = it
                    }
                }
            }
            BootstrapCol {
                attrs.xs = 2

                if (state.loading) {
                    BootstrapButton {
                        attrs.disabled = true
                        span {
                            attrs.classes = jsObjectBackedSetOf("spinner-border", "spinner-border-sm")
                        }
                        +("Checking...")
                    }
                } else {
                    BootstrapButton {
                        +i("Submit")
                        attrs.disabled = game.heroPlayer.isAnonymous || isDisabled()
                        attrs.className = "modal-submit-answer-button"
                        attrs.onClick = {
                            GlobalScope.launch {
                                onClickSubmitAnswer()
                            }
                        }
                    }
                }
            }
        }
        br { }

        val answers = game.activeScene.playerChallenges.getPlayerChallengesByMissionId(props.missionId).flatMap { it.answers }

        renderPlayerAnswers(answers)

        h4 {
            +i("Problem")
        }
        p {
            unsafeSpan(i(props.challengeSpec.readme))
        }
    }

    private suspend fun onClickSubmitAnswer() {
        if (game.heroPlayer.isAnonymous || textarea.value.isBlank() || isDisabled()) {
            return
        }
        setState {
            loading = true
        }

        val challengeUpdateEventData = submitChallengeAnswer(props.missionId, props.challengeSpec.id, textarea.value)
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

    private fun RBuilder.renderPlayerAnswers(answers: List<PlayerChallengeAnswer>) {
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
                        attrs.className =
                            if (missionAnswer.accomplished) "list-group-item-success mission-player-answer"
                            else "list-group-item-danger mission-player-answer"
                        attrs.`as` = BootstrapCardHeader
                        attrs.eventKey = index.toString()

                        BootstrapRow {
                            BootstrapCol {
                                attrs.md = "auto"
                                if (missionAnswer.accomplished) {
                                    div { attrs.classes = jsObjectBackedSetOf("mission-success-tick-icon", "inline-icon") }
                                } else {
                                    div { attrs.classes = jsObjectBackedSetOf("mission-fail-cross-icon", "inline-icon") }
                                }
                            }
                            BootstrapCol {
                                attrs.md = "auto"
                                +format(missionAnswer.createdAt, game.locale)
                            }
                            BootstrapCol {
                                attrs.md = "auto"
                                attrs.className = "mission-player-answer-inline"
                                +missionAnswer.answer
                            }
                        }
                    }
                    BootstrapAccordionCollapse {
                        attrs.eventKey = index.toString()
                        com.bytelegend.app.client.ui.bootstrap.BootstrapCardBody {
                            pre {
                                attrs.classes = jsObjectBackedSetOf("pre-scrollable", "bg-light")
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
