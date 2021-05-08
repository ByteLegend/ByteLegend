package com.bytelegend.client.app.ui

import kotlinx.html.DIV
import kotlinx.html.classes
import react.RState
import react.dom.RDOMBuilder
import react.dom.div
import react.dom.span

interface CoinCountWidgetProps : GameProps
interface CoinCountWidgetState : RState

const val COIN_INCREMENT_EVENT = "coin.increment"

class CoinCountWidget : AbstractIncrementAnimatableWidget<CoinCountWidgetProps, CoinCountWidgetState>("coin-icon") {
    override val eventName: String = COIN_INCREMENT_EVENT

    override fun RDOMBuilder<DIV>.renderDiv() {
        span {
            attrs.classes = setOf("map-title-text")
            +game.heroPlayer.coin.toString()
        }
        renderIcon()
    }

    override fun onIncrementNewValue(event: NumberIncrementEvent) {
        game.heroPlayer.coin = event.newValue
    }
}
