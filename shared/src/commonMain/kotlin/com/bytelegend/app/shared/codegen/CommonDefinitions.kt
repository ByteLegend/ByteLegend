package com.bytelegend.app.shared.codegen

interface ToKotlinCode {
    fun toKotlinCode(): String
}

fun List<ToKotlinCode>.toKotlinCode() = joinToString(",\n") { it.toKotlinCode() }