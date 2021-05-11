package com.bytelegend.app.client.api

import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.shared.GridCoordinate

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
    // fun enableUserMouse()
    // fun disableUserMouse()
    fun speech(action: SpeechBuilder.() -> Unit)
    fun speech(objectId: String, contentHtmlId: String, args: Array<String> = emptyArray(), arrow: Boolean = true) {
        speech {
            this.objectId = objectId
            this.contentHtmlId = contentHtmlId
            this.args = args
            this.arrow = arrow
        }
    }

    /**
     * Play an animation on object with objectId, with frames and intervals.
     *
     * For example, this shows a shaking animation:
     *
     * playAnimate("CoffeeMachine", [1,2,1,2], 100)
     */
    fun playAnimate(objectId: String, frames: List<Int>, intervalMs: Int)
    fun characterMove(characterId: String, destMapCoordinate: GridCoordinate, callback: UnitFunction = {})
    fun onComplete(action: UnitFunction)
    fun startBeginnerGuide()
    fun putState(key: String, value: String = "1")
    fun removeState(key: String)
    fun removeItem(item: String)
    fun addItem(item: String)
}
