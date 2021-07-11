package com.bytelegend.app.client.api

import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate

class SpeechBuilder {
    /**
     * The person who speaks.
     */
    var objectId: String? = null

    /**
     * The coordinate of speaker. Either `objectId` or `objectCoordinate` needs to be configured.
     */
    var objectCoordinate: PixelCoordinate? = null

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

    /**
     * How long it takes to dismiss the speech. 0 means it must be dismissed by user clicking.
     */
    var dismissMs: Int = 0
}

interface ScriptsBuilder {
    // fun enableUserMouse()
    // fun disableUserMouse()
    fun speech(action: SpeechBuilder.() -> Unit)
    fun speech(
        objectId: String,
        contentHtmlId: String,
        args: Array<String> = emptyArray(),
        arrow: Boolean = true,
        dismissMs: Int = 0
    ) {
        speech {
            this.objectId = objectId
            this.contentHtmlId = contentHtmlId
            this.args = args
            this.arrow = arrow
            this.dismissMs = dismissMs
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
    fun startBeginnerGuide()
    fun putState(key: String, value: String = "1")
    fun removeState(key: String)

    /**
     * Remove item with an optional animation. The optional `targetCoordinate`
     * specify the destination coordinate on map.
     */
    fun removeItem(item: String, targetCoordinate: GridCoordinate? = null)
}
