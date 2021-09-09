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
import com.bytelegend.app.client.ui.bootstrap.BootstrapSpinner
import com.bytelegend.app.shared.entities.mission.Tutorial
import com.bytelegend.client.app.external.ReactMarkdown
import com.bytelegend.client.app.external.ReactPlayer
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.app.web.checkStatusCode
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.html.classes
import react.RBuilder
import react.RComponent
import react.State
import react.dom.iframe
import react.setState

interface TutorialContentProps : GameProps {
    var tutorial: Tutorial
}

interface TutorialContentState : State {
    var ghMarkdown: String?
}

class TutorialContent : RComponent<TutorialContentProps, TutorialContentState>() {
    private var loading = false
    override fun TutorialContentState.init() {
        ghMarkdown = null
    }

    override fun RBuilder.render() {
        when (props.tutorial.type) {
            "video/youtube" -> youtubePlayer()
            "video/bilibili" -> bilibiliPlayer()
            "text/markdown" -> markdown()
            else -> unsupportedType()
        }
    }

    /**
     * GitHub doesn't support iframe, so we have to use raw.githubusercontent.com
     *
     */
    private fun rebuildUrl(url: String): String {
        val replaceToRawGithubUserContent = githubUrlToRawGithubUserContentUrl(url)

        return if (props.game.gfw) {
            replaceToRawGithubUserContent.replace("https://raw.githubusercontent.com/", "/ghraw/")
        } else {
            replaceToRawGithubUserContent
        }
    }

    private fun RBuilder.markdown() {
        if (state.ghMarkdown == null) {
            BootstrapSpinner {
                attrs.animation = "border"
            }
            if (!loading) {
                GlobalScope.launch {
                    loading = true
                    val content = window.fetch(rebuildUrl(props.tutorial.href))
                        .await()
                        .checkStatusCode()
                        .text()
                        .await()
                    loading = false
                    setState {
                        ghMarkdown = content
                    }
                }
            }
        } else {
            ReactMarkdown {
                +state.ghMarkdown!!
                attrs.transformImageUri = { src: String, alt: Any, title: Any ->
                    rebuildUrl(src)
                }
            }
        }
    }

    private fun RBuilder.unsupportedType() {
        +"Unsupported tutorial type: ${props.tutorial.type}"
    }

    private fun RBuilder.bilibiliPlayer() {
        // https://www.bilibili.com/video/BV1JJ41197UK -> BV1JJ41197UK
        val url = props.tutorial.href
        val bvid = when {
            url.contains("www.bilibili.com") -> url.substringAfter("video/")
            url.contains("player.bilibili.com") -> url.substringAfter("bvid=").substringBefore('&')
            else -> ""
        }
        if (bvid.isBlank()) {
            +"Unrecognized url: $url"
        } else {
            iframe {
                attrs.classes = jsObjectBackedSetOf("tutorial-video-player")
                attrs.src = "https://player.bilibili.com/player.html?bvid=$bvid&high_quality=1&t=0"
                attrs.width = "95%"
                attrs.height = "95%"
                attrs["border"] = "0"
                attrs["frameBorder"] = "no"
                attrs["framespacing"] = "0"
                attrs["allowFullScreen"] = true
            }
        }
    }

    private fun RBuilder.youtubePlayer() {
        ReactPlayer {
            attrs.className = "tutorial-video-player"
            attrs.url = props.tutorial.href
            attrs.controls = true
            attrs.width = "90%"
            attrs.height = "90%"
        }
    }

    override fun UNSAFE_componentWillReceiveProps(nextProps: TutorialContentProps) {
        if (nextProps.tutorial.id != props.tutorial.id) {
            loading = false
            setState {
                ghMarkdown = null
            }
        }
    }
}
