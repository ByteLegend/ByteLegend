import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.shared.JAVA_ISLAND
import com.bytelegend.app.shared.JAVA_ISLAND_DEBUGGER_DUNGEON
import kotlinx.browser.window

val gameRuntime = window.asDynamic().gameRuntime.unsafeCast<GameRuntime>()

fun main() {
    gameRuntime.sceneContainer.getSceneById(JAVA_ISLAND_DEBUGGER_DUNGEON).apply {
        objects {
            mapEntrance {
                destMapId = JAVA_ISLAND
                bouncingTitle = false
            }
        }
    }
}
