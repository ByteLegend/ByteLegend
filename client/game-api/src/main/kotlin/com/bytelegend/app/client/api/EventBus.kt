package com.bytelegend.app.client.api

import kotlinx.browser.window
import org.w3c.dom.CustomEvent
import org.w3c.dom.events.Event

interface EventBus {
    fun <T> on(eventType: String, listener: EventListener<T>)
    fun <T> emit(eventType: String, event: T)
    fun <T> remove(eventType: String, listener: EventListener<T>)
}

/**
 * An EventBus bus implementation based on w3c EventTarget and `window` instance
 */
object WindowBasedEventBus : EventBus {
    private val listenerAdapters: MutableMap<EventListener<*>, W3cEventListener<*>> = mutableMapOf()

    override fun <T> on(eventType: String, listener: EventListener<T>) {
        W3cEventListener(listener).apply {
            listenerAdapters[listener] = this
            window.addEventListener(eventType, this)
        }
    }

    override fun <T> emit(eventType: String, event: T) {
        window.dispatchEvent(
            CustomEvent(eventType).apply {
                initCustomEvent(eventType, bubbles = true, cancelable = true, detail = event)
            }
        )
    }

    override fun <T> remove(eventType: String, listener: EventListener<T>) {
        window.removeEventListener(eventType, listenerAdapters.remove(listener))
    }
}

typealias EventListener<T> = (T) -> Unit

class W3cEventListener<T>(private val delegate: EventListener<T>) : org.w3c.dom.events.EventListener {
    @Suppress("UNCHECKED_CAST")
    override fun handleEvent(event: Event) {
        val customEvent = event as CustomEvent
        delegate(customEvent.detail as T)
    }
}

interface EventBusAware {
    var eventBus: EventBus
}
