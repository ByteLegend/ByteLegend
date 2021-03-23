package com.bytelegend.client.app.engine

import com.bytelegend.app.shared.entities.States

class StateContainer(
    private val states: States
) {
    lateinit var gameScene: DefaultGameScene
}
