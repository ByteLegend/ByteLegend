import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.shared.JAVA_ISLAND
import com.bytelegend.app.shared.JAVA_ISLAND_NEWBIE_VILLAGE_PUB
import kotlinx.browser.window

val gameRuntime = window.asDynamic().gameRuntime.unsafeCast<GameRuntime>()

fun main() {
//    gameRuntime.addMapEntrance(JAVA_ISLAND_NEWBIE_VILLAGE_PUB, JAVA_ISLAND)
}
