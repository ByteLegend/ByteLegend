package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.AbstractStaticLocationSprite
import com.bytelegend.app.client.api.Animation
import com.bytelegend.app.client.api.AnimationFrame
import com.bytelegend.app.client.api.CoordinateAware
import com.bytelegend.app.client.api.FramePlayingAnimation
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.GameScriptHelpers
import com.bytelegend.app.client.api.Static
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelBlock
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.objects.GameMapDynamicSprite
import com.bytelegend.app.shared.objects.GameMapMission
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.obj.drawImage
import com.bytelegend.client.app.page.game
import com.bytelegend.client.app.ui.mission.MissionModal
import kotlinx.browser.window
import org.w3c.dom.CanvasRenderingContext2D

class GameMission(
    override val gameScene: GameScene,
    val gameMapMission: GameMapMission,
    private val dynamicSprite: GameMapDynamicSprite
) : CoordinateAware, AbstractStaticLocationSprite(
    gameMapMission.point,
    gameMapMission.point * gameScene.map.tileSize,
    PixelSize(
        gameScene.map.tileSize.width * dynamicSprite.width,
        gameScene.map.tileSize.height * dynamicSprite.height
    )
) {
    override val id: String = gameMapMission.id
    override val layer: Int = 0
    override val roles: Set<GameObjectRole> =
        setOf(
            GameObjectRole.Mission,
            GameObjectRole.Sprite,
            GameObjectRole.CoordinateAware,
            GameObjectRole.Clickable,
            GameObjectRole.UnableToBeSetAsDestination
        )
    var animation: Animation = Static

    override fun init() {
        gameScene.objects.add(this)
        gameScene.blockers[gridCoordinate.y][gridCoordinate.x]--
    }

    override fun close() {
        gameScene.objects.remove<GameObject>(this.id)
    }

    override fun onClick() {
        if (!game.heroPlayer.isAnonymous && gridCoordinate.manhattanDistanceTo(game.hero!!.gridCoordinate) == 1) {
            game.hero!!.direction = GameScriptHelpers(gameScene).faceDirectionOf(game.hero!!, this)
        }
        if ("Book" == dynamicSprite.id) {
            animation = FramePlayingAnimation(listOf(AnimationFrame(1, 500)), false)
            gameScene.unsafeCast<DefaultGameScene>().missions.refresh(id)
            window.setTimeout(
                {
                    game.modalController.show {
                        attrs.className = "mission-modal"
                        child(MissionModal::class) {
                            attrs.game = game
                            attrs.missionId = id
                        }
                    }
                },
                500
            )
        }
    }

    override fun draw(canvas: CanvasRenderingContext2D) {
        val coordinateInCanvas = pixelCoordinate - gameScene.canvasState.getCanvasCoordinateInMap()

        for (y in 0 until dynamicSprite.height) {
            for (x in 0 until dynamicSprite.width) {
                val frame = dynamicSprite.frames[y][x][animation.getNextFrameIndex()]
                canvas.drawImage(
                    gameScene.tileset.htmlElement,
                    PixelBlock(frame * gameScene.map.tileSize, gameScene.map.tileSize),
                    PixelBlock(coordinateInCanvas + gameScene.map.tileSize * GridCoordinate(x, y), gameScene.map.tileSize),
                )
            }
        }
    }
}
