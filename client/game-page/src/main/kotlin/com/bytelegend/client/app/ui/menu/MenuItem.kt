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

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.GameCanvasState
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalHeader
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalTitle
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.Layer
import com.bytelegend.client.app.ui.absoluteDiv
import com.bytelegend.client.app.ui.unsafeSpan
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.browser.window
import kotlinx.html.classes
import kotlinx.html.js.onBlurFunction
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onFocusFunction
import kotlinx.html.js.onMouseMoveFunction
import kotlinx.html.js.onMouseOutFunction
import kotlinx.html.js.onMouseOverFunction
import org.w3c.dom.events.Event
import react.RBuilder
import react.RState
import react.dom.a
import react.dom.div
import react.dom.h5
import react.dom.img
import react.dom.jsStyle
import react.dom.p
import react.setState

interface MenuItemProps : GameProps {
    var index: Int
    var iconImageId: String
    var titleId: String
    var onClickFunction: (Event) -> Unit
}

interface MenuItemState : RState {
    var hover: Boolean
}

private const val SHOW_AD_MODAL_EVENT = "show.ad.modal"

class MenuItem : GameUIComponent<MenuItemProps, MenuItemState>() {
    private val iconSize = 64
    private val onHover: (Event) -> Unit = {
        setState { hover = true }
    }
    private val onHoverOut: (Event) -> Unit = {
        setState { hover = false }
    }

    override fun RBuilder.render() {
        div {
            attrs.onMouseMoveFunction = onHover
            attrs.onMouseOverFunction = onHover
            attrs.onFocusFunction = onHover
            attrs.onMouseOutFunction = onHoverOut
            attrs.onBlurFunction = onHoverOut
            attrs.onClickFunction = props.onClickFunction

            attrs.classes = jsObjectBackedSetOf("menu-item-div")
            attrs.jsStyle {
                height = "${iconSize}px"
                width = "${iconSize}px"
            }

            img {
                attrs.src = game.resolve("/img/ui/${props.iconImageId}.png")
                attrs.jsStyle {
                    height = "${iconSize}px"
                    width = "${iconSize}px"
                }
            }

            if (state.hover) {
                div {
                    +i(props.titleId)
                    attrs.classes = jsObjectBackedSetOf("menu-item-div-title", "white-text-black-shadow-1")
                }
            }
        }
    }
}

interface MenuProps : GameProps

data class MenuItemData(
    val iconImageId: String,
    val titleId: String,
    val onClickFunction: UnitFunction
)

fun GameCanvasState.determineMenuCoordinateInGameContainer(): PixelCoordinate = PixelCoordinate(
    getUICoordinateInGameContainer().x + getUIContainerSize().width - MENU_WIDTH,
    getUICoordinateInGameContainer().y + getUIContainerSize().height - MENU_HEIGHT - 20
)

const val MENU_HEIGHT = 64
const val MENU_WIDTH = 400

class Menu : GameUIComponent<MenuProps, RState>() {
    private val onShowAdModalEventListener: EventListener<Nothing> = this::onShowAdModalEvent
    private val items: List<MenuItemData> = listOf(
        // 1. menu-github: link to github.com/ByteLegend/ByteLegend
        // 2. menu-notification: open notification box
        // 3. menu-settings: open settings box
        // 4. menu-about: about box
        // 5. menu-credits: credits
        // 6. menu-help: help page
        MenuItemData("menu-credits", "MenuCreditsTitle", this::onClickCreditsMenu),
        MenuItemData("menu-about", "MenuAboutTitle", this::onClickContactAboutMenu),
        MenuItemData("menu-settings", "MenuSettingsTitle", this::onClickUnfinishedMenu),
        MenuItemData("menu-help", "MenuHelpTitle", this::onClickUnfinishedMenu),
        MenuItemData("menu-notification", "MenuNotificationTitle", this::onClickUnfinishedMenu),
        MenuItemData("menu-github", "MenuGitHubTitle", this::onClickGitHubMenu),
    )

    override fun RBuilder.render() {
        absoluteDiv(
            left = gameCanvasState.determineMenuCoordinateInGameContainer().x,
            top = gameCanvasState.determineMenuCoordinateInGameContainer().y,
            width = MENU_WIDTH,
            height = MENU_HEIGHT,
            zIndex = Layer.Menu.zIndex(),
        ) {
            items.forEachIndexed { index, item ->
                child(MenuItem::class) {
                    attrs.game = game
                    attrs.index = index
                    attrs.iconImageId = item.iconImageId
                    attrs.titleId = item.titleId
                    attrs.onClickFunction = {
                        item.onClickFunction()
                    }
                }
            }
        }
    }

    private fun onClickCreditsMenu() {
        game.modalController.show {
            child(CreditsModal::class) {
                attrs.game = game
            }
        }
    }

    private fun onClickGitHubMenu() {
        window.open("https://github.com/ByteLegend/ByteLegend", "_blank")
    }

    private fun onClickUnfinishedMenu() {
        game.modalController.show {
            BootstrapModalHeader {
                attrs.closeButton = true
                BootstrapModalTitle {
                    +i("UnfinishedTitle")
                }
            }
            BootstrapModalBody {
                p {
                    a {
                        attrs.onClickFunction = {
                            onClickContactAboutMenu()
                        }
                        attrs.href = "#"
                        +i("ClickHere")
                    }
                    unsafeSpan(i("UnfinishedText"))
                }
            }
        }
    }

    private fun onShowAdModalEvent(n: Nothing) {
        game.modalController.show {
            BootstrapModalHeader {
                attrs.closeButton = true
                BootstrapModalTitle {
                    +i("YourAdHere")
                }
            }

            BootstrapModalBody {
                h5 { +i("ContactUsTitle") }
                p {
                    unsafeSpan(i("ContactUsParagraph"))
                }
            }
        }
    }

    private fun onClickContactAboutMenu() {
        game.modalController.show {
            BootstrapModalHeader {
                attrs.closeButton = true
                BootstrapModalTitle {
                    attrs.asDynamic().id = "contained-modal-title-vcenter"
                    +i("MenuContactTitle")
                }
            }

            BootstrapModalBody {
                h5 { +i("AboutByteLegendTitle") }
                p {
                    unsafeSpan(i("AboutByteLegendParagraph"))
                }

                h5 { +i("ReportBugFeatureRequestTitle") }
                p {
                    unsafeSpan(i("ReportBugFeatureRequestParagraph"))
                }

                h5 { +i("DiscussHowToPlayTitle") }
                p {
                    unsafeSpan(i("DiscussHowToPlayParagraph"))
                }

                h5 { +i("ContactUsTitle") }
                p {
                    unsafeSpan(i("ContactUsParagraph"))
                }
            }
        }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        props.game.eventBus.on(SHOW_AD_MODAL_EVENT, onShowAdModalEventListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.game.eventBus.remove(SHOW_AD_MODAL_EVENT, onShowAdModalEventListener)
    }
}
