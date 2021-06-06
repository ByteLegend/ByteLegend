@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.bytelegend.app.client.api

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.promise
import kotlin.test.Test
import kotlin.test.assertEquals

fun runBlockingTest(block: suspend () -> Unit): dynamic = GlobalScope.promise { block() }

class FramePlayingAnimationTest {
    @Test
    fun repeatingTest() = runBlockingTest {
        val animation = FramePlayingAnimation(
            listOf(
                AnimationFrame(1, 100),
                AnimationFrame(3, 100),
                AnimationFrame(5, 100)
            ),
            true
        )

        assertEquals(1, animation.getNextFrameIndex())
        delay(50)
        assertEquals(1, animation.getNextFrameIndex())
        delay(100)
        assertEquals(3, animation.getNextFrameIndex())
        delay(100)
        assertEquals(5, animation.getNextFrameIndex())
        delay(100)
        assertEquals(1, animation.getNextFrameIndex())
        delay(100)
        assertEquals(3, animation.getNextFrameIndex())
    }

    @Test
    fun nonRepeatingTest() = runBlockingTest {
        val animation = FramePlayingAnimation(
            listOf(
                AnimationFrame(1, 100),
                AnimationFrame(3, 100),
                AnimationFrame(5, 100)
            ),
            false
        )

        assertEquals(1, animation.getNextFrameIndex())
        delay(50)
        assertEquals(1, animation.getNextFrameIndex())
        delay(100)
        assertEquals(3, animation.getNextFrameIndex())
        delay(100)
        assertEquals(5, animation.getNextFrameIndex())
        delay(100)
        assertEquals(5, animation.getNextFrameIndex())
        delay(1000)
        assertEquals(5, animation.getNextFrameIndex())
    }
}
