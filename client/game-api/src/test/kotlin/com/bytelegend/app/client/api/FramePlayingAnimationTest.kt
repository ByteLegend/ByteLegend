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
            arrayOf(
                AnimationFrame(1, 200),
                AnimationFrame(3, 200),
                AnimationFrame(5, 200)
            ),
            true
        )

        assertEquals(1, animation.getNextFrameIndex())
        delay(100)
        assertEquals(1, animation.getNextFrameIndex())
        delay(200)
        assertEquals(3, animation.getNextFrameIndex())
        delay(200)
        assertEquals(5, animation.getNextFrameIndex())
        delay(200)
        assertEquals(1, animation.getNextFrameIndex())
        delay(200)
        assertEquals(3, animation.getNextFrameIndex())
    }

    @Test
    fun nonRepeatingTest() = runBlockingTest {
        val animation = FramePlayingAnimation(
            arrayOf(
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
