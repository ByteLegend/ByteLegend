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

import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalHeader
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalTitle
import com.bytelegend.client.app.engine.Item
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.loadingSpinner
import com.bytelegend.client.app.ui.setState
import kotlinext.js.jso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import react.Component
import react.Fragment
import react.Props
import react.State
import react.create
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import react.react

interface ItemModalState : State {
    var items: Map<String, Item>
    var loading: Boolean
    var hoveredItemId: String?
}

@Suppress("EXPERIMENTAL_API_USAGE")
class ItemModal : GameUIComponent<GameProps, ItemModalState>() {
    init {
        state = jso {
            loading = true
        }
    }

    override fun render() = Fragment.create {
        BootstrapModalHeader {
            closeButton = true
            BootstrapModalTitle {
                +i("MyItems")
            }
        }

        BootstrapModalBody {
            div {
                className = "item-modal"

                if (state.loading) {
                    loadingSpinner()
                    GlobalScope.launch {
                        val items = game.itemManager.getItems()
                        setState {
                            loading = false
                            this.items = items
                        }
                    }
                } else {
                    if (state.items.isEmpty()) {
                        +i("YouDontHaveAnyItems")
                    } else {
                        state.items.forEach { (id, item) ->
                            child(ItemAchievementModalItem::class.react, jso {
                                this.id = id
                                iconUrl = game.resolve(item.metadata.icon)
                                name = game.i(item.metadata.nameTextId)
                                desc = game.i(item.metadata.descTextId)
                                missionTitle = if (item.mission == null) null else game.i(item.mission.title)
                            })
                        }
                    }
                }
            }
        }
    }
}

interface ItemAchievementModalItemProps : Props {
    var id: String
    var iconUrl: String
    var name: String
    var desc: String
    var missionTitle: String?
}

class ItemAchievementModalItem : Component<ItemAchievementModalItemProps, State>() {
    override fun render() = Fragment.create {
        div {
            className = "item-or-achievement"
            div {
                className = "item-title"
                +props.name
            }
            img {
                src = props.iconUrl
            }

            if (props.missionTitle != null) {
                div {
                    className = "item-mission-title"
                    +props.missionTitle!!
                }
            }
        }
    }
}
