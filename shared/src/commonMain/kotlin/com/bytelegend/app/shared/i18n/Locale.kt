package com.bytelegend.app.shared.i18n

import kotlinx.serialization.Serializable

// A language
// https://en.wikipedia.org/wiki/ISO_639-1
enum class Language {
    EN,
    ZH;

    val code: String
        get() = toString().toLowerCase()
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
        get() = toString().toLowerCase()
}

enum class Locale(
    val displayName: String,
    val language: Language,
    val languageScript: LanguageScript?,
    val countryRegion: CountryRegion?
) {
    EN("English", Language.EN, null, null) {
        override fun accept(acceptLanguageHeader: String): Boolean = acceptLanguageHeader.toLowerCase().startsWith("en")
    },
    ZH_HANS("简体中文", Language.ZH, LanguageScript.HANS, CountryRegion.CN) {
        override fun accept(acceptLanguageHeader: String): Boolean = acceptLanguageHeader.toLowerCase() == "zh-cn" || acceptLanguageHeader.toLowerCase() == "zh"
    },
    ZH_HANT("繁體中文", Language.ZH, LanguageScript.HANT, CountryRegion.TW) {
        override fun accept(acceptLanguageHeader: String): Boolean = acceptLanguageHeader.toLowerCase() == "zh-tw"
    };

    abstract fun accept(acceptLanguageHeader: String): Boolean

    companion object {
        fun of(str: String?, default: Locale = EN): Locale = str?.toUpperCase()?.let { valueOf(it) } ?: default
    }

    fun toLowerCase() = toString().toLowerCase()
}

val DEFAULT_LOCALE = Locale.EN

@Serializable
data class LocalizedText(
    val id: String,
    val data: Map<Locale, String>
) {
    fun getText(locale: Locale) = data[locale] ?: data.getValue(DEFAULT_LOCALE)
}