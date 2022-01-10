package com.bytelegend.utils

import com.bytelegend.app.shared.RGBA
import java.awt.image.BufferedImage

fun BufferedImage.getRGBA(x: Int, y: Int): RGBA {
    return getRGB(x, y).let {
        if (type == BufferedImage.TYPE_BYTE_BINARY)
            RGBA(
                ((it and 0xff0000) ushr 16),
                ((it and 0xff00) ushr 8),
                (it and 0xff),
                0xff
            )
        else
            RGBA(
                ((it and 0xff0000) ushr 16),
                ((it and 0xff00) ushr 8),
                (it and 0xff),
                ((it.toLong() and 0xff000000) ushr 24).toInt()
            )
    }
}
