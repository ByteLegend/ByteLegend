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
import com.bytelegend.client.app.ui.unsafeDiv
import com.bytelegend.client.app.ui.unsafeSpan
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.RBuilder
import react.RElementBuilder
import react.State
import react.dom.RDOMBuilder
import react.dom.a
import react.dom.br
import react.dom.h5
import react.dom.li
import react.dom.p
import react.dom.ul
import react.setState

val CREDITS_TAB = "CreditsTab"
val OPENSOURCE_TAB = "OpenSourceTab"
val MATERIAL_TAB = "MaterialTab"

interface CreditsModalState : State {
    var activeTabIndex: Int
}

data class CreditsTab(
    val key: String,
    val title: String,
    val fn: (RElementBuilder<*>) -> Unit
)

fun RDOMBuilder<*>.aTag(text: String, onClickFunction: (Event) -> Unit) {
    a {
        +text
        attrs.href = "#"
        attrs.onClickFunction = onClickFunction
    }
}

class CreditsModal : GameUIComponent<GameProps, CreditsModalState>() {
    private val tabs: List<CreditsTab> = listOf(
        CreditsTab(CREDITS_TAB, "MenuCreditsTitle", this::creditsTab),
        CreditsTab(OPENSOURCE_TAB, OPENSOURCE_TAB, this::openSourceTab),
        CreditsTab(MATERIAL_TAB, MATERIAL_TAB, this::materialTab),
    )

    override fun CreditsModalState.init() {
        activeTabIndex = 0
    }

    private fun creditsTab(builder: RElementBuilder<*>) {
        builder.unsafeDiv(i("SpecialThanks"))

        builder.h5 {
            +i("Thanks")
        }

        builder.ul {
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

    private fun openSourceTab(builder: RElementBuilder<*>) {
        builder.child(OpenSourceSoftwareTable::class) {
            attrs.game = game
        }
    }

    private fun materialTab(builder: RElementBuilder<*>) {
        builder.child(GameMaterialTable::class) {
            attrs.game = game
        }
    }

    override fun RBuilder.render() {
        BootstrapModalBody {
            BootstrapNav {
                attrs.variant = "tabs"
                tabs.forEachIndexed { index: Int, tab: CreditsTab ->
                    BootstrapNavItem {
                        BootstrapNavLink {
                            attrs.active = tab == tabs[state.activeTabIndex]
                            attrs.eventKey = tab.key
                            attrs.onSelect = {
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
