import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.shared.JAVA_ISLAND_CONCURRENCY_DUNGEON
import com.bytelegend.app.shared.JAVA_ISLAND_MAVEN_DUNGEON
import com.bytelegend.app.shared.JAVA_ISLAND_SPRING_DUNGEON
import com.bytelegend.app.shared.PixelCoordinate
import kotlinx.browser.window

val gameRuntime = window.asDynamic().gameRuntime.unsafeCast<GameRuntime>()

fun main() {
    gameRuntime.sceneContainer.getSceneById("JavaIslandSeniorJavaCastle").apply {
        objects {
            listOf(
                JAVA_ISLAND_CONCURRENCY_DUNGEON,
                JAVA_ISLAND_MAVEN_DUNGEON,
                JAVA_ISLAND_SPRING_DUNGEON
            ).forEach {
                mapEntrance {
                    destMapId = it
                }
            }

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

            mapEntrance {
                destMapId = "WebIsland"
                coordinatePointId = "SeniorJavaCastle-WebIsland-entrance-point"
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
