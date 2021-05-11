package com.bytelegend.app.shared.entities.mission

import com.bytelegend.app.shared.annotations.JsonCreator
import com.bytelegend.app.shared.i18n.Locale

/**
 * Represent the data to display at tutorial tab
 */
data class TutorialsSpec(
    val data: List<Tutorial>
)

data class Tutorial(
    val id: String,
    val title: String,
    val type: TutorialType,
    val href: String,
    val language: Any
) {
    val locales: List<Locale> = when (language) {
        is String -> listOf(Locale.of(language))
        is Array<*> -> language.map { Locale.of(it.toString()) }
        else -> throw IllegalStateException("Unsupported: $language")
    }
}

data class TutorialType constructor(val type: String, val subtype: String) {
    // For deserialization
    @JsonCreator
    constructor(typeAndSubType: String) :
        this(typeAndSubType.substringBefore("/"), typeAndSubType.substringAfter("/"))
}
