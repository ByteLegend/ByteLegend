package com.bytelegend.client.app.ui

// package common.ui

import kotlinx.css.Position
import kotlinx.css.position
import kotlinx.css.px
import kotlinx.css.right
import kotlinx.css.top
import kotlinx.css.zIndex
import kotlinx.html.classes
import kotlinx.html.id
import kotlinx.html.title
import react.RBuilder
import react.RState
import react.dom.a
import react.dom.img
import react.dom.span
import styled.css
import styled.styledSpan

val AVATAR_WIDTH = 64
val AVATAR_HEIGHT = 64

interface UserAvatarWidgetProps : GameProps

interface UserAvatarWidgetState : RState

class UserAvatarWidget : GameUIComponent<UserAvatarWidgetProps, UserAvatarWidgetState>() {
    override fun RBuilder.render() {
        val styleBuilder: dynamic.() -> Unit = {
            cursor = "pointer"
            backgroundColor = "white"
        }
        absoluteDiv(
            uiContainerCoordinateInGameContainer.x + uiContainerSize.width - AVATAR_WIDTH + 2,
            uiContainerCoordinateInGameContainer.y + 2,
            AVATAR_WIDTH,
            AVATAR_HEIGHT,
            Layer.UserAvatarWidget.zIndex(),
            classes = setOf("picture-frame-border"),
            extraStyleBuilder = styleBuilder
        ) {
            if (game.player.isAnonymous) {
                attrs.title = i("Login")

                img {
                    attrs.classes = setOf("avatar-img")
                    attrs.src = game.resolve("/img/ui/login.png")
                }

                span {
                    attrs.classes = setOf("avatar-login-span")
                    a {
                        attrs.id = "login-link"
                        attrs.href = "/login?redirect=/"
                        +i("Login")
                    }
                }
            } else {
                img {
                    attrs.classes = setOf("avatar-img")
                    attrs.src = game.player.avatarUrl ?: ""
                }
            }
        }

        if (!game.player.isAnonymous) {
            styledSpan {
                attrs.classes = setOf("nickname-span")
                css {
                    position = Position.absolute
                    top = (uiContainerCoordinateInGameContainer.y + 2 + AVATAR_HEIGHT).px
                    right = (gameContainerWidth - uiContainerCoordinateInGameContainer.x - uiContainerSize.width).px
                    zIndex = Layer.UserAvatarWidget.zIndex()
                }
                a {
                    attrs.id = "logout-link"
                    attrs.title = i("Logout")
                    attrs.href = "/logout?redirect=/"
                    +(game.player.nickname ?: "#Error")
                }
            }
        }

//        if (!currentUser.anonymous) {
//            styledSpan {
//                attrs.classes = setOf("nickname-span")
//                css {
//                    position = Position.absolute
//                    top = (canvasCoordinateInGameContainer.y + 2 + AVATAR_HEIGHT).px
//                    right = (containerWidth - canvasCoordinateInGameContainer.x - canvasPixelSize.width).px
//                    zIndex = props.layer.zIndex
//                }
//                +currentUser.nickname
//            }
//
//            styledSpan {
//                attrs.classes = setOf("nickname-span")
//                attrs["role"] = "img"
//                attrs["aria-label"] = "star"
//                css {
//                    position = Position.absolute
//                    top = (canvasCoordinateInGameContainer.y + 2 + AVATAR_HEIGHT + 32).px
//                    right = (containerWidth - canvasCoordinateInGameContainer.x - canvasPixelSize.width).px
//                    zIndex = props.layer.zIndex
//                }
//                +"${game.starNumber} ⭐"
//            }
//        }
    }
}
