package com.bytelegend.app.shared

import com.bytelegend.app.shared.entities.Player
import com.bytelegend.app.shared.enums.ServerLocation
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.app.shared.i18n.LocalizedText
import kotlinx.serialization.Serializable

/**
 * Some data rendered to HTML page directly so it can be used
 * BEFORE AJAX resources are loaded. More specifically, everything needed for
 * loading page (before the main game page is rendered.).
 */
data class GameInitData(
    val initMapId: String,
    val onlineCount: Int,
    val serverLocation: ServerLocation,
    val rrbd: String,
    val enjoyProgrammingText: String,
    val player: Player,
    val maps: List<GameMapDefinition>,
    /**
     * Some localized texts which contain all language versions,
     * see LocaleSelectionDropdown.kt
     */
    val localizedTexts: List<LocalizedText>
) {
    fun resolve(path: String) = "$rrbd$path"

    private val idToLocalizedText = localizedTexts
        .map { it.id to it }
        .toMap()

    fun getI18nText(id: String, locale: Locale) =
        idToLocalizedText.getValue(id).getTextOrDefaultLocale(locale)
}

data class GameMapDefinition(
    val id: String,
    val children: List<GameMapDefinition>,
    /**
     * How many frames in background animation, this value is used to pre-render background.
     */
    val frames: Int
)