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
import com.bytelegend.app.shared.EvictingList
import kotlin.test.Test
import kotlin.test.assertEquals

class EvictingListTest {
    private val evictingList = EvictingList<Int>(5)

    @Test
    fun evictWhenAddAndFull() {
        (1..10).forEach { evictingList.add(it) }
        assertEquals((6..10).toList(), evictingList.toList())
        assertEquals(6, evictingList.first())
        assertEquals(6, evictingList.firstOrNull())
        assertEquals(10, evictingList.last())
        assertEquals(10, evictingList.lastOrNull())

        evictingList.clear()
        assertEquals(null, evictingList.lastOrNull())
        assertEquals(null, evictingList.firstOrNull())
    }

    @Test
    fun evictWhenAddAllFullAndFull() {
        evictingList.addAll((1..3))
        evictingList.addAll((1..3))
        assertEquals(listOf(2, 3, 1, 2, 3), evictingList.toList())
    }
}
