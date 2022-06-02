@file:Suppress("EXPERIMENTAL_API_USAGE")

package app.paysintech.api.info

import app.paysintech.api.PaysInTechApiRoute
import app.paysintech.plugins.controllers
import app.paysintech.utils.returnResponse
import io.ktor.locations.get
import io.ktor.routing.Route

fun Route.InfoApi() {

    val controller by lazy { controllers.infoController() }

    get<PaysInTechApiRoute.Info.JobProfiles> {
        returnResponse(controller.getAllJobProfiles())
    }

    get<PaysInTechApiRoute.Info.Technologies> {
        returnResponse(controller.getAllTechnologies())
    }

    get<PaysInTechApiRoute.Info.ProgrammingLanguages> {
        returnResponse(controller.getAllProgrammingLanguages())
    }

    get<PaysInTechApiRoute.Info.ContributionLevels> {
        returnResponse(controller.getAllContributionLevels())
    }
}
