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

package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.objects.CoordinateAware
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.app.client.utils.jsObjectBackedSetOf

class EmptyClickablePoint(
    override val id: String,
    private val gameScene: GameScene,
    override val gridCoordinate: GridCoordinate,
    private val onInitFunction: UnitFunction = {},
    private val onClickFunction: UnitFunction = {},
    private val onTouchFunction: (GameObject) -> Unit = {},
) : GameObject, CoordinateAware {
    override val layer: Int = 0
    override val roles: Set<String> = jsObjectBackedSetOf(
        GameObjectRole.CoordinateAware,
        GameObjectRole.Clickable,
        GameObjectRole.UnableToBeSetAsDestination.toString()
    )
    override val pixelCoordinate: PixelCoordinate = gridCoordinate * gameScene.map.tileSize

    init {
        onInitFunction()
    }

    override fun onTouch(obj: GameObject) {
        onTouchFunction(obj)
    }

    override fun onClick() {
        onClickFunction()
    }
}
