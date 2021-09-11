package app.techsalaries

import app.techsalaries.plugins.configureHTTP
import app.techsalaries.plugins.configureRouting
import app.techsalaries.plugins.configureSecurity
import app.techsalaries.plugins.configureSerialization
import io.ktor.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureRouting()
    configureSerialization()
    configureSecurity()
    configureHTTP()
}
