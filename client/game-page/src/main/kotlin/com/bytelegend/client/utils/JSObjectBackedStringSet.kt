/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bytelegend.app.client.utils

import kotlinx.js.Object
import kotlinx.js.jso

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
    constructor() : this(jso())

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

    fun toJSArray(): dynamic {
        return Object.keys(delegate)
    }
}
