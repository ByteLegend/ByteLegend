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
import com.bytelegend.client.app.engine.Game
import com.bytelegend.client.app.external.LoadableMarkdown
import com.bytelegend.client.app.page.game
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.unsafeDiv
import com.bytelegend.client.app.ui.unsafeH4
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinext.js.jsObject
import kotlinx.html.classes
import react.RBuilder
import react.State
import react.dom.br
import react.dom.details
import react.dom.div
import react.dom.jsStyle

interface PullRequestChallengeTabProps : GameProps {
    var missionId: String
    var challengeSpec: ChallengeSpec
    var whitelist: List<String>
}

class PullRequestChallengeTab : GameUIComponent<PullRequestChallengeTabProps, State>() {
    override fun RBuilder.render() {
        if (props.challengeSpec.tldr.isNotBlank()) {
            unsafeH4(i("TLDR"))
            unsafeDiv(i(props.challengeSpec.tldr))
            br { }
        }

        child(WebEditor::class) {
            attrs.whitelist = props.whitelist
            attrs.game = props.game
            attrs.missionId = props.missionId
            attrs.challengeSpec = props.challengeSpec
        }
    }
}

fun RBuilder.renderReadme(game: Game, readmeOrLink: String) {
    if (readmeOrLink.startsWith("https://")) {
        div {
            attrs.classes = jsObjectBackedSetOf("mission-modal-challenge-readme")
            child(LoadableMarkdown::class) {
                attrs.game = game
                attrs.link = readmeOrLink
                attrs.allowRawHtml = true
                attrs.components = jsObject {
                    h1 = "h3"
                    h2 = "h4"
                    details = openDetailsWithDefaultLocale
                }
            }
        }
    } else {
        unsafeDiv(game.i(readmeOrLink)) {
            attrs.classes = jsObjectBackedSetOf("mission-modal-challenge-readme")
        }
    }
}

@Suppress("UnsafeCastFromDynamic")
private val openDetailsWithDefaultLocale: (dynamic, dynamic) -> dynamic = { node, _ ->
    val localeDisplayName = game.locale.displayName

    react.buildElements {
        details {
            var open = false
            node.children.forEach { it ->
                child(it)
            }
            node.node.children.forEach { it ->
                if (it.tagName == "summary" && it.children.length > 0 && it.children[0].value == localeDisplayName) {
                    open = true
                    attrs.open = true
                    return@forEach null
                }
                null
            }
            if (!open) {
                attrs.jsStyle {
                    display = "none"
                }
            }
        }
    }
}
