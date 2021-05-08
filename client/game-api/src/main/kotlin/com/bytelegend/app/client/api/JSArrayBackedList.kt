@file:Suppress("UnsafeCastFromDynamic")

package com.bytelegend.app.client.api

private fun <E> Collection<E>.toJSArray(): dynamic {
    val jsArray = js("[]")
    forEach {
        jsArray.push(it)
    }
    return jsArray
}

class JSArrayBackedList<E>(
    val delegate: dynamic
) : MutableList<E> {
    constructor() : this(delegate = js("[]"))
    constructor(collection: Collection<E>) : this(delegate = collection.toJSArray())

    override val size: Int
        get() = delegate.length

    override fun contains(element: E): Boolean {
        for (i in 0 until size) {
            if (delegate[i] == element) {
                return true
            }
        }
        return false
    }

    override fun containsAll(elements: Collection<E>): Boolean {
        elements.forEach {
            if (!contains(it)) {
                return false
            }
        }
        return true
    }

    override fun get(index: Int): E {
        return delegate[index]
    }

    override fun indexOf(element: E): Int {
        for (i in 0 until size) {
            if (delegate[i] == element) {
                return i
            }
        }
        return -1
    }

    override fun isEmpty(): Boolean {
        return delegate.length == 0
    }

    inner class JSArrayBackedIterator<E> : MutableIterator<E> {
        var currentIndex = 0
        override fun hasNext() = delegate.length > currentIndex

        override fun next(): E = delegate[currentIndex++]

        override fun remove() {
            removeAt(currentIndex)
        }
    }

    override fun iterator(): MutableIterator<E> {
        return JSArrayBackedIterator()
    }

    override fun lastIndexOf(element: E): Int {
        TODO("Not yet implemented")
    }

    override fun add(element: E): Boolean {
        delegate.push(element)
        return true
    }

    override fun add(index: Int, element: E) {
        TODO("Not yet implemented")
    }

    override fun addAll(index: Int, elements: Collection<E>): Boolean {
        TODO("Not yet implemented")
    }

    override fun addAll(elements: Collection<E>): Boolean {
        elements.forEach {
            delegate.push(it)
        }
        return true
    }

    override fun clear() {
        delegate.length = 0
    }

    override fun listIterator(): MutableListIterator<E> {
        TODO("Not yet implemented")
    }

    override fun listIterator(index: Int): MutableListIterator<E> {
        TODO("Not yet implemented")
    }

    override fun remove(element: E): Boolean {
        val index = indexOf(element)
        if (index > -1) {
            delegate.splice(index, 1)
            return true
        } else {
            return false
        }
    }

    override fun removeAll(elements: Collection<E>): Boolean {
        var result = false
        elements.forEach {
            if (remove(it)) {
                result = true
            }
        }
        return result
    }

    override fun removeAt(index: Int): E {
        val ret = delegate[index]
        delegate.splice(index, 1)
        return ret
    }

    override fun retainAll(elements: Collection<E>): Boolean {
        TODO("Not yet implemented")
    }

    override fun set(index: Int, element: E): E {
        val ret = delegate[index]
        delegate[index] = element
        return ret
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<E> {
        return JSArrayBackedList(delegate.slice(fromIndex, toIndex))
    }
}
