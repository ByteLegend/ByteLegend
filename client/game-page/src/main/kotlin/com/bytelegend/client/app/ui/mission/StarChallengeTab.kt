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
package com.bytelegend.client.app.ui.mission

import com.bytelegend.client.utils.jsObjectBackedSetOf
import com.bytelegend.client.app.page.game
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.unsafeSpan
import kotlinx.html.classes
import react.RBuilder
import react.RComponent
import react.State
import react.dom.div
import react.dom.img

class StarChallengeTab : RComponent<GameProps, State>() {
    override fun RBuilder.render() {
        div {
            attrs.classes = jsObjectBackedSetOf("mission-tab-content")
            unsafeSpan(game.i("StarByteLegendChallengeText"))

            img {
                attrs.src = game.resolve("/gif/star-bytelegend.gif")
            }
        }
    }
}
