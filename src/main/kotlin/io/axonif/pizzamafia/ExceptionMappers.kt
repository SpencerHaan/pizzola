package io.axonif.pizzamafia

import java.lang.Exception
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper


class DefaultExceptionMapper : ExceptionMapper<Exception> {

    override fun toResponse(exception: Exception?): Response {
        exception?.printStackTrace(System.err)
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(exception?.message)
                .build()
    }
}

class NoSuchEntityMapper : ExceptionMapper<NoSuchEntityException> {

    override fun toResponse(exception: NoSuchEntityException?): Response {
        exception?.printStackTrace(System.err)
        return Response.status(Response.Status.NOT_FOUND)
                .entity(exception?.message)
                .build()
    }
}