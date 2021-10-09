package app.techsalaries.api.response

import io.ktor.http.HttpStatusCode
import kotlinx.serialization.Serializable

sealed interface Response<T : BaseResponse> {
    val statusCode: HttpStatusCode
}

interface BaseResponse {
    val isSuccess: Boolean
}

class Success<T : BaseResponse>(
    val data: @Serializable T,
    override val statusCode: HttpStatusCode = HttpStatusCode.OK
) : Response<T> {
    companion object {
        fun <T : BaseResponse> ok(data: @Serializable T) = Success(data, HttpStatusCode.OK)
        fun <T : BaseResponse> created(data: @Serializable T) = Success(data, HttpStatusCode.Created)
        fun <T : BaseResponse> accepted(data: @Serializable T) = Success(data, HttpStatusCode.Accepted)
    }
}

class Unsuccessful(e: Throwable, override val statusCode: HttpStatusCode) : Response<UnsuccessfulResponse> {
    val message = UnsuccessfulResponse(message = e.message.toString())
}

@Suppress("unused")
@Serializable
class UnsuccessfulResponse(override val isSuccess: Boolean = false, val message: String) : BaseResponse
