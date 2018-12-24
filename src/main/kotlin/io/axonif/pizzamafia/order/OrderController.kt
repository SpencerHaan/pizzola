package io.axonif.pizzamafia.order

import io.axonif.pizzamafia.NoSuchEntityException
import javax.ws.rs.*


@Path("orders")
class OrderController {

    companion object {

        val repository : OrderRepository = OrderRepository()
    }

    @GET
    @Path("all")
    fun listOrders() : ListOrdersResponse {
        return ListOrdersResponse(repository.all().asSequence().map {
            order -> OrderInfo(order.id, order.eventId, order.userId, order.numberOfSlices, order.createDate, order.lastModifyDate)
        }.toList())
    }

    @PUT
    fun placeOrder(request : PlaceOrderRequest) : PlaceOrderResponse {
        val order = Order(request.eventId, request.userId, request.numberOfSlices)
        repository.add(order)
        return PlaceOrderResponse(order.id)
    }

    @DELETE
    @Path("{id}")
    fun cancelOrder(@PathParam("id") id : String) {
        val order = repository.get(id)
        repository.remove(order)
    }

    @GET
    @Path("{eventId}/{userId}")
    fun myOrder(
            @PathParam("eventId") eventId : String,
            @PathParam("userId") userId : String
    ) : MyOrderResponse {
        val order = repository.findByEventForUser(eventId, userId)
                ?: throw NoSuchEntityException(Order::class.java, eventId, userId)
        return MyOrderResponse(OrderInfo(order.id, order.eventId, order.userId, order.numberOfSlices, order.createDate, order.lastModifyDate))
    }
}