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
package com.bytelegend.utils

import com.bytelegend.app.shared.PixelBlock
import com.bytelegend.app.shared.PixelCoordinate
import com.madgag.gif.fmsware.GifDecoder
import java.awt.image.BufferedImage
import java.io.File
import java.io.FileInputStream
import javax.imageio.ImageIO

/**
 * Extract all frames in a gif as a list of pngs. Transparent pixels are trimmed.
 */

fun main() {
    val inputGif = File(System.getProperty("inputGif")!!)
    val decoder = GifDecoder().apply { read(FileInputStream(inputGif)) }
    val opaqueRegion = findOpaqueRegion((0 until decoder.frameCount).map { decoder.getFrame(it) })
    println("Opaque region of $inputGif: $opaqueRegion")
    for (i in 0 until decoder.frameCount) {
        val frame = decoder.getFrame(i)
        ImageIO.write(
            frame.getSubimage(opaqueRegion.x, opaqueRegion.y, opaqueRegion.width, opaqueRegion.height),
            "PNG", File(inputGif.absolutePath.replace(".gif", ".$i.png"))
        )
    }
}

private fun findOpaqueRegion(frames: List<BufferedImage>): PixelBlock {
    frames.map { it.width }.toSet().apply { require(size == 1) }.first()
    frames.map { it.height }.toSet().apply { require(size == 1) }.first()
    val leftTopCorners = frames.map { findLeftTopCorner(it) }
    val rightBottomCorners = frames.map { findRightBottomCorner(it) }
    val leftmostColumn = leftTopCorners.minOf { it.x }
    val topmostRow = leftTopCorners.minOf { it.y }
    val rightmostColumn = rightBottomCorners.maxOf { it.x }
    val bottommostRow = rightBottomCorners.maxOf { it.y }
    return PixelBlock(leftmostColumn, topmostRow, (rightmostColumn - leftmostColumn), (bottommostRow - topmostRow))
}

private fun findLeftTopCorner(frame: BufferedImage): PixelCoordinate {
    val firstOpaqueRow = (0 until frame.height).firstOrNull { y ->
        (0 until frame.width).any { frame.getRGBA(it, y).a != 0 }
    } ?: throw IllegalStateException("All pixels are transparent?")
    val firstOpaqueColumn = (0 until frame.width).firstOrNull { x ->
        (0 until frame.height).any { frame.getRGBA(x, it).a != 0 }
    } ?: throw IllegalStateException("All pixels are transparent?")
    return PixelCoordinate(firstOpaqueColumn, firstOpaqueRow)
}

private fun findRightBottomCorner(frame: BufferedImage): PixelCoordinate {
    val lastOpaqueRow = (0 until frame.height).lastOrNull { y ->
        (0 until frame.width).any { frame.getRGBA(it, y).a != 0 }
    } ?: throw IllegalStateException("All pixels are transparent?")
    val lastOpaqueColumn = (0 until frame.width).lastOrNull { x ->
        (0 until frame.height).any { frame.getRGBA(x, it).a != 0 }
    } ?: throw IllegalStateException("All pixels are transparent?")
    return PixelCoordinate(lastOpaqueColumn, lastOpaqueRow)
}
