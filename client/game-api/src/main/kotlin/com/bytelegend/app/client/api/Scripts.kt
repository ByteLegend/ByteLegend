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
package com.bytelegend.app.client.api

import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate

class SpeechBuilder {
    /**
     * The person who speaks.
     * For NPC/hero: the game object id
     */
    var speakerId: String? = null

    /**
     * The coordinate of the speaker.
     * For non-NPC/Hero player
     */
    var speakerCoordinate: PixelCoordinate? = null

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
            this.speakerId = objectId
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
