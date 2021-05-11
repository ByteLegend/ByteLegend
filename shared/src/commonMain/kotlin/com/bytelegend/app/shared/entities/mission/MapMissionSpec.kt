package com.bytelegend.app.shared.entities.mission

import com.bytelegend.app.shared.GridCoordinate

interface MapMissionSpec {
    val map: String
    val sprite: String

    /**
     * Top-left corner of the mission
     */
    val point: GridCoordinate

    /**
     * If true, this mission is displayed on roadmap.
     */
    val roadmap: Boolean
    val children: List<String>
    val next: String?
}