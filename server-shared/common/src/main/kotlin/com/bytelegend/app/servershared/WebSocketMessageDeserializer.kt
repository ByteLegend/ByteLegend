/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
