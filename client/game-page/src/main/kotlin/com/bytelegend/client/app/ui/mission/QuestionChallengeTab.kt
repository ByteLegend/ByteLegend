package com.bytelegend.client.app.ui.mission

import BootstrapFormRow
import com.bytelegend.app.client.ui.bootstrap.BootstrapAccordion
import com.bytelegend.app.client.ui.bootstrap.BootstrapAccordionCollapse
import com.bytelegend.app.client.ui.bootstrap.BootstrapAccordionToggle
import com.bytelegend.app.client.ui.bootstrap.BootstrapButton
import com.bytelegend.app.client.ui.bootstrap.BootstrapCard
import com.bytelegend.app.client.ui.bootstrap.BootstrapCardHeader
import com.bytelegend.app.client.ui.bootstrap.BootstrapCol
import com.bytelegend.app.client.ui.bootstrap.BootstrapRow
import com.bytelegend.app.shared.entities.MissionAnswer
import com.bytelegend.app.shared.entities.mission.ChallengeSpec
import com.bytelegend.client.app.engine.util.format
import com.bytelegend.client.app.engine.util.jsObjectBackedSetOf
import com.bytelegend.client.app.external.TextareaAutosize
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.unsafeDiv
import com.bytelegend.client.app.ui.unsafeH4
import com.bytelegend.client.app.ui.unsafeSpan
import kotlinx.html.classes
import react.RBuilder
import react.RState
import react.dom.br
import react.dom.div
import react.dom.h4
import react.dom.p
import react.dom.pre
import kotlin.js.Date

interface QuestionChallengeTabProps : GameProps {
    var missionId: String
    var challengeSpec: ChallengeSpec
}

class QuestionChallengeTab : GameUIComponent<QuestionChallengeTabProps, RState>() {
    override fun RBuilder.render() {
        unsafeH4(i("TLDR"))
        unsafeDiv(i(props.challengeSpec.tldr))
        h4 {
            +i("YourAnswer")
        }
        BootstrapFormRow {
            BootstrapCol {
                attrs.xs = 10
                TextareaAutosize {
                    attrs.minRows = 2
                    attrs.maxRows = 5
                }
            }
            BootstrapCol {
                attrs.xs = 2
                BootstrapButton {
                    +i("Submit")
                }
            }
        }
        br { }

//        val answers = game.activeScene.playerMissions.missionAnswers(props.missionId)
        val answers = listOf(
            MissionAnswer().apply {
                answer = "zsh: command not found: java"
                accomplished = false
                createdAt = Date().getTime().toLong() - 1000000
            },
            MissionAnswer().apply {
                answer = """
                    java -version
                    openjdk version "11.0.11" 2021-04-20
                    OpenJDK Runtime Environment AdoptOpenJDK-11.0.11+9 (build 11.0.11+9)
                    OpenJDK 64-Bit Server VM AdoptOpenJDK-11.0.11+9 (build 11.0.11+9, mixed mode)
                """.trimIndent()
                accomplished = true
                createdAt = Date().getTime().toLong()
            },
        )

        renderPlayerAnswers(answers)

        h4 {
            +i("Description")
        }
        p {
            unsafeSpan(i(props.challengeSpec.readme))
        }
    }

    private fun RBuilder.renderPlayerAnswers(answers: List<MissionAnswer>) {
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
                                +missionAnswer.answer!!
                            }
                        }
                    }
                    BootstrapAccordionCollapse {
                        attrs.eventKey = index.toString()
                        com.bytelegend.app.client.ui.bootstrap.BootstrapCardBody {
                            pre {
                                attrs.classes = com.bytelegend.client.app.engine.util.jsObjectBackedSetOf("pre-scrollable")
                                +missionAnswer.answer!!
                            }
                        }
                    }
                }
            }
        }
        br { }
    }
}
