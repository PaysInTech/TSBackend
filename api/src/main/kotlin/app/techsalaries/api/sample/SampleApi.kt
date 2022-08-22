@file:Suppress("EXPERIMENTAL_API_USAGE")

package app.techsalaries.api.sample

import app.techsalaries.api.API_PREFIX
import app.techsalaries.plugins.controllers
import dagger.Lazy
import io.ktor.resources.Resource
import io.ktor.server.application.call
import io.ktor.server.resources.get
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import kotlinx.serialization.Serializable

const val SAMPLE_API = "$API_PREFIX/$API_PREFIX/sample"

@Serializable
@Resource(SAMPLE_API)
class SampleApi

fun Route.sampleApi(controller: Lazy<SampleController> = controllers.sampleController()) {

    get<SampleApi> {
        call.respond(controller.get().getAllSamples())
    }
}
