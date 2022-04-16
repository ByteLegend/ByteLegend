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
@file:Suppress("EXPERIMENTAL_API_USAGE", "OPT_IN_USAGE")

package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.ui.bootstrap.BootstrapListGroupItem
import com.bytelegend.app.shared.protocol.NumberChange
import com.bytelegend.client.app.script.effect.numberIncrementEffect
import csstype.ClassName
import kotlinx.browser.document
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.Node
import react.ChildrenBuilder
import react.Fragment
import react.RefObject
import react.State
import react.create
import react.createRef
import react.dom.html.HTMLAttributes
import react.dom.html.ReactHTML.div

/**
 * A special widget which can show a "+X" animation when updated.
 */
abstract class AbstractIncrementAnimatableWidget<P : GameProps, S : State>(
    val iconClassName: String
) : GameUIComponent<P, S>() {
    abstract val eventName: String
    abstract fun onClick()
    private var div: RefObject<HTMLDivElement> = createRef()

    private val numberChangeEventListener: EventListener<NumberChange> = ::onNumberChange

    override fun render() = Fragment.create {
        BootstrapListGroupItem {
            onClick = {
                this@AbstractIncrementAnimatableWidget.onClick()
            }
            div {
                className = ClassName("text-align-right")
                renderDiv()
                ref = div
            }
        }
    }

    abstract fun <T> T.renderDiv() where T : HTMLAttributes<HTMLDivElement>, T : ChildrenBuilder

    protected abstract fun onNumberChange(numberChange: NumberChange)

    protected fun ChildrenBuilder.renderIcon() {
        div {
            className = ClassName("$iconClassName inline-icon-16")
        }
    }

    protected fun formatChange(change: Int): String = if (change > 0) "+$change" else change.toString()

    private fun getIncrementAnimationDiv(numberChange: NumberChange): Node {
        val div = document.createElement("div")
        div.appendChild(document.createTextNode(formatChange(numberChange.change)))
        div.appendChild(
            document.createElement("div").unsafeCast<HTMLDivElement>().apply {
                className = "$iconClassName inline-icon-16"
            }
        )
        return div
    }

    protected fun animateAndSetState(numberChange: NumberChange) {
        GlobalScope.launch {
            numberIncrementEffect(
                getIncrementAnimationDiv(numberChange),
                div.current!!.getBoundingClientRect().x.toInt(),
                div.current!!.getBoundingClientRect().y.toInt(),
                div.current!!.getBoundingClientRect().width.toInt(),
                div.current!!.getBoundingClientRect().height.toInt(),
            )
        }
        setState { }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        props.game.eventBus.on(eventName, numberChangeEventListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.game.eventBus.remove(eventName, numberChangeEventListener)
    }
}
