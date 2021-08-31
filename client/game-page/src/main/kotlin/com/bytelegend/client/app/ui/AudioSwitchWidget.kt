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

import com.bytelegend.app.client.ui.bootstrap.BootstrapSwitchButton
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.html.classes
import react.RBuilder
import react.RState
import react.dom.div
import react.dom.jsStyle
import react.dom.span
import react.setState

class AudioSwitchWidget : GameUIComponent<GameProps, RState>() {
    override fun RBuilder.render() {
        div {
            span {
                // move the music symbol slightly down
                attrs.jsStyle {
                    position = "relative"
                    top = "4px"
                }
                attrs.classes = jsObjectBackedSetOf("white-text-white-shadow-1")
                +"\uD83C\uDFB5 "
            }
            attrs.classes = jsObjectBackedSetOf("map-title-widget")
            attrs.jsStyle {
                display = "inline-block"
            }
            BootstrapSwitchButton {
                attrs.size = "xs"
                attrs.checked = gameControl.audioEnabled
                attrs.onChange = {
                    gameControl.audioEnabled = it
                    setState { }
                }
            }
        }
    }
}
