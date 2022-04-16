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

package com.bytelegend.client.app.ui.menu

import com.bytelegend.app.shared.entities.BasePlayer
import com.bytelegend.client.app.ui.jsStyle
import csstype.ClassName
import react.ChildrenBuilder
import react.dom.html.AnchorTarget
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.th

class LeaderboardTable : AsyncLoadingTable<dynamic, AsyncLoadingTableProps, AsyncLoadingTableState<dynamic>>(true) {
    override val isLastPage: Boolean
        get() {
            if (state.data == undefined) {
                return true
            }
            return state.data!!.size != 10
        }

    override val url: String
        get() = "/game/api/leaderboard?page=${state.currentPage}"

    override fun ChildrenBuilder.tableHeaderBuilder() {
        th {
            jsStyle { width = "20%" }
            +props.game.i("Ranking")
        }
        th {
            jsStyle { width = "60%" }
            +props.game.i("Player")
        }
        th {
            jsStyle { width = "20%" }
            div {
                className = ClassName("star-icon inline-icon-16")
            }
        }
    }

    override fun ChildrenBuilder.tableRowBuilder(index: Int, rowData: dynamic) {
        val player: BasePlayer = rowData
        td { +((state.currentPage - 1) * 10 + index + 1).toString() }
        td {
            img {
                className = ClassName("inline-icon-16")
                src = player.avatarUrl
            }
            a {
                target = AnchorTarget._blank
                href = "https://github.com/${player.id.substringAfter("gh#")}"
                +player.nickname
            }
        }
        td { +player.star.toString() }
    }
}
