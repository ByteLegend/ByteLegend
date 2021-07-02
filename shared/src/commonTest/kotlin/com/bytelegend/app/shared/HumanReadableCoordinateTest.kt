package com.bytelegend.app.shared

import kotlin.test.Test
import kotlin.test.assertEquals

class HumanReadableCoordinateTest {
    @Test
    fun test() {
        testOne(0, 0, "(A, 0)")
        testOne(0, 1, "(A, 1)")
        testOne(1, 1, "(B, 1)")
        testOne(3, 1, "(D, 1)")
        testOne(25, 1, "(Z, 1)")
        testOne(26, 1, "(AA, 1)")
        testOne(26, 26, "(AA, 26)")
        testOne(52, 26, "(BA, 26)")
        testOne(676, 676, "(AAA, 676)")
    }

    private fun testOne(x: Int, y: Int, humanReadableValue: String) {
        assertEquals(humanReadableValue, HumanReadableCoordinate(x, y).toString())
    }
}
