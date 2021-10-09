@file:Suppress("EXPERIMENTAL_API_USAGE")

package app.techsalaries.api

import app.techsalaries.api.health.HealthApi
import app.techsalaries.api.info.InfoApi
import io.ktor.locations.Location
import io.ktor.routing.Routing

@Location("/api/v1")
class TechSalariesApiRoute {
    @Location("/health")
    class HealthCheck

    @Location("/info")
    class Info {
        @Location("programming-languages")
        class ProgrammingLanguages()

        @Location("technologies")
        class Technologies()

        @Location("job-profiles")
        class JobProfiles()
    }
}

fun Routing.MainRoute() {
    HealthApi()
    InfoApi()
}
