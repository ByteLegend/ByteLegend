package com.bytelegend.client.app.ui

import kotlinx.browser.document
import kotlinx.html.DIV
import kotlinx.html.classes
import org.w3c.dom.HTMLImageElement
import org.w3c.dom.Node
import react.RState
import react.dom.RDOMBuilder
import react.dom.img
import react.dom.span

interface CoinCountWidgetProps : GameProps
interface CoinCountWidgetState : RState

const val COIN_INCREMENT_EVENT = "coin.increment"

class CoinCountWidget : AbstractIncrementAnimatableWidget<CoinCountWidgetProps, CoinCountWidgetState>() {
    override val eventName: String = COIN_INCREMENT_EVENT

    override fun RDOMBuilder<DIV>.renderDiv() {
        span {
            attrs.classes = setOf("map-title-text")
            +"${game.heroPlayer.coin} "
        }
        img {
            attrs.height = "16px"
            attrs.width = "16px"
            attrs.src = game.resolve("/img/icon/money.svg")
        }
    }

    override fun getIncrementAnimationDiv(event: NumberIncrementEvent): Node {
        val div = document.createElement("div")
        div.appendChild(document.createTextNode("+${event.inc} "))
        div.appendChild(
            document.createElement("img").unsafeCast<HTMLImageElement>().apply {
                style.height = "16px"
                style.width = "16px"
                src = game.resolve("/img/icon/money.svg")
            }
        )
        return div
    }

    override fun onIncrementNewValue(event: NumberIncrementEvent) {
        game.heroPlayer.coin = event.newValue
    }
}
