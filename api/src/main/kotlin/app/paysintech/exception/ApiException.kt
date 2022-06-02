package app.paysintech.exception

import app.paysintech.api.response.Unsuccessful
import io.ktor.http.HttpStatusCode

sealed class UnsatisfiedRequestException(
    override val message: String,
    val statusCode: HttpStatusCode
) : RuntimeException() {
    val response get() = Unsuccessful(this, statusCode)
}

open class BadRequestException(override val message: String) : UnsatisfiedRequestException(message, HttpStatusCode.BadRequest)

open class NotFoundException(override val message: String) : UnsatisfiedRequestException(message, HttpStatusCode.NotFound)

open class AuthenticationException(override val message: String) : UnsatisfiedRequestException(message, HttpStatusCode.Unauthorized)
open class AuthorizationException(override val message: String) : UnsatisfiedRequestException(message, HttpStatusCode.Forbidden)

open class ServerError(override val message: String) : UnsatisfiedRequestException(message, HttpStatusCode.InternalServerError)

fun badRequest(message: String): Nothing = throw BadRequestException(message)
fun authenticationError(message: String = "Authentication Error"): Nothing = throw AuthenticationException(message)
