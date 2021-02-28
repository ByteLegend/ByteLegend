package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.entities.Player
import com.bytelegend.app.shared.objects.GameObjectRole

open class PlayerCharacter(
    gameScene: GameScene,
    private val player: Player,
) : AbstractCharacter(
    gameScene,
    GridCoordinate(player.x!!, player.y!!) * gameScene.map.tileSize,
    TwelveTilesAnimationSet(gameScene, player.characterId!!)
) {
    override val id: String = "player-${player._id}"
    override val roles: Set<GameObjectRole> = setOf(
        GameObjectRole.Sprite,
        GameObjectRole.Character
    )
}
