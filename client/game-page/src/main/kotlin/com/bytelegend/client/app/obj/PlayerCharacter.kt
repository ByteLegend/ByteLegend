package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.ImageResourceData
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.Player
import com.bytelegend.app.shared.animationSetId
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.app.shared.playerAnimationSetCoordinate
import com.bytelegend.app.shared.playerAnimationSetResourceId
import com.bytelegend.client.app.engine.Game

fun Game.playerAnimationSet(characterId: Int): AnimationSet = AnimationSet(
    resourceLoader.getLoadedResource<ImageResourceData>(playerAnimationSetResourceId(animationSetId(characterId))).htmlElement,
    playerAnimationSetCoordinate(characterId)
)

open class PlayerCharacter(
    gameScene: GameScene,
    private val player: Player,
) : AbstractCharacter(
    gameScene,
    GridCoordinate(player.x!!, player.y!!) * gameScene.map.tileSize,
    gameScene.gameRuntime.unsafeCast<Game>().playerAnimationSet(player.characterId!!)
) {
    override val id: String = "player-${player.id}"
    override val roles: Set<GameObjectRole> = setOf(
        GameObjectRole.Sprite,
        GameObjectRole.Character
    )
}
