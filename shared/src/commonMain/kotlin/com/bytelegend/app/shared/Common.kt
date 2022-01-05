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
package com.bytelegend.app.shared

import com.bytelegend.app.shared.annotations.JsonIgnore
import kotlinx.serialization.Serializable

@Serializable
data class PixelBlock(
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int
) {
    constructor(coordinate: PixelCoordinate, tileSize: PixelSize) :
        this(coordinate.x, coordinate.y, tileSize.width, tileSize.height)

    constructor(coordinate: GridCoordinate, tileSize: PixelSize) :
        this(coordinate.x * tileSize.width, coordinate.y * tileSize.height, tileSize.width, tileSize.height)

    @get:JsonIgnore
    val coordinate: PixelCoordinate = PixelCoordinate(x, y)

    @get:JsonIgnore
    val size: PixelSize = PixelSize(width, height)
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
