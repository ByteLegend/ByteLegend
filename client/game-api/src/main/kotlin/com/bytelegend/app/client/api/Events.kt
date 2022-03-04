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

package com.bytelegend.app.client.api

// Most of the UI elements depends on properties of GameCanvasState
// Update all UI elements after resizing, or other "update all UI elements" operations.
const val GAME_UI_UPDATE_EVENT = "game.ui.update"
fun closeMissionModalEvent(id: String) = "close.mission.modal.$id"
fun openMissionModalEvent(id: String) = "open.mission.modal.$id"

/**
 * We need to update a mission, like update the star count.
 */
fun missionRepaintEvent(missionId: String) = "mission.repaint.$missionId"

/**
 * When hero approaches or leaves a mission tower, we emit this event and repaint mission item buttons around the hero.
 */
fun missionItemsButtonRepaintEvent(missionId: String) = "mission.item.button.repaint.$missionId"

/**
 * Emit when an item is used.
 */
fun missionItemUsedEvent(missionId: String) = "mission.item.used.$missionId"
