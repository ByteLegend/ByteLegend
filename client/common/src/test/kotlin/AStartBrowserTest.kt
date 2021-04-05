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
