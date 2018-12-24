package io.axonif.pizzamafia.transaction

import io.axonif.pizzamafia.DEFAULTS
import io.axonif.pizzamafia.order.Order
import java.math.BigDecimal
import java.time.Instant
import java.util.*

class Transaction private constructor(val userId: String, val amount: BigDecimal, note: String) {
    val id: String = UUID.randomUUID().toString()

    val date: Instant = Instant.now()

    companion object {
        fun createDeposit(userId: String, amount: BigDecimal, note:String): Transaction {
            return Transaction(userId,amount,note)
        }

        fun createWithdrawl(userId: String, amount: BigDecimal, note: String): Transaction {
            return Transaction(userId, amount.negate(), note)
        }

        fun fromOrder(order: Order): Transaction {
            val price: Double = DEFAULTS.PRICE_PER_SLICE

            return Transaction(order.userId, BigDecimal.valueOf(order.numberOfSlices * price), "ORDER")
        }
    }
}

