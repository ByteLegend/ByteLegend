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

import com.bytelegend.app.shared.entities.HistoryRecord
import com.bytelegend.app.shared.util.currentTimeMillis
import com.bytelegend.app.shared.util.iso8601ToEpochMs
import com.bytelegend.client.app.engine.DefaultGameSceneContainer
import com.bytelegend.client.app.engine.Game
import com.bytelegend.client.app.engine.util.format
import com.bytelegend.client.app.ui.menu.AsyncLoadingTable
import com.bytelegend.client.app.ui.menu.AsyncLoadingTableProps
import com.bytelegend.client.app.ui.menu.AsyncLoadingTableState
import com.bytelegend.client.utils.toHistoryRecord
import csstype.ClassName
import react.ChildrenBuilder
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.th

class CoinHistoryModal : AsyncLoadingTable<HistoryRecord, AsyncLoadingTableProps, AsyncLoadingTableState<HistoryRecord>>(true) {
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

    override fun ChildrenBuilder.tableRowBuilder(index: Int, record: HistoryRecord) {
        if (state.data!!.size - 1 == index) {
            currentPageLastItemEpochMs = record.createdAt.iso8601ToEpochMs() - 1
        }

        td {
            +props.game.format(record.createdAt)
        }
        td {
            if (record.change > 0) {
                className = ClassName("text-success")
                +"+${record.change}"
            } else {
                className = ClassName("text-danger")
                +record.change.toString()
            }
        }
        td {
            unsafeSpan(props.game.i(record.reasonId, *record.reasonArgs.toTypedArray()))
        }
    }

    override suspend fun transformData(data: Array<dynamic>): Array<HistoryRecord> {
        for (i in 0 until data.size) {
            props.game.transformCoinChangeReasonArgs(data[i].reasonId, data[i].reasonArgs)
            data[i] = toHistoryRecord(data[i])
        }
        return data
    }
}

suspend fun Game.transformCoinChangeReasonArgs(reasonId: String, reasonArgs: Array<String>) {
    if (reasonId == "MissionAccomplishedReward" && reasonArgs.size > 1) {
        overwriteReasonArgWithI18nText(reasonArgs[1], reasonArgs[0], reasonArgs, 0)
    } else if (reasonId == "MapSwitchingToll") {
        reasonArgs[0] = i(reasonArgs[0])
        reasonArgs[1] = i(reasonArgs[1])
    } else if (reasonId == "UnlockMissionTutorials") {
        overwriteReasonArgWithI18nText(reasonArgs[0], reasonArgs[1], reasonArgs, 1)
    }
}

private suspend fun Game.overwriteReasonArgWithI18nText(mapId: String, missionId: String, reasonArgs: Array<String>, index: Int) {
    val sceneContainer = sceneContainer.unsafeCast<DefaultGameSceneContainer>()
    val gameMapMission = sceneContainer.getOrLoadMapMission(mapId, missionId)
    reasonArgs[index] = i(gameMapMission.title)
}
