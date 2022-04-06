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
import csstype.ClassName
import react.Component
import react.Fragment
import react.Props
import react.ReactNode
import react.State
import react.create
import react.dom.html.ReactHTML.span

interface TitleStarCounterProps : Props {
    var total: Int
    var current: Int
    var starSize: Int
}

class TitleStarCounter : Component<TitleStarCounterProps, State>() {
    override fun render(): ReactNode {
        return Fragment.create {
            if (props.total > 5) {
                span {
                    className = ClassName("map-title-text")
                    +"${props.current}/${props.total}"
                }
                icon(props.starSize, "mission-star-icon")
            } else {
                repeat(props.current) {
                    icon(props.starSize, "mission-star-icon")
                }
                repeat(props.total - props.current) {
                    icon(props.starSize, "mission-hollow-star-icon")
                }
            }
        }
    }
}
