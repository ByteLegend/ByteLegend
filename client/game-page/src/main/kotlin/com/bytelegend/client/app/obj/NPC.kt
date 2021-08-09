package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.objects.GameMapDynamicSprite
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.engine.atTileBorder
import com.bytelegend.client.utils.jsObjectBackedSetOf

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
