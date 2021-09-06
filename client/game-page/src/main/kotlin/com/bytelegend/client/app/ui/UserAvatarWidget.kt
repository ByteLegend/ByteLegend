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

// package common.ui

import BootstrapDropdownDivider
import BootstrapDropdownItem
import BootstrapDropdownMenu
import BootstrapDropdownToggle
import com.bytelegend.app.client.ui.bootstrap.BootstrapDropdown
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.classes
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onMouseMoveFunction
import kotlinx.html.js.onMouseOutFunction
import kotlinx.html.title
import org.w3c.dom.events.Event
import react.RBuilder
import react.State
import react.dom.a
import react.dom.img
import react.dom.span
import react.setState

const val AVATAR_WIDTH = 64
const val AVATAR_HEIGHT = 64
const val AVATAR_DROPDOWN_TOGGLE_HEIGHT = 32

interface UserAvatarWidgetState : State {
    var showDropdownArrow: Boolean
    var showDropdownMenu: Boolean
}

class UserAvatarWidget : GameUIComponent<GameProps, UserAvatarWidgetState>() {
    override fun UserAvatarWidgetState.init() {
        showDropdownArrow = false
        showDropdownMenu = false
    }

    override fun RBuilder.render() {
        absoluteDiv(
            top = uiContainerCoordinateInGameContainer.y,
            right = uiContainerCoordinateInGameContainer.x,
            width = AVATAR_WIDTH,
            height = AVATAR_HEIGHT,
            zIndex = Layer.UserAvatarWidget.zIndex(),
            classes = jsObjectBackedSetOf("picture-frame-border")
        ) {
            attrs.id = "avatar-div"
            if (game.heroPlayer.isAnonymous) {
                attrs.title = i("Login")

                img {
                    attrs.classes = jsObjectBackedSetOf("avatar-img")
                    attrs.src = game.resolve("/img/ui/login.png")
                }

                span {
                    attrs.classes = jsObjectBackedSetOf("avatar-login-span")
                    a {
                        attrs.id = "login-link"
                        attrs.href = "/game/login?redirect=/"
                        +i("Login")
                    }
                }
            } else {
                img {
                    attrs.classes = jsObjectBackedSetOf("avatar-img")
                    attrs.src = game.heroPlayer.avatarUrl ?: ""
                }
                attrs.onMouseMoveFunction = {
                    setState { showDropdownArrow = true }
                }
                attrs.onMouseOutFunction = {
                    setState { showDropdownArrow = false }
                }
                attrs.onClickFunction = {
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
                attrs.id = "avatar-dropdown"
                BootstrapDropdown {
                    attrs.show = state.showDropdownMenu
                    BootstrapDropdownToggle {
                        attrs.id = "avatar-dropdown-toggle"
                        attrs.variant = "primary"
                        attrs.size = "sm"
                    }
                    BootstrapDropdownMenu {
                        BootstrapDropdownItem {
                            +game.heroPlayer.nickname
                            attrs.onClick = {
                                window.open("https://github.com/${game.heroPlayer.username}", "_blank")
                            }
                        }
                        BootstrapDropdownDivider {}
                        BootstrapDropdownItem {
                            +i("Logout")
                            attrs.href = "/game/logout?redirect=/"
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
