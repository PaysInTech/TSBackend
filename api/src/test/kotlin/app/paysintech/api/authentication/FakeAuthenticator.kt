package app.paysintech.api.authentication

import app.paysintech.core.user.model.User
import app.paysintech.core.user.model.UserRole
import app.paysintech.exception.CommonErrors
import app.paysintech.exception.authenticationError
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
