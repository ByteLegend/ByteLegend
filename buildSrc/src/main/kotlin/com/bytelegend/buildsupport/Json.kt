package com.bytelegend.buildsupport

import com.fasterxml.jackson.databind.ObjectMapper

val objectMapper = ObjectMapper()
fun toJsonString(obj: Any) = objectMapper.writeValueAsString(obj)

