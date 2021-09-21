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
package com.bytelegend.client.app.ui.item

import com.bytelegend.app.client.ui.bootstrap.BootstrapListGroupItem
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.HistoryModal
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.html.classes
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.State
import react.dom.div

interface ItemWidgetProps : GameProps

class ItemsWidget : GameUIComponent<ItemWidgetProps, State>() {
    override fun RBuilder.render() {
        BootstrapListGroupItem {
            val items = game.heroPlayer.items
            if (items.isNotEmpty()) {
                renderOne(items[0])
                renderText("...")
            } else {
                renderText(i("Items"))
            }
        }
    }

    private fun RBuilder.renderOne(item: String) {
        div {
            attrs.classes = jsObjectBackedSetOf("inline-icon-16", item)
        }
    }

    private fun RBuilder.renderText(text: String) {
        div {
            attrs.classes = jsObjectBackedSetOf("map-title-text", "items-widget")
            attrs.onClickFunction = {
                game.modalController.show {
                    attrs.className = "history-modal"
                    child(HistoryModal::class) {
                        attrs.game = game
                    }
                }
            }
            +text
        }
    }
}
