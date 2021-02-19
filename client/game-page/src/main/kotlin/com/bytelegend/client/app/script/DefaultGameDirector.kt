package com.bytelegend.client.app.script

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.GameDirector
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.JSObjectBackedMap
import com.bytelegend.app.client.api.ScriptsBuilder
import com.bytelegend.app.client.api.SpeechBuilder
import com.bytelegend.client.app.engine.GAME_UI_UPDATE_EVENT
import com.bytelegend.client.app.engine.GameControl
import com.bytelegend.client.app.obj.AbstractCharacter
import com.bytelegend.client.app.ui.script.SpeechBubbleWidget
import com.bytelegend.client.app.ui.script.Widget
import org.kodein.di.DI
import org.kodein.di.instance

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

    val currentWidgets: MutableMap<String, Widget> = JSObjectBackedMap()

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
        console.log("next: $index")
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
        block()
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
        val speechBubbleWidget = SpeechBubbleWidget(
            gameScene,
            character.pixelCoordinate,
            gameScene.gameRuntime.i(builder.contentHtmlId!!)
        )

        scripts.add(DisplayWidgetScript(speechBubbleWidget))
    }

    override fun disableUserMouse() {
        scripts.add(object : GameScript {
            override fun start() {
                gameControl.userMouseEnabled = false
                next()
            }
        })
    }

    fun getAndIncrement(): Int {
        return counter++
    }

    inner class DisplayWidgetScript(
        private val widget: Widget
    ) : GameScript {
        private val id = "${gameScene.map.id}-ScriptWidget-${getAndIncrement()}"
        override fun start() {
            currentWidgets[id] = widget
            eventBus.emit(GAME_UI_UPDATE_EVENT, null)
        }

        override fun stop() {
            currentWidgets.remove(id)
            eventBus.emit(GAME_UI_UPDATE_EVENT, null)
        }
    }
}
