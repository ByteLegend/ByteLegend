package com.bytelegend.app.shared.entities

import com.bytelegend.app.shared.annotations.JsonCreator
import com.bytelegend.app.shared.i18n.Locale
import com.bytelegend.app.shared.objects.GameMapObject
import com.bytelegend.app.shared.objects.GameMapObjectType

interface Metadata {
    /**
     * The global unique id, usually meaningful phrase for human,
     * e.g. "JavaVersionChallenge" or "remember-brave-people"
     */
    val id: String

    /**
     * The i18n id of the title to display.
     * Some implementations may support empty string as "no title".
     */
    val title: String
}

data class Checkpoint(
    override val id: String,
    override val title: String = "",
    /**
     * The sprite id to display.
     */
    val sprite: String = "",
    val missions: List<Mission> = emptyList(),
    val tutorials: List<Tutorial> = emptyList(),
    val discussions: String = "none"
) : Metadata, GameMapObject {
    override val layer: Int = 0
    override val type: GameMapObjectType = GameMapObjectType.GameMapCheckpoint
}

data class Tutorial(
    override val id: String,
    override val title: String,
    val type: TutorialType,
    val href: String,
    val language: Any
) : Metadata {
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

    companion object {
        fun of(str: String) {

        }
    }
}
