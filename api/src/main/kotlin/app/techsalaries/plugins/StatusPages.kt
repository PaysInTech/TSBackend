package app.techsalaries.plugins

import app.techsalaries.api.response.UnsuccessfulResponse
import app.techsalaries.exception.UnsatisfiedRequestException
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import kotlinx.serialization.SerializationException

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<SerializationException> {
            call.respond(HttpStatusCode.NotAcceptable, UnsuccessfulResponse(it.message.toString()))
        }
        exception<UnsatisfiedRequestException> {
            call.respond(it.statusCode, it.response.message)
        }
    }
}
