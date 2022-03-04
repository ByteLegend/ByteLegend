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

import com.bytelegend.client.utils.toChallengeAnswer
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class JsonMapperTest {
    @Test
    fun testToMissionAnswer() {
        val answer1 = toChallengeAnswer(
            JSON.parse(
                """
            {"star": 0, "answer": "ThisIsAnswer", "accomplished": true, "time": "2021-10-25T10:11:17.323Z", "data": {}}
        """.trimIndent()
            )
        )
        assertEquals(0, answer1.star)
        assertEquals("ThisIsAnswer", answer1.answer)
        assertTrue(answer1.accomplished)
        assertEquals("2021-10-25T10:11:17.323Z", answer1.time)
    }
}
