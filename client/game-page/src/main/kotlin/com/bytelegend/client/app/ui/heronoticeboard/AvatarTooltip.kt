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
package com.bytelegend.client.app.ui.heronoticeboard

import com.bytelegend.app.shared.entities.mission.HeroNoticeboardTile
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.html.classes
import react.RBuilder
import react.RComponent
import react.State
import react.dom.div
import react.dom.img
import react.dom.jsStyle
import react.dom.p
import react.setState

const val TOOLTIP_WIDTH = 200
const val TOOLTIP_HEIGHT = 64
const val TOOLTIP_OFFSET_X = -88
const val TOOLTIP_OFFSET_Y = -72
const val TRIANGLE_OFFSET_X = 92
const val TRIANGLE_OFFSET_Y = 64

interface AvatarCardProps : GameProps {
    var joinedAtI18n: String
    var tile: HeroNoticeboardTile
}

class AvatarTooltip : RComponent<AvatarCardProps, State>() {
    override fun UNSAFE_componentWillReceiveProps(nextProps: AvatarCardProps) {
        setState { }
    }

    override fun RBuilder.render() {
        div {
            attrs.classes = jsObjectBackedSetOf("brave-people-avatar-tooltip")
            attrs.jsStyle {
                position = "absolute"
                left = "${props.tile.x * AVATAR_TILE_SIZE + TOOLTIP_OFFSET_X}px"
                top = "${props.tile.y * AVATAR_TILE_SIZE + TOOLTIP_OFFSET_Y}px"
                width = "${TOOLTIP_WIDTH}px"
                height = "${TOOLTIP_HEIGHT}px"
                backgroundColor = props.tile.color
                color = "white"
                borderRadius = "5px"
                if (props.tile.color == "#FFFFFF") {
                    boxShadow = "0 0 0 1px black"
                }
            }

            img {
                attrs.jsStyle {
                    position = "absolute"
                    borderRadius = "5px 0 0 5px"
                    width = "${TOOLTIP_HEIGHT}px"
                    height = "${TOOLTIP_HEIGHT}px"
                }
                attrs.src = props.game.transformGitHubUrl("https://avatars.githubusercontent.com/${props.tile.userid}?size=200")
            }
            div {
                attrs.jsStyle {
                    position = "absolute"
                    left = "${TOOLTIP_HEIGHT + 4}px"
                }
                attrs.classes = jsObjectBackedSetOf("white-text-black-shadow-1")
                p {
                    attrs.jsStyle.fontSize = "16px"
                    +props.tile.userid
                }
                p {
                    attrs.jsStyle.fontSize = "12px"
                    +"${props.joinedAtI18n} ${props.tile.createdAt.substring(0, "yyyy-MM-dd".length)}"
                }
            }

            div {
                attrs.jsStyle {
                    position = "absolute"
                    left = "${TRIANGLE_OFFSET_X}px"
                    top = "${TRIANGLE_OFFSET_Y}px"
                    transform = "rotate(180deg)"
                    width = "0"
                    height = "0"
                    border = "8px solid transparent"
                    borderBottom = "8px solid ${props.tile.color}"
                }
            }
        }
    }
}
