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
package com.bytelegend.client.app.ui

import com.bytelegend.app.shared.protocol.NumberChange
import csstype.ClassName
import org.w3c.dom.HTMLDivElement
import react.ChildrenBuilder
import react.State
import react.dom.html.HTMLAttributes
import react.dom.html.ReactHTML.span

interface StarCountWidgetProps : GameProps
interface StarCountWidgetState : State

const val STAR_INCREMENT_EVENT = "star.increment"

class StarCountWidget : AbstractIncrementAnimatableWidget<StarCountWidgetProps, StarCountWidgetState>("star-icon") {
    override val eventName: String = STAR_INCREMENT_EVENT
    override fun <T> T.renderDiv() where T : HTMLAttributes<HTMLDivElement>, T : ChildrenBuilder {
        id = "star-count"
        span {
            className = ClassName("map-title-text")
            +game.heroPlayer.star.toString()
        }
        renderIcon()
    }

    override fun onNumberChange(numberChange: NumberChange) {
        game.heroPlayer.star = numberChange.newValue
        animateAndSetState(numberChange)
    }

    override fun onClick() {
    }
}
