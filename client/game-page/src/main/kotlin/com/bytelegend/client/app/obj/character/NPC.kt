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
package com.bytelegend.client.app.obj.character

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.objects.GameMapDynamicSprite
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.engine.atTileBorder
import com.bytelegend.app.client.utils.jsObjectBackedSetOf

class NPC(
    override val id: String,
    dynamicSprite: GameMapDynamicSprite,
    gameScene: GameScene,
    private val onInitFunction: UnitFunction = {},
    private val onTouchFunction: (GameObject) -> Unit = {},
    private val onClickFunction: UnitFunction = {}
) : CharacterSprite(
    gameScene,
    PixelCoordinate(-1, -1),
    MapTilesetAnimationSet(gameScene, dynamicSprite)
) {
    override val roles: Set<String> = jsObjectBackedSetOf(
        GameObjectRole.Character,
        GameObjectRole.Sprite,
        GameObjectRole.NPC,
        GameObjectRole.CoordinateAware,
        GameObjectRole.Clickable,
    )

    override fun init() {
        super.init()
        onInitFunction()
    }

    override fun onTouch(obj: GameObject) {
        super.onTouch(obj)
        onTouchFunction(obj)
    }

    override fun onClick() {
        // if the player is current moving and not at the border of the tile, not trigger
        // the click event on game object because the object handler may change moving state
        // of the player.
        val hero = gameScene.gameRuntime.hero
        if (hero == null || atTileBorder(gameScene.map.tileSize, hero.pixelCoordinate)) {
            onClickFunction()
        }
    }

    override fun enterTile(gridCoordinate: GridCoordinate) {
        super.enterTile(gridCoordinate)
        gameScene.blockers[gridCoordinate.y][gridCoordinate.x]--
    }

    override fun leaveTile(gridCoordinate: GridCoordinate) {
        super.leaveTile(gridCoordinate)
        gameScene.blockers[gridCoordinate.y][gridCoordinate.x]++
    }
}
