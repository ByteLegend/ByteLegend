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

@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.bytelegend.client.app.ui.item

import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalHeader
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalTitle
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.client.app.engine.Item
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.jsStyle
import com.bytelegend.client.app.ui.loadingSpinner
import com.bytelegend.client.app.ui.setState
import com.bytelegend.client.app.ui.unsafeP
import com.bytelegend.client.app.ui.unsafeSpan
import csstype.ClassName
import kotlinx.js.jso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLDivElement
import react.Component
import react.Fragment
import react.Props
import react.State
import react.create
import react.dom.events.MouseEvent
import react.dom.html.ReactHTML
import react.react

interface ItemOrAchievementModalProps : GameProps {
    var title: String
    var emptyText: String
}

interface ItemOrAchievementModalState : State {
    var items: Map<String, Item>
    var loading: Boolean

    /**
     * Id of the currently selected item. Click once to select an item and click again to unselect it.
     * If any item is selected, hover events on other items are disabled.
     */
    var selectedItem: Item?
    var hoveredItem: Item?

    /**
     * The coordinate of item description div, relative to modal body.
     */
    var descCoordinate: PixelCoordinate?
}

abstract class ItemOrAchievementModal : GameUIComponent<ItemOrAchievementModalProps, ItemOrAchievementModalState>() {
    init {
        state = jso {
            loading = true
        }
    }

    abstract suspend fun loadItems(): Map<String, Item>

    override fun render() = Fragment.create {
        BootstrapModalHeader {
            closeButton = true
            BootstrapModalTitle {
                +i(props.title)
            }
        }

        BootstrapModalBody {
            className = "item-or-achievement-modal"

            ReactHTML.div {
                if (state.loading) {
                    loadingSpinner()
                    GlobalScope.launch {
                        val items = loadItems()
                        setState {
                            loading = false
                            this.items = items
                        }
                    }
                } else {
                    if (state.items.isEmpty()) {
                        +i(props.emptyText)
                    } else {
                        state.items.forEach { (id, item) ->
                            child(ItemAchievementModalItem::class.react, jso {
                                this.id = id
                                iconUrl = game.resolve(item.metadata.iconUrl)
                                name = game.i(item.metadata.nameTextId)
                                desc = game.i(item.metadata.descTextId)
                                missionTitle = if (item.mission == null) null else game.i(item.mission.title)

                                onMouseClick = {
                                    if (state.selectedItem?.metadata?.iconId == id) {
                                        setState {
                                            selectedItem = null
                                            descCoordinate = null
                                        }
                                    } else {
                                        setState {
                                            selectedItem = item
                                            descCoordinate = PixelCoordinate(
                                                it.target.asDynamic().offsetLeft + it.nativeEvent.offsetX,
                                                it.target.asDynamic().offsetTop + it.nativeEvent.offsetY,
                                            )
                                        }
                                    }
                                }

                                onMouseMove = {
                                    if (state.selectedItem == null) {
                                        setState {
                                            hoveredItem = item
                                            descCoordinate = PixelCoordinate(
                                                it.target.asDynamic().offsetLeft + it.nativeEvent.offsetX,
                                                it.target.asDynamic().offsetTop + it.nativeEvent.offsetY,
                                            )
                                        }
                                    }
                                }

                                onMouseOut = {
                                    if (state.selectedItem == null) {
                                        setState {
                                            hoveredItem = null
                                            descCoordinate = null
                                        }
                                    }
                                }
                            })
                        }

                        if (state.descCoordinate != null) {
                            val itemName =
                                state.selectedItem?.metadata?.nameTextId ?: state.hoveredItem?.metadata?.nameTextId
                            val itemDesc =
                                state.selectedItem?.metadata?.descTextId ?: state.hoveredItem?.metadata?.descTextId
                            val itemMission = state.selectedItem?.mission ?: state.hoveredItem?.mission
                            ReactHTML.div {
                                className = ClassName("item-desc")
                                jsStyle {
                                    top = "${state.descCoordinate!!.y + 16}px"
                                    left = "${state.descCoordinate!!.x + 16}px"
                                    zIndex = "${ITEM_Z_INDEX + 1}"
                                }

                                ReactHTML.h5 {
                                    +game.i(itemName!!)
                                }

                                if (itemMission != null) {
                                    unsafeP(game.i("ItemCanOnlyBeUsedOnMission", game.i(itemMission.title)))
                                }

                                unsafeP(game.i(itemDesc!!))
                            }
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

    var onMouseClick: (MouseEvent<HTMLDivElement, *>) -> Unit
    var onMouseMove: (MouseEvent<HTMLDivElement, *>) -> Unit
    var onMouseOut: () -> Unit
}

private const val ITEM_Z_INDEX = 1

class ItemAchievementModalItem : Component<ItemAchievementModalItemProps, State>() {
    override fun render() = Fragment.create {
        ReactHTML.div {
            className = ClassName("item-or-achievement")
            jsStyle {
                zIndex = ITEM_Z_INDEX
            }
            ReactHTML.div {
                className = ClassName("item-title no-pointer-events")
                +props.name
            }
            ReactHTML.img {
                className = ClassName("no-pointer-events")
                src = props.iconUrl
            }

            if (props.missionTitle != null) {
                ReactHTML.div {
                    className = ClassName("item-mission-title no-pointer-events")
                    unsafeSpan(props.missionTitle!!)
                }
            }

            onMouseOut = {
                props.onMouseOut()
            }
            onMouseMove = {
                props.onMouseMove(it)
            }
            onClick = {
                props.onMouseClick(it)
            }
        }
    }
}
