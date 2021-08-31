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
package com.bytelegend.client.utils

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class JsonMapperTest {
    @Test
    fun testToMissionAnswer() {
        val answer1 = toPlayerChallengeAnswer(
            JSON.parse(
                """
            {"star": 0, "answer": "ThisIsAnswer", "accomplished": true, "createdAt":0, "data": {}}
        """.trimIndent()
            )
        )
        assertEquals(0, answer1.star)
        assertEquals("ThisIsAnswer", answer1.answer)
        assertTrue(answer1.accomplished)
        assertEquals("0", answer1.createdAt.toString())
        assertTrue(answer1.data.isEmpty())

        val answer2 = toPlayerChallengeAnswer(
            JSON.parse(
                """
            {"star": 0, "answer": "ThisIsAnswer", "accomplished": false, "createdAt":1, "data": {"a": "1"}}
        """.trimIndent()
            )
        )
        assertEquals(0, answer2.star)
        assertEquals("ThisIsAnswer", answer2.answer)
        assertFalse(answer2.accomplished)
        assertEquals("1", answer2.createdAt.toString())
        assertEquals("1", answer2.data.getValue("a"))
    }
}
