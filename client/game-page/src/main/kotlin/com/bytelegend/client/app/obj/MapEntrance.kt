package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.GameSceneAware
import com.bytelegend.app.client.api.GridCoordinateAware
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.INVISIBLE_OBJECT_PLAYER
import com.bytelegend.app.shared.objects.GameMapPoint
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.engine.Game

fun defaultMapEntranceId(srcMapId: String, destMapId: String) = "$srcMapId-$destMapId-entrance"

fun defaultMapEntrancePointId(mapEntranceId: String) = "$mapEntranceId-point"

fun defaultMapEntrancePointId(srcMapId: String, destMapId: String) = defaultMapEntrancePointId(defaultMapEntranceId(srcMapId, destMapId))

class MapEntrance(
    override val id: String,
    override val gameScene: GameScene,
    override val gridCoordinate: GridCoordinate,
    private val destMapId: String,
) : GameObject, GameSceneAware, GridCoordinateAware {
    override val layer: Int = INVISIBLE_OBJECT_PLAYER
    override val roles: Set<GameObjectRole> = setOf(GameObjectRole.MapEntrance)

    override fun onTouch(character: GameObject) {
        if (gameRuntime.hero != null && gameRuntime.hero!!.id == character.id) {
            val heroId = gameRuntime.hero!!.id
            gameRuntime.sceneContainer.loadScene(destMapId) { oldScene, newScene ->
                val oldHero = oldScene!!.objects.getById<HeroCharacter>(heroId)

                val newHero = HeroCharacter(newScene, oldHero.player)
                newHero.direction = oldHero.direction
                val newMapEntrance = newScene.objects.getById<GameMapPoint>(
                    defaultMapEntrancePointId(destMapId, gameScene.map.id)
                ).point
                newHero.pixelCoordinate = newMapEntrance * newScene.map.tileSize

                oldHero.close()
                gameRuntime.unsafeCast<Game>()._hero = newHero
                newHero.init()
            }
        } else {
            gameRuntime.activeScene.objects.getById<GameObject>(character.id).close()
        }
    }
}
