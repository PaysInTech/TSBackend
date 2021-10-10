package app.techsalaries.api.health

import app.techsalaries.api.TechSalariesApiRoute
import app.techsalaries.api.health.model.HealthResponse
import app.techsalaries.plugins.controllers
import app.techsalaries.utils.returnResponse
import io.ktor.application.call
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route

@OptIn(KtorExperimentalLocationsAPI::class)
fun Route.HealthApi() {

    val controller by lazy { controllers.healthController() }

    get<TechSalariesApiRoute.HealthCheck> {
        returnResponse(controller.checkHealth())
    }
}
