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

import com.bytelegend.app.shared.i18n.Locale
import csstype.ClassName
import react.Fragment
import react.State
import react.create
import react.dom.html.AnchorTarget
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.span

const val ICPServerLocationWidgetWidth = 300
const val ICPServerLocationWidgetHeight = 20

class ICPServerLocationWidget : GameUIComponent<GameProps, State>() {
    override fun render() = Fragment.create {
        absoluteDiv(
            left = (gameContainerWidth - ICPServerLocationWidgetWidth) / 2,
            top = uiContainerCoordinateInGameContainer.y + uiContainerSize.height - ICPServerLocationWidgetHeight,
            width = ICPServerLocationWidgetWidth,
            height = ICPServerLocationWidgetHeight,
            zIndex = Layer.IcpServerLocationWidget.zIndex(),
            className = "flex-center white-text-black-shadow-1"
        ) {
            if (game.locale == Locale.ZH_HANS) {
                a {
                    target = AnchorTarget._blank
                    href = "https://beian.miit.gov.cn"
                    className = ClassName("icp-widget")
                    +"沪ICP备2020033444号"
                }
            }

            if (!game.gameControl.online) {
                span {
                    className = ClassName("server-location-link-offline")
                    +i("OfflineMode")
                }
            }
        }
    }
}
