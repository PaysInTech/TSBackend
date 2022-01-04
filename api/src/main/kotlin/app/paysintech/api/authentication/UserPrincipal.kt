package app.paysintech.api.authentication

import app.paysintech.core.user.model.User
import io.ktor.auth.Principal

data class UserPrincipal(val user: User) : Principal
