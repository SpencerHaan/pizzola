package com.spencerhaan.pizzola.event

import java.time.Instant
import java.util.*

class Event {

    val id : String = UUID.randomUUID().toString()

    var startDate : Instant? = null
        private set

    var endDate : Instant? = null
        private set

    fun start() {
        if (startDate != null) {
            throw Exception("event already started")
        }
        startDate = Instant.now()
    }

    fun end() {
        if (startDate == null) {
            throw Exception("event was not started")
        } else if (endDate != null) {
            throw Exception("event already ended")
        }
        endDate = Instant.now()
    }
}