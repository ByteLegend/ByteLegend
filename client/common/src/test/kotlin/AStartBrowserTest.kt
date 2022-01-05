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
import com.bytelegend.app.client.misc.search
import com.bytelegend.app.shared.GridCoordinate
import kotlin.test.Test
import kotlin.test.assertEquals

class AStartBrowserTest {

    /**
     * Run multiple times to avoid state leftover
     */
    @Test
    fun test1() {
        astarTestWhenWallIs0()
        astarTestWhenWallIs0()
        astarTestWhenWallIs0()
        astarTestWhenWallIs1()
        astarTestWhenWallIs1()
        astarTestWhenWallIs1()
        astarTestWhenWallIsEven()
        astarTestWhenWallIsEven()
        astarTestWhenWallIsEven()
    }

    @Test
    fun test2() {
        astarTestWhenWallIs0()
        astarTestWhenWallIs1()
        astarTestWhenWallIsEven()
        astarTestWhenWallIs0()
        astarTestWhenWallIsEven()
        astarTestWhenWallIs1()
        astarTestWhenWallIsEven()
        astarTestWhenWallIs0()
        astarTestWhenWallIs1()
    }

    private fun astarTestWhenWallIs0() {
        val array = arrayOf(
            arrayOf(1, 1, 1, 1),
            arrayOf(0, 1, 1, 0),
            arrayOf(0, 0, 1, 1),
        )

        assertEquals(
            listOf(
                GridCoordinate(0, 0),
                GridCoordinate(1, 0),
                GridCoordinate(1, 1),
                GridCoordinate(2, 1),
                GridCoordinate(2, 2),
                GridCoordinate(3, 2),
            ),
            search(array, GridCoordinate(0, 0), GridCoordinate(3, 2)) { it == 0 }
        )
    }

    private fun astarTestWhenWallIsEven() {
        val array = arrayOf(
            arrayOf(1, 3, 5, 7),
            arrayOf(0, 1, 3, 2),
            arrayOf(4, 6, 1, 3),
        )

        assertEquals(
            listOf(
                GridCoordinate(0, 0),
                GridCoordinate(1, 0),
                GridCoordinate(1, 1),
                GridCoordinate(2, 1),
                GridCoordinate(2, 2),
                GridCoordinate(3, 2),
            ),
            search(array, GridCoordinate(0, 0), GridCoordinate(3, 2)) { it % 2 == 0 }
        )
    }

    private fun astarTestWhenWallIs1() {
        val array = arrayOf(
            arrayOf(1, 1, 1, 1),
            arrayOf(0, 1, 1, 0),
            arrayOf(0, 0, 1, 1),
        )

        assertEquals(
            listOf(
                GridCoordinate(0, 1),
                GridCoordinate(0, 2),
                GridCoordinate(1, 2)
            ),
            search(array, GridCoordinate(0, 1), GridCoordinate(1, 2)) { it == 1 }
        )
    }
}
