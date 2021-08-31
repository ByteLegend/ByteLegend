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
package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.Sprite
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.objects.CoordinateAware
import com.bytelegend.app.shared.objects.GameMapMission
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.obj.HasBouncingTitle
import com.bytelegend.client.app.obj.createMissionSprite
import com.bytelegend.client.app.page.game
import com.bytelegend.client.app.ui.mission.MissionModal
import com.bytelegend.client.app.ui.mission.MissionTitle
import com.bytelegend.client.utils.jsObjectBackedSetOf
import org.w3c.dom.CanvasRenderingContext2D
import react.RBuilder

/**
 * Represent a mission object in the game
 */
class GameMission(
    override val gameScene: GameScene,
    val gameMapMission: GameMapMission
) : CoordinateAware, Sprite, HasBouncingTitle {
    override val id: String = gameMapMission.id
    override val gridCoordinate: GridCoordinate = gameMapMission.gridCoordinate
    private val sprite = createMissionSprite(gameScene, gridCoordinate, gameMapMission.sprite, {}, this::openMissionModal)
    override val layer: Int = sprite.layer
    override val pixelCoordinate: PixelCoordinate = gameMapMission.gridCoordinate * gameScene.map.tileSize
    override val roles: Set<String> =
        jsObjectBackedSetOf(
            GameObjectRole.Mission.toString(),
            GameObjectRole.HasBouncingTitle.toString(),
            GameObjectRole.Sprite.toString(),
            GameObjectRole.CoordinateAware.toString(),
            GameObjectRole.Clickable.toString(),
            GameObjectRole.UnableToBeSetAsDestination.toString()
        )

    override fun draw(canvas: CanvasRenderingContext2D) = sprite.draw(canvas)
    override fun outOfCanvas(): Boolean = sprite.outOfCanvas()

    init {
        gameScene.objects.add(this)

        for (y in 0 until sprite.dynamicSprite.frames.size) {
            for (x in 0 until sprite.dynamicSprite.frames[0].size) {
                gameScene.blockers[gridCoordinate.y + y][gridCoordinate.x + x]--
                gameScene.objects.putIntoCoordinate(this, gridCoordinate + GridCoordinate(x, y))
            }
        }
    }

    fun close() {
        gameScene.objects.remove<GameObject>(this.id)
    }

    override fun onClick() {
        sprite.onClick()
    }

    private fun openMissionModal() {
        val defaultGameScene = gameScene.unsafeCast<DefaultGameScene>()
        defaultGameScene.missions.refresh(id)

        game.modalController.show {
            attrs.className = "mission-modal"
            child(MissionModal::class) {
                attrs.game = game
                attrs.missionId = id
                attrs.onClose = {
                    sprite.onMissionModalClosed()
                }
            }
        }
    }

    override fun renderBouncingTitle(builder: RBuilder) {
        builder.child(MissionTitle::class) {
            attrs.backgroundColor = "rgba(0,0,0,0.7)"
            attrs.color = "white"
            attrs.gameScene = gameScene
            attrs.pixelCoordinate = pixelCoordinate + PixelCoordinate(sprite.pixelSize.width / 2, 0)
            attrs.title = game.i(gameMapMission.title)
            attrs.totalStar = gameMapMission.totalStar
            attrs.currentStar = gameScene.playerChallenges.missionStar(id)
            attrs.mission = this@GameMission
            attrs.onClickFunction = {
                this@GameMission.onClick()
            }
        }
    }
}
