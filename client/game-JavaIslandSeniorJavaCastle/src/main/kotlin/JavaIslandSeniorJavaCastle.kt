import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.shared.JAVA_ISLAND_CONCURRENCY_DUNGEON
import com.bytelegend.app.shared.JAVA_ISLAND_MAVEN_DUNGEON
import kotlinx.browser.window

val gameRuntime = window.asDynamic().gameRuntime.unsafeCast<GameRuntime>()

fun main() {
    gameRuntime.sceneContainer.getSceneById("JavaIslandSeniorJavaCastle").apply {
        objects {
            listOf(JAVA_ISLAND_CONCURRENCY_DUNGEON, JAVA_ISLAND_MAVEN_DUNGEON).forEach {
                mapEntrance {
                    destMapId = it
                }
            }
        }
    }
}
