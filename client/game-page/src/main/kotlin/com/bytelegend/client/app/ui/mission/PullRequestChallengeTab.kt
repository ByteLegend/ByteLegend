/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bytelegend.client.app.ui.mission

import com.bytelegend.app.shared.entities.MissionModalData
import com.bytelegend.app.shared.entities.mission.ChallengeSpec
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.unsafeDiv
import com.bytelegend.client.app.ui.unsafeH4
import kotlinx.js.jso
import react.Fragment
import react.State
import react.create
import react.dom.html.ReactHTML.br
import react.react

interface PullRequestChallengeTabProps : GameProps {
    var missionModalData: MissionModalData
    var challengeSpec: ChallengeSpec
    var whitelist: List<String>
}

class PullRequestChallengeTab : GameUIComponent<PullRequestChallengeTabProps, State>() {
    override fun render() = Fragment.create {
        if (props.challengeSpec.tldr.isNotBlank()) {
            unsafeH4(i("TLDR"))
            unsafeDiv(i(props.challengeSpec.tldr))
            br { }
        }

        child(WebEditor::class.react, jso {
            whitelist = props.whitelist
            game = props.game
            missionModalData = props.missionModalData
            challengeSpec = props.challengeSpec
        })
    }
}
