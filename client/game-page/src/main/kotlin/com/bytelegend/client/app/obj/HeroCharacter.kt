package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.client.misc.search
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.NON_BLOCKER
import com.bytelegend.app.shared.entities.Player
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.engine.Game
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HeroCharacter(
    gameScene: GameScene,
    player: Player,
) : PlayerSprite(gameScene, player) {
    override val id: String = "hero"
    override val roles: Set<GameObjectRole> = setOf(
        GameObjectRole.Character,
        GameObjectRole.Sprite,
        GameObjectRole.Hero
    )

    override fun enterTile(gridCoordinate: GridCoordinate) {
        super.enterTile(gridCoordinate)

        player.x = gridCoordinate.x
        player.y = gridCoordinate.y
        gameScene.objects.getByCoordinate(gridCoordinate).filter {
            it.id != this.id
        }.forEach {
            it.onTouch(this)
        }
    }

    override fun searchPath(destination: GridCoordinate): List<GridCoordinate> {
        return search(gameScene.blockers, gridCoordinate, destination) {
            it != NON_BLOCKER
        }
    }

    override fun moveAlong(movePath: List<GridCoordinate>, callback: UnitFunction?) {
        super.moveAlong(movePath, callback)
        GlobalScope.launch {
            val destination = movePath.last()
            gameRuntime.unsafeCast<Game>().webSocketClient.moveTo(destination.x, destination.y)
        }
    }
}
