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
