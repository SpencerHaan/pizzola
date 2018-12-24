package io.axonif.pizzola.deal


class DealRepository {

    private var deals = mutableMapOf<String, Deal>()

    fun all() : List<Deal> {
        return deals.values.toList()
    }

    fun add(deal : Deal) {
        if (deals.containsValue(deal)) {
            throw Exception("order, with id ${deal.id}, already exists")
        }
        deals[deal.id] = deal
    }
}