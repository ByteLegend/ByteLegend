package com.bytelegend.app.shared.objects

// GameObject
//   |--- MapEntrance
//   |--- Sprite
//         |--- CharacterSprite
//         |--- GameText

interface GameObject : Lifecycle {
    val id: String
    val layer: Int
    val roles: Set<GameObjectRole>

    fun onClick() {}

    /**
     * Invoked when "touched" by another object.
     * For example, a player enter another scene by "touching" the entrance object,
     * or picking up gold by "touching" it.
     */
    fun onTouch(character: GameObject) {
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
    Clickable;

    companion object {
        fun fromIndex(index: Int): GameObjectRole = values()[index - 1]
    }
}


interface Lifecycle {
    fun init() {
    }

    fun close() {
    }
}

