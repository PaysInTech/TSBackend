@file:Suppress("EXPERIMENTAL_API_USAGE")

package app.techsalaries.api.info

import app.techsalaries.api.TechSalariesApiRoute
import app.techsalaries.plugins.controllers
import app.techsalaries.utils.returnResponse
import io.ktor.locations.get
import io.ktor.routing.Route

fun Route.InfoApi() {

    val controller = controllers.infoController()

    get<TechSalariesApiRoute.Info.JobProfiles> {
        returnResponse(controller.get().getAllJobProfiles())
    }

    get<TechSalariesApiRoute.Info.Technologies> {
        returnResponse(controller.get().getAllTechnologies())
    }

    get<TechSalariesApiRoute.Info.ProgrammingLanguages> {
        returnResponse(controller.get().getAllProgrammingLanguages())
    }
}
