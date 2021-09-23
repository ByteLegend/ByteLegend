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

import com.bytelegend.app.client.misc.githubUrlToRawGithubUserContentUrl
import com.bytelegend.app.shared.entities.mission.ChallengeSpec
import com.bytelegend.client.app.external.LoadableMarkdown
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.unsafeDiv
import com.bytelegend.client.app.ui.unsafeH4
import react.RBuilder
import react.State
import react.dom.br
import react.dom.h4

interface PullRequestChallengeTabProps : GameProps {
    var missionId: String
    var challengeSpec: ChallengeSpec
}

class PullRequestChallengeTab : GameUIComponent<PullRequestChallengeTabProps, State>() {
    override fun RBuilder.render() {
        unsafeH4(i("TLDR"))
        if (props.challengeSpec.tldr.isNotBlank()) {
            unsafeDiv(i(props.challengeSpec.tldr))
        } else {
            unsafeDiv(i("FinishChallengeInRepo", props.challengeSpec.spec))
        }

        br { }

        h4 {
            +i("Problem")
        }

        val readme = githubUrlToRawGithubUserContentUrl(
            props.challengeSpec.readme.ifBlank {
                "https://${props.challengeSpec.spec}/blob/master/README.md"
            }
        )
        if (readme.startsWith("https://raw.githubusercontent.com")) {
            child(LoadableMarkdown::class) {
                attrs.game = props.game
                attrs.link = readme
                attrs.allowRawHtml = true
            }
        } else {
            unsafeDiv(i(readme))
        }
    }
}
