package com.bytelegend.app.shared.objects

import com.bytelegend.app.shared.GridCoordinate
import com.bytelegend.app.shared.PixelCoordinate

interface CoordinateMutable : CoordinateAware {
    override var gridCoordinate: GridCoordinate
    override var pixelCoordinate: PixelCoordinate
}

interface CoordinateAware : GridCoordinateAware {
    val pixelCoordinate: PixelCoordinate
}

interface GridCoordinateAware {
    val gridCoordinate: GridCoordinate
}

interface GameObject {
    val id: String
    val layer: Int
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
    Hero,
    Player,
    Character,
    NPC,
    MapEntrance,
    CoordinateAware,
    Clickable,
    Mission,
    UnableToBeSetAsDestination;
}
