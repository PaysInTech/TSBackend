package app.paysintech

import app.paysintech.plugins.configureDI
import app.paysintech.plugins.configureHTTP
import app.paysintech.plugins.configureRouting
import app.paysintech.plugins.configureSecurity
import app.paysintech.plugins.configureSerialization
import app.paysintech.plugins.configureStatusPages
import io.ktor.application.Application

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@JvmOverloads
fun Application.main(testing: Boolean = true) {
    configureDI()
    configureSecurity()
    configureRouting()
    configureStatusPages()
    configureSerialization()
    configureHTTP()
}
