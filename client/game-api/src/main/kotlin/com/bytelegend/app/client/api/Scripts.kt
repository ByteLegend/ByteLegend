package com.bytelegend.app.client.api

import kotlinx.css.div
import react.RBuilder


class SpeechBuilder {
    /**
     * The person who speaks.
     */
    var objectId: String? = null

    /**
     * The content HTML id.
     */
    var contentHtmlId: String? = null
}

interface ScriptsBuilder {
    fun enableUserMouse()
    fun speech(action: SpeechBuilder.() -> Unit)
    fun disableUserMouse()
}