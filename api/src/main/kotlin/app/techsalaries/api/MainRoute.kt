@file:Suppress("EXPERIMENTAL_API_USAGE")

package app.techsalaries.api

import app.techsalaries.api.health.HealthApi
import app.techsalaries.api.info.InfoApi
import app.techsalaries.api.salary.SalaryApi
import app.techsalaries.api.user.UserApi
import io.ktor.locations.Location
import io.ktor.routing.Routing

@Location("/api/v1")
object TechSalariesApiRoute {
    @Location("/health")
    object HealthCheck

    @Location("/info")
    object Info {
        @Location("programming-languages")
        object ProgrammingLanguages

        @Location("technologies")
        object Technologies

        @Location("job-profiles")
        object JobProfiles

        @Location("contribution-levels")
        object ContributionLevels
    }

    @Location("salaries")
    object Salaries

    @Location("/users")
    object Users {
        @Location("register")
        object Register

        @Location("login")
        object Login

        @Location("me")
        object Me

        @Location("/token/refresh")
        object RefreshToken
    }
}

fun Routing.MainRoute() {
    HealthApi()
    InfoApi()
    SalaryApi()
    UserApi()
}
