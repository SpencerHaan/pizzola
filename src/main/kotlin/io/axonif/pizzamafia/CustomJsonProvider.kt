package io.axonif.pizzamafia

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.ext.Provider


@Provider
@Produces(MediaType.APPLICATION_JSON)
class CustomJsonProvider : JacksonJaxbJsonProvider() {

    companion object {

        val OBJECT_MAPPER : ObjectMapper = ObjectMapper()

        init {
            OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        }
    }

    init {
        setMapper(OBJECT_MAPPER)
    }
}