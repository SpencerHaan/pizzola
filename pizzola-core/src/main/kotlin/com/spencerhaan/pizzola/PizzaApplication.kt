package com.spencerhaan.pizzola

import com.spencerhaan.pizzola.deal.DealController
import com.spencerhaan.pizzola.order.OrderController
import javax.ws.rs.core.Application


class PizzaApplication : Application() {
    override fun getClasses(): MutableSet<Class<*>> {
        return mutableSetOf(
                DefaultExceptionMapper::class.java,
                NoSuchEntityMapper::class.java,
                MarshallingFeature::class.java,
                DealController::class.java,
                EventController::class.java,
                OrderController::class.java,
                WalletController::class.java
        )
    }
}