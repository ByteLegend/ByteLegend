package com.bytelegend.app.client.api

interface Animation {
    fun getNextFrameIndex(): Int
}

object Static : Animation {
    override fun getNextFrameIndex() = 0
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
    private val frames: List<AnimationFrame>,
    private val repeating: Boolean = true
) : Animation {
    private val startTime = Timestamp.now()

    // For example
    // `GameMapDynamicSprite.frames=[0,1,2,3]`, frames=[1,2,3]
    // When lastFramePlayingIndex=0, it refers to frames[0], i.e. GameMapDynamicSprite.frames[1]
    private val totalTime = frames.sumBy { it.durationMs }
    // Note this is the index of `frames`, not the original `GameMapDynamicSprite.frames`
    override fun getNextFrameIndex(): Int {
        var elapsedTimeMs = startTime.elapsedTimeMs()
        if (!repeating && elapsedTimeMs >= totalTime) {
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
                if (repeating) {
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
