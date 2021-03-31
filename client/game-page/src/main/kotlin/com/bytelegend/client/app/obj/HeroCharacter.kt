package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.entities.Player
import com.bytelegend.app.shared.objects.GameObjectRole

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
        gameScene.objects.getByCoordinate(gridCoordinate).filter {
            it.id != this.id
        }.forEach {
            it.onTouch(this)
        }
    }
}
