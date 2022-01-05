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
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.shared.PixelCoordinate
import kotlinx.browser.window

val gameRuntime = window.asDynamic().gameRuntime.unsafeCast<GameRuntime>()

fun main() {
    gameRuntime.sceneContainer.getSceneById("KotlinIsland").apply {
        val showModal = {
            gameRuntime.modalController.showModal(
                gameRuntime.i("KotlinIslandIsARealIsland"),
                gameRuntime.i("DoYouKnow")
            )
        }
        objects {
            bouncingTitle {
                pixelCoordinate = objects.getPointById("WoodenSign-point") * this@apply.map.tileSize + PixelCoordinate(16, 0)
                text = gameRuntime.i("ClickMe")
                color = "white"
                backgroundColor = "rgba(23,162,184,0.8)"
                onClickFunction = showModal
            }

            val roadSignId = "KotlinIslandRoadSign"
            dynamicSprite {
                id = roadSignId
                sprite = "WoodenSign"
                gridCoordinate = objects.getPointById("WoodenSign-point")
                onClick = showModal
            }
        }
    }
}
