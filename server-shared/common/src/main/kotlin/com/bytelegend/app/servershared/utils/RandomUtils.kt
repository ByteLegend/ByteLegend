package com.bytelegend.app.servershared.utils

import java.util.UUID

fun randomString() = UUID.randomUUID().toString().replace("-", "")
