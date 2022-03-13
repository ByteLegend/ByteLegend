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

import com.bytelegend.app.shared.entities.Player
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.app.shared.i18n.LocalizedText

/**
 * Some data rendered to HTML page directly so it can be used
 * BEFORE AJAX resources are loaded. More specifically, everything needed for
 * loading page (before the main game page is rendered.).
 */
data class GameInitData(
    val initMapId: String,
    val onlineCount: Int,
    val rrbd: String,
    val enjoyProgrammingText: String,
    val player: Player,
    val maps: List<GameMapDefinition>,
    /**
     * Some localized texts which contain all language versions,
     */
    val localizedTexts: List<LocalizedText>,
    val joinQQGroupSecret: String
) {
    fun resolve(path: String) = "$rrbd$path"

    private val idToLocalizedText = localizedTexts.associateBy { it.id }

    fun getI18nText(id: String, locale: Locale) =
        idToLocalizedText.getValue(id).getTextOrDefaultLocale(locale)
}

data class GameMapDefinition(
    val id: String,
    /**
     * How many frames in background animation, this value is used to pre-render background.
     */
    val frames: Int,
    val children: List<GameMapDefinition> = emptyList(),
    val roadmap: Boolean = true
)
