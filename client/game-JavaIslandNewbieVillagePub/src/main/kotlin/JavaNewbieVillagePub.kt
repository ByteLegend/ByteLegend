import com.bytelegend.app.client.api.Character
import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.app.client.api.GameScriptHelpers
import com.bytelegend.app.client.api.HERO_ID
import com.bytelegend.app.shared.JAVA_ISLAND
import com.bytelegend.app.shared.JAVA_ISLAND_NEWBIE_VILLAGE_PUB
import kotlinx.browser.window

val gameRuntime = window.asDynamic().gameRuntime.unsafeCast<GameRuntime>()

fun main() {
    gameRuntime.sceneContainer.getSceneById(JAVA_ISLAND_NEWBIE_VILLAGE_PUB).apply {
        val helpers = GameScriptHelpers(this)
        objects {
            mapEntrance {
                destMapId = JAVA_ISLAND
            }

            npc {
                val girlId = "JavaIslandNewbieVillagePubGirl"
                id = girlId
                sprite = "JavaIslandNewbieVillagePubGirl-sprite"
                onClick = helpers.standardNpcSpeech(girlId) {
                    scripts {
                        speech {
                            objectId = girlId
                            contentHtmlId = "PleaseTakeALookAtThatBook"
                        }
                    }
                }
            }
        }
    }
}
