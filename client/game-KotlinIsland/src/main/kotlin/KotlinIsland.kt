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
