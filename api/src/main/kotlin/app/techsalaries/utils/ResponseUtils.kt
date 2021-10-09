package app.techsalaries.utils

import app.techsalaries.api.response.BaseResponse
import app.techsalaries.api.response.Response
import app.techsalaries.api.response.Success
import app.techsalaries.api.response.Unsuccessful
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineContext

suspend fun <T : BaseResponse> PipelineContext<Unit, ApplicationCall>.returnResponse(response: Response<T>) {
    val (statusCode, data) = when (response) {
        is Success<*> -> response.statusCode to response.data
        is Unsuccessful -> response.statusCode to response.message
    }
    call.respond(statusCode, data)
}