package com.bytelegend.app.shared

import com.bytelegend.app.shared.codegen.ToKotlinCode
import kotlinx.serialization.Serializable

@Serializable
data class PixelBlock(
    var x: Int,
    var y: Int,
    var width: Int,
    var height: Int
) {
    constructor(coordinate: PixelCoordinate, tileSize: PixelSize) :
        this(coordinate.x, coordinate.y, tileSize.width, tileSize.height)

    constructor(coordinate: GridCoordinate, tileSize: PixelSize) :
        this(coordinate.x * tileSize.width, coordinate.y * tileSize.height, tileSize.width, tileSize.height)
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (other == null || this::class != other::class) return false
//
//        other as PixelBlock
//
//        if (x != other.x) return false
//        if (y != other.y) return false
//        if (width != other.width) return false
//        if (height != other.height) return false
//
//        return true
//    }
//
//    override fun hashCode(): Int {
//        var result = x
//        result = 31 * result + y
//        result = 31 * result + width
//        result = 31 * result + height
//        return result
//    }
}

interface SpriteSheet {
    val srcSheetId: String
    val frames: Array<PixelBlock>
}

data class CharacterAnimationInfo(
    val sheetId: String,
    val characterId: String,
    val byteSize: Int,
    val upBlocks: List<PixelBlock>,
    val downBlocks: List<PixelBlock>,
    val leftBlocks: List<PixelBlock>,
    val rightBlocks: List<PixelBlock>
) : ToKotlinCode {
    private fun PixelBlock.toKotlinCode() = "PixelBlock($x, $y, $width, $height)"
    private fun List<PixelBlock>.toKotlinCode() = "listOf(${joinToString(",") { it.toKotlinCode() }})"

    override fun toKotlinCode() =
        """
           CharacterAnimationInfo(
                "$sheetId",
                "$characterId",
                $byteSize,
                ${upBlocks.toKotlinCode()},
                ${downBlocks.toKotlinCode()},
                ${leftBlocks.toKotlinCode()},
                ${rightBlocks.toKotlinCode()}
           )
        """.trimIndent()
}

data class TileSheetInfo(
    val id: String,
    val byteSize: Int,
    val tileSize: PixelSize,
    val gridSize: GridSize
) {
    val pixelSize: PixelSize = PixelSize(tileSize.width * gridSize.width, tileSize.height * gridSize.height)

    fun toKotlinCode(): String {
        return """TileSheetInfo("$id", $byteSize, PixelSize(${tileSize.width}, ${tileSize.height}), GridSize(${gridSize.width}, ${gridSize.height}))"""
    }
}

/**
 * A marker annotation which indicates the annotated class is used for data exchange
 * between backend and frontend. The annotated class must:
 *
 * 1. All fields are "var" with initializers.
 * 2. Use Array instead of List because there are issues in JavaScript-JSON.parsed List instances.
 * 3. No enums. JavaScript can't serialize/deserialize correctly.
 *
 * TODO: Add a task to verify all such classes
 */
annotation class DataExchange(val desc: String = "")

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    LEFT_UP,
    LEFT_DOWN,
    RIGHT_UP,
    RIGHT_DOWN,
    NONE
}

inline fun <T, reified R> Array<Array<T>>.mapWithIndex(fn: (T, GridCoordinate) -> R): Array<Array<R>> {
    val tmp: MutableList<Array<R>> = mutableListOf()

    withIndex().forEach { row ->
        tmp.add(row.value.withIndex().map {
            fn(it.value, GridCoordinate(it.index, row.index))
        }.toTypedArray())
    }
    return tmp.toTypedArray()
}

inline fun <T, reified R> List<List<T>>.mapToArrayWithIndex(fn: (T, GridCoordinate) -> R): Array<Array<R>> {
    val tmp: MutableList<Array<R>> = mutableListOf()

    withIndex().forEach { row ->
        tmp.add(row.value.withIndex().map {
            fn(it.value, GridCoordinate(it.index, row.index))
        }.toTypedArray())
    }
    return tmp.toTypedArray()
}

inline fun <T, reified R> List<List<T>>.mapToArray(fn: (T) -> R): Array<Array<R>> {
    val tmp: MutableList<Array<R>> = mutableListOf()

    forEach { row ->
        tmp.add(row.map(fn).toTypedArray())
    }
    return tmp.toTypedArray()
}

inline fun <T, reified R> Array<Array<T>>.map(fn: (T) -> R): Array<Array<R>> {
    val tmp: MutableList<Array<R>> = mutableListOf()

    forEach { row ->
        tmp.add(row.map(fn).toTypedArray())
    }
    return tmp.toTypedArray()
}

inline fun <T, reified R> Array<Array<T>>.mapToList(fn: (T) -> R): List<List<R>> {
    val tmp: MutableList<List<R>> = mutableListOf()

    forEach { row ->
        tmp.add(row.map(fn))
    }
    return tmp
}

inline fun <T, reified R> List<List<T>>.map(fn: (T) -> R): List<List<R>> {
    val tmp: MutableList<List<R>> = mutableListOf()

    forEach { row ->
        tmp.add(row.map(fn).toList())
    }
    return tmp.toList()
}

fun <T> Array<Array<T>>.toListList(): List<List<T>> {
    return this.toList().map { it.toList() }
}

