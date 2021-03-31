package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.entities.Player
import com.bytelegend.app.shared.objects.GameObjectRole

fun playerSpriteId(playerId: String) = "player-$playerId-sprite"

open class PlayerSprite(
    gameScene: GameScene,
    val player: Player,
) : CharacterSprite(
    gameScene,
    GridCoordinate(player.x!!, player.y!!) * gameScene.map.tileSize,
    TwelveTilesAnimationSet(gameScene, player.characterId!!)
) {
    override val id: String = playerSpriteId(player.id!!)
    override val roles: Set<GameObjectRole> = setOf(
        GameObjectRole.Sprite,
        GameObjectRole.Character
    )
}
