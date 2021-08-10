package com.bytelegend.client.app.ui

import BootstrapDropdownDivider
import BootstrapDropdownItem
import com.bytelegend.app.client.ui.bootstrap.BootstrapDropdownButton
import com.bytelegend.client.app.engine.DefaultGameScene
import kotlinx.browser.document
import react.RBuilder
import react.RElementBuilder
import react.RState
import react.dom.img
import react.dom.jsStyle
import react.dom.map

interface MapSelectionDropdownProps : GameProps

class MapSelectionDropdown : GameUIComponent<MapSelectionDropdownProps, RState>() {
    override fun RBuilder.render() {
        BootstrapDropdownButton {
            attrs.className = "map-name-selection map-title-text"
            attrs.id = "map-selection"
            attrs.title = document.createElement("span").apply {
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

    private fun RElementBuilder<*>.dropdownItem(mapId: String, submap: Boolean) {
        BootstrapDropdownItem {
            if (submap) {
                unsafeSpan(i(mapId), "submap-name")
            } else {
                unsafeSpan(i(mapId))
            }
            heroIcon(mapId)
            // disable map selection when game script is running
            attrs.onClick = {
                if (!game.activeScene.unsafeCast<DefaultGameScene>().mainChannelDirector.isRunning) {
                    game.sceneContainer.loadScene(mapId)
                }
            }
        }
    }

    private fun RElementBuilder<*>.heroIcon(mapId: String) {
        if (game._hero?.gameScene?.map?.id == mapId) {
            img(src = HERO_ICON) {
                attrs.jsStyle {
                    width = "16px"
                    height = "16px"
                }
            }
        }
    }
}
