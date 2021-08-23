package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.Animation
import com.bytelegend.app.client.api.AnimationFrame
import com.bytelegend.app.client.api.FramePlayingAnimation
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.Static
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelBlock
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.objects.CoordinateAware
import com.bytelegend.app.shared.objects.GameMapDynamicSprite
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.engine.atTileBorder
import com.bytelegend.client.utils.JSArrayBackedList
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.browser.window
import org.w3c.dom.CanvasRenderingContext2D

var globalCounter = 0

fun createMissionSprite(
    scene: GameScene,
    gridCoordinate: GridCoordinate,
    dynamicSpriteId: String,
    onInitFunction: UnitFunction,
    onClickFunction: UnitFunction
): DynamicSprite = when (dynamicSpriteId) {
    "Book" -> BookSprite(
        "$dynamicSpriteId-${globalCounter++}",
        scene,
        gridCoordinate,
        scene.objects.getById(dynamicSpriteId),
        onClickFunction
    )
    "Gate" -> GateSprite(
        "$dynamicSpriteId-${globalCounter++}",
        scene,
        gridCoordinate,
        scene.objects.getById(dynamicSpriteId)
    )
    "MissionTower" -> MissionTowerSprite(
        "$dynamicSpriteId-${globalCounter++}",
        scene,
        gridCoordinate,
        scene.objects.getById(dynamicSpriteId),
    )
    else -> DynamicSprite(
        "$dynamicSpriteId-${globalCounter++}",
        scene,
        gridCoordinate,
        scene.objects.getById(dynamicSpriteId),
        onInitFunction,
        onClickFunction
    )
}

class BookSprite(
    id: String,
    gameScene: GameScene,
    gridCoordinate: GridCoordinate,
    bookSprite: GameMapDynamicSprite,
    private val onClickFunction: UnitFunction
) : DynamicSprite(id, gameScene, gridCoordinate, bookSprite) {
    override fun onClick() {
        animation = FramePlayingAnimation(listOf(AnimationFrame(1, 500)), false)
        window.setTimeout({ onClickFunction() }, 500)
    }

    override fun onMissionModalClosed() {
        window.setTimeout({ animation = Static }, 500)
    }
}

class GateSprite(
    id: String,
    gameScene: GameScene,
    gridCoordinate: GridCoordinate,
    gateSprite: GameMapDynamicSprite
) : DynamicSprite(id, gameScene, gridCoordinate, gateSprite)

fun GameMapDynamicSprite.animationWithFixedInterval(ms: Int): FramePlayingAnimation {
    val f = JSArrayBackedList<AnimationFrame>()
    repeat(frames[0][0].size) {
        f.add(AnimationFrame(it, ms))
    }
    return FramePlayingAnimation(f)
}

class MissionTowerSprite(
    id: String,
    gameScene: GameScene,
    gridCoordinate: GridCoordinate,
    towerSprite: GameMapDynamicSprite
) : DynamicSprite(id, gameScene, gridCoordinate, towerSprite) {
    init {
        animation = towerSprite.animationWithFixedInterval(500)
    }
}

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

/**
 * A DynamicSprite is added to the map and controlled by game script,
 * which is much more flexible.
 */
open class DynamicSprite(
    override val id: String,
    final override val gameScene: GameScene,
    override val gridCoordinate: GridCoordinate,
    val dynamicSprite: GameMapDynamicSprite,
    private val onInitFunction: UnitFunction = {},
    private val onClickFunction: UnitFunction = {},
    private val onTouchFunction: (GameObject) -> Unit = {},
) : CoordinateAware, AbstractStaticLocationSprite(
    gridCoordinate,
    gridCoordinate * gameScene.map.tileSize,
    PixelSize(
        gameScene.map.tileSize.width * dynamicSprite.size.width,
        gameScene.map.tileSize.height * dynamicSprite.size.height
    )
) {
    override val roles: Set<String> = jsObjectBackedSetOf(
        GameObjectRole.Sprite,
        GameObjectRole.CoordinateAware,
        GameObjectRole.Clickable,
        GameObjectRole.UnableToBeSetAsDestination.toString()
    )
    protected var animation: Animation = Static
    override val layer: Int = dynamicSprite.layer
    val pixelSize: PixelSize = dynamicSprite.size * gameScene.map.tileSize

    init {
        onInitFunction()
    }

    override fun onTouch(obj: GameObject) {
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

    override fun draw(canvas: CanvasRenderingContext2D) {
        val coordinateInCanvas = pixelCoordinate - gameScene.canvasState.getCanvasCoordinateInMap()

        for (y in 0 until dynamicSprite.size.height) {
            for (x in 0 until dynamicSprite.size.width) {
                val frame = dynamicSprite.frames[y][x][animation.getNextFrameIndex()]
                canvas.drawImage(
                    gameScene.tileset.htmlElement,
                    PixelBlock(frame * gameScene.map.tileSize, gameScene.map.tileSize),
                    PixelBlock(coordinateInCanvas + gameScene.map.tileSize * GridCoordinate(x, y), gameScene.map.tileSize),
                )
            }
        }
    }

    open fun onMissionModalClosed() {
    }
}
