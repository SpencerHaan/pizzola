package com.spencerhaan.pizzola.event

import java.time.Instant

data class EventInfo (
    val id : String,
    val startDate : Instant?,
    val endDate : Instant?
)

data class PlaceEventResponse (
    val id : String
)

data class GetEventResponse (
    val event: EventInfo
)

data class StartEventResponse (
    val id : String,
    val startDate: Instant?
)

data class EndEventResponse (
        val id : String,
        val endDate: Instant?
)
