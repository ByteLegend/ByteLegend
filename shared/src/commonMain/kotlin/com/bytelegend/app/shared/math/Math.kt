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
package com.bytelegend.app.shared.math

import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelBlock
import com.bytelegend.app.shared.PixelCoordinate
import com.bytelegend.app.shared.PixelSize

/**
 * Well... I'm not sure this is an accurate name.
 *
 * The intention of this function is to calculate "how many buckets of capacity N
 * we need to put M items", see the code.
 */
fun capacityDivide(itemNumber: Int, bucketCapacity: Int): Int {
    require(itemNumber >= 0 && bucketCapacity > 0) {
        "Invalid: $itemNumber $bucketCapacity"
    }
    return if (itemNumber % bucketCapacity == 0) {
        itemNumber / bucketCapacity
    } else {
        itemNumber / bucketCapacity + 1
    }
}

fun imageBlockOnCanvas(
    coordinateInMap: PixelCoordinate,
    canvasCoordinateInMap: PixelCoordinate,
    tileSize: PixelSize
): PixelBlock {
    return PixelBlock(
        coordinateInMap.x - canvasCoordinateInMap.x,
        coordinateInMap.y - canvasCoordinateInMap.y,
        tileSize.width,
        tileSize.height
    )
}

fun PixelBlock.outOfCanvas(canvasPixelSize: PixelSize): Boolean {
    return x > canvasPixelSize.width ||
        y > canvasPixelSize.height ||
        x + width < 0 ||
        y + height < 0
}

fun limitIn(value: Int) = limitIn(value, value)

fun limitIn(value: Int, rightBorder: Int) = when {
    value < 0 -> 0
    value >= rightBorder -> rightBorder
    else -> value
}

// In certain cases, the canvas might be out of map, or leave large black gap when user keep increasing browser window
fun adjustCanvasCoordinateIfNecessary(
    gameMapPixelSize: PixelSize,
    tileSize: PixelSize,
    expectedCanvasCoordinateInMap: PixelCoordinate,
    canvasPixelSize: PixelSize
): PixelCoordinate {
    val newCanvasCoordinateInMapX = when {
        expectedCanvasCoordinateInMap.x < 0 -> 0
        expectedCanvasCoordinateInMap.x > gameMapPixelSize.width - canvasPixelSize.width -> limitIn(gameMapPixelSize.width - canvasPixelSize.width)
        else -> expectedCanvasCoordinateInMap.x
    }

    val newCanvasCoordinateInMapY = when {
        expectedCanvasCoordinateInMap.y < 0 -> 0
        expectedCanvasCoordinateInMap.y > gameMapPixelSize.height - canvasPixelSize.height -> limitIn(gameMapPixelSize.height - canvasPixelSize.height)
        else -> expectedCanvasCoordinateInMap.y
    }
    return PixelCoordinate(newCanvasCoordinateInMapX, newCanvasCoordinateInMapY).alignToTileBorder(tileSize)
}

fun calculateCanvasCoordinateInMapFromCenterPoint(
    tileSize: PixelSize,
    initMapCenterPoint: GridCoordinate,
    canvasPixelSize: PixelSize,
): PixelCoordinate {
    return PixelCoordinate(
        initMapCenterPoint.x * tileSize.width - canvasPixelSize.width / 2,
        initMapCenterPoint.y * tileSize.height - canvasPixelSize.height / 2
    )
}
