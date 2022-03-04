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
import com.bytelegend.app.client.api.StaticFrame
import com.bytelegend.app.client.api.closeMissionModalEvent
import com.bytelegend.app.client.api.openMissionModalEvent
import com.bytelegend.app.shared.objects.GameMapDynamicSprite
import kotlinx.browser.window

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

/**
 * Create an animation with `ms` interval, beginning at `startFrameIndexInclusive`th frame, ending at `endFrameIndexExclusive-1`th frame.
 */
fun GameMapDynamicSprite.animationWithFixedInterval(
    ms: Number,
    startFrameIndexInclusive: Int = 0,
    endFrameIndexExclusive: Int = -1, /* -1 means all frames */
    repetitive: Boolean = true
): FramePlayingAnimation {
    val endFrameIndex = if (endFrameIndexExclusive == -1) frames[0][0].size else endFrameIndexExclusive
    require(startFrameIndexInclusive < endFrameIndex)
    val frames = (startFrameIndexInclusive until endFrameIndex).map {
        AnimationFrame(it, ms.toInt())
    }.toTypedArray()
    return FramePlayingAnimation(frames, repetitive)
}

fun DynamicSprite.setAnimation(frameIntervalMs: Int, range: AnimationFrameRange) {
    if (range.startIndexInclusive == range.endIndexExclusive) {
        animation = StaticFrame(range.startIndexInclusive)
    } else {
        animation = mapDynamicSprite.animationWithFixedInterval(frameIntervalMs, range.startIndexInclusive, range.endIndexExclusive)
    }
}

/**
 * Represents a list of animation frames.
 *
 * If startIndexInclusive == endIndexExclusive, it means a static frame (no animation).
 * Otherwise, it means the animation should be created from index in [startIndexInclusive, endIndexExclusive)
 */
data class AnimationFrameRange(
    val startIndexInclusive: Int = 0,
    val endIndexExclusive: Int = -1 /* -1 means all frames */
)
