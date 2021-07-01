package com.bytelegend.app.shared

val TRANSPARENT = RGBA(0, 0, 0, 0)
val RED = RGBA(255, 0, 0, 255)
val GREEN = RGBA(0, 255, 0, 255)
val BLUE = RGBA(0, 0, 255, 255)
val YELLOW = RGBA(255, 255, 0, 255)
val WHITE = RGBA(255, 255, 255, 255)

data class RGBA(
    val r: Int,
    val g: Int,
    val b: Int,
    val a: Int
)
