package io.axonif.pizzamafia

import io.axonif.pizzamafia.wallet.WalletService
import java.math.BigDecimal
import javax.ws.rs.*
import javax.ws.rs.core.Response

@Path("wallet")
class WalletController {

    val wallet = WalletService.getInstance()

    @GET
    @Path("overview")
    fun overview() : Response {
        return Response.status(Response.Status.NOT_IMPLEMENTED).build()
    }

    @GET
    fun balance(@QueryParam("user") userId: String) : Float {
        return wallet.balance(userId).toFloat()
    }

    @POST
    @Path("deposit")
    fun deposit(@FormParam("user") userId: String,
                @FormParam("amount") amount : BigDecimal,
                @FormParam("note") note : String) : Response {
        wallet.deposit(userId, amount, note)
        return Response.accepted().build()
    }

    @POST
    @Path("withdraw")
    fun withdraw(@FormParam("user") userId: String,
                 @FormParam("amount") amount : BigDecimal,
                 @FormParam("note") note : String) : Response {
        wallet.withdraw(userId, amount, note)
        return Response.accepted().build()
    }
}