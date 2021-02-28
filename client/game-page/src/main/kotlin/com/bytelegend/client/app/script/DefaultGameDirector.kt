package com.bytelegend.client.app.script

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.GameDirector
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.JSObjectBackedMap
import com.bytelegend.app.client.api.ScriptsBuilder
import com.bytelegend.app.client.api.SpeechBuilder
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.client.app.engine.GAME_UI_UPDATE_EVENT
import com.bytelegend.client.app.engine.GameControl
import com.bytelegend.client.app.obj.AbstractCharacter
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
    private val gameScene: GameScene
) : ScriptsBuilder, GameDirector {
    private val gameControl: GameControl by di.instance()
    private val scripts: MutableList<GameScript> = mutableListOf()
    private val eventBus: EventBus by di.instance()
    private var index = -1

    private var counter = 0

    val currentWidgets: MutableMap<String, Widget<out GameProps>> = JSObjectBackedMap()

    /**
     * Start all scripts
     */
    override fun start() {
        index = 0
        next()
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
        }

        if (index == scripts.size) {
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
        require(scripts.isEmpty() && index == -1)
        disableUserMouse()
        block()
        enableUserMouse()
        start()
    }

    override fun enableUserMouse() {
        scripts.add(object : GameScript {
            override fun start() {
                gameControl.userMouseEnabled = true
                next()
            }
        })
    }

    override fun speech(action: SpeechBuilder.() -> Unit) {
        val builder = SpeechBuilder()
        builder.action()

        val character = gameScene.objects.getById<AbstractCharacter>(builder.objectId!!)
        scripts.add(
            DisplayWidgetScript(SpeechBubbleWidget::class) {
                attrs.game = gameScene.gameRuntime.asDynamic()
                attrs.speakerCoordinate = character.pixelCoordinate
                attrs.contentHtml = gameScene.gameRuntime.i(builder.contentHtmlId!!, *builder.args)
                attrs.arrow = builder.arrow
            }
        )
    }

    override fun starFly(fromObjectId: String) {
        scripts.add(RunNativeJsScript(gameScene.objects.getById<AbstractCharacter>(fromObjectId).pixelCoordinate))
    }

    override fun disableUserMouse() {
        scripts.add(object : GameScript {
            override fun start() {
                gameControl.userMouseEnabled = false
                next()
            }
        })
    }

    override fun fadeIn() {
        scripts.add(RunSuspendFunctionScript { fadeInEffect(gameScene.gameContainerSize) })
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
