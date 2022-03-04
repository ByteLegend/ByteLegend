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
@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.bytelegend.client.app.obj.character

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.HERO_ID
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.client.api.missionItemsButtonRepaintEvent
import com.bytelegend.app.client.utils.jsObjectBackedSetOf
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.NON_BLOCKER
import com.bytelegend.app.shared.entities.Player
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.engine.Game
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HeroCharacter(
    gameScene: GameScene,
    player: Player
) : PlayerSprite(gameScene, player) {
    override val id: String = HERO_ID
    override val roles: Set<String> = jsObjectBackedSetOf(
        GameObjectRole.Character,
        GameObjectRole.Sprite,
        GameObjectRole.Hero
    )
    private val eventBus = gameScene.gameRuntime.eventBus

    override fun enterTile(gridCoordinate: GridCoordinate) {
        super.enterTile(gridCoordinate)

        player.x = gridCoordinate.x
        player.y = gridCoordinate.y
        val objectsOnTile = gameScene.objects.getByCoordinate(gridCoordinate)
        objectsOnTile.objects.forEach {
            if (it.key != this.id) {
                it.value.onTouch(this)
            }
        }
        objectsOnTile.missionsAround.forEach {
            eventBus.emit(missionItemsButtonRepaintEvent(it.id), null)
        }
    }

    override fun leaveTile(gridCoordinate: GridCoordinate) {
        super.leaveTile(gridCoordinate)
        gameScene.objects.getByCoordinate(gridCoordinate).missionsAround.forEach {
            eventBus.emit(missionItemsButtonRepaintEvent(it.id), null)
        }
    }

    override fun searchPath(destination: GridCoordinate): List<GridCoordinate> {
        return gameScene.searchPath(gridCoordinate, destination) {
            it != NON_BLOCKER
        }
    }

    override fun moveAlong(movePath: List<GridCoordinate>, callback: UnitFunction?) {
        super.moveAlong(movePath, callback)
        GlobalScope.launch {
            val destination = movePath.last()
            gameRuntime.unsafeCast<Game>().webSocketClient.moveTo(gameScene.map.id, destination.x, destination.y)
        }
    }
}
