package com.bytelegend.client.app.ui

import kotlinx.html.DIV
import kotlinx.html.classes
import kotlinx.html.id
import react.RState
import react.dom.RDOMBuilder
import react.dom.span

interface ReputationCountWidgetProps : GameProps
interface ReputationCountWidgetState : RState

const val REPUTATION_INCREMENT_EVENT = "reputation.increment"

class ReputationCountWidget : AbstractIncrementAnimatableWidget<ReputationCountWidgetProps, ReputationCountWidgetState>("heart-icon") {
    override val eventName: String = REPUTATION_INCREMENT_EVENT

    override fun RDOMBuilder<DIV>.renderDiv() {
        attrs.id = "reputation-count"
        span {
            attrs.classes = setOf("map-title-text")
            +game.heroPlayer.reputation.toString()
        }
        renderIcon()
    }

    override fun onIncrementNewValue(event: NumberIncrementEvent) {
        game.heroPlayer.reputation = event.newValue
    }
}
