package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.ImageResourceData
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.npcAnimationSetCoordinate
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.engine.Game
import com.bytelegend.client.app.engine.mapNpcAnimationSetResourceId

class NPC(
    npcId: String,
    gameScene: GameScene,
    gridCoordinate: GridCoordinate,
    npcAnimationSetIndex: Int,
    private val onInitFunction: () -> Unit,
    private val onTouchFunction: (GameObject) -> Unit,
    private val onClickFunction: () -> Unit
) : AbstractCharacter(
    gameScene,
    gridCoordinate * gameScene.map.tileSize,
    gameScene.npcAnimationSet(npcAnimationSetIndex)
) {
    override fun init() {
        super.init()
        onInitFunction()
        enterTile(gridCoordinate)
    }

    override val id: String = "npc=$npcId"
    override val roles: Set<GameObjectRole> = setOf(
        GameObjectRole.Character,
        GameObjectRole.Sprite,
        GameObjectRole.NPC
    )

    override fun onTouch(obj: GameObject) {
        super.onTouch(obj)
        onTouchFunction(obj)
    }

    override fun onClick() {
        super.onClick()
        onClickFunction()
    }

    override fun enterTile(gridCoordinate: GridCoordinate) {
        gameScene.blockers[gridCoordinate.y][gridCoordinate.x]++
    }

    override fun leaveTile(gridCoordinate: GridCoordinate) {
        gameScene.blockers[gridCoordinate.y][gridCoordinate.x]--
    }
}

private fun GameScene.npcAnimationSet(animationSetIndex: Int) = AnimationSet(
    unsafeCast<Game>().resourceLoader.getLoadedResource<ImageResourceData>(mapNpcAnimationSetResourceId(map.id)).htmlElement,
    npcAnimationSetCoordinate(animationSetIndex)
)
