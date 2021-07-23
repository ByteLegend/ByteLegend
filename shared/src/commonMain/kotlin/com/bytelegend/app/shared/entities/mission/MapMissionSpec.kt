package com.bytelegend.app.shared.entities.mission

import com.bytelegend.app.shared.GridCoordinate

interface MapMissionSpec {
    val map: String
    val sprite: String

    /**
     * Coordinate on the map
     */
    val gridCoordinate: GridCoordinate

    /**
     * If true, this mission is displayed on roadmap.
     */
    val roadmap: Boolean
    val next: List<String>
}
