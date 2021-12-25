@file:Suppress("EXPERIMENTAL_API_USAGE")

package app.paysintech.api

import app.paysintech.api.health.HealthApi
import app.paysintech.api.info.InfoApi
import app.paysintech.api.salary.SalaryApi
import app.paysintech.api.user.UserApi
import io.ktor.locations.Location
import io.ktor.routing.Routing

@Location("/api/v1")
object PaysInTechApiRoute {
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
