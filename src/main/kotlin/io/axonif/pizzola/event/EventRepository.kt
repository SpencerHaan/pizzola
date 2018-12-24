package io.axonif.pizzola.event

class EventRepository {
    private val events = mutableMapOf<String, Event>()

    fun get(id : String) : Event {
        return events[id] ?: throw Exception("Event entity not found")
    }

    fun add(event : Event) {
        events[event.id] = event
    }

    fun remove(event : Event) {
        events.remove(event.id)
    }
}