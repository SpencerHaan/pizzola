package com.spencerhaan.pizzola.deal

import com.spencerhaan.pizzola.order.OrderRepository
import java.math.BigDecimal
import java.math.RoundingMode


class DealService(
        private val repository: DealRepository,
        private val orderRepository: OrderRepository
) {

    fun calculateBestDeal(eventId : String) : EvaluatedDeal? {
        val orders = orderRepository.findByEvent(eventId)
        val totalOrderedSlices = orders
                .asSequence()
                .map { order -> order.numberOfSlices }
                .sum()
                .toBigDecimal()
        val totalCharged = totalOrderedSlices.multiply(PRICE_PER_SLICE)

        return repository.all()
                .asSequence()
                .map { deal -> evaluateDeal(deal, totalOrderedSlices, totalCharged) }
                .filterNotNull()
                .sortedByDescending { evaluatedDeal -> evaluatedDeal.surplus }
                .firstOrNull()
    }

    private fun evaluateDeal(deal : Deal, totalOrderedSlices : BigDecimal, totalCharged : BigDecimal) : EvaluatedDeal? {
        val numberOfDeals = totalOrderedSlices.divide(BigDecimal(deal.slices), RoundingMode.CEILING)
        val cost = numberOfDeals.multiply(deal.price)
        val tax = cost.multiply(HST)

        val totalCost = cost.add(tax)
        val extraSlices = BigDecimal(deal.slices).multiply(numberOfDeals).subtract(totalOrderedSlices).toInt()
        val costPerSlice = totalCost.divide(totalOrderedSlices, RoundingMode.HALF_UP)
        val surplus = totalCharged.subtract(totalCost)

        return if (surplus > BigDecimal.ZERO)
            EvaluatedDeal(
                    deal,
                    totalCost.setScale(2, RoundingMode.HALF_UP),
                    extraSlices,
                    costPerSlice.setScale(2, RoundingMode.HALF_UP),
                    surplus.setScale(2, RoundingMode.HALF_UP)
            )
        else null
    }

    data class EvaluatedDeal(
            val deal : Deal,
            val totalCost : BigDecimal,
            val extraSlices : Int,
            val costPerSlice : BigDecimal,
            val surplus : BigDecimal
    )

    companion object {

        private val PRICE_PER_SLICE : BigDecimal = BigDecimal(1.25)
        private val HST : BigDecimal = BigDecimal(0.13)
    }
}