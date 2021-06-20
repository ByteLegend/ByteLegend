package com.bytelegend.app.shared.util

import kotlin.js.Date

actual fun currentTimeMillis(): Long = Date().getTime().toLong()
