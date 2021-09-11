@file:Suppress("EXPERIMENTAL_API_USAGE")

package app.techsalaries.api.sample

import app.techsalaries.api.TechSalariesApiRoute
import app.techsalaries.plugins.controllers
import dagger.Lazy
import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route

fun Route.SampleApi(controller: Lazy<SampleController> = controllers.sampleController()) {

    get<TechSalariesApiRoute.Samples> {
        call.respond(controller.get().getAllSamples())
    }
}