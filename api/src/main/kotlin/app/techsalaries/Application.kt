package app.techsalaries

import app.techsalaries.config.DatabaseConfig
import app.techsalaries.db.DatabaseFactory
import app.techsalaries.plugins.appComponent
import app.techsalaries.plugins.configureDI
import app.techsalaries.plugins.configureHTTP
import app.techsalaries.plugins.configureRouting
import app.techsalaries.plugins.configureSecurity
import app.techsalaries.plugins.configureSerialization
import app.techsalaries.plugins.configureStatusPages
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.resources.href
import io.ktor.server.response.respondRedirect

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@JvmOverloads
fun Application.init(testing: Boolean = true) {
    configureDI()
    configureStatusPages()
    configureSerialization()
    configureSecurity()
    configureRouting()
    configureHTTP()
    setupDatabase()
}

fun Application.setupDatabase() {
    val databaseConfig: DatabaseConfig = appComponent.databaseConfig()
    with(databaseConfig) {
        DatabaseFactory.init(databaseUrl = url, databaseUser = username, databasePassword = password)
    }
}

suspend inline fun <reified T : Any> ApplicationCall.redirect(resource: T) {
    val path = application.href(resource)
    respondRedirect(path)
}
