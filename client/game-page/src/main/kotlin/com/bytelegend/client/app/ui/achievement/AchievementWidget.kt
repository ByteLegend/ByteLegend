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
import com.bytelegend.client.app.ui.setState
import kotlinext.js.jso
import react.ChildrenBuilder
import react.Fragment
import react.State
import react.create
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import react.dom.html.ReactHTML.p
import react.react

interface AchievementWidgetProps : GameProps

class AchievementWidget : GameUIComponent<AchievementWidgetProps, State>() {
    override fun render() = Fragment.create {
        BootstrapListGroupItem {
            onClick = {
                game.modalController.show {
                    child(AchievementModal::class.react, jso {
                        this.game = props.game
                    })
                }
            }
            div {
                className = "map-title-text"

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
    init {
        state = jso { hoveredAchievementId = null }
    }

    override fun render() = Fragment.create {
        BootstrapModalHeader {
            closeButton = true
            BootstrapModalTitle {
                +i("MyAchievements")
            }
        }

        BootstrapModalBody {
            div {
                className = "item-modal"

                game.heroPlayer.achievements.forEach { renderOneAchievement(it) }

                onMouseOut = {
                    setState { hoveredAchievementId = null }
                }

                p {
                    if (state.hoveredAchievementId == null) {
                        className = "transparent-text"
                        +"Yay! You found an easter egg!"
                    } else {
                        +i(state.hoveredAchievementId!!)
                    }
                }
            }
        }
    }

    private fun ChildrenBuilder.renderOneAchievement(achievementId: String) {
        div {
            className = "achievement-item flex-center"
            onMouseOver = {
                setState { hoveredAchievementId = achievementId }
            }
            onMouseOut = {
                setState { hoveredAchievementId = null }
            }
            img {
                src = game.getIconUrl(achievementId)
            }
        }
    }
}
