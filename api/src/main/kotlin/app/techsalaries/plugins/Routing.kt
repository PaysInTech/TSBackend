@file:Suppress("EXPERIMENTAL_API_USAGE")

package app.techsalaries.plugins

import app.techsalaries.api.health.healthApi
import app.techsalaries.api.sample.sampleApi
import app.techsalaries.api.user.signup
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.application.log
import io.ktor.server.resources.Resources
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    install(Resources)
    val jwtService = appComponent.jwtService()
    routing {
        trace { application.log.trace(it.buildText()) }
        healthApi()
        sampleApi()
        signup(jwtService = jwtService) { s: String -> hash(s) }
    }
}
