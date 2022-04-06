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
package com.bytelegend.client.app.ui

import BootstrapDropdownDivider
import BootstrapDropdownItem
import BootstrapDropdownMenu
import BootstrapDropdownToggle
import com.bytelegend.app.client.ui.bootstrap.BootstrapDropdown
import com.bytelegend.client.app.web.LOGIN_LINK_CLICKED_EVENT
import csstype.ClassName
import kotlinx.js.jso
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.events.Event
import react.Fragment
import react.State
import react.create
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.img
import react.dom.html.ReactHTML.span

const val AVATAR_WIDTH = 64
const val AVATAR_HEIGHT = 64
const val AVATAR_DROPDOWN_TOGGLE_HEIGHT = 32

interface UserAvatarWidgetState : State {
    var showDropdownArrow: Boolean
    var showDropdownMenu: Boolean
}

class UserAvatarWidget : GameUIComponent<GameProps, UserAvatarWidgetState>() {
    init {
        state = jso {
            showDropdownArrow = false
            showDropdownMenu = false
        }
    }

    override fun render() = Fragment.create {
        absoluteDiv(
            top = uiContainerCoordinateInGameContainer.y,
            right = uiContainerCoordinateInGameContainer.x,
            width = AVATAR_WIDTH,
            height = AVATAR_HEIGHT,
            zIndex = Layer.UserAvatarWidget.zIndex(),
            className = "picture-frame-border"
        ) {
            it.id = "avatar-div"
            if (game.heroPlayer.isAnonymous) {
                it.title = i("Login")

                img {
                    className = ClassName("avatar-img")
                    src = game.resolve("/img/ui/login.png")
                }

                span {
                    className = ClassName("avatar-login-span")
                    a {
                        id = "login-link"
                        href = "/game/login?redirect=/"
                        onClick = {
                            props.game.eventBus.emit(LOGIN_LINK_CLICKED_EVENT, null)
                        }
                        +i("Login")
                    }
                }
            } else {
                img {
                    className = ClassName("avatar-img")
                    src = game.heroPlayer.avatarUrl ?: ""
                }
                it.onMouseMove = {
                    setState { showDropdownArrow = true }
                }
                it.onMouseOut = {
                    setState { showDropdownArrow = false }
                }
                it.onClick = {
                    setState { showDropdownMenu = !showDropdownMenu }
                }
            }
        }

        if (!game.heroPlayer.isAnonymous && (state.showDropdownArrow || state.showDropdownMenu)) {
            absoluteDiv(
                top = uiContainerCoordinateInGameContainer.y + AVATAR_HEIGHT - AVATAR_DROPDOWN_TOGGLE_HEIGHT,
                left = gameContainerWidth - uiContainerCoordinateInGameContainer.x,
                zIndex = Layer.UserAvatarWidget.zIndex()
            ) {
                it.id = "avatar-dropdown"
                BootstrapDropdown {
                    show = state.showDropdownMenu
                    BootstrapDropdownToggle {
                        id = "avatar-dropdown-toggle"
                        variant = "primary"
                        size = "sm"
                    }
                    BootstrapDropdownMenu {
                        BootstrapDropdownItem {
                            +game.heroPlayer.nickname
                            onClick = {
                                window.open("https://github.com/${game.heroPlayer.username}", "_blank")
                            }
                        }
                        BootstrapDropdownDivider {}
                        BootstrapDropdownItem {
                            +i("Logout")
                            href = "/game/logout?redirect=/"
                        }
                    }
                }
            }
        }
    }

    private val outsideEventListener: (Event) -> Unit = this::onClick

    private fun onClick(event: Event) {
        if (!state.showDropdownMenu) {
            return
        }
        var target = event.target
        while (target != document) {
            if (target.asDynamic()?.id == "avatar-div" || target.asDynamic()?.id == "avatar-dropdown") {
                return
            }
            target = target.asDynamic().parentNode
        }
        setState { showDropdownMenu = false }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        window.addEventListener("click", outsideEventListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        window.removeEventListener("click", outsideEventListener)
    }
}
