package app.techsalaries.plugins

import app.techsalaries.exception.UnsatisfiedRequestException
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.StatusPages
import io.ktor.response.respond

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<UnsatisfiedRequestException> {
            call.respond(it.statusCode, it.response.message)
        }
    }
}
