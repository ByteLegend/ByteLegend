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

val TRANSPARENT = RGBA(0, 0, 0, 0)
val RED = RGBA(255, 0, 0, 255)
val GREEN = RGBA(0, 255, 0, 255)
val BLUE = RGBA(0, 0, 255, 255)
val YELLOW = RGBA(255, 255, 0, 255)
val WHITE = RGBA(255, 255, 255, 255)

data class RGBA(
    val r: Int,
    val g: Int,
    val b: Int,
    val a: Int
) {
    fun isOpaque() = a > 5
}
