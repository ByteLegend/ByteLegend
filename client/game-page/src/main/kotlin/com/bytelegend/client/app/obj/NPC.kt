package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.objects.GameMapDynamicSprite
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.engine.atTileBorder

class NPC(
    override val id: String,
    dynamicSprite: GameMapDynamicSprite,
    gameScene: GameScene,
    private val onInitFunction: UnitFunction = {},
    private val onTouchFunction: (GameObject) -> Unit = {},
    private val onClickFunction: UnitFunction = {}
) : CharacterSprite(
    gameScene,
    PixelCoordinate(0, 0),
    MapTilesetAnimationSet(gameScene, dynamicSprite)
) {
    override fun init() {
        super.init()
        onInitFunction()
        enterTile(gridCoordinate)
    }

    override val roles: Set<GameObjectRole> = setOf(
        GameObjectRole.Character,
        GameObjectRole.Sprite,
        GameObjectRole.NPC,
        GameObjectRole.CoordinateAware,
        GameObjectRole.Clickable,
    )

    override fun onTouch(character: GameObject) {
        super.onTouch(character)
        onTouchFunction(character)
    }

    override fun onClick(): Boolean {
        super.onClick()
        // if the player is current moving and not at the border of the tile, not trigger
        // the click event on game object because the object handler may change moving state
        // of the player.
        val hero = gameScene.gameRuntime.hero
        if (hero == null || atTileBorder(gameScene.map.tileSize, hero.pixelCoordinate)) {
            onClickFunction()
        }
        return true
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
