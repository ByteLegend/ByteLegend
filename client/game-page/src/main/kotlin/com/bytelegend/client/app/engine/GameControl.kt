package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameSceneContainer
import com.bytelegend.app.shared.BLOCKER
import common.utils.search
import org.kodein.di.DI
import org.kodein.di.instance

class GameControl(
    private val di: DI
) {
    private val gameRuntime: GameRuntime by di.instance()
    private val game: Game by lazy { gameRuntime.unsafeCast<Game>() }
    private val eventBus: EventBus by di.instance()
    private val gameSceneContainer: GameSceneContainer by di.instance()
    private val onClickEventListener: EventListener<GameMouseEvent> = this::onClick

    fun start() {
        eventBus.on(MOUSE_CLICK_EVENT, onClickEventListener)
    }

    private fun onClick(clickEvent: GameMouseEvent) {
        val scene = gameSceneContainer.activeScene!!
        val coordinate = clickEvent.mapCoordinate
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
