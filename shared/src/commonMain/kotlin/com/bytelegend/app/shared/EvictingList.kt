package com.bytelegend.app.shared

/**
 * A deque which holds at most maxSize elements. The excessive elements will
 * be removed from head automatically when adding.
 */
class EvictingList<E>(
    private val maxSize: Int,
    private val delegate: ArrayDeque<E> = ArrayDeque(maxSize)
) : MutableList<E> by delegate {
    init {
        require(maxSize > 0)
    }

    override fun add(element: E): Boolean {
        val ret = delegate.add(element)
        while (delegate.size > maxSize) {
            delegate.removeFirst()
        }
        return ret
    }

    override fun addAll(elements: Collection<E>): Boolean {
        val ret = delegate.addAll(elements)
        while (delegate.size > maxSize) {
            delegate.removeFirst()
        }
        return ret
    }

    override fun addAll(index: Int, elements: Collection<E>): Boolean {
        val ret = delegate.addAll(index, elements)
        while (delegate.size > maxSize) {
            delegate.removeFirst()
        }
        return ret
    }

    /**
     * Returns the first element, or throws [NoSuchElementException] if this deque is empty.
     */
    fun first(): E = delegate.first()

    /**
     * Returns the first element, or `null` if this deque is empty.
     */
    fun firstOrNull(): E? = delegate.firstOrNull()

    /**
     * Returns the last element, or throws [NoSuchElementException] if this deque is empty.
     */
    fun last(): E = delegate.last()

    /**
     * Returns the last element, or `null` if this deque is empty.
     */
    fun lastOrNull(): E? = delegate.lastOrNull()

    /**
     * Prepends the specified [element] to this deque.
     */
    fun addLast(element: E) = delegate.addLast(element)

    /**
     * Removes the last element from this deque and returns that removed element, or throws [NoSuchElementException] if this deque is empty.
     */
    fun removeLast(): E = delegate.removeLast()

    /**
     * Removes the last element from this deque and returns that removed element, or returns `null` if this deque is empty.
     */
    fun removeLastOrNull(): E? = delegate.removeLastOrNull()
}
