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
package com.bytelegend.app.client.utils

import com.bytelegend.app.client.api.AnimationFrame
import com.bytelegend.app.client.api.DynamicSprite
import com.bytelegend.app.client.api.EventListener
import com.bytelegend.app.client.api.FramePlayingAnimation
import com.bytelegend.app.client.api.Static
import com.bytelegend.app.client.api.closeMissionModalEvent
import com.bytelegend.app.client.api.openMissionModalEvent
import com.bytelegend.app.shared.objects.GameMapDynamicSprite
import kotlinx.browser.window
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

// TODO this should not in `game-api` module

fun DynamicSprite.configureBookSprite() {
    onClickFunction = {
        animation = FramePlayingAnimation(arrayOf(AnimationFrame(1, 500)), false)
        window.setTimeout({
            gameScene.gameRuntime.eventBus.emit(openMissionModalEvent(id), null)
        }, 500)
    }

    val onMissionModalClose: EventListener<Unit> = {
        window.setTimeout({ animation = Static }, 500)
    }

    gameScene.gameRuntime.eventBus.on(closeMissionModalEvent(id), onMissionModalClose)

    onCloseFunction = {
        gameScene.gameRuntime.eventBus.remove(closeMissionModalEvent(id), onMissionModalClose)
    }
}

suspend fun DynamicSprite.runAnimationWithFixedInterval(spriteId: String, ms: Long, startFrameIndex: Int = 0): Int {
    val sprite = gameScene.objects.getById<GameMapDynamicSprite>(spriteId)
    animation = sprite.animationWithFixedInterval(ms, startFrameIndex, false)
    val frameNumber = if (startFrameIndex == 0) sprite.frames[0][0].size else startFrameIndex
    /*
    Don't use delay here
    "ClassCastException: Illegal cast
    at Object.captureStack (webpack-internal:///./kotlin-dce-dev/kotlin.js:38600:15)
    at ClassCastException.Exception [as constructor] (webpack-internal:///./kotlin-dce-dev/kotlin.js:38933:14)
    at ClassCastException.RuntimeException [as constructor] (webpack-internal:///./kotlin-dce-dev/kotlin.js:38959:17)
    at RuntimeException_init_0 (webpack-internal:///./kotlin-dce-dev/kotlin.js:38970:24)
    at new ClassCastException (webpack-internal:///./kotlin-dce-dev/kotlin.js:39089:7)
    at throwCCE_0 (webpack-internal:///./kotlin-dce-dev/kotlin.js:42807:13)
    at get_DefaultDelay (webpack-internal:///./kotlin-dce-dev/kotlinx-coroutines-core.js:31316:84)
    at get_delay (webpack-internal:///./kotlin-dce-dev/kotlinx-coroutines-core.js:2393:136)
    at eval (webpack-internal:///./kotlin-dce-dev/kotlinx-coroutines-core.js:2378:5)
    at eval (webpack-internal:///./kotlin-dce-dev/kotlinx-coroutines-core.js:2345:3)"
     */
    return suspendCoroutine { continuation ->
        window.setTimeout({
            continuation.resume(0)
        }, (frameNumber * ms).toInt())
    }
}

/**
 * Create an animation with `ms` interval, and the beginning `startFrameIndex` frames. `frameNum=0` means all frames.
 */
fun GameMapDynamicSprite.animationWithFixedInterval(ms: Number, startFrameIndex: Int = 0, repetitive: Boolean = true): FramePlayingAnimation {
    val size = if (startFrameIndex == 0) frames[0][0].size else startFrameIndex
    val array = emptyArray<AnimationFrame>()
    repeat(size) {
        array[it] = (AnimationFrame(it, ms.toInt()))
    }
    return FramePlayingAnimation(array, repetitive)
}
