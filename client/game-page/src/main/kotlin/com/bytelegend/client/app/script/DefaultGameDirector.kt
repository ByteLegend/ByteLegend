package com.bytelegend.client.app.script

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.JSObjectBackedMap
import com.bytelegend.app.client.api.ScriptsBuilder
import com.bytelegend.app.client.api.SpeechBuilder
import com.bytelegend.app.client.api.dsl.SuspendUnitFunction
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.shared.Direction
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.client.app.engine.GAME_SCRIPT_NEXT
import com.bytelegend.client.app.engine.GAME_UI_UPDATE_EVENT
import com.bytelegend.client.app.engine.Game
import com.bytelegend.client.app.engine.GameControl
import com.bytelegend.client.app.engine.GameMouseEvent
import com.bytelegend.client.app.engine.MOUSE_CLICK_EVENT
import com.bytelegend.client.app.engine.logger
import com.bytelegend.client.app.obj.CharacterSprite
import com.bytelegend.client.app.script.effect.showArrowGif
import com.bytelegend.client.app.ui.COORDINATE_BORDER_FLICKER
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.HIGHTLIGHT_MISSION_EVENT
import com.bytelegend.client.app.ui.script.SpeechBubbleWidget
import com.bytelegend.client.app.ui.script.Widget
import com.bytelegend.client.app.web.WebSocketClient
import kotlinx.browser.document
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.instance
import org.w3c.dom.HTMLElement
import react.RHandler
import kotlin.reflect.KClass

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
const val STAR_FLYING_CHANNEL = "StarFlying"

/**
 * A director directs the scripts running on the scene, in a specific channel.
 */
