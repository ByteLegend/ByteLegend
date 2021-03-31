package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.ImageResource
import com.bytelegend.app.client.api.JSArrayBackedList
import com.bytelegend.app.client.api.JSObjectBackedMap
import com.bytelegend.app.client.api.ResourceLoader
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.NON_BLOCKER
import com.bytelegend.app.shared.entities.Player
import com.bytelegend.app.shared.playerAnimationSetResourceId
import com.bytelegend.app.shared.protocol.playerEnterSceneEvent
import com.bytelegend.app.shared.protocol.playerLeaveSceneEvent
import com.bytelegend.app.shared.protocol.playerMoveOnSceneEvent
import com.bytelegend.client.app.obj.AnimationSet
import com.bytelegend.client.app.obj.PlayerSprite
import com.bytelegend.client.app.page.game
import com.bytelegend.client.app.web.WebSocketClient
import common.utils.search

class PlayerContainer(
    private val mapId: String,
    private val eventBus: EventBus,
    private val client: WebSocketClient,
    private val resourceLoader: ResourceLoader,
    private val players: List<Player>
) {
    lateinit var gameScene: GameScene
    private val idToPlayer: MutableMap<String, Player> = JSObjectBackedMap<Player>().apply {
        players.forEach {
            put(it.id!!, it)
        }
    }
    private val idToSprite: MutableMap<String, PlayerSprite> = JSObjectBackedMap()
    private val eventHandlers = JSObjectBackedMap<EventListener<*>>().apply {
        put(playerEnterSceneEvent(mapId), this@PlayerContainer::onPlayerEnterScene)
        put(playerLeaveSceneEvent(mapId), this@PlayerContainer::onPlayerLeaveScene)
        put(playerMoveOnSceneEvent(mapId), this@PlayerContainer::onPlayerMoveOnScene)
    }

    fun init(gameScene: GameScene) {
        this.gameScene = gameScene
        eventHandlers.forEach { (key: String, value: EventListener<*>) ->
            client.subscribe(key)
            eventBus.on(key, value)
        }
    }

    fun close() {
        eventHandlers.forEach { (key: String, value: EventListener<*>) ->
            client.unsubscribe(key)
            eventBus.remove(key, value)
        }
        idToSprite.values.forEach {
            it.close()
        }
    }

    private fun onPlayerMoveOnScene(player: Player) {
        val currentPlayer = idToPlayer[player.id!!] ?: return
        idToSprite[player.id!!]?.apply {
            movePath = search(
                gameScene.blockers,
                GridCoordinate(gridCoordinate.x, gridCoordinate.y),
                GridCoordinate(player.x!!, player.y!!),
            ) {
                it != NON_BLOCKER
            }
        }
        currentPlayer.x = player.x!!
        currentPlayer.y = player.y!!
    }

    private fun onPlayerLeaveScene(player: Player) {
        idToPlayer.remove(player.id!!)
        idToSprite.remove(player.id)?.close()
    }

    private fun onPlayerEnterScene(player: Player) {
        idToPlayer[player.id!!] = player
    }

    fun getDrawableCharacters(): List<PlayerSprite> {
        val ret = JSArrayBackedList<PlayerSprite>()
        for (it in idToPlayer.values) {
            val sprite = idToSprite[it.id]
            if (sprite == null) {
                val animationSetId = playerAnimationSetResourceId(it.characterId!!)
                val animationSet = resourceLoader.getLoadedResourceOrNull<AnimationSet>(animationSetId)
                if (animationSet == null) {
                    if (!resourceLoader.isResourceLoading(animationSetId)) {
                        resourceLoader.loadAsync(
                            ImageResource(
                                animationSetId,
                                game.resolve("/img/player/$animationSetId.png"),
                                1
                            ),
                            false
                        )
                    }
                } else {
                    val character = PlayerSprite(gameScene, it)
                    character.init()
                    idToSprite[it.id!!] = character
                    if (!character.outOfCanvas()) {
                        ret.add(character)
                    }
                }
            } else {
                if (!sprite.outOfCanvas()) {
                    ret.add(sprite)
                }
            }
        }
        return ret
    }
}
