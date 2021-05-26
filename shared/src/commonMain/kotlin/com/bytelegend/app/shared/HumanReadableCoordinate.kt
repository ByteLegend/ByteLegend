package com.bytelegend.app.shared

import kotlinx.serialization.Serializable
import kotlin.math.abs

/**
 * A coordinate system for human reading:
 *
 * (0,0) -> (A, 0)
 * (0,1) -> (A, 1)
 * (1,1) -> (B, 1)
 * (2,1) -> (C, 1)
 * (3,1) -> (D, 1)
 * ...
 * (26,26) -> (AA, 26)
 */
data class HumanReadableCoordinate(val x: Int, val y: Int) {
    constructor(viewportCoordinate: GridCoordinate) : this(viewportCoordinate.x, viewportCoordinate.y)

    val letters: String = toLetters(x)

    /**
     * 0 -> A
     * 1 -> B
     * ...
     * 25 -> Z
     * 26 -> AA
     * 27 -> AB
     * 28 -> AC
     * ...
     *
     */
    private fun toLetters(x: Int): String {
        val charArray = mutableListOf<Char>()
        var current = x
        while (true) {
            if (current >= 26) {
                charArray.add('A' + current % 26)
                current /= 26
            } else {
                if (charArray.isEmpty()) {
                    charArray.add('A' + current);
                } else {
                    charArray.add('A' + current - 1);
                }
                return charArray.reversed().joinToString("")
            }
        }
    }

    override fun toString(): String {
        return "(${letters}, $y)"
    }
}

@Serializable
data class PixelSize(val width: Int, val height: Int) {
    constructor(another: PixelSize) : this(another.width, another.height)

    operator fun div(ratio: Ratio) = PixelSize((width / ratio.horizontal).toInt(), (height / ratio.vertical).toInt())
    operator fun times(ratio: Ratio) = PixelSize((width * ratio.horizontal).toInt(), (height * ratio.vertical).toInt())

    operator fun times(gridCoordinate: GridCoordinate) = PixelCoordinate(width * gridCoordinate.x, height * gridCoordinate.y)
    operator fun times(gridSize: GridSize) = PixelSize(width * gridSize.width, height * gridSize.height)
    operator fun div(gridSize: GridSize) = PixelSize(width / gridSize.width, height / gridSize.height)
    operator fun div(pixelSize: PixelSize) = GridSize(width / pixelSize.width, height / pixelSize.height)
    operator fun times(n: Int): PixelSize = PixelSize(width * n, height * n)
    operator fun minus(offset: PixelSize) = PixelSize(width - offset.width, height - offset.height)
}

@Serializable
data class GridSize constructor(val width: Int, val height: Int) {
    constructor(another: GridSize) : this(another.width, another.height)

    operator fun times(pixelSize: PixelSize) = PixelSize(width * pixelSize.width, height * pixelSize.height)
}

@Serializable
data class PixelCoordinate(val x: Int, val y: Int) {
    constructor(list: List<Int>) : this(list[0], list[1])
    constructor(array: Array<Int>) : this(array[0], array[1])

    operator fun div(tileSize: PixelSize) = GridCoordinate(x / tileSize.width, y / tileSize.height)
    operator fun div(ratio: Ratio) = PixelCoordinate((x / ratio.horizontal).toInt(), (y / ratio.vertical).toInt())
    operator fun times(ratio: Ratio) = PixelCoordinate((x * ratio.horizontal).toInt(), (y * ratio.vertical).toInt())

    fun offset(offsetX: Int, offsetY: Int) = PixelCoordinate(x + offsetX, y + offsetY)
    operator fun minus(other: PixelCoordinate) = PixelCoordinate(x - other.x, y - other.y)
    operator fun plus(other: PixelCoordinate) = PixelCoordinate(x + other.x, y + other.y)
    fun manhattanDistanceTo(another: PixelCoordinate) = abs(x - another.x) + abs(y - another.y)

    fun alignToTileBorder(tileSize: PixelSize) = this / tileSize * tileSize
    fun compress(): List<Int> = listOf(x, y)
    operator fun plus(tileSize: PixelSize) = PixelCoordinate(x + tileSize.width, y + tileSize.height)
}

data class Ratio(val horizontal: Double, val vertical: Double)

/**
 * The coordinate in map grid, for example, the top-left tile has coordinate (0,0)
 */
@Serializable
data class GridCoordinate(val x: Int, val y: Int) {
    constructor(list: List<Int>) : this(list[0], list[1])
    constructor(array: Array<Int>) : this(array[0], array[1])

    operator fun times(tileSize: PixelSize) = PixelCoordinate(x * tileSize.width, y * tileSize.height)
    operator fun plus(other: GridCoordinate) = GridCoordinate(x + other.x, y + other.y)
    operator fun minus(other: GridCoordinate) = GridCoordinate(x - other.x, y - other.y)
    operator fun div(ratio: Ratio) = GridCoordinate((x / ratio.horizontal).toInt(), (y / ratio.vertical).toInt())

    fun manhattanDistanceTo(another: GridCoordinate) = abs(x - another.x) + abs(y - another.y)
    fun toCompressedList() = listOf(x, y)
    fun outOf(gridSize: GridSize) = x < 0 || y < 0 || x >= gridSize.width || y >= gridSize.height
    fun compress(): List<Int> = listOf(x, y)
    override fun toString() = "($x,$y)"
    fun toHumanReadableCoordinate() = HumanReadableCoordinate(this)
}