package com.bytelegend.app.shared

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
}

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

inline fun <T, reified R> Array<Array<Array<Array<T>>>>.mapToList4(fn: (T) -> R): List<List<List<List<R>>>> {
    return mapToList { it.mapToList(fn) }
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

