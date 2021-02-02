package com.bytelegend.app.shared

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