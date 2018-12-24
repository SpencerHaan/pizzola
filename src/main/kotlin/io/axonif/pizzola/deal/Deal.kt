package io.axonif.pizzola.deal

import java.math.BigDecimal


class Deal(
        val id : String,
        val name : String,
        val numberOfPizzas : Long,
        val slicesPerPizza : Long,
        val price : BigDecimal
) {

    val slices : Long
        get() = numberOfPizzas * slicesPerPizza
}