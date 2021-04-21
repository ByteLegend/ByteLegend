package com.bytelegend.client.app.script

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.JSObjectBackedMap
import com.bytelegend.app.client.api.ModalController
import com.bytelegend.app.client.api.ScriptsBuilder
import com.bytelegend.app.client.api.SpeechBuilder
import com.bytelegend.app.client.api.dsl.SuspendUnitFunction
import com.bytelegend.app.client.misc.searchForNonHero
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.client.app.engine.GAME_UI_UPDATE_EVENT
import com.bytelegend.client.app.engine.GameControl
import com.bytelegend.client.app.engine.GameMouseEvent
import com.bytelegend.client.app.engine.MOUSE_CLICK_EVENT
import com.bytelegend.client.app.obj.CharacterSprite
import com.bytelegend.client.app.script.effect.fadeInEffect
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.script.SpeechBubbleWidget
import com.bytelegend.client.app.ui.script.Widget
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.instance
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

class DefaultGameDirector(
    di: DI,
    private val gameScene: GameScene
) : ScriptsBuilder {
    private val gameControl: GameControl by di.instance()
    private val game: GameRuntime by di.instance()
    private val modalController: ModalController by lazy { game.modalController }
    private val scripts: MutableList<GameScript> = mutableListOf()
    private val eventBus: EventBus by di.instance()
    private var respondToClick: Boolean = false

    // Point to next script to run
    private var index = -1

    /**
     * A counter providing unique id for widgets in DOM
     */
    private var counter = 0

    val currentWidgets: MutableMap<String, Widget<out GameProps>> = JSObjectBackedMap()

    private val isRunning
        get() = index != -1

    init {
        eventBus.on(MOUSE_CLICK_EVENT, this::onMouseClickOnCanvas)
    }

    /**
     * Start all scripts, if modal is not shown.
     */
    fun start() {
        if (!modalController.visible) {
            index = 0
            next()
        }
    }

    private fun onMouseClickOnCanvas(event: GameMouseEvent) {
        if (respondToClick) {
            next()
        }
    }

    /**
     * Trigger next script to run.
     */
    fun next() {
        if (scripts.isEmpty()) {
            return
        }
        if (index != 0) {
            scripts[index - 1].stop()
        } else {
            gameControl.mapMouseClickEnabled = false
        }

        if (index == scripts.size) {
            gameControl.mapMouseClickEnabled = true
            reset()
        } else {
            scripts[index++].start()
        }
    }

    private fun reset() {
        scripts.clear()
        index = -1
    }

    fun scripts(block: ScriptsBuilder.() -> Unit) {
        block()
        if (!isRunning) {
            start()
        }
    }

    @Suppress("UnsafeCastFromDynamic")
    override fun speech(action: SpeechBuilder.() -> Unit) {
        val builder = SpeechBuilder()
        builder.action()

        val character = gameScene.objects.getById<CharacterSprite>(builder.objectId!!)
        scripts.add(
            DisplayWidgetScript(SpeechBubbleWidget::class) {
                attrs.game = gameScene.gameRuntime.asDynamic()
                attrs.speakerCoordinate = character.pixelCoordinate
                attrs.contentHtml = gameScene.gameRuntime.i(builder.contentHtmlId!!, *builder.args)
                attrs.arrow = builder.arrow
            }
        )
    }

    fun suspendAnimation(fn: SuspendUnitFunction) {
        scripts.add(RunSuspendFunctionScript(fn))
    }

    override fun playAnimate(objectId: String, frames: List<Int>, intervalMs: Int) {
        TODO("Not yet implemented")
    }

    override fun characterMove(characterId: String, destMapCoordinate: GridCoordinate) {
        scripts.add(CharacterMoveScript(gameScene.objects.getById(characterId), destMapCoordinate))
    }

    override fun fadeIn() {
        scripts.add(RunSuspendFunctionScript { fadeInEffect(gameScene.gameContainerSize) })
    }

    override fun onComplete(action: () -> Unit) {
        TODO("Not yet implemented")
    }

    fun getAndIncrement(): Int {
        return counter++
    }

    inner class DisplayWidgetScript<P : GameProps>(
        private val klass: KClass<out GameUIComponent<P, *>>,
        private val handler: RHandler<P>
    ) : GameScript {
        private val id = "${gameScene.map.id}-ScriptWidget-${getAndIncrement()}"
        override fun start() {
            respondToClick = true
            currentWidgets[id] = Widget(klass, handler)
            eventBus.emit(GAME_UI_UPDATE_EVENT, null)
        }

        override fun stop() {
            respondToClick = false
            currentWidgets.remove(id)
            eventBus.emit(GAME_UI_UPDATE_EVENT, null)
        }
    }

    inner class CharacterMoveScript(
        val character: CharacterSprite,
        val destMapCoordinate: GridCoordinate
    ) : GameScript {
        override fun start() {
            character.movePath = searchForNonHero(gameScene.blockers, character.gridCoordinate, destMapCoordinate)

            GlobalScope.launch {
                while (character.gridCoordinate != destMapCoordinate) {
                    delay(500)
                }
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
