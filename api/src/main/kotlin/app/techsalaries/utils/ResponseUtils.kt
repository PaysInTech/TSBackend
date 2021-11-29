package app.techsalaries.utils

import app.techsalaries.api.response.BaseResponse
import app.techsalaries.api.response.HttpResponse
import app.techsalaries.api.response.Success
import app.techsalaries.api.response.Unsuccessful
import app.techsalaries.core.exception.ResourceNotFoundException
import app.techsalaries.exception.BadRequestException
import app.techsalaries.exception.NotFoundException
import app.techsalaries.exception.ServerError
import app.techsalaries.exception.UnsatisfiedRequestException
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineContext

suspend fun <T : BaseResponse> PipelineContext<Unit, ApplicationCall>.returnResponse(response: HttpResponse<T>) {
    val (statusCode, data) = when (response) {
        is Success<*> -> response.statusCode to response.data
        is Unsuccessful -> response.statusCode to response.message
    }
    call.respond(statusCode, data)
}

/**
 * Handles response globally and if any Exception occurs, it maps it to the API exceptions
 */
suspend fun <T : BaseResponse> handleResponse(response: suspend () -> HttpResponse<T>): HttpResponse<T> {
    return try {
        response()
    } catch (e: Exception) {
        e.printStackTrace()
        throw when (e) {
            is UnsatisfiedRequestException -> e
            is IllegalStateException -> BadRequestException(e.message.toString())
            is ResourceNotFoundException -> NotFoundException(e.message.toString())
            else -> ServerError("Something went wrong. Failed to serve the request")
        }
    }
}
