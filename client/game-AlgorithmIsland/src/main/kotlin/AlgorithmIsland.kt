
import com.bytelegend.app.client.api.GameRuntime
import kotlinx.browser.window

val gameRuntime = window.asDynamic().gameRuntime.unsafeCast<GameRuntime>()

fun main() {
    gameRuntime.sceneContainer.getSceneById("AlgorithmIsland").apply {
        objects {
        }
    }
}
