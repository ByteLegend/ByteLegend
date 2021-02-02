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
