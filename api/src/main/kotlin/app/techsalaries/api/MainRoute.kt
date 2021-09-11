@file:Suppress("EXPERIMENTAL_API_USAGE")

package app.techsalaries.api

import app.techsalaries.api.health.HealthApi
import io.ktor.locations.Location
import io.ktor.routing.Routing

@Location("/api")
class TechSalariesApiRoute {
    @Location("/health")
    class HealthCheck {
        @Location("")
        class CheckStatus
    }
}

fun Routing.MainRoute() {
    HealthApi()
}
