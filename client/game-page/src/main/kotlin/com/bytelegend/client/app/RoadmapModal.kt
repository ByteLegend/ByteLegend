package com.bytelegend.client.app

import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import react.RBuilder
import react.RState
import react.dom.div
import react.dom.jsStyle

const val PARCHMENT_WIDTH = 429
const val PARCHMENT_HEIGHT = 357

class RoadmapModal : GameUIComponent<GameProps, RState>() {
    override fun RBuilder.render() {
        div {
            val divHeight = gameContainerHeight
            val divWidth: Double = 1.0 * divHeight / PARCHMENT_HEIGHT * PARCHMENT_WIDTH
            attrs.jsStyle {
                width = "${divWidth.toInt()}px"
                height = "${divHeight}px"
                background = "url('${game.resolve("/img/ui/parchment.svg")}')"
                backgroundSize = "100% 100%"
            }
        }
    }
}
