package com.bytelegend.utils

import com.bytelegend.app.shared.ConstantPoolEntry
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import kotlinx.serialization.json.Json.Default.encodeToString

object JacksonConstantPoolSerializer : StdSerializer<ConstantPoolEntry>(ConstantPoolEntry::class.java) {
    override fun serialize(value: ConstantPoolEntry, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeStartObject()
        gen.writeNumberField("t", value.type.index)
        gen.writeRaw(",\"v\":" + encodeToString(value.type.serializer, value.value))
        gen.writeEndObject()
    }
}
