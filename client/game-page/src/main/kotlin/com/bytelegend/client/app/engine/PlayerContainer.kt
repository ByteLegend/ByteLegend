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
@file:Suppress("DeferredResultUnused")

package com.bytelegend.client.app.engine

import com.bytelegend.app.client.api.EventBus
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.ResourceLoader
import com.bytelegend.app.client.utils.JSArrayBackedList
import com.bytelegend.app.client.utils.JSObjectBackedMap
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.entities.BasePlayer
import com.bytelegend.app.shared.playerAnimationSetResourceId
import com.bytelegend.app.shared.protocol.PlayerSpeechEventData
import com.bytelegend.app.shared.protocol.playerEnterSceneEvent
import com.bytelegend.app.shared.protocol.playerLeaveSceneEvent
import com.bytelegend.app.shared.protocol.playerMoveOnSceneEvent
import com.bytelegend.app.shared.protocol.playerSpeechEvent
import com.bytelegend.client.app.engine.resource.ImageResource
import com.bytelegend.client.app.obj.character.AnimationSet
import com.bytelegend.client.app.obj.character.PlayerSprite
import com.bytelegend.client.app.web.WebSocketClient

/**
 * Container of all players OTHER THAN hero
 */
class PlayerContainer(
    private val mapId: String,
    private val eventBus: EventBus,
    private val client: WebSocketClient,
    private val resourceLoader: ResourceLoader,
    private val players: List<BasePlayer>
) {
    lateinit var gameScene: GameScene
    private val game: Game by lazy {
        gameScene.gameRuntime.unsafeCast<Game>()
    }
    private val idToPlayer: MutableMap<String, BasePlayer> = JSObjectBackedMap<BasePlayer>().apply {
        players.forEach {
            put(it.id, it)
        }
    }
    private val idToSprite: MutableMap<String, PlayerSprite> = JSObjectBackedMap()
    private val eventHandlers = JSObjectBackedMap<EventListener<*>>().apply {
        put(playerEnterSceneEvent(mapId), this@PlayerContainer::onPlayerEnterScene)
        put(playerLeaveSceneEvent(mapId), this@PlayerContainer::onPlayerLeaveScene)
        put(playerMoveOnSceneEvent(mapId), this@PlayerContainer::onPlayerMoveOnScene)
        put(playerSpeechEvent(mapId), this@PlayerContainer::onPlayerSpeechOnScene)
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

    private fun onPlayerSpeechOnScene(playerSpeechEventData: PlayerSpeechEventData) {
        val playerId = playerSpeechEventData.playerId
        if (playerId == game.heroPlayer.id || !gameScene.isActive) {
            return
        }
        val player = idToPlayer[playerId]
        if (player != null) {
            gameScene.showSpeechBubble(player, playerSpeechEventData)
        }
    }

    private fun onPlayerMoveOnScene(player: BasePlayer) {
        if (player.id == game.heroPlayer.id) {
            return
        }
        val currentPlayer = idToPlayer[player.id]
        if (currentPlayer == null) {
            onPlayerEnterScene(player)
        } else {
            idToSprite[player.id]?.apply {
                moveTo(GridCoordinate(player.x, player.y))
            }
            currentPlayer.x = player.x
            currentPlayer.y = player.y
        }
    }

    private fun onPlayerLeaveScene(player: BasePlayer) {
        if (player.id != game.heroPlayer.id) {
            idToPlayer.remove(player.id)
            idToSprite.remove(player.id)?.close()
        }
    }

    private fun onPlayerEnterScene(player: BasePlayer) {
        if (player.id != game.heroPlayer.id) {
            idToPlayer[player.id] = player
        }
    }

    fun getDrawableCharacters(): List<PlayerSprite> {
        val ret = JSArrayBackedList<PlayerSprite>()
        for (it in idToPlayer.values) {
            val sprite = idToSprite[it.id]
            if (sprite == null) {
                val animationSetId = playerAnimationSetResourceId(it.characterId)
                val animationSet = resourceLoader.getLoadedResourceOrNull<AnimationSet>(animationSetId)
                if (animationSet == null) {
                    if (!resourceLoader.isResourceLoading(animationSetId)) {
                        resourceLoader.loadAsync(
                            ImageResource(
                                animationSetId,
                                game.resolve("/img/player/$animationSetId.png")
                            ),
                            false
                        )
                    }
                } else {
                    val character = PlayerSprite(gameScene, it).apply { init() }
                    idToSprite[it.id] = character
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

fun GameScene.showSpeechBubble(player: BasePlayer, playerSpeechEventData: PlayerSpeechEventData) {
    unsafeCast<DefaultGameScene>().scripts("playerSpeech-${player.id}", true) {
        speech {
            speakerCoordinate = GridCoordinate(player.x, player.y) * map.tileSize
            contentHtmlId = playerSpeechEventData.sentenceId
            dismissMs = 3000
        }
    }
}
