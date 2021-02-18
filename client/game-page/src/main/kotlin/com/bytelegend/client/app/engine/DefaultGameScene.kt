package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.GameCanvasState
import com.bytelegend.app.client.api.GameContainerSizeAware
import com.bytelegend.app.client.api.GameObjectContainer
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.ImageResourceData
import com.bytelegend.app.client.api.dsl.GameSceneBuilder
import com.bytelegend.app.client.api.dsl.MapEntranceBuilder
import com.bytelegend.app.client.api.dsl.NoticeboardBuilder
import com.bytelegend.app.client.api.dsl.NpcBuilder
import com.bytelegend.app.client.api.dsl.ObjectBuilder
import com.bytelegend.app.client.api.dsl.SpriteBuilder
import com.bytelegend.app.shared.GameMap
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.mapToArray
import com.bytelegend.app.shared.objects.GameMapCurve
import com.bytelegend.app.shared.objects.GameMapDynamicSprite
import com.bytelegend.app.shared.objects.GameMapObjectType
import com.bytelegend.app.shared.objects.GameMapPoint
import com.bytelegend.app.shared.objects.GameMapRegion
import com.bytelegend.app.shared.objects.GameMapText
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.obj.DynamicSprite
import com.bytelegend.client.app.obj.GameCurveSprite
import com.bytelegend.client.app.obj.GameTextSprite
import com.bytelegend.client.app.obj.GenericCoordinateAwareGameObject
import com.bytelegend.client.app.obj.MapEntrance
import com.bytelegend.client.app.obj.NPC
import com.bytelegend.client.app.obj.NoEffect
import com.bytelegend.client.app.obj.NoticeboardSprite
import com.bytelegend.client.app.obj.RectangleOuterGlowEffect
import com.bytelegend.client.app.obj.defaultMapEntranceId
import com.bytelegend.client.app.obj.defaultMapEntrancePointId
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
    GameSceneBuilder,
    DIAware,
    GameContainerSizeAware by canvasState {
    override val gameRuntime: GameRuntime by di.instance()
    override val blockers: Array<Array<Int>> = map.rawTiles.mapToArray {
        it.blocker
    }

    override val objects: GameObjectContainer = DefaultGameObjectContainer(this)
    override fun configure(block: GameSceneBuilder.() -> Unit) {
        block()
    }

    init {
        map.objects.forEach {
            when (it.type) {
                GameMapObjectType.GameMapText -> gameMapText(it.unsafeCast<GameMapText>())
                GameMapObjectType.GameMapRegion -> objects.add(it.unsafeCast<GameMapRegion>())
                GameMapObjectType.GameMapPoint -> objects.add(it.unsafeCast<GameMapPoint>())
                GameMapObjectType.GameMapCurve -> gameMapCurve(it.unsafeCast<GameMapCurve>())
                GameMapObjectType.GameMapDynamicSprite -> objects.add(it.unsafeCast<GameMapDynamicSprite>())
            }
        }
    }

    override fun mapEntrance(action: MapEntranceBuilder.() -> Unit) {
        val builder = MapEntranceBuilder()
        builder.action()

        val destMapId = builder.destMapId!!
        val entranceId = builder.id ?: defaultMapEntranceId(map.id, destMapId)
        val entrancePointId = builder.coordinatePointId ?: defaultMapEntrancePointId(entranceId)
        val coordinate = objects.getById<GameMapPoint>(entrancePointId).point
        val backEntrancePointId = builder.backEntrancePointId ?: defaultMapEntrancePointId(destMapId, map.id)

        val mapEntrance = MapEntrance(
            entranceId,
            this,
            coordinate,
            destMapId,
            backEntrancePointId
        )

        objects.add(mapEntrance)
    }

    override fun obj(action: ObjectBuilder.() -> Unit) {
        val builder = ObjectBuilder()
        builder.action()

        val coordinate = objects.getById<GameMapPoint>(builder.coordinatePointId!!).point

        objects.add(
            GenericCoordinateAwareGameObject(
                builder.id!!,
                coordinate,
                coordinate * map.tileSize,
                builder.onClick
            )
        )
    }

    override fun noticeboard(action: NoticeboardBuilder.() -> Unit) {
        val builder = NoticeboardBuilder()
        builder.action()

        val dynamicSprite = objects.getById<GameMapDynamicSprite>(builder.spriteId!!)

        NoticeboardSprite(builder.id!!, this, dynamicSprite).init()
    }

    override fun npc(action: NpcBuilder.() -> Unit) {
        val builder = NpcBuilder()
        builder.action()

        NPC(
            builder.id!!,
            objects.getById(builder.spriteId!!),
            this,
            onInitFunction = builder.onInit,
            onTouchFunction = builder.onTouch,
            onClickFunction = builder.onClick
        ).init()
    }

    override fun sprite(action: SpriteBuilder.() -> Unit) {
        val builder = SpriteBuilder()
        builder.action()

        val effect = if (builder.glow)
            RectangleOuterGlowEffect(4, 10, 24, 12, 10, "white")
        else
            NoEffect

        val roles = if (builder.clickable)
            setOf(GameObjectRole.Clickable, GameObjectRole.CoordinateAware, GameObjectRole.Sprite)
        else
            setOf(GameObjectRole.CoordinateAware, GameObjectRole.Sprite)

        DynamicSprite(
            builder.id!!,
            this,
            objects.getById(builder.spriteId!!),
            onInitFunction = builder.onInit,
            onTouchFunction = builder.onTouch,
            onClickFunction = builder.onClick,
            effect = effect,
            roles = roles
        ).init()
    }

    private fun gameMapText(gameMapText: GameMapText) {
        objects.add(GameTextSprite(this, gameMapText))
    }

    private fun gameMapCurve(curve: GameMapCurve) {
        objects.add(GameCurveSprite(this, curve))
    }
}
