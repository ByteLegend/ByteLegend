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
@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.bytelegend.client.app.script

import com.bytelegend.app.client.api.AnimationBuilder
import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.HERO_ID
import com.bytelegend.app.client.api.ScriptsBuilder
import com.bytelegend.app.client.api.SpeechBuilder
import com.bytelegend.app.client.api.dsl.SuspendUnitFunction
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.client.misc.playAudio
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.client.app.engine.DefaultGameScene
import com.bytelegend.client.app.engine.DefaultGameSceneContainer
import com.bytelegend.client.app.engine.GAME_SCRIPT_NEXT
import com.bytelegend.client.app.engine.GAME_UI_UPDATE_EVENT
import com.bytelegend.client.app.engine.Game
import com.bytelegend.client.app.engine.GameControl
import com.bytelegend.client.app.engine.GameMouseEvent
import com.bytelegend.client.app.engine.Item
import com.bytelegend.client.app.engine.calculateCoordinateInGameContainer
import com.bytelegend.client.app.engine.logger
import com.bytelegend.client.app.engine.resource.AudioResource
import com.bytelegend.client.app.engine.resource.ImageResource
import com.bytelegend.client.app.obj.DefaultAnimationSprite
import com.bytelegend.client.app.obj.character.CharacterSprite
import com.bytelegend.client.app.obj.character.NPC
import com.bytelegend.client.app.script.effect.itemPopupEffect
import com.bytelegend.client.app.script.effect.showArrowGif
import com.bytelegend.client.app.ui.COORDINATE_BORDER_FLICKER
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.determineRightSideBarTopLeftCornerCoordinateInGameContainer
import com.bytelegend.client.app.ui.mission.HIGHTLIGHT_TITLES_EVENT
import com.bytelegend.client.app.ui.script.SpeechBubbleWidget
import com.bytelegend.client.app.ui.script.Widget
import com.bytelegend.client.app.web.WebSocketClient
import kotlinx.js.jso
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.instance
import org.w3c.dom.HTMLElement
import react.ElementType
import react.react

interface GameScript {
    /**
     * Start an execution of script. A script can be:
     *
     * 1. Display a speech bubble.
     * 2. Display an animation, such as star, fireworks, etc.
     * 3. Move viewport to other part of map.
     * 4. Move NPC to another location.
     * ...
     *
     */
    fun start()
    fun stop() {}
}

const val STAR_BYTELEGEND_MISSION_ID = "star-bytelegend"
const val MAIN_CHANNEL = "MainChannel"
const val ASYNC_ANIMATION_CHANNEL = "AsyncAnimation"

/**
 * A director directs the scripts running on the scene, in a specific channel.
 */
