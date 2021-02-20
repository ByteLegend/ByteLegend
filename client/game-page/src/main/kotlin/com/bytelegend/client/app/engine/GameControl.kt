package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameSceneContainer
import com.bytelegend.app.client.api.getAudioElementOrNull
import com.bytelegend.app.shared.BLOCKER
import com.bytelegend.app.shared.Direction
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.client.app.script.DefaultGameDirector
import com.bytelegend.client.app.ui.MAP_SCROLL_EVENT
import common.utils.search
import org.kodein.di.DI
import org.kodein.di.instance

class GameControl(
    private val di: DI
) {
    var audioEnabled: Boolean = false
        set(value) {
            field = value
            if (value) {
                getAudioElementOrNull("forest")?.play()
            } else {
                getAudioElementOrNull("forest")?.pause()
            }
        }
    var userMouseEnabled = false
    private val gameRuntime: GameRuntime by di.instance()
    private val game: Game by lazy { gameRuntime.unsafeCast<Game>() }
    private val eventBus: EventBus by di.instance()
    private val gameSceneContainer: GameSceneContainer by di.instance()

    fun start() {
        userMouseEnabled = true
    }

    fun onMouseClickOnCanvas(event: GameMouseEvent) {
        if (userMouseEnabled) {
            clickObjectsAndMove(event.mapCoordinate)
            eventBus.emit(MOUSE_CLICK_EVENT, event)
        } else {
            console.log("click")
            game.activeScene.director.unsafeCast<DefaultGameDirector>().next()
        }
    }

    fun onMouseMoveOnCanvas(event: GameMouseEvent) {
        if (userMouseEnabled) {
            eventBus.emit(MOUSE_MOVE_EVENT, event)
        }
    }

    fun onMouseMoveOutOfCanvas() {
        if (userMouseEnabled) {
            eventBus.emit(MOUSE_OUT_OF_MAP_EVENT, null)
        }
    }

    fun onScroll(direction: Direction) {
        if (userMouseEnabled) {
            eventBus.emit(MAP_SCROLL_EVENT, direction)
        }
    }

    private fun clickObjectsAndMove(coordinate: GridCoordinate) {
        val scene = gameSceneContainer.activeScene!!
        val gameObjects = scene.objects.getByCoordinate(coordinate)

        gameObjects.forEach {
            it.onClick()
        }

        if (gameRuntime.hero != null && gameRuntime.activeScene == game._hero!!.gameScene) {
            val hero = gameRuntime.hero!!
            if (scene.blockers[coordinate.y][coordinate.x] != BLOCKER) {
                val path = search(scene.blockers, hero.gridCoordinate, coordinate)
                if (path.isNotEmpty()) {
                    hero.movePath = path
                }
            }
        }
    }
}
