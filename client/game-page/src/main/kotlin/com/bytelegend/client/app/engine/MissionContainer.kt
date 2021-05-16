package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.JSObjectBackedMap
import com.bytelegend.app.shared.entities.MissionModalData
import com.bytelegend.client.app.web.WebSocketClient
import com.bytelegend.client.app.web.getMissionModalData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.instance

const val MISSION_DATA_LOAD_FINISH = "mission.data.load.finish"

class MissionContainer(
    private val di: DI,
    private val gameScene: DefaultGameScene
) {
    private val eventBus: EventBus by di.instance()
    private val webSocketClient: WebSocketClient by di.instance()
    private val loadingMissions: MutableSet<String> = mutableSetOf()
    private val missionData: MutableMap<String, MissionModalData> = JSObjectBackedMap()

    fun isMissionModalDataLoading(missionId: String): Boolean = loadingMissions.contains(missionId)

    fun getMissionModalDataById(missionId: String): MissionModalData {
        return missionData.getValue(missionId)
    }

    /**
     * Refresh the mission modal data. Usually, we cache the data in browser session
     * so next time we don't need to load it again. Use force=true to forcibly refresh the data.
     */
    fun refresh(missionId: String, force: Boolean = false) {
        if (!loadingMissions.contains(missionId)) {
            if (missionData.containsKey(missionId)) {
                if (force) {
                    load(missionId)
                }
            } else {
                load(missionId)
            }
        }
    }

    private fun load(missionId: String) {
        loadingMissions.add(missionId)
        GlobalScope.launch {
            missionData[missionId] = getMissionModalData(missionId)
            loadingMissions.remove(missionId)
            eventBus.emit(MISSION_DATA_LOAD_FINISH, missionId)
        }
    }
}
