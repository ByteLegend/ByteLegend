package com.bytelegend.app.shared.entities.mission

import com.bytelegend.app.shared.annotations.JsonCreator
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
    val type: String,
    val href: String,
    val languages: List<Locale>
) {
    @JsonCreator
    constructor(
        id: String,
        title: String,
        type: String,
        href: String,
        language: Locale?,
        languages: List<Locale>?
    ) : this(id, title, type, href, languages ?: listOf(language!!))
}
