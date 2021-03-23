package com.bytelegend.app.client.api

class SpeechBuilder {
    /**
     * The person who speaks.
     */
    var objectId: String? = null

    /**
     * The content HTML id.
     */
    var contentHtmlId: String? = null

    /**
     * The arguments to render the content
     */
    var args: Array<String> = emptyArray()

    /**
     * Whether display a tiny shaking arrow at the end of the speech
     * aka. next speech indicator
     */
    var arrow: Boolean = false
}

interface ScriptsBuilder {
    fun enableUserMouse()
    fun speech(action: SpeechBuilder.() -> Unit)
    fun speech(objectId: String, contentHtmlId: String, args: Array<String> = emptyArray(), arrow: Boolean = true) {
        speech {
            this.objectId = objectId
            this.contentHtmlId = contentHtmlId
            this.args = args
            this.arrow = arrow
        }
    }

    fun starFly(fromObjectId: String)
//    fun characterMove(characterId: String, destMapCoordinate: GridCoordinate)
    fun disableUserMouse()
    fun fadeIn()
}
