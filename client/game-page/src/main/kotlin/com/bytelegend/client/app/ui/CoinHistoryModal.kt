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

import com.bytelegend.app.shared.objects.GameMapMission
import com.bytelegend.app.shared.util.currentTimeMillis
import com.bytelegend.app.shared.util.iso8601ToEpochMs
import com.bytelegend.client.app.engine.DefaultGameSceneContainer
import com.bytelegend.client.app.engine.util.format
import com.bytelegend.client.app.ui.menu.AsyncLoadingTable
import com.bytelegend.client.app.ui.menu.AsyncLoadingTableProps
import com.bytelegend.client.app.ui.menu.AsyncLoadingTableState
import com.bytelegend.client.utils.toHistoryItem
import kotlinx.coroutines.awaitAll
import react.ChildrenBuilder
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.th

class CoinHistoryModal : AsyncLoadingTable<AsyncLoadingTableProps, AsyncLoadingTableState>(true) {
    private val pageSize = 10
    private var currentPageLastItemEpochMs: Long = currentTimeMillis()

    override val url: String
        get() = "/game/api/coin/history?pageSize=$pageSize&before=$currentPageLastItemEpochMs"

    override val isLastPage: Boolean
        get() {
            if (state.data == undefined) {
                return true
            }
            return state.data!!.size != pageSize
        }

    override fun ChildrenBuilder.tableHeaderBuilder() {
        th {
            jsStyle { width = "30%" }
            +props.game.i("Time")
        }
        th {
            jsStyle { width = "10%" }
            +props.game.i("Change")
        }
        th {
            jsStyle { width = "60%" }
            +props.game.i("Reason")
        }
    }

    override fun ChildrenBuilder.tableRowBuilder(index: Int, rowData: dynamic) {
        val record = toHistoryItem(rowData)

        if (state.data!!.size - 1 == index) {
            currentPageLastItemEpochMs = record.createdAt.iso8601ToEpochMs() - 1
        }

        td {
            +props.game.format(record.createdAt)
        }
        td {
            if (record.change > 0) {
                className = "text-success"
                +"+${record.change}"
            } else {
                className = "text-danger"
                +record.change.toString()
            }
        }
        td {
            console.log("Args: ${JSON.stringify(record.reasonArgs)}")
            unsafeSpan(props.game.i(record.reasonId, *record.reasonArgs.toTypedArray()))
        }
    }

    override suspend fun transformData(data: Array<dynamic>): Array<dynamic> {
        data.forEach { rowData ->
            if (rowData.reasonId == "MissionAccomplishedReward" && rowData.reasonArgs[1] != undefined) {
                val missionId = rowData.reasonArgs[0]
                val mapId = rowData.reasonArgs[1]
                val sceneContainer = props.game.sceneContainer.unsafeCast<DefaultGameSceneContainer>()
                val gameMap = sceneContainer.loadGameMap(mapId, false)
                val i18n = sceneContainer.loadI18nResource(mapId, false)
                listOf(gameMap, i18n).awaitAll()

                gameMap.await().objects.firstOrNull { it.id == missionId }?.let { obj ->
                    i18n.await()[obj.unsafeCast<GameMapMission>().title]?.let {
                        rowData.reasonArgs[0] = it
                    }
                }
            } else if (rowData.reasonId == "MapSwitchingToll") {
                rowData.reasonArgs[0] = props.game.i(rowData.reasonArgs[0])
                rowData.reasonArgs[1] = props.game.i(rowData.reasonArgs[1])
            }
        }
        return data
    }
}
