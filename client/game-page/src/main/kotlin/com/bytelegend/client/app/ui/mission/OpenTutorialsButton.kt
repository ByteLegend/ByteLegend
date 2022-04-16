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
@file:Suppress("OPT_IN_USAGE")

package com.bytelegend.client.app.ui.mission

import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.GameMission
import com.bytelegend.app.client.ui.bootstrap.BootstrapButton
import com.bytelegend.app.shared.protocol.MISSION_TUTORIALS_UNLOCKED_EVENT
import com.bytelegend.app.shared.protocol.MissionTutorialsUnlockedEventData
import com.bytelegend.client.app.ui.setState
import com.bytelegend.client.app.ui.unsafeDiv
import com.bytelegend.client.app.web.request
import csstype.ClassName
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import react.ChildrenBuilder
import react.dom.html.ReactHTML.div

const val WEBEDITOR_OPEN_TUTORIALS_EVENT = "webeditor.open.tutorials"

interface OpenTutorialsButtonProps : AbstractWebEditorButtonProps

interface OpenTutorialsButtonState : AbstractWebEditorButtonState

class OpenTutorialsButton(props: OpenTutorialsButtonProps) : AbstractWebEditorButton<OpenTutorialsButtonProps, OpenTutorialsButtonState>(props) {
    private val onMissionTutorialsUnlockedEventListener: EventListener<MissionTutorialsUnlockedEventData> = { event ->
        if (event.missionId == props.missionModalData.missionId) {
            setState {
                imageName = "tutorial-open"
            }
        }
    }

    override fun componentDidMount() {
        super.componentDidMount()
        props.game.eventBus.on(MISSION_TUTORIALS_UNLOCKED_EVENT, onMissionTutorialsUnlockedEventListener)
    }

    override fun componentWillUnmount() {
        super.componentWillUnmount()
        props.game.eventBus.remove(MISSION_TUTORIALS_UNLOCKED_EVENT, onMissionTutorialsUnlockedEventListener)
    }

    override fun ChildrenBuilder.renderPopup() {
        div {
            className = ClassName("unlock-tutorials-popup")
            unsafeDiv(
                props.game.i(
                    "PayCoinToUnlockTutorials",
                    props.game.activeScene.objects.getById<GameMission>(props.missionModalData.missionId).gameMapMission.tutorialsPrice.toString(),
                    props.game.heroPlayer.coin.toString()
                )
            ) {
                className = ClassName("unlock-tutorials-popup-text")
            }
            div {
                className = ClassName("unlock-tutorials-popup-buttons")
                BootstrapButton {
                    +"Yes"
                    onClick = {
                        GlobalScope.launch {
                            val response = request("POST", "/game/api/mission/${props.missionModalData.missionId}/unlockTutorials")
                            if (response.status.toInt() == 402 /* PAYMENT REQUIRED */) {
                                props.game.toastController.addToast(props.game.i("NoEnoughCoin"), props.game.i("YouDonHaveEnoughCoin"), 10_000)
                            } else if (response.status < 200 || response.status >= 300) {
                                props.game.toastController.addToast("Error", "Error when unlocking tutorials: server returns ${response.status}", 0)
                            }
                        }
                        closeAllPopups()
                    }
                }
                BootstrapButton {
                    +"No"
                    onClick = {
                        closeAllPopups()
                    }
                }
            }
        }
    }

    override fun onClick() {
        if (props.missionModalData.tutorialsUnlocked) {
            props.game.eventBus.emit(WEBEDITOR_OPEN_TUTORIALS_EVENT, null)
        } else {
            props.game.eventBus.emit(WEBEDITOR_POPUP_OPENED, props.textId)
            setState { showBubble = true }
        }
    }
}
