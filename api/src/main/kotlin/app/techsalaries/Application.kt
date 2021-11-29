package app.techsalaries

import app.techsalaries.firebase.FirebaseInitializer
import app.techsalaries.plugins.configureDI
import app.techsalaries.plugins.configureHTTP
import app.techsalaries.plugins.configureRouting
import app.techsalaries.plugins.configureSecurity
import app.techsalaries.plugins.configureSerialization
import app.techsalaries.plugins.configureStatusPages
import io.ktor.application.Application

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@JvmOverloads
fun Application.main(testing: Boolean = true) {
    FirebaseInitializer()
    configureDI()
    configureSecurity()
    configureRouting()
    configureStatusPages()
    configureSerialization()
    configureHTTP()
}
