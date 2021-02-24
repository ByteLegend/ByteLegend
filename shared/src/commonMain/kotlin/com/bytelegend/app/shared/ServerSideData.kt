package com.bytelegend.app.shared

import com.bytelegend.app.shared.i18n.Locale
import kotlinx.serialization.Serializable

/**
 * Some data rendered to HTML page directly so it can be used
 * before AJAX resources are loaded.
 */
@Serializable
data class ServerSideData(
    val serverLocation: ServerLocation,
    val RRBD: String,
    val locale: Locale,
    val enjoyProgrammingText: String,
    val mapId: String,
    val player: Player,
    val playerCoordinate: GridCoordinate?
) {
    fun resolve(path: String) = "$RRBD$path"
}

@Serializable
data class GameMapDefinition(
    val id: String,
    val submaps: List<GameMapDefinition>,
    /**
     * How many frames in background animation, this value is used to pre-render background.
     */
    val frameNumber: Int
)