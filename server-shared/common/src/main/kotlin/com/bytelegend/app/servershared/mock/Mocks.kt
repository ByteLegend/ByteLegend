package com.bytelegend.app.servershared.mock

import com.bytelegend.app.shared.entities.Player
import kotlin.random.Random

const val JAVA_MAP_ID = "JavaIsland"

val mockPlayer = Player().apply {
    id = "gh#ByteLegendBot"
    username = "ByteLegendBot"
    nickname = "ByteLegendBot"
    emails.add("bot@bytelegend.com")
    avatarUrl = "https://avatars0.githubusercontent.com/u/76512065?s=60&v=4"
    characterId = Random.nextInt(100) + 1
    map = JAVA_MAP_ID
    x = 6
    y = 90
}

val anonymousPlayer = Player().apply {
    id = "anon#Anonymous"
    username = "Anonymous"
    nickname = "Anonymous"
    map = JAVA_MAP_ID
}
