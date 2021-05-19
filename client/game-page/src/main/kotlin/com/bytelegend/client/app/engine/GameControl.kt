package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameSceneContainer
import com.bytelegend.app.client.api.getAudioElementOrNull
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.client.app.script.STAR_FLYING_CHANNEL
import com.bytelegend.client.app.web.WebSocketClient
import kotlinx.browser.document
import org.kodein.di.DI
import org.kodein.di.instance

class GameControl(
    di: DI
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

    /**
     * During the scripts, e.g. speech with NPC, you can scroll the map, move cursor, but can't click
     * on map elements.
     */
    var mapMouseClickEnabled = false
    private val gameRuntime: GameRuntime by di.instance()
    private val game: Game by lazy { gameRuntime.unsafeCast<Game>() }
    private val eventBus: EventBus by di.instance()
    private val gameSceneContainer: GameSceneContainer by di.instance()
    private val webSocketClient: WebSocketClient by di.instance()
    val isWindowVisible: Boolean
        get() = document.asDynamic().visibilityState != "hidden"
    val online: Boolean
        get() = webSocketClient.connected

    fun start() {
        mapMouseClickEnabled = true
        eventBus.on(MOUSE_CLICK_EVENT, this::onMouseClickOnCanvas)
        document.addEventListener(
            "visibilitychange",
            {
                if (isWindowVisible) {
                    eventBus.emit(GAME_SCRIPT_NEXT, STAR_FLYING_CHANNEL)
                }
            }
        )
    }

    private fun onMouseClickOnCanvas(event: GameMouseEvent) {
        if (mapMouseClickEnabled) {
            clickObjectsAndMove(event.mapCoordinate)
        }
    }

    // In offline mode, everything is still clickable, but hero won't move.
    private fun clickObjectsAndMove(coordinate: GridCoordinate) {
        val scene = gameSceneContainer.activeScene!!.unsafeCast<DefaultGameScene>()
        val gameObjects = scene.objects.getByCoordinate(coordinate)

//        console.log(scene.mainChannelDirector.index)
//        if (scene.mainChannelDirector.isRunning) {
//            // if the speech bubble is on, don't allow to click on NPCs
//            gameObjects
//                .filter { !it.roles.contains(GameObjectRole.NPC) }
//                .forEach { it.onClick() }
//        } else {
        gameObjects.forEach { it.onClick() }
//        }

        if (!online) {
            gameRuntime.toastController.addToast(
                gameRuntime.i("YouAreOffline"),
                gameRuntime.i("PleaseRefreshPage"),
                5000
            )
        }

        if (online &&
            game._hero != null &&
            gameRuntime.activeScene == game._hero!!.gameScene
        ) {
            game.hero!!.moveTo(coordinate)
        }
    }
}
