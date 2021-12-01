package app.techsalaries.api.authentication

import app.techsalaries.core.user.model.User
import app.techsalaries.core.user.model.UserRole
import app.techsalaries.exception.CommonErrors
import app.techsalaries.exception.authenticationError
import javax.inject.Inject

class FakeAuthenticator @Inject constructor() : Authenticator {
    override suspend fun authenticate(token: String): User =
        if (token == "valid-token") {
            AuthenticatedUser
        } else {
            authenticationError(CommonErrors.INVALID_AUTHENTICATION_TOKEN)
        }
}

val AuthenticatedUser = User(
    id = "user1234",
    username = "user1234",
    email = "example@email.com",
    totalCoins = 1000,
    roles = listOf(UserRole.MEMBER)
)

val VALID_TOKEN = "Bearer valid-token"
val INVALID_TOKEN = "Bearer invalid-token"
