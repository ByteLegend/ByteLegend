package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.CoordinateAware
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.GameSceneAware
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.INVISIBLE_OBJECT_LAYER
import com.bytelegend.app.shared.PixelCoordinate
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
    private val backEntrancePointId: String
) : GameObject, GameSceneAware, CoordinateAware {
    override val layer: Int = INVISIBLE_OBJECT_LAYER
    override val roles: Set<GameObjectRole> = setOf(GameObjectRole.MapEntrance, GameObjectRole.CoordinateAware)
    override val pixelCoordinate: PixelCoordinate = gridCoordinate * gameScene.map.tileSize

    override fun onTouch(character: GameObject) {
        if (gameRuntime.hero != null && gameRuntime.hero!!.id == character.id) {
            val heroId = gameRuntime.hero!!.id
            gameRuntime.sceneContainer.loadScene(destMapId) { oldScene, newScene ->
                val oldHero = oldScene!!.objects.getById<HeroCharacter>(heroId)

                val newHero = HeroCharacter(newScene, oldHero.player)
                newHero.direction = oldHero.direction
                val newMapEntrance = newScene.objects.getById<GameMapPoint>(backEntrancePointId).point
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
