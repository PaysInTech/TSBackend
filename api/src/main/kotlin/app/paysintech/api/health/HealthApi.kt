package app.paysintech.api.health

import app.paysintech.api.PaysInTechApiRoute
import app.paysintech.plugins.controllers
import app.paysintech.utils.returnResponse
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.get
import io.ktor.routing.Route

@OptIn(KtorExperimentalLocationsAPI::class)
fun Route.HealthApi() {

    val controller by lazy { controllers.healthController() }

    get<PaysInTechApiRoute.HealthCheck> {
        returnResponse(controller.checkHealth())
    }
}
