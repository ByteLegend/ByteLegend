import com.bytelegend.app.client.api.GameRuntime
import kotlinx.browser.window

val gameRuntime = window.asDynamic().gameRuntime.unsafeCast<GameRuntime>()

fun main() {
    gameRuntime.sceneContainer.getSceneById("KotlinIsland").apply {
        objects {
            val roadSignId = "KotlinIslandRoadSign"
            dynamicSprite {
                id = roadSignId
                sprite = "WoodenSign"
                gridCoordinate = objects.getPointById("WoodenSign-point")
                onClick = {
                    gameRuntime.modalController.showModal("KotlinIslandIsARealIsland", "DoYouKnow")
                }
            }
        }
    }
}
