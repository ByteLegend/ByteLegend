package com.bytelegend.client.app.ui

import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.client.app.engine.util.jsObjectBackedSetOf
import kotlinx.html.classes
import react.RBuilder
import react.RState
import react.dom.a
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
            if (game.locale == Locale.ZH_HANS) {
                a {
                    attrs.target = "_blank"
                    attrs.href = "https://beian.miit.gov.cn"
                    attrs.classes = jsObjectBackedSetOf("icp-widget")
                    +"沪ICP备2020033444号"
                }
            }

            if (!game.gameControl.online) {
                span {
                    attrs.classes = jsObjectBackedSetOf("server-location-link-offline")
                    +i("OfflineMode")
                }
            }
        }
    }
}
