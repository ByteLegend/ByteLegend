package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameSceneContainer
import com.bytelegend.app.client.api.getAudioElementOrNull
import com.bytelegend.app.shared.Direction
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.NON_BLOCKER
import com.bytelegend.client.app.ui.MAP_SCROLL_EVENT
import com.bytelegend.client.app.web.WebSocketClient
import common.utils.search
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
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
    private val webSocketClient: WebSocketClient by di.instance()
    val online: Boolean
        get() = webSocketClient.connected

    fun start() {
        userMouseEnabled = true
    }

    fun onMouseClickOnCanvas(event: GameMouseEvent) {
        if (userMouseEnabled) {
            clickObjectsAndMove(event.mapCoordinate)
        } else {
            game.director.next()
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

    // In offline mode, everything is still clickable, but hero won't move.
    private fun clickObjectsAndMove(coordinate: GridCoordinate) {
        val scene = gameSceneContainer.activeScene!!
        val gameObjects = scene.objects.getByCoordinate(coordinate)

        gameObjects.forEach {
            it.onClick()
        }

        if (!online) {
            gameRuntime.toastController.addToast(
                gameRuntime.i("YouAreOffline"),
                gameRuntime.i("PleaseRefreshPage"),
                5000
            )
        }

        if (online &&
            game._hero != null &&
            gameRuntime.activeScene == game._hero!!.gameScene &&
            !isBlocker(coordinate)
        ) {
            val hero = gameRuntime.hero!!
            val path = search(scene.blockers, hero.gridCoordinate, coordinate)
            if (path.isNotEmpty()) {
                GlobalScope.async {
                    webSocketClient.moveTo(coordinate.x, coordinate.y)
                }
                game._hero!!.movePath = path
            }
        }
    }

    private fun isBlocker(coordinate: GridCoordinate) = gameSceneContainer.activeScene!!.blockers[coordinate.y][coordinate.x] > NON_BLOCKER
}
