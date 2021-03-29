package com.bytelegend.client.app.script

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.GameSceneContainer
import com.bytelegend.app.client.api.JSObjectBackedMap
import com.bytelegend.app.client.api.ModalController
import com.bytelegend.app.client.api.ScriptsBuilder
import com.bytelegend.app.client.api.SpeechBuilder
import com.bytelegend.app.client.api.dsl.SuspendUnitFunction
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.client.app.engine.GAME_UI_UPDATE_EVENT
import com.bytelegend.client.app.engine.GameControl
import com.bytelegend.client.app.obj.CharacterSprite
import com.bytelegend.client.app.script.effect.fadeInEffect
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.script.SpeechBubbleWidget
import com.bytelegend.client.app.ui.script.Widget
import kotlinx.coroutines.GlobalScope
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
) : ScriptsBuilder {
    private val sceneContainer: GameSceneContainer by di.instance()
    private val gameControl: GameControl by di.instance()
    private val game: GameRuntime by di.instance()
    private val modalController: ModalController by lazy { game.modalController }
    private val scripts: MutableList<GameScript> = mutableListOf()
    private val eventBus: EventBus by di.instance()
    private var index = -1
    private val gameScene: GameScene
        get() = game.activeScene

    /**
     * A counter providing unique id for widgets in DOM
     */
    private var counter = 0

    val currentWidgets: MutableMap<String, Widget<out GameProps>> = JSObjectBackedMap()

    private val isRunning
        get() = index != -1

    /**
     * Start all scripts, if modal is not shown.
     */
    fun start() {
        if (!modalController.visible) {
            index = 0
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
            gameControl.userMouseEnabled = false
        }

        if (index == scripts.size) {
            gameControl.userMouseEnabled = true
            reset()
            return
        }
        scripts[index++].start()
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
        TODO("Not yet implemented")
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
            currentWidgets[id] = Widget(klass, handler)
            eventBus.emit(GAME_UI_UPDATE_EVENT, null)
        }

        override fun stop() {
            currentWidgets.remove(id)
            eventBus.emit(GAME_UI_UPDATE_EVENT, null)
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
