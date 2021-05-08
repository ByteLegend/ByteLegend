package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.ui.bootstrap.BootstrapListGroupItem
import com.bytelegend.client.app.script.effect.numberIncrementEffect
import kotlinx.browser.document
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.html.DIV
import kotlinx.html.classes
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
abstract class AbstractIncrementAnimatableWidget<P : GameAwareProps, S : RState>(
    private val iconClassName: String
) : GameUIComponent<P, S>() {
    abstract val eventName: String
    lateinit var div: HTMLDivElement

    private val incrementEventListener: EventListener<NumberIncrementEvent> = this::onIncrement

    override fun RBuilder.render() {
        BootstrapListGroupItem {
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
    protected fun RBuilder.renderIcon() {
        div {
            attrs.classes = setOf(iconClassName, "inline-icon")
        }
    }

    private fun getIncrementAnimationDiv(event: NumberIncrementEvent): Node {
        val div = document.createElement("div")
        div.appendChild(document.createTextNode("+${event.inc}"))
        div.appendChild(
            document.createElement("div").unsafeCast<HTMLDivElement>().apply {
                className = "$iconClassName inline-icon"
            }
        )
        return div
    }

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
