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

import com.bytelegend.app.client.api.dsl.EMPTY_FUNCTION
import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate
import org.w3c.dom.CanvasRenderingContext2D

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

    var showYesNo: Boolean = false
    var onYes: UnitFunction = EMPTY_FUNCTION
}

class AnimationBuilder {
    var animationId: String? = null
    var audioId: String? = null
    var initDelayMs: Long = 0
    var frameDurationMs: Long = 200
    var loop: Int = 1
    var onStart: () -> Unit = {}
    var onDraw: AnimationSprite.(CanvasRenderingContext2D, Int) -> Unit = { _, _ -> }
    var onEnd: () -> Unit = {}
}

interface ScriptsBuilder {
    fun speech(action: SpeechBuilder.() -> Unit)
    fun speech(
        objectId: String,
        contentHtmlId: String,
        args: Array<String> = emptyArray(),
        arrow: Boolean = true,
        dismissMs: Int = 0,
        showYesNo: Boolean = false,
        onYes: UnitFunction = EMPTY_FUNCTION,
    ) {
        speech {
            this.speakerId = objectId
            this.contentHtmlId = contentHtmlId
            this.args = args
            this.arrow = arrow
            this.dismissMs = dismissMs
            this.showYesNo = showYesNo
            this.onYes = onYes
        }
    }

    fun characterMove(characterId: String, destMapCoordinate: GridCoordinate, onArrival: UnitFunction = EMPTY_FUNCTION)

    /**
     * Player enters a vehicle and moves to another map. Upon arrival, the scene is automatically switched to target map.
     */
    fun characterEnterVehicleAndMoveToMap(characterId: String, vehicleSpriteId: String, movingPath: List<GridCoordinate>, destMap: String)
    fun startBeginnerGuide()
    fun putState(key: String, value: String = "1")

    /**
     * Enter another scene.
     * After this script, the coin is deducted from player account and player is placed in entrance of target map,
     * but the scene doesn't load immediately to allow some animations.
     *
     * If anything fails (usually insufficient coin), `onFail` callback is called.
     */
    fun enterScene(targetMapId: String, onSuccess: UnitFunction, onFail: UnitFunction)

    /**
     * Remove item with an optional animation. The optional `targetCoordinate`
     * specify the destination coordinate on map.
     */
    fun useItem(item: String, targetCoordinate: GridCoordinate? = null)
    fun animation(action: AnimationBuilder.() -> Unit)
    fun animation(vararg builders: AnimationBuilder)
    fun sleep(ms: Long)
    fun runSuspend(fn: suspend () -> Unit)
}
