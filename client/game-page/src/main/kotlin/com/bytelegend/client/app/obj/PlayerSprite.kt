package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.entities.BasePlayer
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.utils.jsObjectBackedSetOf

fun playerSpriteId(playerId: String) = "player-$playerId-sprite"

open class PlayerSprite(
    gameScene: GameScene,
    val player: BasePlayer,
) : CharacterSprite(
    gameScene,
    GridCoordinate(player.x, player.y) * gameScene.map.tileSize,
    TwelveTilesAnimationSet(gameScene, player.characterId)
) {
    override val id: String = playerSpriteId(player.id)
    override val roles: Set<String> = jsObjectBackedSetOf(
        GameObjectRole.Sprite,
        GameObjectRole.Character
    )
}