class DefaultGameDirector(
    di: DI,
    private val channel: String,
    private val gameScene: GameScene
) : ScriptsBuilder {
    private val gameControl: GameControl by di.instance()
    private val game: GameRuntime by di.instance()
    private val eventBus: EventBus by di.instance()

    /**
     * Main channel means that it can respond to user click or other events.
     * Also, user mouse will be disabled during scripts running.
     *
     * This channel is usually used to display main story, like NPC speech.
     */
    private val mainChannel: Boolean = channel == MAIN_CHANNEL

    /**
     * When it is true, the user mouse click can trigger next script to run,
     * like speech bubbles.
     */
    private var clickEnabled: Boolean = false
    private val webSocketClient: WebSocketClient by lazy {
        gameScene.gameRuntime.unsafeCast<Game>().webSocketClient
    }

    private val scripts: MutableList<GameScript> = mutableListOf()

    // Point to next script to run
    var index = -1

    /**
     * A counter providing unique id for widgets in DOM
     */
    private var counter = 0

    val currentWidgets: MutableMap<String, Widget<out GameProps>> = JSObjectBackedMap()

    init {
        eventBus.on(MOUSE_CLICK_EVENT, this::onMouseClickOnCanvas)
        eventBus.on<String?>(GAME_SCRIPT_NEXT) { channel ->
            if (channel == this.channel) {
                next()
            }
        }
    }

    private fun enableClick(enabled: Boolean) {
        if (mainChannel) {
            clickEnabled = enabled
        }
    }

    private fun onMouseClickOnCanvas(event: GameMouseEvent) {
        if (clickEnabled) {
            next()
            if (index == -1 && mainChannel) {
                // all scripts have been finished. Re-trigger the mouse event
                eventBus.emit(MOUSE_CLICK_EVENT, event)
            }
        }
    }

    /**
     * Trigger next script to run.
     */
    private fun next() {
        if (scripts.isEmpty()) {
            throw IllegalStateException("Scripts should not be empty!")
        }
        if (channel == STAR_FLYING_CHANNEL && (!gameControl.isWindowVisible || game.modalController.visible)) {
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

        if (index == 0 && mainChannel) {
            logger.debug("Disable user mouse")
            gameControl.mapMouseClickEnabled = false
        }

        val script = scripts[index++]
        logger.debug("Running script $channel:${index - 1}: $script")

        script.start()
    }

    private fun reset() {
        if (mainChannel) {
            logger.debug("Enable user mouse")
            gameControl.mapMouseClickEnabled = true
        }
        scripts.clear()
        index = -1
    }

    fun scripts(block: ScriptsBuilder.() -> Unit) {
        scripts(true, block)
    }

    fun scripts(runImmediately: Boolean, block: ScriptsBuilder.() -> Unit) {
        block()
        if (runImmediately) {
            next()
        }
    }

    @Suppress("UnsafeCastFromDynamic")
    override fun speech(action: SpeechBuilder.() -> Unit) {
        val builder = SpeechBuilder()
        builder.action()

        val character = gameScene.objects.getById<CharacterSprite>(builder.objectId!!)
        scripts.add(
            DisplayWidgetScript(
                SpeechBubbleWidget::class,
                {
                    attrs.game = gameScene.gameRuntime.asDynamic()
                    attrs.speakerCoordinate = character.pixelCoordinate
                    attrs.contentHtml = gameScene.gameRuntime.i(builder.contentHtmlId!!, *builder.args)
                    attrs.arrow = builder.arrow
                },
                builder.contentHtmlId
            )
        )
    }

    fun suspendAnimation(fn: SuspendUnitFunction) {
        scripts.add(RunSuspendFunctionScript(fn))
    }

    override fun playAnimate(objectId: String, frames: List<Int>, intervalMs: Int) {
        TODO("Not yet implemented")
    }

    override fun characterMove(characterId: String, destMapCoordinate: GridCoordinate, callback: UnitFunction) {
        scripts.add(CharacterMoveScript(gameScene.objects.getById(characterId), destMapCoordinate, callback))
    }

    override fun startBeginnerGuide() {
        scripts.add(BeginnerGuideScript())
    }

    override fun putState(key: String, value: String) {
        scripts.add(
            RunSuspendFunctionScript {
                webSocketClient.putState(key, value)
                game.heroPlayer.states[key] = value
            }
        )
    }

    override fun removeState(key: String) {
        scripts.add(
            RunSuspendFunctionScript {
                webSocketClient.removeState(key)
                game.heroPlayer.states.remove(key)
            }
        )
    }

    override fun addItem(item: String) {
        scripts.add(
            RunSuspendFunctionScript {
                webSocketClient.addItem(item)
                if (!game.heroPlayer.items.contains(item)) {
                    game.heroPlayer.items.add(item)
                }
            }
        )
    }

    override fun removeItem(item: String) {
        scripts.add(
            RunSuspendFunctionScript {
                webSocketClient.removeItem(item)
                game.heroPlayer.items.remove(item)
            }
        )
    }

    inner class BeginnerGuideScript : GameScript {
        lateinit var arrowGif: HTMLElement
        override fun start() {
            // show gif arrow pointing to the coordinate
            // highlight the first mission
            enableClick(true)
            arrowGif = showArrowGif(gameScene.canvasState.getUICoordinateInGameContainer(), game.i("ThisIsCoordinate"))
            eventBus.emit(COORDINATE_BORDER_FLICKER, true)
            eventBus.emit(HIGHTLIGHT_MISSION_EVENT, listOf(STAR_BYTELEGEND_MISSION_ID))
        }

        override fun stop() {
            enableClick(false)
            eventBus.emit(COORDINATE_BORDER_FLICKER, false)
            eventBus.emit(HIGHTLIGHT_MISSION_EVENT, null)
            document.body?.removeChild(arrowGif)
        }
    }

    fun getAndIncrement(): Int {
        return counter++
    }

    inner class DisplayWidgetScript<P : GameProps>(
        private val klass: KClass<out GameUIComponent<P, *>>,
        private val handler: RHandler<P>,
        private val stringRepresentation: String?
    ) : GameScript {
        constructor(klass: KClass<out GameUIComponent<P, *>>, handler: RHandler<P>) : this(klass, handler, null)

        private val id = "${gameScene.map.id}-ScriptWidget-$channel-${getAndIncrement()}"
        override fun start() {
            enableClick(true)
            currentWidgets[id] = Widget(klass, handler)
            eventBus.emit(GAME_UI_UPDATE_EVENT, null)
        }

        override fun stop() {
            enableClick(false)
            currentWidgets.remove(id)
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
                character.direction = Direction.DOWN
                next()
            }
        }
    }

    inner class RunNativeJsScript(
        val pixelCoordinate: PixelCoordinate
    ) : GameScript {
        override fun start() {
            next()
//            window.asDynamic().starFly(0, 0, 500, 500, 2).then
//            , {
//                next()
//            })
        }
    }

    inner class RunSuspendFunctionScript(
        private val fn: suspend () -> Unit
    ) : GameScript {
        override fun start() {
            GlobalScope.launch {
                fn()
                next()
            }
        }
    }
}
