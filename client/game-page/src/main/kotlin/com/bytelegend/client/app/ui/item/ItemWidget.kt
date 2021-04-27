package com.bytelegend.client.app.ui.item

import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import kotlinx.html.classes
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RState
import react.dom.div

interface ItemWidgetProps : GameProps

class ItemWidget : GameUIComponent<ItemWidgetProps, RState>() {
    override fun RBuilder.render() {
        if (game.heroPlayer.isAnonymous) {
            return
        }
        div {
            attrs.classes = setOf("sidebar-item-widget")
            attrs.onClickFunction = {
                game.modalController.show {
                    child(ItemModal::class) {}
                }
            }
        }

        val items = game.heroPlayer.items
        if (items.size > 3) {
            renderOne(items[0])
            renderOne(items[1])
            renderDotDotDot()
        } else {
            items.forEach { renderOne(it) }
        }
    }

    private fun renderOne(item: String) {
    }

    private fun renderDotDotDot() {
    }
}
