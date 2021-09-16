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
    gameRuntime.sceneContainer.getSceneById("JavaIslandSeniorJavaCastle").apply {
        objects {
            bouncingTitle {
                val point = objects.getPointById("SeniorJavaCastle-JUnitDungeon-entrance-point")
                pixelCoordinate = point * map.tileSize + PixelCoordinate(16, 0)
                text = gameRuntime.i("JavaIslandJUnitDungeon")
                backgroundColor = "rgba(36,102,233,0.8)"
                tileCoordinates.add(point)
            }

            bouncingTitle {
                val point = objects.getPointById("SeniorJavaCastle-RedisIsland-entrance-point")
                pixelCoordinate = point * map.tileSize + PixelCoordinate(16, 0)
                text = gameRuntime.i("RedisIsland")
                backgroundColor = "rgba(36,102,233,0.8)"
                tileCoordinates.add(point)
            }

            bouncingTitle {
                val point = objects.getPointById("SeniorJavaCastle-MessageQueueIsland-entrance-point")
                pixelCoordinate = point * map.tileSize + PixelCoordinate(16, 0)
                text = gameRuntime.i("MessageQueueIsland")
                backgroundColor = "rgba(36,102,233,0.8)"
                tileCoordinates.add(point)
            }

            bouncingTitle {
                val point = objects.getPointById("SeniorJavaCastle-DistributedSystemIsland-entrance-point")
                pixelCoordinate = point * map.tileSize + PixelCoordinate(16, 0)
                text = gameRuntime.i("DistributedSystemIsland")
                backgroundColor = "rgba(36,102,233,0.8)"
                tileCoordinates.add(point)
            }

            bouncingTitle {
                val point = objects.getPointById("SeniorJavaCastle-JavaRPCDungeon-entrance-point")
                pixelCoordinate = point * map.tileSize + PixelCoordinate(16, 0)
                text = gameRuntime.i("JavaRPCDungeon")
                backgroundColor = "rgba(36,102,233,0.8)"
                tileCoordinates.add(point)
            }
            bouncingTitle {
                val point = objects.getPointById("SeniorJavaCastle-OpenJDKDungeon-entrance-point")
                pixelCoordinate = point * map.tileSize + PixelCoordinate(16, 0)
                text = gameRuntime.i("OpenJDKDungeon")
                backgroundColor = "rgba(36,102,233,0.8)"
                tileCoordinates.add(point)
            }

            bouncingTitle {
                val point = objects.getPointById("SeniorJavaCastle-JavaPerformanceDungeon-entrance-point")
                pixelCoordinate = point * map.tileSize + PixelCoordinate(16, 0)
                text = gameRuntime.i("JavaPerformanceDungeon")
                backgroundColor = "rgba(36,102,233,0.8)"
                tileCoordinates.add(point)
            }
            bouncingTitle {
                val point = objects.getPointById("SeniorJavaCastle-JavaASMDungeon-entrance-point")
                pixelCoordinate = point * map.tileSize + PixelCoordinate(16, 0)
                text = gameRuntime.i("JavaASMDungeon")
                backgroundColor = "rgba(36,102,233,0.8)"
                tileCoordinates.add(point)
            }
        }
    }
}
