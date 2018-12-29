package com.spencerhaan.pizzola.order

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

data class OrderInfo(
        val id : String,
        val eventId : String,
        val userId : String,
        val numberOfSlices: Long,
        val createDate: Instant,
        val lastModifyDate: Instant
)

data class ListOrdersResponse(
        val orders : List<OrderInfo>
)

data class PlaceOrderRequest
@JsonCreator constructor(
        @JsonProperty("eventId") val eventId : String,
        @JsonProperty("userId") val userId : String,
        @JsonProperty("numberOfSlices") val numberOfSlices : Long
)

data class PlaceOrderResponse(
        val id : String
)

data class MyOrderResponse(
        val order : OrderInfo
)