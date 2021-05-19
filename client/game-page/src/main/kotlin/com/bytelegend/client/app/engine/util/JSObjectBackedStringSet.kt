package com.bytelegend.client.app.engine.util

import kotlinext.js.Object
import kotlinext.js.jsObject

fun jsObjectBackedSetOf(vararg elements: Any): MutableSet<String> {
    val ret = JSObjectBackedStringSet()
    elements.forEach {
        ret.add(it.toString())
    }
    return ret
}

class JSObjectBackedStringSet(
    private val delegate: dynamic
) : MutableSet<String> {
    constructor() : this(jsObject())

    override val size: Int
        get() = Object.keys(delegate).size

    override fun contains(element: String) = delegate[element] != null

    override fun containsAll(elements: Collection<String>): Boolean {
        for (element in elements) {
            if (!contains(element)) {
                return false
            }
        }
        return true
    }

    override fun isEmpty() = size == 0

    override fun iterator(): MutableIterator<String> = JSObjectBackedKeyIterator(delegate)

    override fun add(element: String): Boolean {
        val ret = !contains(element)
        delegate[element] = 1
        return ret
    }

    override fun addAll(elements: Collection<String>): Boolean {
        var modified = false
        for (element in elements) {
            if (add(element)) {
                modified = true
            }
        }
        return modified
    }

    override fun clear() {
        val iterator = iterator()
        while (iterator.hasNext()) {
            iterator.remove()
        }
    }

    override fun remove(element: String): Boolean {
        val ret = contains(element)
        delete(delegate[element])
        return ret
    }

    override fun removeAll(elements: Collection<String>): Boolean {
        var modified = false
        for (element in elements) {
            if (remove(element)) {
                modified = true
            }
        }
        return modified
    }

    override fun retainAll(elements: Collection<String>): Boolean {
        var modified = false
        val it: MutableIterator<String> = iterator()
        while (it.hasNext()) {
            if (!elements.contains(it.next())) {
                it.remove()
                modified = true
            }
        }
        return modified
    }
}
