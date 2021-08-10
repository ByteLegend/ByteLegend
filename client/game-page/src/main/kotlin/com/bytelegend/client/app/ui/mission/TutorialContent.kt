package com.bytelegend.client.app.ui.mission

import com.bytelegend.app.shared.entities.mission.Tutorial
import com.bytelegend.client.app.external.ReactPlayer
import com.bytelegend.client.app.ui.GameProps
import com.bytelegend.client.utils.jsObjectBackedSetOf
import kotlinx.html.classes
import react.RBuilder
import react.RComponent
import react.RState
import react.dom.iframe

interface TutorialContentProps : GameProps {
    var tutorial: Tutorial
}

interface TutorialContentState : RState

class TutorialContent : RComponent<TutorialContentProps, TutorialContentState>() {
    override fun TutorialContentState.init() {
    }

    override fun RBuilder.render() {
        when (props.tutorial.type) {
            "video/youtube" -> youtubePlayer()
            "video/bilibili" -> bilibiliPlayer()
            else -> unsupportedType()
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
            attrs.`class` = "tutorial-video-player"
            attrs.url = props.tutorial.href
            attrs.controls = true
            attrs.width = "90%"
            attrs.height = "90%"
        }
    }
}
