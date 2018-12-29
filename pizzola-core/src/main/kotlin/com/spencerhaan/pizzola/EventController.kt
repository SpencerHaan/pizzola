package com.spencerhaan.pizzola

import com.spencerhaan.pizzola.event.*
import com.spencerhaan.pizzola.order.OrderController
import com.spencerhaan.pizzola.wallet.WalletService
import javax.ws.rs.*

@Path("events")
class EventController {

    companion object {

        private val eventRepository : EventRepository = EventRepository()
    }

    @GET
    @Path("{id}")
    fun getEvent(@PathParam("id") id : String) : GetEventResponse {
        val event : Event = eventRepository.get(id)
        return GetEventResponse(EventInfo(event.id, event.startDate, event.endDate))
    }

    @POST
    fun create() : PlaceEventResponse {
        val event = Event()
        eventRepository.add(event)
        return PlaceEventResponse(event.id)
    }

    @DELETE
    @Path("{id}")
    fun cancel(@PathParam("id") id : String) {
        val event : Event = eventRepository.get(id)
        eventRepository.remove(event)
    }

    @POST
    @Path("{id}/start")
    fun start(@PathParam("id") id : String) : StartEventResponse {
        val event : Event = eventRepository.get(id)
        event.start()
        return StartEventResponse(event.id, event.startDate)
    }

    @POST
    @Path("{id}/end")
    fun end(@PathParam("id") id : String) : EndEventResponse {
        val event : Event = eventRepository.get(id)
        event.end()
        OrderController.repository.findByEvent(id).forEach { order ->
            WalletService.service.withdraw(order.userId, DEFAULTS.PRICE_PER_SLICE.toBigDecimal(), "charged for event $id")
        }
        return EndEventResponse(event.id, event.startDate)
    }
}