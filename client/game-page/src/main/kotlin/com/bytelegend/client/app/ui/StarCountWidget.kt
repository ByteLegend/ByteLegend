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

import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.html.DIV
import kotlinx.html.classes
import kotlinx.html.id
import react.State
import react.dom.RDOMBuilder
import react.dom.span

interface StarCountWidgetProps : GameProps
interface StarCountWidgetState : State

const val STAR_INCREMENT_EVENT = "star.increment"

class StarCountWidget : AbstractIncrementAnimatableWidget<StarCountWidgetProps, StarCountWidgetState>("star-icon") {
    override val eventName: String = STAR_INCREMENT_EVENT
    override fun RDOMBuilder<DIV>.renderDiv() {
        attrs.id = "star-count"
        span {
            attrs.classes = jsObjectBackedSetOf("map-title-text")
            +game.heroPlayer.star.toString()
        }
        renderIcon()
    }

    override fun onIncrementNewValue(event: NumberIncrementEvent) {
        game.heroPlayer.star = event.newValue
    }
}
