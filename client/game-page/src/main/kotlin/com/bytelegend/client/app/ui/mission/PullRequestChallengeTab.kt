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
import com.bytelegend.client.app.page.game
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.unsafeDiv
import com.bytelegend.client.app.ui.unsafeH4
import kotlinext.js.jsObject
import react.RBuilder
import react.State
import react.dom.br
import react.dom.details
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
                "https://${props.challengeSpec.spec}/blob/main/README.md"
            }
        )
        if (readme.startsWith("https://raw.githubusercontent.com")) {
            child(LoadableMarkdown::class) {
                attrs.game = props.game
                attrs.link = readme
                attrs.allowRawHtml = true
                attrs.components = jsObject {
                    h1 = "h3"
                    h2 = "h4"
                    details = openDetailsWithDefaultLocale
                }
            }
        } else {
            unsafeDiv(i(readme))
        }
    }
}

@Suppress("UnsafeCastFromDynamic")
private val openDetailsWithDefaultLocale: (dynamic, dynamic) -> dynamic = { node, props ->
    val localeDisplayName = game.locale.displayName
    react.buildElements {
        details {
            node.children.forEach { it ->
                child(it)
            }
            node.node.children.forEach { it ->
                if (it.tagName == "summary" && it.children.length > 0 && it.children[0].value == localeDisplayName) {
                    attrs.open = true
                    return@forEach null
                }
                null
            }
        }
    }
}
