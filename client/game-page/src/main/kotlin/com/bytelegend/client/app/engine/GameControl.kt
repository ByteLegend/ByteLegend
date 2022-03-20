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

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameSceneContainer
import com.bytelegend.app.client.misc.getAudioElementOrNull
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.NON_BLOCKER
import com.bytelegend.app.shared.objects.GameObjectRole.UnableToBeSetAsDestination
import com.bytelegend.client.app.engine.resource.AudioResource
import com.bytelegend.client.app.script.ASYNC_ANIMATION_CHANNEL
import com.bytelegend.client.app.web.WebSocketClient
import kotlinx.browser.document
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.instance

class GameControl(
    di: DI
) {
    var audioEnabled: Boolean = false
        set(value) {
            field = value
            GlobalScope.launch {
                game.resourceLoader.loadAsync(AudioResource("forest", game.resolve("/audio/forest.ogg")), false).await()
                if (value) {
                    getAudioElementOrNull("forest")?.play()
                } else {
                    getAudioElementOrNull("forest")?.pause()
                }
            }
        }

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
        eventBus.on(MOUSE_CLICK_EVENT, this::onMouseClickOnCanvas)
        document.addEventListener(
            "visibilitychange",
            {
                if (isWindowVisible) {
                    eventBus.emit(GAME_SCRIPT_NEXT, ASYNC_ANIMATION_CHANNEL)
                }
            }
        )
    }

    private fun onMouseClickOnCanvas(event: GameMouseEvent) {
        logger.debug("User clicked ${event.mapCoordinate} ${event.humanReadableCoordinate}")
        val mainChannel = gameRuntime.activeScene.unsafeCast<DefaultGameScene>().mainChannelDirector
        val mainChannelRunning = mainChannel.isRunning
        mainChannel.onMouseClickOnCanvas(event)
        if (!mainChannelRunning) {
            clickObjectsAndMove(event.mapCoordinate)
        }
    }

    // In offline mode, everything is still clickable, but hero won't move.
    private fun clickObjectsAndMove(coordinate: GridCoordinate) {
        val scene = gameSceneContainer.activeScene!!.unsafeCast<DefaultGameScene>()
        val gameObjects = scene.objects.getByCoordinate(coordinate).objects

        var canBeSetAsDestination = true
        gameObjects.forEach {
            canBeSetAsDestination = !it.value.roles.contains(UnableToBeSetAsDestination.toString())
            it.value.onClick()
        }

        if (!online) {
            gameRuntime.toastController.addToast(
                gameRuntime.i("YouAreOffline"),
                gameRuntime.i("PleaseRefreshPage"),
                5000
            )
        }

        if (online &&
            canBeSetAsDestination &&
            game._hero != null &&
            gameRuntime.activeScene == game._hero!!.gameScene &&
            gameRuntime.activeScene.blockers[coordinate.y][coordinate.x] == NON_BLOCKER
        ) {
            game.hero!!.moveTo(coordinate)
        }
    }
}
