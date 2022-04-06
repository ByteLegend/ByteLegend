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
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.Layer
import com.bytelegend.client.app.ui.absoluteDiv
import com.bytelegend.client.app.ui.jsStyle
import com.bytelegend.client.app.ui.setState
import com.bytelegend.client.app.ui.unsafeDiv
import com.bytelegend.client.app.ui.unsafeSpan
import csstype.ClassName
import kotlinx.js.jso
import kotlinx.browser.window
import react.Fragment
import react.State
import react.create
import react.dom.events.EventHandler
import react.dom.events.FocusEventHandler
import react.dom.events.MouseEventHandler
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h5
import react.dom.html.ReactHTML.img
import react.dom.html.ReactHTML.p
import react.react

interface MenuItemProps : GameProps {
    var index: Int
    var iconImageId: String
    var titleId: String
    var onClickFunction: MouseEventHandler<*>
}

interface MenuItemState : State {
    var hover: Boolean
}

private const val SHOW_AD_MODAL_EVENT = "show.ad.modal"

class MenuItem : GameUIComponent<MenuItemProps, MenuItemState>() {
    init {
        state = jso { hover = false }
    }

    private val iconSize = 64
    private val onHover: EventHandler<*> = {
        setState { hover = true }
    }
    private val onHoverOut: EventHandler<*> = {
        setState { hover = false }
    }

    override fun render() = Fragment.create {
        div {
            onMouseMove = onHover.unsafeCast<MouseEventHandler<*>>()
            onMouseOver = onHover.unsafeCast<MouseEventHandler<*>>()
            onFocus = onHover.unsafeCast<FocusEventHandler<*>>()
            onMouseOut = onHoverOut.unsafeCast<MouseEventHandler<*>>()
            onBlur = onHoverOut.unsafeCast<FocusEventHandler<*>>()
            onClick = props.onClickFunction

            className = ClassName("menu-item-div")
            jsStyle {
                height = "${iconSize}px"
                width = "${iconSize}px"
            }

            img {
                src = props.game.resolve("/img/ui/${props.iconImageId}.png")
                jsStyle {
                    this.height = "${iconSize}px"
                    this.width = "${iconSize}px"
                }
            }

            if (state.hover) {
                div {
                    +i(props.titleId)
                    className = ClassName("menu-item-div-title white-text-black-shadow-1")
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

class Menu : GameUIComponent<MenuProps, State>() {
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
        MenuItemData("menu-ranking", "MenuLeaderboardTitle", this::onClickLeaderboardMenu),
        MenuItemData("menu-notification", "MenuNotificationTitle", this::onClickUnfinishedMenu),
        MenuItemData("menu-github", "MenuGitHubTitle", this::onClickGitHubMenu),
    )

    override fun render() = Fragment.create {
        absoluteDiv(
            left = gameCanvasState.determineMenuCoordinateInGameContainer().x,
            top = gameCanvasState.determineMenuCoordinateInGameContainer().y,
            width = MENU_WIDTH,
            height = MENU_HEIGHT,
            zIndex = Layer.Menu.zIndex(),
        ) {
            items.forEachIndexed { index, item ->
                child(MenuItem::class.react, jso {
                    this.game = props.game
                    this.index = index
                    iconImageId = item.iconImageId
                    titleId = item.titleId
                    onClickFunction = {
                        item.onClickFunction()
                    }
                })
            }
        }
    }

    private fun onClickCreditsMenu() {
        game.modalController.show {
            child(CreditsModal::class.react, jso {
                this.game = props.game
            })
        }
    }

    private fun onClickGitHubMenu() {
        window.open("https://github.com/ByteLegend/ByteLegend", "_blank")
    }

    private fun onClickLeaderboardMenu() {
        game.modalController.show {
            BootstrapModalHeader {
                closeButton = true
                BootstrapModalTitle {
                    +i("MenuLeaderboardTitle")
                }
            }
            BootstrapModalBody {
                child(LeaderboardTable::class.react, jso {
                    this.game = props.game
                })
            }
        }
    }

    private fun onClickUnfinishedMenu() {
        game.modalController.show {
            BootstrapModalHeader {
                closeButton = true
                BootstrapModalTitle {
                    +i("UnfinishedTitle")
                }
            }
            BootstrapModalBody {
                p {
                    a {
                        onClick = {
                            onClickContactAboutMenu()
                        }
                        href = "#"
                        +i("ClickHere")
                    }
                    unsafeSpan(i("UnfinishedText"))
                }
            }
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onShowAdModalEvent(n: Nothing) {
        game.modalController.show {
            BootstrapModalHeader {
                closeButton = true
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
                closeButton = true
                BootstrapModalTitle {
                    id = "contained-modal-title-vcenter"
                    +i("MenuContactTitle")
                }
            }

            BootstrapModalBody {
                unsafeDiv(i("AboutByteLegendBody"))

                if (!game.heroPlayer.isAnonymous && game.locale == Locale.ZH_HANS) {
                    h5 {
                        +"加入QQ群"
                    }

                    unsafeDiv(
                        """<p>欢迎加入玩家QQ群788942934，您的个人入群密码为<span style='color:red;font-weight: bold;'>${game.joinQQGroupSecret}</span>，请勿透露给他人。
                            <a target="_blank" href="https://qm.qq.com/cgi-bin/qm/qr?k=C0UVtpyyFqkAQp6bRfAiE0caJucOSzXQ&jump_from=webapi"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="字节传说ByteLegend" title="字节传说ByteLegend"></a>
                            </p>"""
                    )
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
