@file:OptIn(KtorExperimentalLocationsAPI::class)

package app.techsalaries.api.salary

import app.techsalaries.api.TechSalariesApiRoute
import app.techsalaries.plugins.AUTHENTICATION_NO_STRICT
import app.techsalaries.plugins.controllers
import app.techsalaries.utils.returnResponse
import app.techsalaries.utils.userPrincipalOrNull
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.authentication
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.routing.Route

fun Route.SalaryApi() {
    val controller by lazy { controllers.salaryController() }

    authenticate(AUTHENTICATION_NO_STRICT) {
        post<TechSalariesApiRoute.Salaries> {
            val currency = call.request.headers["Currency"] ?: "INR"
            returnResponse(
                controller.submitSalary(
                    addSalaryDetailRequest = call.receive(),
                    currencyCode = currency,
                    user = call.authentication.userPrincipalOrNull?.user,
                    ipAddress = call.request.local.remoteHost
                )
            )
        }
    }
}
