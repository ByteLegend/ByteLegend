package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.shared.objects.GameMapMission
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.obj.DynamicSprite

class GameMission(
    override val gameScene: GameScene,
    val gameMapMission: GameMapMission
) : DynamicSprite(
    gameMapMission.id,
    gameScene,
    gameMapMission.point,
    gameScene.objects.getById(gameMapMission.sprite)
) {
    override val id: String = gameMapMission.id
    override val layer: Int = 0
    override val roles: Set<GameObjectRole> =
        setOf(
            GameObjectRole.Mission,
            GameObjectRole.Sprite,
            GameObjectRole.CoordinateAware,
        )
}
