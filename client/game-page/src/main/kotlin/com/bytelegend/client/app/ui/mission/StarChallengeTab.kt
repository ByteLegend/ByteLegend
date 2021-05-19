package com.bytelegend.client.app.ui.mission

import com.bytelegend.client.app.engine.util.jsObjectBackedSetOf
import com.bytelegend.client.app.page.game
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.unsafeHtml
import kotlinx.html.classes
import react.RBuilder
import react.RComponent
import react.RState
import react.dom.div
import react.dom.img

class StarChallengeTab : RComponent<GameProps, RState>() {
    override fun RBuilder.render() {
        div {
            attrs.classes = jsObjectBackedSetOf("mission-tab-content")
            unsafeHtml(game.i("StarByteLegendChallengeText"))

            img {
                attrs.src = game.resolve("/gif/star-bytelegend.gif")
            }
        }
    }
}
