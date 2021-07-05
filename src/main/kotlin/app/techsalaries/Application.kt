package app.techsalaries

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import app.techsalaries.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSerialization()
        configureSecurity()
        configureHTTP()
    }.start(wait = true)
}
