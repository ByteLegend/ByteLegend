package common.utils

import com.bytelegend.app.shared.BLOCKER
import com.bytelegend.app.shared.GridCoordinate
import kotlinext.js.jsObject
import kotlinx.browser.window

/**
 * This is a wrapper for https://github.com/bgrins/javascript-astar/blob/master/astar.js
 * with customizations:
 *
 * 1. Input/output type adaptor.
 * 2. The original implementation uses different X/Y definition. This was adjusted in astar.js.
 * 3. The original implementation return value doesn't contain start point, it's added in the return value.
 *
 * By default, 0 means wall point. For example:
 * Given:
 *
 *  arrayOf(
 *    arrayOf(1, 1, 1, 1),
 *    arrayOf(0, 1, 1, 0),
 *    arrayOf(0, 0, 1, 1)
 *  )
 *
 *  Start: (0,0)
 *  End: (3,2)
 *  Returns: [(0,0),(1,0),(1,1),(2,1),(2,2),(3,2)]
 *
 *  Empty return array means no reachable path.
 *
 *  You can override this behavior by passing "wallPredicate"
 */
@Suppress("UnsafeCastFromDynamic")
fun search(
    mapArray: Array<Array<Int>>,
    start: GridCoordinate,
    end: GridCoordinate,
    wallPredicate: (Int) -> Boolean
): List<GridCoordinate> {
    val array: Array<dynamic> = window.asDynamic().astar.search(
        mapArray, start.x, start.y, end.x, end.y,
        jsObject {
            this.wallPredicate = wallPredicate
        }
    )
    return if (array.isEmpty()) {
        emptyList()
    } else {
        mutableListOf(start).apply {
            array.forEach { add(GridCoordinate(it.x, it.y)) }
        }
    }
}

fun search(
    mapArray: Array<Array<Int>>,
    start: GridCoordinate,
    end: GridCoordinate
): List<GridCoordinate> {
    return search(mapArray, start, end) {
        it == BLOCKER
    }
}
