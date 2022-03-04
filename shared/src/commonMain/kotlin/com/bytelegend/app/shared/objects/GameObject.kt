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
package com.bytelegend.app.shared.objects

import com.bytelegend.app.shared.Direction
import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate

interface CoordinateMutable : CoordinateAware {
    override var gridCoordinate: GridCoordinate
    override var pixelCoordinate: PixelCoordinate
}

interface CoordinateAware : GridCoordinateAware {
    val pixelCoordinate: PixelCoordinate

    fun directionTo(other: CoordinateAware): Direction {
        val otherCoordinate = other.gridCoordinate
        return when {
            gridCoordinate.x == otherCoordinate.x && gridCoordinate.y < otherCoordinate.y -> Direction.DOWN
            gridCoordinate.x == otherCoordinate.x && gridCoordinate.y > otherCoordinate.y -> Direction.UP
            gridCoordinate.y == otherCoordinate.y && gridCoordinate.x < otherCoordinate.x -> Direction.RIGHT
            gridCoordinate.y == otherCoordinate.y && gridCoordinate.x > otherCoordinate.x -> Direction.LEFT
            gridCoordinate.x < otherCoordinate.x && gridCoordinate.y < otherCoordinate.y -> Direction.DOWN
            gridCoordinate.x > otherCoordinate.x && gridCoordinate.y < otherCoordinate.y -> Direction.DOWN
            gridCoordinate.x < otherCoordinate.x && gridCoordinate.y > otherCoordinate.y -> Direction.UP
            gridCoordinate.x > otherCoordinate.x && gridCoordinate.y > otherCoordinate.y -> Direction.UP
            else -> Direction.DOWN
        }
    }
}

interface GridCoordinateAware {
    val gridCoordinate: GridCoordinate
}

interface GameObject {
    val id: String
    val layer: Int

    /**
     * This is a label-like mechanism to avoid extremely slow type checking in Kotlin JS.
     * Simple test shows that Kotlin `is` check is 10x slower than `roles.contains`.
     *
     * For example, for a class `Sub: Base(), MyInterface {}`, instead of using `if(obj is MyInterface)`
     * you can say `if(obj.roles.contains("MyInterface")`.
     *
     * https://youtrack.jetbrains.com/issue/KT-24784
     * https://youtrack.jetbrains.com/issue/KT-42743
     */
    val roles: Set<String>

    /**
     * Respond to the click event.
     */
    fun onClick() {}

    /**
     * Invoked when "touched" by another object.
     * For example, a player enter another scene by "touching" the entrance object,
     * or picking up gold by "touching" it.
     */
    fun onTouch(obj: GameObject) {
    }
}

/**
 * A GameObject can have multiple roles, so that we can fetch all objects
 * of same roles, for example, the hero object can have "Hero", "Player", "Character" roles.
 *
 * Roles are very similar to interfaces, but with better performance.
 */
enum class GameObjectRole {
    MapText,
    MapRegion,
    MapPoint,
    MapCurve,
    Sprite,
    Animation,
    Hero,
    Player,
    Character,
    NPC,
    MapEntrance,
    HasBouncingTitle,
    CoordinateAware,
    Clickable,
    Mission,
    UnableToBeSetAsDestination;
}
