package app.techsalaries.api.response

import io.ktor.http.HttpStatusCode
import kotlinx.serialization.Serializable

/**
 * Generic HTTP response
 */
sealed interface HttpResponse<T : BaseResponse> {
    val statusCode: HttpStatusCode
}

/**
 * HTTP response model for success codes (2XX)
 */
class Success<T : BaseResponse>(
    val data: @Serializable T,
    override val statusCode: HttpStatusCode = HttpStatusCode.OK
) : HttpResponse<T> {
    companion object {
        fun <T : BaseResponse> ok(data: @Serializable T) = Success(data, HttpStatusCode.OK)
        fun <T : BaseResponse> created(data: @Serializable T) = Success(data, HttpStatusCode.Created)
        fun <T : BaseResponse> accepted(data: @Serializable T) = Success(data, HttpStatusCode.Accepted)
    }
}

/**
 * HTTP response model for error/failure codes (4XX, 5XX)
 */
class Unsuccessful(e: Throwable, override val statusCode: HttpStatusCode) : HttpResponse<UnsuccessfulResponse> {

    constructor(message: String?, statusCode: HttpStatusCode) : this(Exception(message), statusCode)

    val message = UnsuccessfulResponse(message = e.message.toString())
}

/**
 * Common Unsuccessful/Error response
 */
@Suppress("unused")
@Serializable
class UnsuccessfulResponse(val message: String) : BaseResponse(isSuccess = false)
