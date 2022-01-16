@file:Suppress("EXPERIMENTAL_API_USAGE")

package app.techsalaries.api

import app.techsalaries.api.health.HealthApi
import app.techsalaries.api.sample.SampleApi
import io.ktor.auth.*
import io.ktor.locations.Location
import io.ktor.routing.Routing

@Location("/api")
class TechSalariesApiRoute {
    @Location("/health")
    class HealthCheck

    @Location("/samples")
    class Samples
}

fun Routing.MainRoute() {
    HealthApi()
    authenticate {
        SampleApi()
    }
}
