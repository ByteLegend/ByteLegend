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
import com.bytelegend.client.app.page.game
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.ui.GameUIComponent
import com.bytelegend.client.app.ui.jsStyle
import com.bytelegend.client.app.ui.unsafeDiv
import com.bytelegend.client.app.ui.unsafeH4
import kotlinext.js.jso
import react.ChildrenBuilder
import react.FC
import react.Fragment
import react.Props
import react.State
import react.create
import react.dom.html.ReactHTML.br
import react.dom.html.ReactHTML.details
import react.react

interface PullRequestChallengeTabProps : GameProps {
    var missionId: String
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
            missionId = props.missionId
            challengeSpec = props.challengeSpec
        })
    }
}

fun ChildrenBuilder.renderReadme(game: Game, readmeOrLink: String) {
//    if (readmeOrLink.startsWith("https://")) {
//        div {
//            className = "mission-modal-challenge-readme"
//            child(LoadableMarkdown::class.react, jso {
//                this.game = game
//                link = readmeOrLink
//                allowRawHtml = true
//                components = jso {
//                    h1 = "h3"
//                    h2 = "h4"
//                    details = openDetailsWithDefaultLocale
//                }
//            })
//        }
//    } else {
//        unsafeDiv(game.i(readmeOrLink)) {
//            className = "mission-modal-challenge-readme"
//        }
//    }
}

@Suppress("UnsafeCastFromDynamic")
private val openDetailsWithDefaultLocale: (dynamic, dynamic) -> dynamic = { node, _ ->
    val localeDisplayName = game.locale.displayName

    FC<Props> {
        details {
            var open = false
            node.children.forEach { it ->
                child(it)
            }
            node.node.children.forEach { it ->
                if (it.tagName == "summary" && it.children.length > 0 && it.children[0].value == localeDisplayName) {
                    open = true
                    return@forEach null
                }
                null
            }
            if (!open) {
                jsStyle {
                    display = "none"
                }
            }
        }
    }
}
