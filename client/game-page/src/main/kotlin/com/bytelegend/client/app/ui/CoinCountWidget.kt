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

import com.bytelegend.app.client.misc.playAudio
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalHeader
import com.bytelegend.app.shared.protocol.COIN_UPDATE_EVENT
import com.bytelegend.app.shared.protocol.CoinUpdateEventData
import com.bytelegend.app.shared.protocol.NumberChange
import kotlinext.js.jso
import org.w3c.dom.HTMLDivElement
import react.ChildrenBuilder
import react.State
import react.dom.html.HTMLAttributes
import react.dom.html.ReactHTML.span
import react.react

interface CoinCountWidgetProps : GameProps
interface CoinCountWidgetState : State

class CoinCountWidget : AbstractIncrementAnimatableWidget<CoinCountWidgetProps, CoinCountWidgetState>("coin-icon") {
    override val eventName: String = COIN_UPDATE_EVENT

    override fun <T> T.renderDiv() where T : HTMLAttributes<HTMLDivElement>, T : ChildrenBuilder {
        span {
            id = "coin-count"
            className = "map-title-text"
            +game.heroPlayer.coin.toString()
        }
        renderIcon()
        onClick = {
            game.modalController.show {
                BootstrapModalHeader {
                    closeButton = true
                    +props.game.i("MyCoinHistory")
                }
                BootstrapModalBody {
                    child(CoinHistoryModal::class.react, jso {
                        this.game = props.game
                    })
                }
            }
        }
    }

    override fun onNumberChange(numberChange: NumberChange) {
        val coinChange = numberChange.unsafeCast<CoinUpdateEventData>()
        if (numberChange.change > 0) {
            playAudio("coin")
        }
        game.toastController.addToast(
            "${formatChange(numberChange.change)} <div class='$iconClassName inline-icon-16'></div>",
            game.i(coinChange.reasonId, *coinChange.reasonArgs),
            5000
        )
        game.heroPlayer.coin = numberChange.newValue
        animateAndSetState(numberChange)
    }
}
