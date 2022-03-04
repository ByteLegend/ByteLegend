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
package com.bytelegend.app.client.api.dsl

import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.objects.GameObject

typealias UnitFunction = () -> Unit
typealias GameObjectFunction = (GameObject) -> Unit
typealias SuspendUnitFunction = suspend () -> Unit

val EMPTY_FUNCTION: UnitFunction = {}

class BouncingTitleBuilder {
    var pixelCoordinate: PixelCoordinate? = null
    var text: String? = null
    var backgroundColor: String = "rgba(0,0,0,0.8)"
    var color: String = "white"

    /**
     * If specified, the onclick function will also be applied to the tiles.
     */
    var tileCoordinates: MutableList<GridCoordinate> = mutableListOf()
    var onClickFunction: UnitFunction? = null
}

class NpcBuilder {
    var id: String? = null

    /**
     * The dynamic sprite id, see GameMapDynamicSprite
     */
    var sprite: String? = null
    var onInit: UnitFunction = {}
    var onTouch: GameObjectFunction = {}
    var onClick: UnitFunction = {}
}

class DynamicSpriteBuilder {
    var id: String? = null

    /**
     * The dynamic sprite id, see GameMapDynamicSprite.
     * If null, it will be an "empty" point.
     */
    var sprite: String? = null
    var gridCoordinate: GridCoordinate? = null

    /**
     * If set, it will be "clickable" and "unable to be set as destination",
     * otherwise, it is a walkable point.
     */
    var onClick: UnitFunction? = null

    /**
     * The function when the player steps into the tile.
     */
    var onTouch: GameObjectFunction? = null
}

interface ObjectsBuilder {
    fun bouncingTitle(action: BouncingTitleBuilder.() -> Unit)
    fun npc(action: NpcBuilder.() -> Unit)
    fun dynamicSprite(action: DynamicSpriteBuilder.() -> Unit)
    fun invitationCodeBox(point: GridCoordinate)
}
