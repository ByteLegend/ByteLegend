package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.shared.entities.Mission

class MissionContainer(
    private val missions: Map<String, Mission>
) {
    var gameScene: GameScene? = null
}
