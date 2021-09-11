package app.techsalaries

import app.techsalaries.di.component.ControllerComponent
import app.techsalaries.di.component.DaggerAppComponent
import app.techsalaries.plugins.configureDI
import app.techsalaries.plugins.configureHTTP
import app.techsalaries.plugins.configureRouting
import app.techsalaries.plugins.configureSecurity
import app.techsalaries.plugins.configureSerialization
import app.techsalaries.plugins.configureStatusPages
import io.ktor.application.Application
import io.ktor.util.AttributeKey

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@JvmOverloads
fun Application.main(testing: Boolean = true) {
    configureDI()
    configureRouting()
    configureStatusPages()
    configureSerialization()
    configureSecurity()
    configureHTTP()
}


