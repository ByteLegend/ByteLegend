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
package com.bytelegend.app.client.api

import com.bytelegend.app.client.api.dsl.UnitFunction
import com.bytelegend.app.shared.PixelSize
import com.bytelegend.app.shared.objects.CoordinateAware
import com.bytelegend.app.shared.objects.GameMapAnimation
import com.bytelegend.app.shared.objects.GameMapDynamicSprite
import com.bytelegend.app.shared.objects.GameObject
import org.w3c.dom.CanvasRenderingContext2D

/**
 * Sprite is an object which can be drawn on the canvas.
 */
interface Sprite : GameObject {
    val gameScene: GameScene

    /**
     * Whether the sprite is inside the canvas or not.
     *
     * Return true if the sprite is completely out of canvas (no overlapping) so we don't need to paint it at all.
     */
    fun outOfCanvas(): Boolean = true

    fun draw(canvas: CanvasRenderingContext2D)
}

interface DynamicSprite : CoordinateAware, Sprite {
    var mapDynamicSprite: GameMapDynamicSprite

    var animation: Animation
    var onClickFunction: UnitFunction?
    var onCloseFunction: UnitFunction?

    val pixelSize: PixelSize
        get() = mapDynamicSprite.size * gameScene.map.tileSize
}

interface AnimationSprite : Sprite {
    val image: ImageResourceData
    val gameMapAnimation: GameMapAnimation
}
