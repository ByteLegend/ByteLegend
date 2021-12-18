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

import com.bytelegend.app.shared.entities.mission.ChallengeSpec
import com.bytelegend.client.app.ui.GameProps
import react.RBuilder
import react.RComponent
import react.State

interface StarChallengeTabProps : GameProps {
    var contentHtml: String
    var missionId: String
    var challengeSpec: ChallengeSpec
}

class StarChallengeTab : RComponent<StarChallengeTabProps, State>() {
    override fun RBuilder.render() {
        child(WebEditor::class) {
            attrs.game = props.game
            attrs.missionId = props.missionId
            attrs.challengeSpec = props.challengeSpec
        }
    }
}
