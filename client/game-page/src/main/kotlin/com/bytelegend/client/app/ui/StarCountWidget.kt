package com.bytelegend.client.app.ui

import kotlinx.browser.document
import kotlinx.html.DIV
import kotlinx.html.id
import org.w3c.dom.Node
import react.RState
import react.dom.RDOMBuilder

interface StarCountWidgetProps : GameProps
interface StarCountWidgetState : RState

const val STAR_INCREMENT_EVENT = "star.increment"

class StarCountWidget : AbstractIncrementAnimatableWidget<StarCountWidgetProps, StarCountWidgetState>() {
    override val eventName: String = STAR_INCREMENT_EVENT
    override fun RDOMBuilder<DIV>.renderDiv() {
        attrs.id = "star-count"
        +"${game.heroPlayer.star} ⭐"
    }

    override fun getIncrementAnimationDiv(event: NumberIncrementEvent): Node = document.createTextNode("+${event.inc}⭐")

    override fun onIncrementNewValue(event: NumberIncrementEvent) {
        game.heroPlayer.star = event.newValue
    }
}
