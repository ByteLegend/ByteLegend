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

import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.shared.Direction
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.objects.CoordinateMutable
import com.bytelegend.app.shared.objects.GameObject

interface Character : GameObject, CoordinateMutable {
    /**
     * 1. player-{playerId}, e.g. player-gh#ByteLegendBot
     * 2. npc-{npcId}, e.g. npc-JavaIslandNewbieVillageOldMan
     */
    override val id: String

    /**
     * The direction of the character, only UP/DOWN/LEFT/RIGHT.
     * Note this means "how we paint the character", not moving direction.
     */
    var direction: Direction

    fun moveTo(destination: GridCoordinate, callback: UnitFunction? = null)
    fun moveAlong(movePath: List<GridCoordinate>, callback: UnitFunction? = null)
    fun searchPath(destination: GridCoordinate): List<GridCoordinate>
    fun isMoving(): Boolean
}
