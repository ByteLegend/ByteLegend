package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.Sprite
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.objects.CoordinateAware
import com.bytelegend.app.shared.objects.GameMapMission
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.obj.createMissionSprite
import com.bytelegend.client.app.page.game
import com.bytelegend.client.app.ui.mission.MissionModal
import com.bytelegend.client.utils.jsObjectBackedSetOf
import org.w3c.dom.CanvasRenderingContext2D

/**
 * Represent a mission object in the game
 */
class GameMission(
    override val gameScene: GameScene,
    val gameMapMission: GameMapMission
) : CoordinateAware, Sprite {
    override val id: String = gameMapMission.id
    override val gridCoordinate: GridCoordinate = gameMapMission.gridCoordinate
    private val sprite = createMissionSprite(gameScene, gridCoordinate, gameMapMission.sprite, {}, this::openMissionModal)
    override val layer: Int = sprite.layer
    override val pixelCoordinate: PixelCoordinate = gameMapMission.gridCoordinate * gameScene.map.tileSize
    override val roles: Set<String> =
        jsObjectBackedSetOf(
            GameObjectRole.Mission.toString(),
            GameObjectRole.HasFloatingTitle.toString(),
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
}
