package com.bytelegend.app.serverapi.mock

import com.bytelegend.app.shared.Player
import kotlin.random.Random

val mockPlayer = Player().apply {
    id = "gh#ByteLegendBot"
    username = "ByteLegendBot"
    nickname = "ByteLegendBot"
    x = 6
    y = 90
    email = "bot@bytelegend.com"
    avatarUrl = "https://avatars0.githubusercontent.com/u/76512065?s=60&v=4"
    characterId = Random.nextInt(100) + 1
}

val anonymousPlayer = Player().apply {
    id = "anon#Anonymous"
    username = "anon#Anonymous"
}
