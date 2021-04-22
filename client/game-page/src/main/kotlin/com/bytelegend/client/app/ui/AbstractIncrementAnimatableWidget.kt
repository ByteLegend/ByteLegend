package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.client.app.script.effect.numberIncrementEffect
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.html.DIV
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.Node
import react.RBuilder
import react.RState
import react.dom.RDOMBuilder
import react.dom.div
import react.setState

data class NumberIncrementEvent(
    val inc: Int,
    val newValue: Int
)

/**
 * A special widget which can show a "+X" animation when updated.
 */
abstract class AbstractIncrementAnimatableWidget<P : GameAwareProps, S : RState> : GameUIComponent<P, S>() {
    abstract val eventName: String
    lateinit var div: HTMLDivElement

    private val incrementEventListener: EventListener<NumberIncrementEvent> = this::onIncrement

    override fun RBuilder.render() {
        if (!game.heroPlayer.isAnonymous) {
            div {
                renderDiv()
                ref {
                    if (it != null) {
                        div = it as HTMLDivElement
                    }
                }
            }
        }
    }

    abstract fun RDOMBuilder<DIV>.renderDiv()
    abstract fun onIncrementNewValue(event: NumberIncrementEvent)
    abstract fun getIncrementAnimationDiv(event: NumberIncrementEvent): Node

    private fun onIncrement(event: NumberIncrementEvent) {
        GlobalScope.launch {
            numberIncrementEffect(
                getIncrementAnimationDiv(event),
                div.getBoundingClientRect().x.toInt(),
                div.getBoundingClientRect().y.toInt(),
                div.getBoundingClientRect().width.toInt(),
                div.getBoundingClientRect().height.toInt(),
            )
        }
        onIncrementNewValue(event)
        setState { }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        props.game.eventBus.on(eventName, incrementEventListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.game.eventBus.remove(eventName, incrementEventListener)
    }
}
