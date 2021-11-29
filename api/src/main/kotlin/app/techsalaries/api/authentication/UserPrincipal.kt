package app.techsalaries.api.authentication

import app.techsalaries.core.user.model.User
import io.ktor.auth.Principal

data class UserPrincipal(val user: User): Principal