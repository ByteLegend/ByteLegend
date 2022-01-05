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

import BootstrapDropdownDivider
import BootstrapDropdownItem
import com.bytelegend.app.client.ui.bootstrap.BootstrapDropdownButton
import kotlinx.browser.document
import react.ChildrenBuilder
import react.Fragment
import react.State
import react.create
import react.dom.html.ReactHTML.img

interface MapSelectionDropdownProps : GameProps

class MapSelectionDropdown : GameUIComponent<MapSelectionDropdownProps, State>() {
    override fun render() = Fragment.create {
        BootstrapDropdownButton {
            className = "map-name-selection map-title-text"
            id = "map-selection"
            title = document.createElement("span").apply {
                innerHTML = i(gameMap.id)
            }.textContent ?: ""

            game.mapHierarchy.forEachIndexed { index, it ->
                val currentMainMap = it.id

                dropdownItem(currentMainMap, false)

                it.children.filter { it.id != currentMainMap }.forEach {
                    dropdownItem(it.id, true)
                }
                if (index != game.mapHierarchy.size - 1) {
                    BootstrapDropdownDivider {}
                }
            }
        }
    }

    private fun ChildrenBuilder.dropdownItem(mapId: String, submap: Boolean) {
        BootstrapDropdownItem {
            if (submap) {
                unsafeSpan(i(mapId), "submap-name")
            } else {
                unsafeSpan(i(mapId))
            }
            heroIcon(mapId)
            // disable map selection when game script is running
            onClick = {
                game.sceneContainer.loadScene(mapId)
            }
        }
    }

    private fun ChildrenBuilder.heroIcon(mapId: String) {
        if (game._hero?.gameScene?.map?.id == mapId) {
            img {
                src = HERO_ICON
                width = 16.0
                height = 16.0
            }
        }
    }
}
