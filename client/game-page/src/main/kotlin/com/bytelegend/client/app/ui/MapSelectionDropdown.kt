package com.bytelegend.client.app.ui

import BootstrapDropdownDivider
import BootstrapDropdownItem
import com.bytelegend.app.client.ui.bootstrap.BootstrapDropdownButton
import react.RBuilder
import react.RElementBuilder
import react.RState
import react.dom.jsStyle
import react.dom.span

interface MapSelectionDropdownProps : GameProps

class MapSelectionDropdown : GameUIComponent<MapSelectionDropdownProps, RState>() {
    override fun RBuilder.render() {
        BootstrapDropdownButton {
            attrs.className = "map-name-selection map-title-text"
            attrs.id = "map-selection"
            attrs.title = i(gameMap.id)

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

    private fun RElementBuilder<*>.dropdownItem(mapId: String, submap: Boolean) {
        BootstrapDropdownItem {
            if (submap) {
                span {
                    attrs.jsStyle {
                        paddingLeft = "10px"
                    }
                    +i(mapId)
                }
            } else {
                +i(mapId)
            }
            // disable map selection when game script is running
            attrs.onClick = gameControlAwareEventHandler(
                stateUpdatingEventHandler {
                    game.sceneContainer.loadScene(mapId)
                }
            )
        }
    }
}
