package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.GameCanvasState
import com.bytelegend.app.client.api.GameContainerSizeAware
import com.bytelegend.app.client.api.GameObjectContainer
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.ImageResourceData
import com.bytelegend.app.client.api.PullRequestLogContainer
import com.bytelegend.app.client.api.ScriptsBuilder
import com.bytelegend.app.client.api.dsl.BouncingTitleBuilder
import com.bytelegend.app.client.api.dsl.DynamicSpriteBuilder
import com.bytelegend.app.client.api.dsl.EMPTY_FUNCTION
import com.bytelegend.app.client.api.dsl.MapEntranceBuilder
import com.bytelegend.app.client.api.dsl.NoticeboardBuilder
import com.bytelegend.app.client.api.dsl.NpcBuilder
import com.bytelegend.app.client.api.dsl.ObjectsBuilder
import com.bytelegend.app.shared.GameMap
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.mapToArray
import com.bytelegend.app.shared.objects.GameMapCurve
import com.bytelegend.app.shared.objects.GameMapDynamicSprite
import com.bytelegend.app.shared.objects.GameMapMission
import com.bytelegend.app.shared.objects.GameMapObjectType
import com.bytelegend.app.shared.objects.GameMapPoint
import com.bytelegend.app.shared.objects.GameMapRegion
import com.bytelegend.app.shared.objects.GameMapText
import com.bytelegend.app.shared.objects.defaultMapEntranceDestinationId
import com.bytelegend.app.shared.objects.defaultMapEntranceId
import com.bytelegend.app.shared.objects.defaultMapEntrancePointId
import com.bytelegend.client.app.obj.BouncingTitleObject
import com.bytelegend.client.app.obj.DynamicSprite
import com.bytelegend.client.app.obj.EmptyClickablePoint
import com.bytelegend.client.app.obj.GameCurveSprite
import com.bytelegend.client.app.obj.GameMapEntrance
import com.bytelegend.client.app.obj.GameTextSprite
import com.bytelegend.client.app.obj.NPC
import com.bytelegend.client.app.obj.uuid
import com.bytelegend.client.app.script.DefaultGameDirector
import com.bytelegend.client.app.script.MAIN_CHANNEL
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.mission.DefaultPullRequestLogContainer
import com.bytelegend.client.app.ui.script.Widget
import com.bytelegend.client.utils.JSArrayBackedList
import com.bytelegend.client.utils.JSObjectBackedMap
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
    ObjectsBuilder,
    DIAware,
    GameContainerSizeAware by canvasState {
    override val isActive: Boolean
        get() = gameRuntime.activeScene == this
    override val gameRuntime: GameRuntime by di.instance()
    override val blockers: Array<Array<Int>> = map.rawTiles.mapToArray {
        it.blocker
    }
    override val logs: PullRequestLogContainer = DefaultPullRequestLogContainer(this)

    override val objects: GameObjectContainer = DefaultGameObjectContainer(this)
    private val channelToDirector: MutableMap<String, DefaultGameDirector> = JSObjectBackedMap()
    val missions: MissionContainer = MissionContainer(di, this)
    val mainChannelDirector by lazy {
        getDirectorOfChannel(MAIN_CHANNEL)
    }
    val scriptWidgets: MutableMap<String, Widget<out GameProps>> = JSObjectBackedMap()

    lateinit var players: PlayerContainer

    override lateinit var playerChallenges: DefaultPlayerChallengeContainer

    override fun objects(block: ObjectsBuilder.() -> Unit) {
        block()
    }

    override fun scripts(block: ScriptsBuilder.() -> Unit) {
        scripts(MAIN_CHANNEL, true, block)
    }

    fun scripts(channel: String, runImmediately: Boolean, block: ScriptsBuilder.() -> Unit) {
        getDirectorOfChannel(channel).scripts(runImmediately, block)
    }

    private fun getDirectorOfChannel(channel: String): DefaultGameDirector {
        return channelToDirector.getOrPut(channel) {
            DefaultGameDirector(di, channel, this)
        }
    }

    init {
        val missions = JSArrayBackedList<GameMapMission>()
        map.objects.forEach {
            when (it.type) {
                GameMapObjectType.GameMapText -> gameMapText(it.unsafeCast<GameMapText>())
                GameMapObjectType.GameMapRegion -> objects.add(it.unsafeCast<GameMapRegion>())
                GameMapObjectType.GameMapPoint -> objects.add(it.unsafeCast<GameMapPoint>())
                GameMapObjectType.GameMapCurve -> gameMapCurve(it.unsafeCast<GameMapCurve>())
                GameMapObjectType.GameMapDynamicSprite -> objects.add(it.unsafeCast<GameMapDynamicSprite>())
                GameMapObjectType.GameMapMission -> {
                    missions.add(it.unsafeCast<GameMapMission>())
                    // it may reference dynamic sprite so we need a second pass
                }
                else -> throw IllegalStateException("Unsupported type: ${it.type}")
            }
        }
        missions.forEach { mission ->
            GameMission(this, mission)
        }
    }

    override fun mapEntrance(action: MapEntranceBuilder.() -> Unit) {
        val builder = MapEntranceBuilder()
        builder.action()

        val destMapId = builder.destMapId ?: throw IllegalArgumentException("Dest map id not set!")
        val entranceId = builder.id ?: defaultMapEntranceId(map.id, destMapId)
        val entrancePointId = builder.coordinatePointId ?: defaultMapEntrancePointId(entranceId)

        val coordinate = objects.getPointById(entrancePointId)
        val backEntrancePointId = builder.backEntrancePointId ?: defaultMapEntranceDestinationId(entranceId)

        val mapEntrance = GameMapEntrance(
            entranceId,
            this,
            coordinate,
            destMapId,
            backEntrancePointId,
            builder.bouncingTitle,
            gameRuntime.unsafeCast<Game>().webSocketClient
        )

        objects.add(mapEntrance)
    }

    override fun bouncingTitle(action: BouncingTitleBuilder.() -> Unit) {
        val builder = BouncingTitleBuilder()
        builder.action()
        val text = builder.text ?: throw IllegalArgumentException("Text id not set!")
        val coordinate = builder.pixelCoordinate ?: throw IllegalArgumentException("Coordinate not set for bouncing title!")
        val id = "bouncing-title-${uuid()}"
        objects.add(
            BouncingTitleObject(
                id,
                text,
                builder.color,
                builder.backgroundColor,
                coordinate,
                builder.onClickFunction,
                this
            )
        )

        builder.tileCoordinates.forEachIndexed { index, gridCoordinate ->
            dynamicSprite {
                this.id = "$id-$index"
                this.gridCoordinate = gridCoordinate
                this.onClick = builder.onClickFunction ?: EMPTY_FUNCTION
            }
        }
    }

    override fun noticeboard(action: NoticeboardBuilder.() -> Unit) {
//        val builder = NoticeboardBuilder()
//        builder.action()
//
//        val dynamicSprite = objects.getById<GameMapDynamicSprite>(builder.spriteId!!)
//
//        NoticeboardSprite(builder.id!!, this, dynamicSprite).apply {
//            objects.add(this)
//            init()
//        }
    }

    override fun npc(action: NpcBuilder.() -> Unit) {
        val builder = NpcBuilder()
        builder.action()

        NPC(
            builder.id ?: throw IllegalArgumentException("No id specified for NPC!"),
            objects.getById(builder.sprite ?: throw IllegalArgumentException("No sprite specified for NPC!")),
            this,
            onInitFunction = builder.onInit,
            onTouchFunction = builder.onTouch,
            onClickFunction = builder.onClick
        ).init()
    }

    override fun dynamicSprite(action: DynamicSpriteBuilder.() -> Unit) {
        val builder = DynamicSpriteBuilder()
        builder.action()

        val id = builder.id ?: throw IllegalArgumentException("No id specified for dynamicSprite!")
        val gridCoordinate = builder.gridCoordinate ?: throw IllegalArgumentException("No gridCoordinate specified for ${builder.id}")

        if (builder.sprite == null) {
            objects.add(
                EmptyClickablePoint(
                    id,
                    this,
                    gridCoordinate,
                    onInitFunction = builder.onInit,
                    onTouchFunction = builder.onTouch,
                    onClickFunction = builder.onClick
                )
            )
        } else {
            objects.add(
                DynamicSprite(
                    id,
                    this,
                    gridCoordinate,
                    objects.getById(builder.sprite!!),
                    onInitFunction = builder.onInit,
                    onTouchFunction = builder.onTouch,
                    onClickFunction = builder.onClick
                )
            )
        }
    }

    private fun gameMapText(gameMapText: GameMapText) {
        objects.add(GameTextSprite(this, gameMapText))
    }

    private fun gameMapCurve(curve: GameMapCurve) {
        objects.add(GameCurveSprite(this, curve))
    }
}
