package app.techsalaries.api.authentication

import app.techsalaries.core.user.model.User
import app.techsalaries.exception.CommonErrors
import app.techsalaries.exception.authenticationError
import javax.inject.Inject

class FakeAuthenticator @Inject constructor() : Authenticator {
    private var user: User? = null

    override suspend fun authenticate(token: String): User =
        if (token == VALID_TOKEN && user != null) {
            user!!
        } else {
            authenticationError(CommonErrors.INVALID_AUTHENTICATION_TOKEN)
        }

    fun provideFakeUser(user: User?) {
        this.user = user
    }
}

val VALID_TOKEN = "Bearer valid-token"
val INVALID_TOKEN = "Bearer invalid-token"
