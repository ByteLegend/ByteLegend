package com.bytelegend.app.shared.util

inline fun <T> Collection<T>.ifNotEmpty(fn: Collection<T>.() -> Unit) {
    if (isNotEmpty()) {
        this.fn()
    }
}

inline fun <K, V> Map<K, V>.ifNotEmpty(fn: Map<K, V>.() -> Unit) {
    if (isNotEmpty()) {
        this.fn()
    }
}