class DefaultGameDirector(
    di: DI,
    private val channel: String,
    private val gameScene: DefaultGameScene
) : ScriptsBuilder {
    private val gameControl: GameControl by di.instance()
    private val gameRuntime: GameRuntime by di.instance()
    private val game: Game = gameRuntime.unsafeCast<Game>()
    private val eventBus: EventBus by di.instance()

    /**
     * Main channel means that it can respond to user click or other events.
     * Also, user mouse will be disabled during scripts running.
     *
     * This channel is usually used to display main story, like NPC speech.
     */
    private val isMainChannel: Boolean = channel == MAIN_CHANNEL

    /**
     * When it is true, the user mouse click can trigger next script to run,
     * like speech bubbles.
     */
    private var isRespondToClick: Boolean = false
    private val webSocketClient: WebSocketClient by lazy {
        gameScene.gameRuntime.unsafeCast<Game>().webSocketClient
    }

    private val scripts: MutableList<GameScript> = mutableListOf()

    // Point to next script to run
    private var index = -1

    /**
     * A counter providing unique id for widgets in DOM
     */
    private var counter = 0

    val isRunning: Boolean
        get() = index != -1

    init {
        eventBus.on<String>(GAME_SCRIPT_NEXT) { channel ->
            if (channel == this.channel && gameScene.isActive) {
                if (scripts.isNotEmpty()) {
                    next()
                }
            }
        }
    }

    private fun respondToClick(enabled: Boolean) {
        if (isMainChannel) {
            isRespondToClick = enabled
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun onMouseClickOnCanvas(event: GameMouseEvent) {
        if (isRunning && isRespondToClick) {
            next()
        }
    }

    /**
     * Trigger next script to run.
     */
    private fun next() {
        if (scripts.isEmpty()) {
            throw IllegalStateException("Scripts should not be empty!")
        }
        if (channel == ASYNC_ANIMATION_CHANNEL && (!gameControl.isWindowVisible || gameRuntime.modalController.visible)) {
            return
        }

        if (index == -1) {
            index = 0
        }

        if (index != 0) {
            scripts[index - 1].stop()
        }

        if (index == scripts.size) {
            reset()
            return
        }

        val script = scripts[index++]
        logger.debug("Running script $channel:${index - 1}: $script")

        script.start()
    }

    private fun reset() {
        scripts.clear()
        index = -1
    }

    fun scripts(block: ScriptsBuilder.() -> Unit) {
        scripts(true, block)
    }

    fun scripts(runImmediately: Boolean, block: ScriptsBuilder.() -> Unit) {
        block()
        // `scripts {}` may be called during running.
        // In this case we don't need to start, just let each script take over.
        if (!isRunning && runImmediately) {
            next()
        }
    }

    @Suppress("UnsafeCastFromDynamic")
    override fun speech(action: SpeechBuilder.() -> Unit) {
        val builder = SpeechBuilder()
        builder.action()

        if (builder.speakerId == null && builder.speakerCoordinate == null) {
            throw IllegalArgumentException("Either speakerId or speakerCoordinate need to be set for speech!")
        }

        scripts.add(
            DisplayWidgetScript(
                SpeechBubbleWidget::class.react,
                jso {
                    game = gameScene.gameRuntime.asDynamic()
                    speakerId = builder.speakerId
                    speakerCoordinate = builder.speakerCoordinate
                    contentHtml = gameScene.gameRuntime.i(builder.contentHtmlId!!, *builder.args)
                    arrow = builder.arrow
                    showYesNo = builder.showYesNo
                    onYes = {
                        builder.onYes()
                        next()
                    }
                    onNo = { next() }
                },
                builder.contentHtmlId,
                builder.dismissMs
            )
        )
    }

    fun suspendAnimation(fn: SuspendUnitFunction) {
        scripts.add(RunSuspendFunctionScript(fn = fn))
    }

    override fun characterMove(characterId: String, destMapCoordinate: GridCoordinate, onArrival: UnitFunction) {
        scripts.add(CharacterMoveScript(gameScene.objects.getById(characterId), destMapCoordinate, onArrival))
    }

    override fun characterEnterVehicleAndMoveToMap(
        characterId: String,
        vehicleSpriteId: String,
        movingPath: List<GridCoordinate>,
        destMap: String
    ) {
        scripts.add(FunctionScript {
            gameScene.objects.getById<CharacterSprite>(HERO_ID).close()

            // Create a special NPC, and make it move.
            val playerInVehicle = NPC(
                characterId,
                gameScene.objects.getById(vehicleSpriteId),
                gameScene
            )
            playerInVehicle.pixelCoordinate = movingPath[0] * gameScene.map.tileSize
            playerInVehicle.init()
            game._hero = playerInVehicle

            playerInVehicle.moveAlong(movingPath) {
                next()
                if (isRunning) {
                    logger.warn("We're going to switch scene but the script hasn't finised yet.")
                }
                game.sceneContainer.unsafeCast<DefaultGameSceneContainer>().heroEnterScene(destMap)
            }
        })
    }

    override fun startBeginnerGuide() {
        scripts.add(BeginnerGuideScript())
    }

    override fun putState(key: String, value: String) {
        scripts.add(
            RunSuspendFunctionScript {
                webSocketClient.putState(key, value)
                gameRuntime.heroPlayer.states[key] = value
            }
        )
    }

    override fun enterScene(targetMapId: String, onSuccess: UnitFunction, onFail: UnitFunction) {
        scripts.add(RunSuspendFunctionScript {
            try {
                webSocketClient.switchScene(targetMapId)
                onSuccess()
            } catch (e: Exception) {
                logger.error(e.stackTraceToString())
                onFail()
            }
        })
    }

    override fun useItem(item: String, targetCoordinate: GridCoordinate?) {
        scripts.add(RemoveItemScript(item, targetCoordinate))
    }

    override fun animation(action: AnimationBuilder.() -> Unit) {
        val builder = AnimationBuilder()
        builder.action()
        animation(builder)
    }

    override fun animation(vararg builders: AnimationBuilder) {
        builders.forEach {
            require(!isMainChannel || it.loop != 0) { "Infinite animation loop in main channel is not allowed!" }
            require(!it.animationId.isNullOrEmpty()) { "Animation is empty!" }
        }

        scripts.add(RunSuspendFunctionScript(false) {
            (builders.filter { it.audioId != null }
                .map {
                    game.resourceLoader.loadAsync(
                        AudioResource(it.audioId!!, game.resolve("/audio/${it.audioId!!}.mp3")),
                        false
                    )
                } + builders.map { game.resourceLoader.loadAsync(ImageResource(it.animationId!!, game.resolve("/img/animations/${it.animationId!!}.png")), false) }).awaitAll()

            var runningAnimationNumber = builders.size

            builders.forEach {
                if (it.audioId != null) {
                    window.setTimeout({ playAudio(it.audioId!!) }, it.initDelayMs.toInt())
                }
                DefaultAnimationSprite(
                    gameScene,
                    game.resourceLoader.getLoadedResource(it.animationId!!),
                    gameScene.objects.getById(it.animationId!!),
                    it.frameDurationMs,
                    it.loop,
                    it.initDelayMs,
                    onDraw = it.onDraw,
                    onClose = {
                        it.onEnd()
                        if (--runningAnimationNumber == 0) {
                            next()
                        }
                    }
                ).init()
                if (it.initDelayMs == 0L) {
                    it.onStart()
                } else {
                    window.setTimeout(it.onStart, it.initDelayMs.toInt())
                }
            }
        })
    }

    override fun sleep(ms: Long) {
        scripts.add(RunSuspendFunctionScript(true) {
            delay(ms)
        })
    }

    override fun runSuspend(fn: suspend () -> Unit) {
        scripts.add(RunSuspendFunctionScript(true, fn))
    }

    inner class BeginnerGuideScript : GameScript {
        lateinit var arrowGif: HTMLElement
        override fun start() {
            // show gif arrow pointing to the coordinate
            // highlight the first mission
            respondToClick(true)
            arrowGif = showArrowGif(gameScene.canvasState.getUICoordinateInGameContainer(), gameRuntime.i("ThisIsCoordinate"))
            eventBus.emit(COORDINATE_BORDER_FLICKER, true)
            eventBus.emit(HIGHTLIGHT_TITLES_EVENT, listOf(STAR_BYTELEGEND_MISSION_ID))
        }

        override fun stop() {
            respondToClick(false)
            eventBus.emit(COORDINATE_BORDER_FLICKER, false)
            eventBus.emit(HIGHTLIGHT_TITLES_EVENT, null)
            document.body?.removeChild(arrowGif)
        }
    }

    fun getAndIncrement(): Int {
        return counter++
    }

    inner class DisplayWidgetScript<P : GameProps>(
        private val type: ElementType<P>,
        private val props: P,
        private val stringRepresentation: String?,
        private val dismissMs: Int = 0
    ) : GameScript {
        private val id = "${gameScene.map.id}-ScriptWidget-$channel-${getAndIncrement()}"
        override fun start() {
            respondToClick(true)
            gameScene.scriptWidgets[id] = Widget(type, props)
            eventBus.emit(GAME_UI_UPDATE_EVENT, null)

            if (dismissMs != 0) {
                window.setTimeout({
                    next()
                }, dismissMs)
            }
        }

        override fun stop() {
            respondToClick(false)
            gameScene.scriptWidgets.remove(id)
            eventBus.emit(GAME_UI_UPDATE_EVENT, null)
        }

        override fun toString(): String {
            return stringRepresentation ?: id
        }
    }

    inner class CharacterMoveScript(
        val character: CharacterSprite,
        val destMapCoordinate: GridCoordinate,
        val callback: UnitFunction
    ) : GameScript {
        override fun start() {
            character.moveTo(destMapCoordinate) {
                callback()
                next()
            }
        }
    }

    inner class RemoveItemScript(
        private val item: String,
        private val destination: GridCoordinate?
    ) : GameScript {
        override fun start() {
            GlobalScope.launch {
                val item = game.itemAchievementManager.getItems().getValue(item)
                if (destination != null) {
                    val canvasState = gameScene.canvasState
                    val iconUrl = game.resolve(item.metadata.iconUrl)
                    itemPopupEffect(
                        iconUrl,
                        canvasState.gameContainerSize,
                        canvasState.determineRightSideBarTopLeftCornerCoordinateInGameContainer() + PixelCoordinate(0, 200), /* items box offset */
                        canvasState.calculateCoordinateInGameContainer(destination),
                        3.0
                    )
                }

                useItem(item)
                next()
            }
        }

        private suspend fun useItem(item: Item) {
            val result = game.itemAchievementManager.useItem(item)
            if (result != null) {
                val title = game.i("UseItemFailed")
                val body = game.i("UseItemFailedWith", game.i(item.metadata.nameTextId), result.message ?: "")
                game.toastController.addToast(title, body, 5000)
            }

            game.eventBus.emit(GAME_UI_UPDATE_EVENT, null)
        }
    }

    inner class RunSuspendFunctionScript(
        private val autoNext: Boolean = true,
        private val fn: suspend () -> Unit
    ) : GameScript {
        override fun start() {
            GlobalScope.launch {
                fn()
                if (autoNext) {
                    next()
                }
            }
        }
    }

    inner class FunctionScript(
        private val fn: () -> Unit
    ) : GameScript {
        override fun start() {
            fn()
        }
    }
}
