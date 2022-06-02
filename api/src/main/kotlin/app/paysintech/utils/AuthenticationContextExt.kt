package app.paysintech.utils

import app.paysintech.api.authentication.UserPrincipal
import io.ktor.auth.AuthenticationContext

val AuthenticationContext.userPrincipal get() = principal as UserPrincipal

val AuthenticationContext.userPrincipalOrNull get() = principal as? UserPrincipal?
