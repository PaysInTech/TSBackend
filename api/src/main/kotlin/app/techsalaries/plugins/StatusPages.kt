package app.techsalaries.plugins

import app.techsalaries.exception.AuthenticationException
import app.techsalaries.exception.AuthorizationException
import app.techsalaries.exception.BadRequestException
import app.techsalaries.exception.NotFoundException
import app.techsalaries.exception.ServerError
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<BadRequestException> { call: ApplicationCall, cause: Throwable ->
            call.respond(HttpStatusCode.BadRequest)
        }
        exception<NotFoundException> { call: ApplicationCall, cause: Throwable ->
            call.respond(HttpStatusCode.NotFound)
        }
        exception<AuthenticationException> { call: ApplicationCall, cause: Throwable ->
            call.respond(HttpStatusCode.Unauthorized)
        }
        exception<AuthorizationException> { call: ApplicationCall, cause: Throwable ->
            call.respond(HttpStatusCode.Forbidden)
        }
        exception<ServerError> { call: ApplicationCall, cause: Throwable ->
            call.respond(HttpStatusCode.InternalServerError)
        }
    }
}
