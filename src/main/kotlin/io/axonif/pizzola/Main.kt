package io.axonif.pizzola

import io.undertow.Handlers
import io.undertow.Undertow
import io.undertow.servlet.Servlets
import io.undertow.servlet.Servlets.servlet
import org.glassfish.jersey.servlet.ServletContainer


fun main(args: Array<String>) {
    val servletBuilder = Servlets.deployment()
            .setClassLoader(PizzaApplication::class.java.classLoader)
            .setContextPath("/pizza")
            .setDeploymentName("pizza-mafia")
            .addServlet(servlet("jerseyServlet", ServletContainer::class.java)
                    .setLoadOnStartup(1)
                    .addInitParam("javax.ws.rs.Application", PizzaApplication::class.java.name)
                    .addMapping("/api/*"))

    val manager = Servlets.defaultContainer().addDeployment(servletBuilder)
    manager.deploy()

    val path = Handlers.path(Handlers.redirect("/pizza"))
            .addPrefixPath("/pizza", manager.start())

    val server = Undertow.builder()
            .addHttpListener(8080, "localhost")
            .setHandler(path)
            .build()
    server.start()
}

