@file:Suppress("EXPERIMENTAL_API_USAGE")

package app.techsalaries.plugins

import app.techsalaries.api.MainRoute
import app.techsalaries.di.component.ControllerComponent
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.locations.Location
import io.ktor.locations.Locations
import io.ktor.routing.routing

fun Application.configureRouting() {
    install(Locations) {}
    routing {
        MainRoute()
    }
}