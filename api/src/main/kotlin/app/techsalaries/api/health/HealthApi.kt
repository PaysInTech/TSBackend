package app.techsalaries.api.health

import app.techsalaries.api.API_PREFIX
import app.techsalaries.api.API_VERSION
import app.techsalaries.api.health.model.HealthResponse
import io.ktor.resources.Resource
import io.ktor.server.application.call
import io.ktor.server.resources.get
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import kotlinx.serialization.Serializable

const val HEALTH_API = "$API_PREFIX/$API_VERSION/health"

@Serializable
@Resource(HEALTH_API)
class HealthApi

fun Route.healthApi() {
    get<HealthApi> {
        // TODO: Check resources here
        call.respond(HealthResponse.ok())
    }
}
