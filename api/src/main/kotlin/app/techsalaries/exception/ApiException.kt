package app.techsalaries.exception

open class BadRequestException(override val message: String) : RuntimeException()

open class NotFoundException(override val message: String) : RuntimeException()

open class AuthenticationException(override val message: String) : RuntimeException()
open class AuthorizationException(override val message: String) : RuntimeException()

open class ServerError(override val message: String) : RuntimeException()