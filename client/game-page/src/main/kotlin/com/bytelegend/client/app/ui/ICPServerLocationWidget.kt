package com.bytelegend.client.app.ui

import com.bytelegend.app.client.ui.bootstrap.BootstrapModalBody
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalHeader
import com.bytelegend.app.client.ui.bootstrap.BootstrapModalTitle
import com.bytelegend.app.shared.enums.ServerLocation
import com.bytelegend.client.app.engine.util.jsObjectBackedSetOf
import kotlinx.html.classes
import kotlinx.html.js.onClickFunction
import kotlinx.html.title
import react.RBuilder
import react.RState
import react.dom.a
import react.dom.h4
import react.dom.img
import react.dom.jsStyle
import react.dom.p
import react.dom.span

val ICPServerLocationWidgetWidth = 300
val ICPServerLocationWidgetHeight = 20

interface ICPServerLocationWidgetState : RState {
//    var show: Boolean
}

class ICPServerLocationWidget : GameUIComponent<GameProps, ICPServerLocationWidgetState>() {
    override fun RBuilder.render() {
        absoluteDiv(
            left = (gameContainerWidth - ICPServerLocationWidgetWidth) / 2,
            top = uiContainerCoordinateInGameContainer.y + uiContainerSize.height - ICPServerLocationWidgetHeight,
            width = ICPServerLocationWidgetWidth,
            height = ICPServerLocationWidgetHeight,
            zIndex = Layer.IcpServerLocationWidget.zIndex(),
            classes = jsObjectBackedSetOf("flex-center", "white-text-black-shadow-1")
        ) {
            if (game.serverLocation == ServerLocation.BEIJING) {
                a {
                    attrs.target = "_blank"
                    attrs.href = "https://beian.miit.gov.cn"
                    attrs.jsStyle {
                        color = "white"
                        fontSize = "14px"
                        margin = "0 5px 0 5px"
                    }
                    +"沪ICP备2020033444号"
                }
            }

            img {
                attrs.height = "12px"
                attrs.width = "12px"
                attrs.src = game.resolve("/img/icon/server.png")
            }

            if (game.gameControl.online) {
                a {
                    attrs.classes = jsObjectBackedSetOf("server-location-link")
                    attrs.title = getServerLocationTitle()
                    attrs.onClickFunction = {
                        showServerLocationModal()
                    }

                    +getServerLocationDisplayName()
                }
            } else {
                span {
                    attrs.classes = jsObjectBackedSetOf("server-location-link-offline")
                    +i("OfflineMode")
                }
            }
        }
    }

    private fun showServerLocationModal() {
        game.modalController.show {
            BootstrapModalHeader {
                attrs.closeButton = true
                BootstrapModalTitle {
                    attrs.asDynamic().id = "contained-modal-title-vcenter"
                    unsafeSpan(getServerLocationTitleHtml())
                }
            }

            BootstrapModalBody {
                h4 { +i("WhatIsTheServerLocation") }
                p {
                    +i("ServerLocationExplanation")
                }
            }
        }
    }

    private fun getServerLocationDisplayName() = i(game.serverLocation.displayNameId())
    private fun getServerLocationTitle() = i("ServerLocationTitle", getServerLocationDisplayName())
    private fun getServerLocationTitleHtml() = i("ServerLocationTitleHtml", getServerLocationDisplayName())
}
