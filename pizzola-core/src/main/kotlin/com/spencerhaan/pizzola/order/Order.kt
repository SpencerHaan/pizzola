package com.spencerhaan.pizzola.order

import java.time.Instant
import java.util.*


class Order(
        val eventId : String,
        val userId : String,
        private var initialNumberOfSlices : Long
) {

    val id : String = UUID.randomUUID().toString()

    var numberOfSlices : Long
        get() = initialNumberOfSlices
        set(value) {
            initialNumberOfSlices = value
            lastModifyDate = Instant.now()
        }

    val createDate : Instant = Instant.now()

    var lastModifyDate : Instant = Instant.now()
        private set
}