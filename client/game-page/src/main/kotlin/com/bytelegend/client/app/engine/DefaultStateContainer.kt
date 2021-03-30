package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.StateContainer
import com.bytelegend.app.shared.entities.States

class DefaultStateContainer(
    private val states: States
) : StateContainer {
    lateinit var gameScene: GameScene

    fun init(scene: GameScene) {
        gameScene = scene
    }
}
