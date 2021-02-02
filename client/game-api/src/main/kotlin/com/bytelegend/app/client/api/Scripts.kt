package com.bytelegend.app.client.api

import kotlinx.css.div
import react.RBuilder

interface GameDirector {
    val scripts: List<GameScript>
}

interface GameScript {
    fun play(builder: RBuilder, gameRuntime: GameRuntime)
}

// class CharacterMoveScript: GameScript {
// }
