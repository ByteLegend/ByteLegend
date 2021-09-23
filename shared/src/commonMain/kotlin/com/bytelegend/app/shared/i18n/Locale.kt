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
package com.bytelegend.app.shared.i18n

const val PREFERRED_LOCALE_COOKIE_NAME = "PREFERRED_LOCALE"

// A language
// https://en.wikipedia.org/wiki/ISO_639-1
enum class Language {
    // English
    EN,

    // Chinese
    ZH,

    // Spanish
    ES,

    // Arabic
    AR,

    // Portuguese
    PT,

    // Indonesian
    ID,

    // French
    FR,

    // Japanese
    JA,

    // Russian
    RU,

    // Germany
    DE,

    // Korean
    KO,

    // Italian
    IT,

    // A special locale for filter
    ALL;

    val code: String
        get() = toString().lowercase()
}

// A writing system for language
// https://en.wikipedia.org/wiki/ISO_15924
enum class LanguageScript(
    val code: String
) {
    HANS("Hans"),
    HANT("Hant")
}

// Country(region)
// https://en.wikipedia.org/wiki/ISO_3166
enum class CountryRegion {
    CN,
    TW;

    val code: String
        get() = toString().lowercase()
}

enum class Locale(
    val displayName: String,
    val language: Language,
    // Whether the translation is performed by machine.
    // If so, we don't select the language by Accept-Language header automatically, users need to do that manually.
    val byMachine: Boolean,
    val languageScript: LanguageScript?,
    val countryRegion: CountryRegion?
) {
    // A special locale for filter
    ALL("All", Language.ALL, true, null, null),
    EN("English", Language.EN, false, null, null) {
        override fun accept(acceptLanguageHeader: String): Boolean = acceptLanguageHeader.lowercase().startsWith("en")
    },
    ZH_HANS("简体中文", Language.ZH, false, LanguageScript.HANS, CountryRegion.CN) {
        override fun accept(acceptLanguageHeader: String): Boolean = acceptLanguageHeader.lowercase() == "zh-cn" || acceptLanguageHeader.lowercase() == "zh"
    },
    ZH_HANT("繁體中文", Language.ZH, true, LanguageScript.HANT, CountryRegion.TW) {
        override fun accept(acceptLanguageHeader: String): Boolean = acceptLanguageHeader.lowercase() == "zh-tw"
    },

    // Spanish
    ES("Español", Language.ES, true, null, null),

    // Arabic
    AR("العربية", Language.AR, true, null, null),

    // Portuguese
    PT("Português", Language.PT, true, null, null),

    // Indonesian
    ID("Bahasa Indonesia", Language.ID, true, null, null),

    // French
    FR("Français", Language.FR, true, null, null),

    // Japanese
    JA("日本語", Language.JA, true, null, null),

    // Russian
    RU("Русский", Language.RU, true, null, null),

    // Germany
    DE("Deutsch", Language.DE, true, null, null),

    // Korean
    KO("한국어", Language.KO, true, null, null),

    // Italian
    IT("Italiano", Language.IT, true, null, null),

    ;

    open fun accept(acceptLanguageHeader: String): Boolean {
        if (byMachine) {
            return false
        } else {
            throw IllegalStateException("This should be overridden by subclasses!")
        }
    }

    val javascriptLocale: String
        get() = if (countryRegion == null) this.lowercase()
        // zh-CN, zh-TW
        else "${language.code.lowercase()}-$countryRegion"

    val googleTranslateApiCode: String
        get() =
            if (countryRegion == null) this.lowercase()
            // zh-CN, zh-TW
            else "${language.code.lowercase()}-$countryRegion"

    companion object {
        fun of(str: String?, default: Locale = DEFAULT_LOCALE): Locale =
            try {
                str?.uppercase()?.let { valueOf(it) } ?: default
            } catch (e: Throwable) {
                default
            }

        fun fromList(str: String): List<Locale> {
            return if (str.contains("ALL")) {
                values().toList()
            } else {
                str.split(",").map { of(it) }.distinct()
            }
        }
    }

    fun lowercase() = toString().lowercase()
}

fun List<Locale>.joinToString() = joinToString(",")

val DEFAULT_LOCALE = Locale.EN

data class LocalizedText(
    val id: String,
    // string-key is front-end friendly
    val data: Map<String, String>,
    val format: LocalizedTextFormat = LocalizedTextFormat.TEXT
) {
    fun validate() {
        if (data.values.any { it.contains("</") } && format == LocalizedTextFormat.TEXT) {
            throw IllegalStateException("$id should have HTML format!")
        }
    }

    fun getTextOrDefaultLocale(locale: Locale) = data[locale.toString()] ?: data.getValue(DEFAULT_LOCALE.toString())
    fun getTextOrNull(locale: Locale) = data[locale.toString()]
}

enum class LocalizedTextFormat {
    TEXT,
    HTML,
    MARKDOWN
}

fun String.render(vararg args: String): String {
    var ret = this
    args.withIndex().forEach {
        ret = ret.replace("{${it.index}}", it.value)
    }
    return ret
}
