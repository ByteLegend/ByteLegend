package com.bytelegend.app.client.api.dsl

import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.objects.GameObject

fun GameRuntime.gameScene(id: String, builder: GameSceneBuilder.() -> Unit) {
}

fun GameRuntime.gameScene(builder: GameSceneBuilder.() -> Unit) {
}

class MapEntranceBuilder {
    var destMapId: String? = null
}

interface ObjectsConfigurer {
    fun mapEntrance(builder: MapEntranceBuilder.() -> Unit)

    fun npc(builder: NpcBuilder.() -> Unit)
}

class NpcBuilder {
    var id: String? = null
    var animationSetIndex: Int? = null
    var onInit: (() -> Unit)? = null
    var onTouch: ((GameObject) -> Unit)? = null
    var onClick: (() -> Unit)? = null
}

class GameSceneBuilder {
    fun objects(builder: ObjectsConfigurer.() -> Unit) {
    }
}