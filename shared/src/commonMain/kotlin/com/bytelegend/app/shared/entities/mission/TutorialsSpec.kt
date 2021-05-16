package com.bytelegend.app.shared.entities.mission

import com.bytelegend.app.shared.annotations.JsonCreator
import com.bytelegend.app.shared.annotations.JsonValue
import com.bytelegend.app.shared.i18n.Locale

/**
 * Represent the data to display at tutorial tab
 */
data class TutorialsSpec(
    val data: List<Tutorial>
)

data class Tutorial constructor(
    val id: String,
    val title: String,
    val type: TutorialType,
    val href: String,
    val languages: List<Locale>
) {
    @JsonCreator
    constructor(
        id: String,
        title: String,
        type: TutorialType,
        href: String,
        language: Locale?,
        languages: List<Locale>?
    ) : this(id, title, type, href, languages ?: listOf(language!!))
}

data class TutorialType constructor(val type: String, val subtype: String) {
    // For deserialization
    @JsonCreator
    constructor(typeAndSubType: String) :
        this(typeAndSubType.substringBefore("/"), typeAndSubType.substringAfter("/"))

    @JsonValue
    override fun toString(): String {
        return "$type/$subtype"
    }
}
