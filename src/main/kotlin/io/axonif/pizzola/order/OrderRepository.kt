package io.axonif.pizzola.order

import io.axonif.pizzola.NoSuchEntityException


class OrderRepository {

    private val orders = mutableMapOf<String, Order>()

    fun all() : List<Order> {
        return orders.values.toList()
    }

    fun get(id : String) : Order {
        return orders[id] ?: throw NoSuchEntityException(id, Order::class.java)
    }

    fun find(id : String) : Order? {
        return orders[id]
    }

    fun findByEvent(eventId : String) : List<Order> {
        return orders.values.filter { order -> order.eventId == eventId }.toList()
    }

    fun findByEventForUser(eventId : String, userId : String) : Order? {
        return orders.values.find { order -> order.eventId == eventId && order.userId == userId }
    }

    fun add(order : Order) {
        if (orders.containsValue(order)) {
            throw Exception("order, with id ${order.id}, already exists")
        }
        orders[order.id] = order
    }

    fun remove(order : Order) {
        if (!orders.containsValue(order)) {
            throw Exception("order, with id ${order.id}, does not exist")
        }
        orders.remove(order.id)
    }
}
