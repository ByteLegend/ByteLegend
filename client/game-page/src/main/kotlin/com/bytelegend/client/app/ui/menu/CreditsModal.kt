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
package com.bytelegend.client.app.ui.menu

import BootstrapNavItem
import BootstrapNavLink
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapNav
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.setState
import com.bytelegend.client.app.ui.unsafeDiv
import com.bytelegend.client.app.ui.unsafeSpan
import kotlinx.js.jso
import react.ChildrenBuilder
import react.Fragment
import react.State
import react.create
import react.dom.events.MouseEventHandler
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.br
import react.dom.html.ReactHTML.h5
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ul
import react.react

val CREDITS_TAB = "CreditsTab"
val OPENSOURCE_TAB = "OpenSourceTab"
val MATERIAL_TAB = "MaterialTab"

interface CreditsModalState : State {
    var activeTabIndex: Int
}

data class CreditsTab(
    val key: String,
    val title: String,
    val fn: (ChildrenBuilder) -> Unit
)

fun ChildrenBuilder.aTag(text: String, onClickFunction: MouseEventHandler<*>) {
    a {
        +text
        href = "#"
        onClick = onClickFunction
    }
}

class CreditsModal : GameUIComponent<GameProps, CreditsModalState>() {
    private val tabs: List<CreditsTab> = listOf(
        CreditsTab(CREDITS_TAB, "MenuCreditsTitle") {
            it.creditsTab()
        },
        CreditsTab(OPENSOURCE_TAB, OPENSOURCE_TAB) {
            it.openSourceTab()
        },
        CreditsTab(MATERIAL_TAB, MATERIAL_TAB) {
            it.materialTab()
        }
    )

    init {
        state = jso { activeTabIndex = 0 }
    }

    private fun ChildrenBuilder.creditsTab() {
        unsafeDiv(i("SpecialThanks"))

        h5 {
            +i("Thanks")
        }

        ul {
            li {
                unsafeSpan(i("SpecialThanksGradleParagraph"))
            }
            li {
                unsafeSpan(i("SpecialThanksKotlinParagraph"))
            }
            li {
                unsafeSpan(i("SpecialThanksTiledParagraph"))
            }
            li {
                unsafeSpan(i("SpecialThanksIdeaParagraph"))
            }
            li {
                unsafeSpan(i("SpecialThanksGitHub1sParagraph"))
            }
            li {
                aTag(i("ClickHere")) {
                    setState {
                        activeTabIndex = 1
                    }
                }
                unsafeSpan(i("ClickHereToSeeOpenSourceSoftware"))
            }

            li {
                aTag(i("ClickHere")) {
                    setState {
                        activeTabIndex = 2
                    }
                }
                +i("ClickHereToSeeArtwork")
            }
        }
    }

    private fun ChildrenBuilder.openSourceTab() {
        child(OpenSourceSoftwareTable::class.react, jso {
            this.game = props.game
        })
    }

    private fun ChildrenBuilder.materialTab() {
        child(GameMaterialTable::class.react, jso {
            this.game = props.game
        })
    }

    override fun render() = Fragment.create {
        BootstrapModalBody {
            BootstrapNav {
                variant = "tabs"
                tabs.forEachIndexed { index: Int, tab: CreditsTab ->
                    BootstrapNavItem {
                        BootstrapNavLink {
                            active = tab == tabs[state.activeTabIndex]
                            eventKey = tab.key
                            onSelect = {
                                setState {
                                    activeTabIndex = index
                                }
                            }
                            +i(tab.title)
                        }
                    }
                }
            }
            br { }

            tabs[state.activeTabIndex].fn(this)
        }
    }
}
