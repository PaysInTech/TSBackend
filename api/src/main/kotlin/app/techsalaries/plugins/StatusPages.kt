package app.techsalaries.plugins

import app.techsalaries.exception.AuthenticationException
import app.techsalaries.exception.AuthorizationException
import app.techsalaries.exception.BadRequestException
import app.techsalaries.exception.NotFoundException
import app.techsalaries.exception.ServerError
import app.techsalaries.exception.UnsatisfiedRequestException
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<UnsatisfiedRequestException> {
            call.respond(it.statusCode, it.response.message)
        }
    }
}
