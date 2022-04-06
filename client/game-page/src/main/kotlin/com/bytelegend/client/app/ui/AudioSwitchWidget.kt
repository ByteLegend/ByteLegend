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
import csstype.ClassName
import react.Fragment
import react.State
import react.create
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.span

class AudioSwitchWidget : GameUIComponent<GameProps, State>() {
    override fun render() = Fragment.create {
        div {
            span {
                // move the music symbol slightly down
                jsStyle {
                    position = "relative"
                    top = "4px"
                }
                className = ClassName("white-text-white-shadow-1")
                +"\uD83C\uDFB5 "
            }
            className = ClassName("map-title-widget")
            jsStyle {
                display = "inline-block"
            }
            BootstrapSwitchButton {
                size = "xs"
                checked = gameControl.audioEnabled
                onChange = {
                    gameControl.audioEnabled = it
                    setState { }
                }
            }
        }
    }
}
