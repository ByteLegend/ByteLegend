package com.bytelegend.client.app.ui

import kotlinx.browser.document
import kotlinx.html.DIV
import kotlinx.html.id
import org.w3c.dom.Node
import react.RState
import react.dom.RDOMBuilder

interface ReputationCountWidgetProps : GameProps
interface ReputationCountWidgetState : RState

const val REPUTATION_INCREMENT_EVENT = "reputation.increment"

class ReputationCountWidget : AbstractIncrementAnimatableWidget<ReputationCountWidgetProps, ReputationCountWidgetState>() {
    override val eventName: String = REPUTATION_INCREMENT_EVENT

    override fun RDOMBuilder<DIV>.renderDiv() {
        attrs.id = "reputation-count"
        +"${game.heroPlayer.reputation} ❤️"
    }

    override fun getIncrementAnimationDiv(event: NumberIncrementEvent): Node = document.createTextNode("+${event.inc}❤️")

    override fun onIncrementNewValue(event: NumberIncrementEvent) {
        game.heroPlayer.reputation = event.newValue
    }
}
