package com.bytelegend.app.shared.protocol

import com.bytelegend.app.shared.annotations.JsonIgnore

data class PlayerSpeechEventData(
    val playerId: String,
    val sentenceId: String
) {
    @JsonIgnore
    fun isOnymous(): Boolean = !playerId.startsWith("anon#")
}
