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

package com.bytelegend.client.app.ui.achievement

import com.bytelegend.app.client.ui.bootstrap.BootstrapListGroupItem
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalHeader
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalTitle
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.client.app.engine.getIconUrl
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.html.classes
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onMouseOutFunction
import kotlinx.html.js.onMouseOverFunction
import react.RBuilder
import react.State
import react.dom.div
import react.dom.img
import react.dom.p
import react.setState

interface AchievementWidgetProps : GameProps

class AchievementWidget : GameUIComponent<AchievementWidgetProps, State>() {
    override fun RBuilder.render() {
        BootstrapListGroupItem {
            div {
                attrs.classes = jsObjectBackedSetOf("map-title-text")
                attrs.onClickFunction = {
                    game.modalController.show {
                        child(AchievementModal::class) {
                            attrs.game = game
                        }
                    }
                }
                if (game.locale == Locale.EN) {
                    +"Achv"
                } else {
                    +i("Achievement")
                }
            }
        }
    }
}

interface AchievementModalState : State {
    var hoveredAchievementId: String?
}

class AchievementModal : GameUIComponent<GameProps, AchievementModalState>() {
    override fun AchievementModalState.init() {
        hoveredAchievementId = null
    }

    override fun RBuilder.render() {
        BootstrapModalHeader {
            attrs.closeButton = true
            BootstrapModalTitle {
                +i("MyAchievement")
            }
        }

        BootstrapModalBody {
            div {
                attrs.classes = jsObjectBackedSetOf("achievement-modal")

                game.heroPlayer.achievements.forEach { renderOneAchievement(it) }

                attrs.onMouseOutFunction = {
                    setState { hoveredAchievementId = null }
                }

                p {
                    if (state.hoveredAchievementId == null) {
                        attrs.classes = jsObjectBackedSetOf("transparent-text")
                        +"Yay! You found an easter egg!"
                    } else {
                        +i(state.hoveredAchievementId!!)
                    }
                }
            }
        }
    }

    private fun RBuilder.renderOneAchievement(achievementId: String) {
        div {
            attrs.classes = jsObjectBackedSetOf("achievement-item", "flex-center")
            attrs.onMouseOverFunction = {
                setState { hoveredAchievementId = achievementId }
            }
            attrs.onMouseOutFunction = {
                setState { hoveredAchievementId = null }
            }
            img {
                attrs.src = game.getIconUrl(achievementId)
            }
        }
    }
}
