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

import com.bytelegend.app.client.api.Animation
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.GameMission
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.HasBouncingTitle
import com.bytelegend.app.client.api.Static
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.client.api.openMissionModalEvent
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.objects.GameMapDynamicSprite
import com.bytelegend.app.shared.objects.GameMapMission
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.obj.AbstractStaticLocationSprite
import com.bytelegend.client.app.obj.draw
import com.bytelegend.client.app.ui.mission.MissionModal
import com.bytelegend.client.app.ui.mission.MissionTitle
import com.bytelegend.app.client.utils.jsObjectBackedSetOf
import kotlinx.js.jso
import org.w3c.dom.CanvasRenderingContext2D
import react.ChildrenBuilder
import react.react

/**
 * Represent a mission object in the game
 */
class DefaultGameMission(
    override val gameScene: GameScene,
    override val gameMapMission: GameMapMission,
    override var mapDynamicSprite: GameMapDynamicSprite
) : AbstractStaticLocationSprite(
    gameMapMission.gridCoordinate,
    gameMapMission.gridCoordinate * gameScene.map.tileSize
), GameMission, HasBouncingTitle {
    override val id: String = gameMapMission.id
    override val layer: Int = gameMapMission.layer

    override var bouncingTitleEnabled: Boolean = true
    override var animation: Animation = Static
    override var onClickFunction: UnitFunction? = null
    override var onCloseFunction: UnitFunction? = null
    override val pixelSize: PixelSize = mapDynamicSprite.size * gameScene.map.tileSize

    override val roles: Set<String> =
        jsObjectBackedSetOf(
            GameObjectRole.Mission.toString(),
            GameObjectRole.HasBouncingTitle.toString(),
            GameObjectRole.Sprite.toString(),
            GameObjectRole.CoordinateAware.toString(),
            GameObjectRole.Clickable.toString(),
            GameObjectRole.UnableToBeSetAsDestination.toString()
        )

    override fun draw(canvas: CanvasRenderingContext2D) {
        canvas.draw(this)
    }

    private val onOpenModal: EventListener<Unit> = this::openModal

    init {
        gameScene.objects.add(this)
        gameScene.gameRuntime.eventBus.on(openMissionModalEvent(id), onOpenModal)

        for (y in 0 until mapDynamicSprite.size.height) {
            for (x in 0 until mapDynamicSprite.size.width) {
                gameScene.blockers[gridCoordinate.y + y][gridCoordinate.x + x]--
                gameScene.objects.putIntoCoordinate(this, gridCoordinate + GridCoordinate(x, y))
            }
        }
    }

    fun close() {
        onCloseFunction?.invoke()
        gameScene.gameRuntime.eventBus.remove(openMissionModalEvent(id), onOpenModal)
        gameScene.objects.remove<GameObject>(this.id)
    }

    @Suppress("UNUSED_PARAMETER")
    private fun openModal(u: Unit) {
        val defaultGameScene = gameScene.unsafeCast<DefaultGameScene>()
        defaultGameScene.missions.refresh(id)

        val game = gameScene.gameRuntime.unsafeCast<Game>()
        game.modalController.show { modalProps ->
            modalProps.className = "mission-modal"
            modalProps.onHide = {
                game.modalController.hide(id)
            }
            child(MissionModal::class.react, jso {
                this.game = game
                this.missionId = id
            })
        }
    }

    override fun onClick() {
        if (onClickFunction == null) {
            openModal(Unit)
        } else {
            onClickFunction!!()
        }
    }

    override fun renderBouncingTitle(builder: ChildrenBuilder) {
        val scene = gameScene
        val missionCoordinate = pixelCoordinate
        builder.child(MissionTitle::class.react, jso {
            backgroundColor = "rgba(0,0,0,0.7)"
            color = "white"
            gameScene = scene
            pixelCoordinate = missionCoordinate + PixelCoordinate(pixelSize.width / 2, 0)
            title = scene.gameRuntime.i(gameMapMission.title)
            totalStar = gameMapMission.totalStar
            mission = this@DefaultGameMission
            onClickFunction = {
                this@DefaultGameMission.onClick()
            }
        })
    }
}
