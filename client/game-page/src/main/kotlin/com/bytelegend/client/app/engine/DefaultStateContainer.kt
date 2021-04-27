package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.StateContainer
import com.bytelegend.app.shared.entities.States
import com.bytelegend.client.app.web.WebSocketClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DefaultStateContainer(
    private val states: States
) : StateContainer {
    private lateinit var gameScene: GameScene
    private val webSocketClient: WebSocketClient by lazy {
        gameScene.gameRuntime.unsafeCast<Game>().webSocketClient
    }

    fun init(scene: GameScene) {
        gameScene = scene
    }

    override fun hasState(state: String): Boolean = states.states.containsKey(state)

    override fun getState(name: String): String = states.states.getValue(name)

    override fun removeState(state: String) {
        GlobalScope.launch {
            webSocketClient.removeState(gameScene.map.id, state)
            states.states.remove(state)
        }
    }
}
