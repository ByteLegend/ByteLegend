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
@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.bytelegend.client.app.obj

import com.bytelegend.app.client.api.GameScene
import com.bytelegend.app.client.api.GameSceneAware
import com.bytelegend.app.client.api.HasBouncingTitle
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.INVISIBLE_OBJECT_LAYER
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.objects.CoordinateAware
import com.bytelegend.app.shared.objects.GameObject
import com.bytelegend.app.shared.objects.GameObjectRole
import com.bytelegend.client.app.engine.DefaultGameSceneContainer
import com.bytelegend.client.app.engine.Game
import com.bytelegend.client.app.obj.character.CharacterSprite
import com.bytelegend.client.app.ui.mission.BouncingTitleWidget
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import react.RBuilder

class GameMapEntrance(
    override val id: String,
    override val gameScene: GameScene,
    override val gridCoordinate: GridCoordinate,
    private val destMapId: String,
    override var bouncingTitleEnabled: Boolean
) : GameObject, GameSceneAware, CoordinateAware, HasBouncingTitle {
    override val layer: Int = INVISIBLE_OBJECT_LAYER
    override val roles: Set<String> = jsObjectBackedSetOf(
        GameObjectRole.MapEntrance.toString(),
        GameObjectRole.CoordinateAware.toString(),
        GameObjectRole.HasBouncingTitle.toString()
    )
    override val pixelCoordinate: PixelCoordinate = gridCoordinate * gameScene.map.tileSize

    @Suppress("UnsafeCastFromDynamic")
    override fun onTouch(obj: GameObject) {
        if (gameRuntime.hero != null && gameRuntime.hero!!.id == obj.id) {
            GlobalScope.launch {
                gameScene.gameRuntime.unsafeCast<Game>().webSocketClient.switchScene(destMapId)
                // after switching scene, load the new scene
                gameRuntime.sceneContainer.unsafeCast<DefaultGameSceneContainer>().heroEnterScene(destMapId)
            }
        } else {
            gameRuntime.activeScene.objects.getById<CharacterSprite>(obj.id).close()
        }
    }

    override fun renderBouncingTitle(builder: RBuilder) {
        builder.child(BouncingTitleWidget::class) {
            attrs.title = gameScene.gameRuntime.i(destMapId)
            attrs.pixelCoordinate = pixelCoordinate + PixelCoordinate(gameScene.map.tileSize.width / 2, 0)
            attrs.gameScene = gameScene
            attrs.color = "white"
            attrs.backgroundColor = "rgba(36,102,233,0.8)"
            attrs.onClickFunction = {
                gameScene.gameRuntime.sceneContainer.loadScene(destMapId)
            }
        }
    }
}
