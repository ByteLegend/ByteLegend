package com.bytelegend.app.serverapi.mock

import com.bytelegend.app.shared.entities.Player
import kotlin.random.Random

val mockPlayer = Player().apply {
    id = "gh#ByteLegendBot"
    username = "ByteLegendBot"
    nickname = "ByteLegendBot"
    email = "bot@bytelegend.com"
    avatarUrl = "https://avatars0.githubusercontent.com/u/76512065?s=60&v=4"
    characterId = Random.nextInt(100) + 1
}
