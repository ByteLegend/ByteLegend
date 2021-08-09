package com.bytelegend.client.app.ui

import com.bytelegend.app.client.api.GameRuntime
import com.bytelegend.client.app.engine.DefaultGameScene
import com.bytelegend.client.app.obj.GameMapEntrance
import com.bytelegend.client.app.ui.mission.FloatingTitle
import com.bytelegend.client.app.ui.mission.FloatingTitleProps
import react.RBuilder

interface EntranceRoadSignProps : FloatingTitleProps {
    var game: GameRuntime
    var entrance: GameMapEntrance
}

class EntranceRoadSign : FloatingTitle<EntranceRoadSignProps>() {
    override fun RBuilder.render() {
        renderTitle("rgba(36,102,233,0.9)") {
        }
    }

    override fun onClick() {
        if (!props.game.activeScene.unsafeCast<DefaultGameScene>().mainChannelDirector.isRunning) {
            props.game.sceneContainer.loadScene(props.entrance.destMapId)
        }
    }
}
