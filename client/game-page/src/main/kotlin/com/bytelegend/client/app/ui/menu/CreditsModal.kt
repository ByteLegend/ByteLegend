package com.bytelegend.client.app.ui.menu

import BootstrapNavItem
import BootstrapNavLink
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapNav
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.unsafeSpan
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.RBuilder
import react.RElementBuilder
import react.RState
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

interface CreditsModalState : RState {
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
        builder.h5 {
            +i("SpecialThanks")
        }

        builder.p {
            +i("SpecialThanksMyDaughter")
        }

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
