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

import com.bytelegend.client.app.engine.DefaultGameScene
import com.bytelegend.client.app.ui.script.Widget
import react.Fragment
import react.State
import react.create

interface GameScriptWidgetDisplayLayerProps : GameProps

class GameScriptWidgetDisplayLayer : GameUIComponent<GameScriptWidgetDisplayLayerProps, State>() {
    @Suppress("UnsafeCastFromDynamic")
    override fun render() = Fragment.create {
        game.activeScene.unsafeCast<DefaultGameScene>().scriptWidgets.entries.forEach {
            val widget: Widget<GameProps> = it.value.unsafeCast<Widget<GameProps>>()
            child(widget.type, widget.props)
        }
    }
}
