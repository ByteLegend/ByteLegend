package com.bytelegend.client.app.ui.item

import com.bytelegend.app.client.ui.bootstrap.BootstrapListGroupItem
import com.bytelegend.client.utils.jsObjectBackedSetOf
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import kotlinx.html.classes
import react.RBuilder
import react.RState
import react.dom.div

interface ItemWidgetProps : GameProps

class ItemsWidget : GameUIComponent<ItemWidgetProps, RState>() {
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
            attrs.classes = jsObjectBackedSetOf("inline-icon", item)
        }
    }

    private fun RBuilder.renderText(text: String) {
        div {
            attrs.classes = jsObjectBackedSetOf("map-title-text", "items-widget")
            +text
        }
    }
}
