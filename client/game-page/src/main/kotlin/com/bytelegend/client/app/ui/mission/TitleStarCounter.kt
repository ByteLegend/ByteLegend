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

import com.bytelegend.client.app.ui.icon
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.html.classes
import react.Props
import react.RBuilder
import react.RComponent
import react.State
import react.dom.span

interface TitleStarCounterProps : Props {
    var total: Int
    var current: Int
}

class TitleStarCounter : RComponent<TitleStarCounterProps, State>() {
    override fun RBuilder.render() {
        if (props.total > 5) {
            span {
                attrs.classes = jsObjectBackedSetOf("map-title-text")
                +"${props.current}/${props.total}"
            }
            icon(24, jsObjectBackedSetOf("mission-star-icon"))
        } else {
            repeat(props.current) {
                icon(24, jsObjectBackedSetOf("mission-star-icon"))
            }
            repeat(props.total - props.current) {
                icon(24, jsObjectBackedSetOf("mission-hollow-star-icon"))
            }
        }
    }
}
