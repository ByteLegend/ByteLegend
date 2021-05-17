package com.bytelegend.client.app.ui.mission

import com.bytelegend.app.client.ui.bootstrap.BootstrapSpinner
import com.bytelegend.app.shared.entities.mission.Tutorial
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import react.RBuilder
import react.RState

interface TutorialContentProps : GameProps {
    var tutorial: Tutorial
}

interface TutorialContentState : RState {
    var loading: Boolean
}

class TutorialContent : GameUIComponent<TutorialContentProps, TutorialContentState>() {
    override fun TutorialContentState.init() {
        loading = true
    }

    override fun RBuilder.render() {
        if (state.loading) {
            BootstrapSpinner {
                attrs.animation = "border"
                attrs.className = "tutorial-spinner"
            }
        }

        +"This is the content"
    }
}
