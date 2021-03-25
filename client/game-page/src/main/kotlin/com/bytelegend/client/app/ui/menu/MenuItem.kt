package com.bytelegend.client.app.ui.menu

import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.Layer
import com.bytelegend.client.app.ui.absoluteDiv
import common.ui.bootstrap.BootstrapModalBody
import common.ui.bootstrap.BootstrapModalHeader
import common.ui.bootstrap.BootstrapModalTitle
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

            attrs.classes = setOf("menu-item-div")
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
                    attrs.classes = setOf("menu-item-div-title", "white-text-black-shadow-1")
                }
            }
        }
    }
}

interface MenuProps : GameProps

data class MenuItemData(
    val iconImageId: String,
    val titleId: String,
    val onClickFunction: () -> Unit = {}
)

class Menu : GameUIComponent<MenuProps, RState>() {
    private val menuHeight = 64
    private val menuWidth = 400
    private val items: List<MenuItemData> = listOf(
        // 1. menu-github: link to github.com/ByteLegend/ByteLegend
        // 2. menu-notification: open notification box
        // 3. menu-settings: open settings box
        // 4. menu-about: about box
        // 5. menu-credits: credits
        // 6. menu-help: help page
        MenuItemData("menu-credits", "MenuCreditsTitle", this::onClickCreditsMenu),
        MenuItemData("menu-about", "MenuAboutTitle", this::onClickContactAboutMenu),
        MenuItemData("menu-settings", "MenuSettingsTitle"),
        MenuItemData("menu-help", "MenuHelpTitle"),
        MenuItemData("menu-notification", "MenuNotificationTitle"),
        MenuItemData("menu-github", "MenuGitHubTitle", this::onClickGitHubMenu),
    )

    override fun RBuilder.render() {
        absoluteDiv(
            left = uiContainerCoordinateInGameContainer.x + uiContainerSize.width - menuWidth,
            top = uiContainerCoordinateInGameContainer.y + uiContainerSize.height - menuHeight - 20,
            width = menuWidth,
            height = menuHeight,
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
                    consumer.onTagContentUnsafe {
                        +i("AboutByteLegendParagraph")
                    }
                }

                h5 { +i("ContactUsTitle") }
                p {
                    consumer.onTagContentUnsafe {
                        +i("ContactUsParagraph")
                    }
                }
            }
        }
    }
}
