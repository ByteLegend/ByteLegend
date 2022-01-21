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

interface Animation {
    val isStatic: Boolean
    fun getNextFrameIndex(): Int
}

object Static : Animation {
    override val isStatic: Boolean = true
    override fun getNextFrameIndex() = 0
}

class StaticFrame(val frameIndex: Int) : Animation {
    override val isStatic: Boolean = true
    override fun getNextFrameIndex(): Int = frameIndex
}

class AnimationFrame(
    // 0~GameMapDynamicSprite.frames.size-1
    val index: Int,
    // ms
    val durationMs: Int
)

/**
 * Play a series of frame repetitively or one-time.
 */
class FramePlayingAnimation(
    private val frames: Array<AnimationFrame>,
    private val repetitive: Boolean = true
) : Animation {
    override val isStatic: Boolean = false
    private val startTime = Timestamp.now()

    // For example
    // `GameMapDynamicSprite.frames=[0,1,2,3]`, frames=[1,2,3]
    // When lastFramePlayingIndex=0, it refers to frames[0], i.e. GameMapDynamicSprite.frames[1]
    private val totalTime = frames.sumOf { it.durationMs }

    // Note this is the index of `frames`, not the original `GameMapDynamicSprite.frames`
    override fun getNextFrameIndex(): Int {
        var elapsedTimeMs = startTime.elapsedTimeMs()
        if (!repetitive && elapsedTimeMs >= totalTime) {
            return frames.last().index
        }
        elapsedTimeMs %= totalTime
        var currentIndex = 0
        while (true) {
            if (elapsedTimeMs < frames[currentIndex].durationMs) {
                return frames[currentIndex].index
            }
            elapsedTimeMs -= frames[currentIndex].durationMs
            if (currentIndex == frames.size - 1) {
                if (repetitive) {
                    currentIndex = 0
                } else {
                    return frames[currentIndex].index
                }
            } else {
                currentIndex++
            }
        }
    }
}
