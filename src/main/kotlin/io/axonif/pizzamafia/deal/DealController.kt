package io.axonif.pizzamafia.deal

import io.axonif.pizzamafia.order.OrderController
import java.math.BigDecimal
import java.math.RoundingMode
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam

@Path("deals")
class DealController {

    companion object {

        private val repository : DealRepository = DealRepository()

        init {
            repository.add(Deal("1", "Godzilla Deal", 2, 12, BigDecimal(21.99).setScale(2, RoundingMode.HALF_UP)))
            repository.add(Deal("2", "Triple Deal", 3, 12, BigDecimal(33.99).setScale(2, RoundingMode.HALF_UP)))
            repository.add(Deal("3", "Crazy Deal - XLarge", 1, 12, BigDecimal(16.49).setScale(2, RoundingMode.HALF_UP)))
        }

        val service : DealService = DealService(repository, OrderController.repository)
    }

    @GET
    @Path("{eventId}")
    fun bestDeal(@PathParam("eventId") eventId : String) : DealService.EvaluatedDeal? {
        return service.calculateBestDeal(eventId) ?: throw Exception("no deals could be selected")
    }
}