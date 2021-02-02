package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.GameCanvasState
import com.bytelegend.app.client.api.GameContainerSizeAware
import com.bytelegend.app.client.api.GameObjectContainer
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.ImageResourceData
import com.bytelegend.app.client.api.dsl.MapEntranceBuilder
import com.bytelegend.app.client.api.dsl.NpcBuilder
import com.bytelegend.app.client.api.dsl.ObjectsConfigurer
import com.bytelegend.app.shared.GameMap
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.mapToArray
import com.bytelegend.app.shared.objects.GameMapCurve
import com.bytelegend.app.shared.objects.GameMapObjectType
import com.bytelegend.app.shared.objects.GameMapPoint
import com.bytelegend.app.shared.objects.GameMapRegion
import com.bytelegend.app.shared.objects.GameMapText
import com.bytelegend.client.app.obj.MapEntrance
import com.bytelegend.client.app.obj.defaultMapEntranceId
import com.bytelegend.client.app.obj.defaultMapEntrancePointId
import com.bytelegend.client.app.sprite.GameCurveSprite
import com.bytelegend.client.app.sprite.GameTextSprite
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

class DefaultGameScene(
    override val di: DI,
    override val map: GameMap,
    override val tileset: ImageResourceData,
    initContainerSize: PixelSize,
    override val canvasState: GameCanvasState = DefaultGameCanvasState(
        di,
        map,
        initContainerSize,
    )
) : GameScene,
    ObjectsConfigurer,
    DIAware,
    GameContainerSizeAware by canvasState {
    override val gameRuntime: GameRuntime by di.instance()
    override val blockers: Array<Array<Int>> = map.rawTiles.mapToArray {
        it.blocker
    }

    override val objects: GameObjectContainer = DefaultGameObjectContainer(this)

    init {
        map.objects.forEach {
            when (it.type) {
                GameMapObjectType.GameMapText -> gameMapText(it.unsafeCast<GameMapText>())
                GameMapObjectType.GameMapRegion -> objects.add(it.unsafeCast<GameMapRegion>())
                GameMapObjectType.GameMapPoint -> objects.add(it.unsafeCast<GameMapPoint>())
                GameMapObjectType.GameMapCurve -> gameMapCurve(it.unsafeCast<GameMapCurve>())
            }
        }
    }

    override fun mapEntrance(action: MapEntranceBuilder.() -> Unit) {
        val builder = MapEntranceBuilder()
        builder.action()

        val destMapId = builder.destMapId!!
        val id = defaultMapEntranceId(map.id, destMapId)
        val coordinate = objects.getById<GameMapPoint>(defaultMapEntrancePointId(id)).point

        val mapEntrance = MapEntrance(
            id,
            this,
            coordinate,
            destMapId
        )

        objects.add(mapEntrance)
    }

    override fun npc(builder: NpcBuilder.() -> Unit) {
    }

    private fun gameMapText(gameMapText: GameMapText) {
        objects.add(GameTextSprite(this, gameMapText))
    }

    private fun gameMapCurve(curve: GameMapCurve) {
        objects.add(GameCurveSprite(this, curve))
    }
}
