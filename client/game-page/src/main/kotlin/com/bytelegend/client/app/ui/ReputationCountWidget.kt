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
import com.bytelegend.app.shared.protocol.REPUTATION_UPDATE_EVENT
import com.bytelegend.app.shared.protocol.ReputationUpdateEventData
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.html.DIV
import kotlinx.html.classes
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import react.State
import react.dom.RDOMBuilder
import react.dom.span

interface ReputationCountWidgetProps : GameProps
interface ReputationCountWidgetState : State

class ReputationCountWidget : AbstractIncrementAnimatableWidget<ReputationCountWidgetProps, ReputationCountWidgetState>("heart-icon") {
    override val eventName: String = REPUTATION_UPDATE_EVENT

    override fun RDOMBuilder<DIV>.renderDiv() {
        attrs.id = "reputation-count"
        span {
            attrs.classes = jsObjectBackedSetOf("map-title-text")
            +game.heroPlayer.reputation.toString()
        }
        renderIcon()
        attrs.onClickFunction = {
            game.modalController.show {
                attrs.className = "history-modal"
                child(HistoryModal::class) {
                    attrs.game = game
                }
            }
        }
    }

    override fun onNumberChange(numberChange: NumberChange) {
        val coinChange = numberChange.unsafeCast<ReputationUpdateEventData>()
        game.toastController.addToast(
            "${if (numberChange.change > 0) "+" else ""}${numberChange.change} <div class='$iconClassName inline-icon-16'></div>",
            game.i(coinChange.reasonId, *coinChange.reasonArgs),
            5000
        )
        game.heroPlayer.reputation = numberChange.newValue
        animateAndSetState(numberChange)
    }
}
