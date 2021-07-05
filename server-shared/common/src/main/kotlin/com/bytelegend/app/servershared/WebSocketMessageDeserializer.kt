package com.bytelegend.app.servershared

import com.bytelegend.app.shared.protocol.SendMessage
import com.bytelegend.app.shared.protocol.SubscribeUnsubscribeMessage
import com.bytelegend.app.shared.protocol.WebSocketMessage
import com.bytelegend.app.shared.protocol.WebSocketMessageType
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.node.ArrayNode

class WebSocketMessageDeserializer : StdDeserializer<WebSocketMessage>(WebSocketMessage::class.java) {
    override fun deserialize(jp: JsonParser, ctx: DeserializationContext): WebSocketMessage {
        val node = jp.codec.readTree<JsonNode>(jp)
        return when (val type = WebSocketMessageType.valueOf(node.get("type").asText())) {
            WebSocketMessageType.SUBSCRIBE, WebSocketMessageType.UNSUBSCRIBE -> {
                SubscribeUnsubscribeMessage(type, node.get("event").asText())
            }

            WebSocketMessageType.SEND ->
                SendMessage(
                    node.get("name").asText(),
                    node.get("params").asList { asText() },
                    node.get("replyAddress").asText()
                )
            else -> throw IllegalArgumentException("Unsupported type: $type")
        }
    }

    private fun <T> JsonNode.asList(fn: JsonNode.() -> T): List<T> = (this as ArrayNode).map(fn)
}
