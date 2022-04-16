/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import com.bytelegend.app.client.api.dsl.NpcBuilder
import com.bytelegend.app.client.api.dsl.ObjectsBuilder
import com.bytelegend.app.client.misc.search
import com.bytelegend.app.client.misc.uuid
import com.bytelegend.app.client.utils.JSArrayBackedList
import com.bytelegend.app.client.utils.JSObjectBackedMap
import com.bytelegend.app.shared.GameMap
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.mapToArray
import com.bytelegend.app.shared.objects.GameMapAnimation
import com.bytelegend.app.shared.objects.GameMapCurve
import com.bytelegend.app.shared.objects.GameMapDynamicSprite
import com.bytelegend.app.shared.objects.GameMapEntrancePoint
import com.bytelegend.app.shared.objects.GameMapMission
import com.bytelegend.app.shared.objects.GameMapObjectType
import com.bytelegend.app.shared.objects.GameMapPoint
import com.bytelegend.app.shared.objects.GameMapRegion
import com.bytelegend.app.shared.objects.GameMapText
import com.bytelegend.app.shared.objects.mapEntranceId
import com.bytelegend.client.app.obj.BouncingTitleObject
import com.bytelegend.client.app.obj.DefaultDynamicSprite
import com.bytelegend.client.app.obj.EmptySprite
import com.bytelegend.client.app.obj.GameCurveSprite
import com.bytelegend.client.app.obj.GameMapEntrance
import com.bytelegend.client.app.obj.GameTextSprite
import com.bytelegend.client.app.obj.character.NPC
import com.bytelegend.client.app.script.DefaultGameDirector
import com.bytelegend.client.app.script.MAIN_CHANNEL
import com.bytelegend.client.app.ui.DefaultModalController
import com.bytelegend.client.app.ui.invitationcode.InvitationCodeModal
import com.bytelegend.client.app.ui.mission.DefaultPullRequestLogContainer
import com.bytelegend.client.app.ui.script.Widget
import kotlinx.js.jso
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import react.react

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
    val missions: MissionModalDataContainer = MissionModalDataContainer(di)
    val mainChannelDirector by lazy {
        getDirectorOfChannel(MAIN_CHANNEL)
    }
    val scriptWidgets: MutableMap<String, Widget<*>> = JSObjectBackedMap()

    lateinit var players: PlayerContainer

    override lateinit var challengeAnswers: DefaultChallengeAnswersContainer

    override fun objects(block: ObjectsBuilder.() -> Unit) {
        block()
    }

    override fun scripts(block: ScriptsBuilder.() -> Unit) {
        scripts(MAIN_CHANNEL, true, block)
    }

    override fun scripts(channel: String, block: ScriptsBuilder.() -> Unit) {
        scripts(channel, true, block)
    }

    override fun searchPath(start: GridCoordinate, end: GridCoordinate, wallPredicate: (Int) -> Boolean): List<GridCoordinate> {
        return search(blockers, start, end, wallPredicate)
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
                    // it may reference a dynamic sprite, so we need a second pass
                }
                GameMapObjectType.GameMapEntrancePoint -> addMapEntrance(it.unsafeCast<GameMapEntrancePoint>())
                GameMapObjectType.GameMapEntranceDestinationPoint -> objects.add(it.unsafeCast<GameMapEntrancePoint>())
                GameMapObjectType.GameMapAnimation -> objects.add(it.unsafeCast<GameMapAnimation>())
                else -> throw IllegalStateException("Unsupported type: ${it.type}")
            }
        }
        missions.forEach { mission ->
            DefaultGameMission(this, mission, objects.getById(mission.sprite))
        }
    }

    private fun addMapEntrance(entrancePoint: GameMapEntrancePoint) {
        objects.add(entrancePoint)
        val entranceId = mapEntranceId(entrancePoint.srcMap, entrancePoint.destMap)

        val mapEntrance = GameMapEntrance(
            entranceId,
            this,
            entrancePoint.gridCoordinate,
            entrancePoint.destMap,
            true
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
                EmptySprite(
                    id,
                    this,
                    gridCoordinate,
                    onClickFunction = builder.onClick,
                    onTouchFunction = builder.onTouch
                )
            )
        } else {
            objects.add(
                DefaultDynamicSprite(
                    id,
                    this,
                    gridCoordinate,
                    objects.getById(builder.sprite!!)
                ).apply {
                    onClickFunction = builder.onClick
                }
            )
        }
    }

    override fun invitationCodeBox(point: GridCoordinate) {
        objects.add(
            DefaultDynamicSprite(
                "invitation-code-box",
                this,
                point,
                objects.getById("InvitationCodeBox")
            ).apply {
                onClickFunction = {
                    gameRuntime.modalController.unsafeCast<DefaultModalController>().show {
                        child(InvitationCodeModal::class.react, jso {
                            game = gameRuntime.unsafeCast<Game>()
                        })
                    }
                }
            }
        )
    }

    private fun gameMapText(gameMapText: GameMapText) {
        objects.add(GameTextSprite(this, gameMapText))
    }

    private fun gameMapCurve(curve: GameMapCurve) {
        objects.add(curve)
        objects.add(GameCurveSprite(this, curve))
    }
}
