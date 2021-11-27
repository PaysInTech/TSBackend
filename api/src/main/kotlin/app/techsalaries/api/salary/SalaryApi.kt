@file:OptIn(KtorExperimentalLocationsAPI::class)

package app.techsalaries.api.salary

import app.techsalaries.api.TechSalariesApiRoute
import app.techsalaries.plugins.controllers
import app.techsalaries.utils.returnResponse
import io.ktor.application.call
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route

fun Route.SalaryApi() {
    val controller by lazy { controllers.salaryController() }

    post<TechSalariesApiRoute.Salaries> {
        returnResponse(controller.submitSalary(call.receive(), ipAddress = call.request.local.remoteHost))
    }
}
